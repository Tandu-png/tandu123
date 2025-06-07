package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GoogleModal extends AppCompatActivity {

    private LinearLayout btnContinue;
    private TextView txtInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_modal); // Đổi tên XML nếu khác

        // Ánh xạ View
        btnContinue = findViewById(R.id.btnContinue);
        txtInstruction = findViewById(R.id.txtInstruction);

        // Xử lý khi nhấn nút "Continue"
        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(GoogleModal.this, SignUp05.class);
            startActivity(intent);
            finish();
        });
    }

    private void onGoogleSignInSuccess() {
        Intent intent = new Intent(GoogleModal.this, SignUp05.class);
        // Có thể truyền dữ liệu Google account ở đây, ví dụ: intent.putExtra("googleEmail", userEmail);
        startActivity(intent);
        finish();
    }
}
