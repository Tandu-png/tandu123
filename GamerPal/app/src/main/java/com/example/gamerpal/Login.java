package com.example.gamerpal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.LinearGradient;

public class Login extends AppCompatActivity {

    private TextView backButton, tvForgotPassword, tvLogin;
    private LinearLayout btnContinue, btnSignUpWithG, layoutRemember;
    private View boxRemember;
    private EditText etEmail, etPassword;

    private boolean isRememberChecked = false;
    private Shader shader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_01);  // Đổi thành tên file XML của bạn

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

        // Xử lý nút Back (đóng activity)
        backButton.setOnClickListener(v -> finish());

        // Xử lý Remember me toggle
        layoutRemember.setOnClickListener(v -> {
            isRememberChecked = !isRememberChecked;
            if (isRememberChecked) {
                // Đổi background thành checked
                boxRemember.setBackgroundResource(R.drawable.box_checked);
            } else {
                // Đổi background thành unchecked
                boxRemember.setBackgroundResource(R.drawable.box_unchecked);
            }
        });

        // Xử lý nút Continue (Log in)
        btnContinue.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString();

            if (email.isEmpty()) {
                etEmail.setError("Please enter your username");
                etEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Please enter your password");
                etPassword.requestFocus();
                return;
            }

            // TODO: Thực hiện kiểm tra đăng nhập với server hoặc logic khác
            // Hiện demo: nếu username = "admin" và password = "123456" thì đăng nhập thành công
            if (email.equals("admin") && password.equals("123456")) {
                Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                // Chuyển sang màn hình Home (ví dụ)
                Intent intent = new Intent(Login.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Sign Up With Google
        btnSignUpWithG.setOnClickListener(v -> {
            // TODO: Xử lý đăng nhập Google tại đây
            Toast.makeText(Login.this, "Sign in with Google clicked", Toast.LENGTH_SHORT).show();

            // Ví dụ: chuyển sang màn hình đăng ký tiếp theo
            Intent intent = new Intent(Login.this, GoogleModal.class);
            startActivity(intent);
        });

        // Xử lý nút Forgot Password
        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);
        });
        // Tạo hiệu ứng Shader cho chữ "Sign Up"
        String loginText = "New to GamePal? Sign Up";
        SpannableString loginSpan = new SpannableString(loginText);
        int loginStart = loginText.indexOf("Sign Up");
        int loginEnd = loginStart + "Sign Up".length();

        if (loginStart != -1) {
            // Tạo hiệu ứng gradient nếu cần (hoặc dùng Shader có sẵn)
            // Ví dụ tạo shader đơn giản nếu bạn chưa có shader:

            TextPaint paint = new TextPaint();
            shader = new LinearGradient(0, 0, 0, paint.getTextSize(),
            new int[]{Color.parseColor("#F39A15"), Color.parseColor("#F39A15")},
            null, Shader.TileMode.CLAMP);


            // Áp dụng ShaderSpan
            loginSpan.setSpan(new SignUpSocial01.ShaderSpan(shader), loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Áp dụng click riêng cho "Sign Up"
            loginSpan.setSpan(new android.text.style.ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    // Chuyển sang màn hình đăng ký
                    Intent intent = new Intent(Login.this, SignUp01.class);
                    startActivity(intent);
                }
            }, loginStart, loginEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        tvLogin.setText(loginSpan);
        tvLogin.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

    }
}
