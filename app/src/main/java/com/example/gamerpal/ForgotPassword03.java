package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword03 extends AppCompatActivity {

    private EditText etPassword, etConfirmPassword;
    private LinearLayout btnResetPassword;
    private FirebaseAuth mAuth;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_03);

        // Ánh xạ view
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnResetPassword = findViewById(R.id.roz7lejsdhme); // ID của nút LinearLayout

        mAuth = FirebaseAuth.getInstance();

        // Lấy email từ intent
        Intent intent = getIntent();
        userEmail = intent.getStringExtra("user");

        btnResetPassword.setOnClickListener(v -> {
            String newPassword = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidPassword(newPassword)) {
                Toast.makeText(this, "Password must be at least 8 characters,\ninclude 1 number and 1 special symbol", Toast.LENGTH_LONG).show();
                return;
            }

            // Gửi link reset mật khẩu đến email (do chưa đăng nhập)
            mAuth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Reset link sent to " + userEmail, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ForgotPassword03.this, Login.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private boolean isValidPassword(String password) {
        // Tối thiểu 8 ký tự, có ít nhất 1 số và 1 ký tự đặc biệt
        return password.matches("^(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$");
    }
}
