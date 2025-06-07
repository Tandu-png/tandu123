package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class ForgotPassword extends AppCompatActivity {

    EditText inputEmailPhone;
    LinearLayout btnSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_01); // đổi đúng tên XML bạn đặt

        inputEmailPhone = findViewById(R.id.rdqf0z7r6cho); // Email or phone input
        btnSendCode = findViewById(R.id.roz7lejsdhme);     // Send Reset Code button

        btnSendCode.setOnClickListener(view -> {
            String userInput = inputEmailPhone.getText().toString().trim();

            if (!userInput.isEmpty()) {
                Intent intent = new Intent(ForgotPassword.this, ForgotPassword02.class);
                intent.putExtra("user", userInput);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please enter your email or phone", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
