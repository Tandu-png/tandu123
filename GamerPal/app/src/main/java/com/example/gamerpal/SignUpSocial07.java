package com.example.gamerpal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpSocial07 extends AppCompatActivity {

    private TextView txtInstruction;
    private ProgressBar progressBar;
    private static final int DELAY_MILLIS = 2500; // 2.5 giây

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_social07);

        // Ánh xạ các view
        txtInstruction = findViewById(R.id.txtInstruction);
        progressBar = findViewById(R.id.progressBar);

        // Lấy tên người dùng từ Intent (nếu có)
        String userName = getIntent().getStringExtra("username");
        if (userName == null || userName.isEmpty()) {
            userName = "JohnDoe"; // Mặc định
        }

        txtInstruction.setText("Great choices, " + userName + "!");

        // Sau 2.5 giây thì chuyển sang HomeActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SignUpSocial07.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Đóng màn hiện tại
        }, DELAY_MILLIS);
    }
}
