package com.example.gamerpal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp01 extends AppCompatActivity {

    private EditText etEmail, etDisplayName, etUsername, etPassword, etConfirmPassword;
    private TextView tvCharCountDisplay1, tvCharCountDisplay, backButton;
    private LinearLayout btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_01);

        etEmail = findViewById(R.id.etEmail);
        etDisplayName = findViewById(R.id.etDisplayName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        tvCharCountDisplay1 = findViewById(R.id.tvCharCountDisplay1);
        tvCharCountDisplay = findViewById(R.id.tvCharCountDisplay);
        btnContinue = findViewById(R.id.btnContinue);
        backButton = findViewById(R.id.textViewBack); // thêm ánh xạ backButton

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView tvTerms = findViewById(R.id.tvTerms);
        setGradientToTerms(tvTerms);

        etDisplayName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCharCountDisplay1.setText(s.length() + "/20");
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvCharCountDisplay.setText(s.length() + "/20");
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        btnContinue.setOnClickListener(view -> validateForm());

        // Xử lý nút Back quay về màn hình trước
        backButton.setOnClickListener(v -> finish());
    }

    private void validateForm() {
        String emailOrPhone = etEmail.getText().toString().trim();
        String displayName = etDisplayName.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (emailOrPhone.isEmpty() || (!Patterns.EMAIL_ADDRESS.matcher(emailOrPhone).matches() && !Patterns.PHONE.matcher(emailOrPhone).matches())) {
            showToast("Please enter a valid email address or phone number");
            return;
        }

        if (displayName.isEmpty()) {
            showToast("Please enter a display name");
            return;
        }

        if (username.isEmpty()) {
            showToast("Please enter a username");
            return;
        }

        if (!isValidPassword(password)) {
            showToast("Password must be at least 8 characters, include a number and a special symbol (!@#$%*)");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showToast("Passwords do not match");
            return;
        }

        Intent intent = new Intent(SignUp01.this, SignUp02.class);
        intent.putExtra("email", emailOrPhone);
        intent.putExtra("displayName", displayName);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[!@#$%*].*") &&
                password.matches(".*[0-9].*");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setGradientToTerms(TextView textView) {
        String fullText = "By signing up, you agree to our Terms of Service and Privacy Policy.";
        String term1 = "Terms of Service";
        String term2 = "Privacy Policy";

        SpannableString spannable = new SpannableString(fullText);

        Shader shader = new LinearGradient(
                0, 0, 300, 0,
                new int[]{Color.parseColor("#F39A15"), Color.parseColor("#EA4F0D")},
                null,
                Shader.TileMode.CLAMP
        );

        int start1 = fullText.indexOf(term1);
        int end1 = start1 + term1.length();
        spannable.setSpan(new ShaderSpan(shader), start1, end1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int start2 = fullText.indexOf(term2);
        int end2 = start2 + term2.length();
        spannable.setSpan(new ShaderSpan(shader), start2, end2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannable);
    }
}
