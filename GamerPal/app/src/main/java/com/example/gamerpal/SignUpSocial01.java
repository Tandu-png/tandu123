package com.example.gamerpal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpSocial01 extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private TextView tvCharCountDisplay1, tvLogin, tvTerms;
    private LinearLayout btnContinue;
    private ImageView ivEmailCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_social01);
        tvTerms = findViewById(R.id.tvTerms);
        ivEmailCheck = findViewById(R.id.r274wvo4qdfy);
        ivEmailCheck.setVisibility(View.GONE);
        tvLogin = findViewById(R.id.tvLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvCharCountDisplay1 = findViewById(R.id.tvCharCountDisplay1);
        btnContinue = findViewById(R.id.btnContinue);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailInput = s.toString().trim();
                tvCharCountDisplay1.setText(emailInput.length() + "/20");

                if (emailInput.isEmpty()) {
                    ivEmailCheck.setVisibility(View.GONE);
                    return;
                }

                if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
                        && emailInput.toLowerCase().endsWith("@gmail.com")) {
                    ivEmailCheck.setVisibility(View.VISIBLE);
                } else {
                    ivEmailCheck.setVisibility(View.GONE);
                }
            }
        });

        // Gradient shader
        Shader shader = new LinearGradient(
                0, 0, 300, 0,
                new int[]{Color.parseColor("#F39A15"), Color.parseColor("#EA4F0D")},
                null,
                Shader.TileMode.CLAMP
        );

        // Gradient cho Terms of Service + Privacy Policy
        String fullText = "By clicking continue, you agree to our\n Terms of Service  and  Privacy Policy.";
        SpannableString spannable = new SpannableString(fullText);

        int termsStart = fullText.indexOf("Terms of Service");
        int termsEnd = termsStart + "Terms of Service".length();
        spannable.setSpan(new ShaderSpan(shader), termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int policyStart = fullText.indexOf("Privacy Policy");
        int policyEnd = policyStart + "Privacy Policy".length();
        spannable.setSpan(new ShaderSpan(shader), policyStart, policyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvTerms.setText(spannable);

        // Gradient cho Log in
        String loginText = "Already have an account? Log in";
        SpannableString loginSpan = new SpannableString(loginText);
        int loginStart = loginText.indexOf("Log in");
        int loginEnd = loginStart + "Log in".length();
        loginSpan.setSpan(new ShaderSpan(shader), loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLogin.setText(loginSpan);

        // Đếm số ký tự email
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCharCountDisplay1.setText(s.length() + "/20");
            }
        });

        // Xử lý Continue
        btnContinue.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Please enter your email");
                etEmail.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Invalid email format");
                etEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Please enter your password");
                etPassword.requestFocus();
                return;
            }

            if (password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters");
                etPassword.requestFocus();
                return;
            }
            startActivity(new Intent(SignUpSocial01.this, SignUpSocial6.class));
            finish();
        });

        // Xử lý Log in
        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(SignUpSocial01.this, Login.class));
            finish();
        });
    }

    // ShaderSpan để dùng gradient cho TextView
    public static class ShaderSpan extends ReplacementSpan {
        private final Shader shader;

        public ShaderSpan(Shader shader) {
            this.shader = shader;
        }

        @Override
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            return (int) paint.measureText(text, start, end);
        }

        @Override
        public void draw(android.graphics.Canvas canvas, CharSequence text, int start, int end,
                         float x, int top, int y, int bottom, android.graphics.Paint paint) {
            TextPaint textPaint = new TextPaint(paint);
            textPaint.setShader(shader);
            canvas.drawText(text, start, end, x, y, textPaint);
        }
    }
}
