package com.example.gamerpal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp02 extends AppCompatActivity {

    private EditText editTextCode;
    private LinearLayout continueButton;
    private TextView resendCodeText;
    private TextView backButton;
    private TextView emailText; // TextView hiển thị email

    private String correctCode = "123456"; // Giả lập mã xác nhận
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign02);

        // Nhận email từ SignUp01
        userEmail = getIntent().getStringExtra("email");

        // Ánh xạ view
        editTextCode = findViewById(R.id.rdqf0z7r6cho);
        continueButton = findViewById(R.id.roz7lejsdhme);
        resendCodeText = findViewById(R.id.ro5cvxh2y7ro);
        backButton = findViewById(R.id.textViewBack);
        emailText = findViewById(R.id.example123);

        // Gán email vào TextView
        if (userEmail != null && !userEmail.isEmpty()) {
            emailText.setText(userEmail);
        }

        // Nút Continue → kiểm tra mã xác nhận
        continueButton.setOnClickListener(v -> {
            String code = editTextCode.getText().toString().trim();
            if (code.isEmpty()) {
                Toast.makeText(SignUp02.this, "Please enter the confirmation code", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SignUp02.this, "Code accepted. Moving to Login...", Toast.LENGTH_SHORT).show();

                // Chuyển sang Login, gửi email và mật khẩu mặc định "1111"
                Intent intent = new Intent(SignUp02.this, Login.class);
                intent.putExtra("email", userEmail);
                intent.putExtra("password", "1111");
                intent.putExtra("fromGoogle", false);
                startActivity(intent);
                finish();
            }
        });


        // Nút quay lại
        backButton.setOnClickListener(v -> finish());

        // Gradient + Click cho "Send again"
        String fullText = "Didn't receive a code? Send again";
        SpannableString spannable = new SpannableString(fullText);
        int start = fullText.indexOf("Send again");
        int end = start + "Send again".length();

        Shader shader = new LinearGradient(
                0, 0, 300, 0,
                new int[]{Color.parseColor("#F39A15"), Color.parseColor("#EA4F0D")},
                null,
                Shader.TileMode.CLAMP
        );

        spannable.setSpan(new ShaderSpan(shader), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(SignUp02.this, "A new code has been sent to your email: " + userEmail, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Bỏ gạch chân
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        resendCodeText.setText(spannable);
        resendCodeText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
