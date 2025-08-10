package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp02 extends AppCompatActivity {

    private EditText editTextCode;
    private LinearLayout continueButton;
    private TextView resendCodeText;
    private TextView backButton;
    private Shader shader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign02);

        // Ánh xạ view
        editTextCode = findViewById(R.id.rdqf0z7r6cho);
        continueButton = findViewById(R.id.roz7lejsdhme);
        resendCodeText = findViewById(R.id.ro5cvxh2y7ro);
        backButton = findViewById(R.id.textViewBack);
        // Nút "Continue"
        continueButton.setOnClickListener(v -> {
            String code = editTextCode.getText().toString().trim();
            if (code.isEmpty()) {
                Toast.makeText(SignUp02.this, "Please enter the confirmation code", Toast.LENGTH_SHORT).show();
            } else {
                // kiểm tra mã code thực tế nếu có backend
                Intent intent = new Intent(SignUp02.this, Login.class); // chuyển sang Login tạm thời
                startActivity(intent);
            }
        });

        // Nút quay lại
        backButton.setOnClickListener(v -> finish());

        // Tạo hiệu ứng gradient cho chữ "Send again"
        String loginText = "Didn't receive a code? Send again";
        SpannableString loginSpan = new SpannableString(loginText);
        int loginStart = loginText.indexOf("Send again");
        int loginEnd = loginStart + "Send again".length();

        TextPaint paint = new TextPaint();
        shader = new LinearGradient(0, 0, 0, paint.getTextSize(),
                new int[]{Color.parseColor("#D98100"), Color.parseColor("#D98100")},
                null, Shader.TileMode.CLAMP);

        // Gán hiệu ứng shader
        loginSpan.setSpan(new SignUpSocial01.ShaderSpan(shader), loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Gán sự kiện click
        loginSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(SignUp02.this, "A new code has been sent to your email.", Toast.LENGTH_SHORT).show();
            }
        }, loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        resendCodeText.setText(loginSpan);
        resendCodeText.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
