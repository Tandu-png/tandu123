package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SignUpSocial6 extends AppCompatActivity {

    private TextView backButton;
    private EditText searchEditText;
    private ImageView searchIcon;
    private GridLayout gameGrid;
    private LinearLayout btnContinue;
    private TextView skipStep;

    private ArrayList<LinearLayout> gameItems = new ArrayList<>();
    private ArrayList<String> selectedGames = new ArrayList<>();
    private final int MAX_SELECTED = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_social6);

        // Ánh xạ view
        backButton = findViewById(R.id.back_button);
        searchEditText = findViewById(R.id.etEmail);
        searchIcon = findViewById(R.id.r274wvo4qdfy);
        gameGrid = findViewById(R.id.game_grid);
        btnContinue = findViewById(R.id.btnContinue);
        skipStep = findViewById(R.id.skip_step);

        btnContinue.setEnabled(false);

        int childCount = gameGrid.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = gameGrid.getChildAt(i);
            if (child instanceof LinearLayout) {
                LinearLayout gameItem = (LinearLayout) child;
                gameItems.add(gameItem);

                final int index = i;

                ImageView imageView = findGameImageView(gameItem);
                if (imageView != null) {
                    imageView.setOnClickListener(v -> toggleGameSelection(gameItem, index));
                }
            }
        }

        if (backButton != null) {
            backButton.setOnClickListener(v -> onBackPressed());
        }

        searchIcon.setOnClickListener(v -> {
            String keyword = searchEditText.getText().toString().trim().toLowerCase();
            filterGames(keyword);
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                filterGames(s.toString().toLowerCase());
            }
        });

        // Continue → Lưu game vào Firebase → Sang SignUp07
        btnContinue.setOnClickListener(v -> {
            if (selectedGames.size() == MAX_SELECTED) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String uid = currentUser.getUid();

                    DatabaseReference userRef = FirebaseDatabase.getInstance("https://gamepal-3912b-default-rtdb.asia-southeast1.firebasedatabase.app/")
                            .getReference("Users").child(uid).child("favoriteGames");

                    userRef.setValue(selectedGames)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SignUpSocial6.this, SignUpSocial07.class);
                                    intent.putStringArrayListExtra("selectedGames", selectedGames);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(this, "Failed to save games to Firebase", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignUpSocial6.this, "Please select exactly 3 games", Toast.LENGTH_SHORT).show();
            }
        });

        // Skip → Lưu danh sách rỗng → Sang SignUp07
        if (skipStep != null) {
            skipStep.setOnClickListener(v -> {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String uid = currentUser.getUid();
                    DatabaseReference userRef = FirebaseDatabase.getInstance("https://gamepal-3912b-default-rtdb.asia-southeast1.firebasedatabase.app/")
                            .getReference("Users").child(uid).child("favoriteGames");

                    userRef.setValue(new ArrayList<>()) // Lưu rỗng nếu bỏ qua
                            .addOnCompleteListener(task -> {
                                Intent intent = new Intent(SignUpSocial6.this, SignUpSocial07.class);
                                intent.putStringArrayListExtra("selectedGames", selectedGames);
                                startActivity(intent);
                                finish();
                            });
                } else {
                    Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void toggleGameSelection(LinearLayout gameItem, int index) {
        TextView gameNameTextView = null;
        for (int i = 0; i < gameItem.getChildCount(); i++) {
            View v = gameItem.getChildAt(i);
            if (v instanceof RelativeLayout) {
                RelativeLayout rl = (RelativeLayout) v;
                for (int j = 0; j < rl.getChildCount(); j++) {
                    View inner = rl.getChildAt(j);
                    if (inner instanceof TextView) {
                        gameNameTextView = (TextView) inner;
                        break;
                    }
                }
            }
        }

        if (gameNameTextView == null) return;

        String gameName = gameNameTextView.getText().toString();

        if (selectedGames.contains(gameName)) {
            selectedGames.remove(gameName);
            gameItem.setBackgroundResource(R.drawable.shadow_rounded);
            gameItem.setElevation(0f);
        } else {
            if (selectedGames.size() < MAX_SELECTED) {
                selectedGames.add(gameName);
                gameItem.setBackgroundResource(R.drawable.selected_game_yellow);
                gameItem.setElevation(12f);
            } else {
                Toast.makeText(this, "You can only select up to 3 games", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        updateContinueButton();
    }

    private void filterGames(String keyword) {
        for (LinearLayout gameItem : gameItems) {
            TextView gameNameTextView = null;
            for (int i = 0; i < gameItem.getChildCount(); i++) {
                View v = gameItem.getChildAt(i);
                if (v instanceof RelativeLayout) {
                    RelativeLayout rl = (RelativeLayout) v;
                    for (int j = 0; j < rl.getChildCount(); j++) {
                        View inner = rl.getChildAt(j);
                        if (inner instanceof TextView) {
                            gameNameTextView = (TextView) inner;
                            break;
                        }
                    }
                }
            }

            if (gameNameTextView != null) {
                String gameName = gameNameTextView.getText().toString().toLowerCase();
                if (gameName.contains(keyword)) {
                    gameItem.setVisibility(View.VISIBLE);
                } else {
                    gameItem.setVisibility(View.GONE);
                }

                String name = gameNameTextView.getText().toString();
                if (selectedGames.contains(name)) {
                    gameItem.setBackgroundResource(R.drawable.selected_game_yellow);
                    gameItem.setElevation(12f);
                } else {
                    gameItem.setBackgroundResource(R.drawable.shadow_rounded);
                    gameItem.setElevation(0f);
                }
            }
        }
    }

    private void updateContinueButton() {
        btnContinue.setEnabled(selectedGames.size() == MAX_SELECTED);
    }

    private ImageView findGameImageView(LinearLayout gameItem) {
        for (int i = 0; i < gameItem.getChildCount(); i++) {
            View child = gameItem.getChildAt(i);
            if (child instanceof RelativeLayout) {
                RelativeLayout rl = (RelativeLayout) child;
                for (int j = 0; j < rl.getChildCount(); j++) {
                    View v = rl.getChildAt(j);
                    if (v instanceof ImageView) {
                        return (ImageView) v;
                    }
                }
            }
        }
        return null;
    }
}
