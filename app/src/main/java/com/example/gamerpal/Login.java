package com.example.gamerpal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView backButton, tvForgotPassword, tvLogin;
    private LinearLayout btnContinue, btnSignUpWithG, layoutRemember;
    private View boxRemember;
    private EditText etEmail, etPassword;
    private boolean isRememberChecked = false;
    private FirebaseAuth mAuth;
    private Shader shader;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_01); // <- PHẢI gọi trước

        // Khởi tạo Firebase và SharedPreferences
        mAuth = FirebaseAuth.getInstance();
        prefs = getSharedPreferences("GamerPalPrefs", Context.MODE_PRIVATE);

        // Ánh xạ view
        backButton = findViewById(R.id.backButton);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvLogin = findViewById(R.id.tvLogin);
        btnContinue = findViewById(R.id.btnContinue);
        btnSignUpWithG = findViewById(R.id.btnSignUpWithG);
        layoutRemember = findViewById(R.id.layoutRemember);
        boxRemember = findViewById(R.id.boxRemember);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        // ✅ Xử lý email/password được gửi từ SignUp01 hoặc Google
        Intent intent = getIntent();
        String fromEmail = intent.getStringExtra("email");
        String fromPassword = intent.getStringExtra("password");

        if (intent.getBooleanExtra("fromGoogle", false)) {
            if (fromEmail != null) {
                etEmail.setText(fromEmail);
                etPassword.setText(""); // Google không có password
            }
        } else if (fromEmail != null && fromPassword != null) {
            etEmail.setText(fromEmail);
            etPassword.setText(fromPassword);
            isRememberChecked = true;
            boxRemember.setBackgroundResource(R.drawable.box_checked);
            saveAccount(fromEmail, fromPassword);

            Toast.makeText(this, "Tài khoản đã đăng ký thành công! Vui lòng đăng nhập.", Toast.LENGTH_LONG).show();
        }

        // Auto điền nếu nhớ tài khoản
        loadRememberedAccount();

        // Xử lý quay lại
        backButton.setOnClickListener(v -> finish());

        // Toggle "Remember me"
        layoutRemember.setOnClickListener(v -> {
            isRememberChecked = !isRememberChecked;
            boxRemember.setBackgroundResource(isRememberChecked ?
                    R.drawable.box_checked : R.drawable.box_unchecked);
        });

        // Xử lý nút Continue (Đăng nhập)
        btnContinue.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (email.isEmpty()) {
                etEmail.setError("Please enter your email");
                etEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Please enter your password");
                etPassword.requestFocus();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            if (isRememberChecked) {
                                saveAccount(email, password);
                            } else {
                                clearRememberedAccount();
                            }
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Chuyển sang ForgotPassword
        tvForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, ForgotPassword.class));
        });

        // Đăng nhập bằng Google
        btnSignUpWithG.setOnClickListener(v -> {
            Toast.makeText(Login.this, "Sign in with Google clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this, GoogleModal.class));
        });

        // Gradient + sự kiện "Sign Up"
        setGradientSignUp();
    }

    private void setGradientSignUp() {
        String loginText = "New to GamePal? Sign Up";
        SpannableString loginSpan = new SpannableString(loginText);
        int loginStart = loginText.indexOf("Sign Up");
        int loginEnd = loginStart + "Sign Up".length();

        shader = new LinearGradient(0, 0, 300, 0,
                new int[]{Color.parseColor("#F39A15"), Color.parseColor("#F39A15")},
                null, Shader.TileMode.CLAMP);

        loginSpan.setSpan(new ShaderSpan(shader), loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        loginSpan.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(Login.this, SignUp01.class));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false); // Bỏ gạch chân
            }
        }, loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvLogin.setText(loginSpan);
        tvLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void saveAccount(String email, String password) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("saved_email", email);
        editor.putString("saved_password", password);
        editor.apply();
    }

    private void clearRememberedAccount() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("saved_email");
        editor.remove("saved_password");
        editor.apply();
    }

    private void loadRememberedAccount() {
        String savedEmail = prefs.getString("saved_email", "");
        String savedPassword = prefs.getString("saved_password", "");
        if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
            etEmail.setText(savedEmail);
            etPassword.setText(savedPassword);
            isRememberChecked = true;
            boxRemember.setBackgroundResource(R.drawable.box_checked);
        }
    }
}
