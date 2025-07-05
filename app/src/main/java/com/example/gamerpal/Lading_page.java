package com.example.gamerpal;
import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Lading_page extends AppCompatActivity {
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private LinearLayout btnSignUp, btnLogIn, btnSignUpWithG;
    private TextView tvContinueAsGuest, tvUTG;
    private static final int REQUEST_GOOGLE_MODAL = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        mAuth= FirebaseAuth.getInstance();
        String email = "null";
        String password = "null";

// Chỉ thực hiện tạo tài khoản nếu email và password hợp lệ
        if (!email.equals("null") && !password.equals("null") &&
                !email.isEmpty() && !password.isEmpty()) {

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("Main", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Toast.makeText(getApplicationContext(),
                                        "Tài khoản đã tạo: " + user.getEmail(),
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Log.w("Main", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Lading_page.this,
                                        "Authentication failed: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            Log.d("Main", "Email hoặc password không hợp lệ, bỏ qua việc tạo tài khoản test.");
        }


        // Khởi tạo Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://gamepal-3912b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference();
//        myRef.child("SinhVien").child("Luan").child("ten").setValue("Nguyen Van A");
//           myRef.child("SinhVien").child("Luan").child("tuoi").setValue(20);
//           myRef.child("SinhVien").child("Luan").child("email").setValue("vana@gmail.com");
        // Ánh xạ view
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUpWithG = findViewById(R.id.btnSignUpWithG);
        tvContinueAsGuest = findViewById(R.id.tvContinueAsGuest);
        tvUTG = findViewById(R.id.tvUTG);

        // Xử lý click Continue as Guest → chuyển sang Guest01
        tvContinueAsGuest.setOnClickListener(v -> {
            Intent intent = new Intent(Lading_page.this, Guest01.class);
            startActivity(intent);
        });

        // Tô màu gradient cho tiêu đề tvUTG
        Shader textShader = new LinearGradient(
                0, 0,
                tvUTG.getPaint().measureText(tvUTG.getText().toString()),
                tvUTG.getTextSize(),
                new int[]{
                        Color.parseColor("#F39A15"),
                        Color.parseColor("#EA4F0D")
                },
                new float[]{0, 1},
                Shader.TileMode.CLAMP
        );
        tvUTG.getPaint().setShader(textShader);

        // Chuyển sang màn Sign Up
        btnSignUp.setOnClickListener(v ->
                startActivity(new Intent(Lading_page.this, SignUp01.class)));

        // Chuyển sang màn Login
        btnLogIn.setOnClickListener(v ->
                startActivity(new Intent(Lading_page.this, Login.class)));

        // Mở modal Google → chuyển sang SignUp05
        btnSignUpWithG.setOnClickListener(v -> {
            Intent intentModal = new Intent(Lading_page.this, GoogleModal.class);
            startActivity(intentModal);

            // Delay rồi chuyển tiếp sang SignUp05
            new Handler().postDelayed(() -> {
                Intent intentSignup = new Intent(Lading_page.this, SignUp05.class);
                startActivity(intentSignup);
            }, 1000);
        });
    }
}
