package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword03 extends AppCompatActivity {

    EditText etPassword, etConfirmPassword;
    LinearLayout btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_03); // đổi đúng tên XML bạn đặt

        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnReset = findViewById(R.id.roz7lejsdhme); // "Reset Password" layout

        btnReset.setOnClickListener(view -> {
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isPasswordValid(password)) {
                Toast.makeText(this, "Password does not meet the requirements.", Toast.LENGTH_LONG).show();
                return;
            }

            // Thành công: trở về màn hình đăng nhập hoặc home
            Toast.makeText(this, "Password reset successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ForgotPassword03.this, Login.class));
            finish();
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 &&
                password.matches(".*[!@#$%*].*") &&
                password.matches(".*\\d.*");
    }
}
