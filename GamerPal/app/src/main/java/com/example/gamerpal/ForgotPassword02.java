package com.example.gamerpal;

import android.annotation.SuppressLint;
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

public class ForgotPassword02 extends AppCompatActivity {

    TextView tvInstruction, tvResendCode;
    EditText codeInput;
    LinearLayout btnContinue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_02);

        // Ánh xạ View
        tvInstruction = findViewById(R.id.tvInstruction); // cần thêm ID trong XML
        tvResendCode = findViewById(R.id.ro5cvxh2y7ro);
        codeInput = findViewById(R.id.codeInput);
        btnContinue = findViewById(R.id.btnContinue); // thêm ID cho LinearLayout chứa nút Continue

        // Lấy dữ liệu từ ForgotPassword
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");

        // Cập nhật nội dung TextView
        if (user != null) {
            String message = "A password reset code was sent to\n" + user;
            tvInstruction.setText(message);
        }

        // Xử lý hiệu ứng click cho "Send again"
        String fullText = "Didn’t receive a code? Send again.";
        SpannableString spannable = new SpannableString(fullText);
        int start = fullText.indexOf("Send again");
        int end = start + "Send again".length();

        Shader shader = new LinearGradient(0, 0, 0, 40,
                new int[]{Color.parseColor("#D98100"), Color.parseColor("#FFAA00")},
                null, Shader.TileMode.CLAMP);

        spannable.setSpan(new SignUpSocial01.ShaderSpan(shader), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(ForgotPassword02.this, "Code sent again!", Toast.LENGTH_SHORT).show();
                // TODO: Gửi lại mã nếu muốn
            }
        }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvResendCode.setText(spannable);
        tvResendCode.setMovementMethod(LinkMovementMethod.getInstance());

        // Xử lý nút Continue
        btnContinue.setOnClickListener(view -> {
            String code = codeInput.getText().toString().trim();
            if (code.isEmpty()) {
                codeInput.setError("Please enter the confirmation code");
            } else {
                // Giả lập xác thực thành công → sang màn hình tạo mật khẩu mới
                Intent intent1 = new Intent(ForgotPassword02.this, ForgotPassword03.class);
                startActivity(intent1);
            }
        });
    }
}
