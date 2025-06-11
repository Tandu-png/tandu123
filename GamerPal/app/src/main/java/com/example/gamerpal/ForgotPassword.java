package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    EditText inputEmailPhone;
    LinearLayout btnSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_01);

        inputEmailPhone = findViewById(R.id.rdqf0z7r6cho); // Trường nhập email hoặc số điện thoại
        btnSendCode = findViewById(R.id.roz7lejsdhme);     // Nút gửi mã xác minh

        btnSendCode.setOnClickListener(view -> {
            String userInput = inputEmailPhone.getText().toString().trim();

            if (userInput.isEmpty()) {
                Toast.makeText(this, "Please enter your email or phone number", Toast.LENGTH_SHORT).show();
            } else if (isValidEmail(userInput) || isValidPhone(userInput)) {
                Intent intent = new Intent(ForgotPassword.this, ForgotPassword02.class);
                intent.putExtra("user", userInput);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid email or phone number format", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Kiểm tra email hợp lệ
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Kiểm tra số điện thoại hợp lệ (tối thiểu 9-11 chữ số, không chứa chữ cái)
    private boolean isValidPhone(String phone) {
        return phone.matches("^\\d{9,11}$");
    }
}
