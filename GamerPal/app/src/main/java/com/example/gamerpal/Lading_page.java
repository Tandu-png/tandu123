package com.example.gamerpal;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Lading_page extends AppCompatActivity {

    private LinearLayout btnSignUp, btnLogIn, btnSignUpWithG;
    private TextView tvContinueAsGuest, tvUTG;
    private static final int REQUEST_GOOGLE_MODAL = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        // Ánh xạ view
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUpWithG = findViewById(R.id.btnSignUpWithG);
        tvContinueAsGuest = findViewById(R.id.tvContinueAsGuest);
        tvUTG = findViewById(R.id.tvUTG);

        // Xử lý click Continue as Guest → chuyển sang Guest01
        tvContinueAsGuest.setOnClickListener(v -> {
            Intent intent = new Intent(Lading_page.this, Guest01.class);
            startActivity(intent);
        });

        // Tô màu gradient cho tiêu đề tvUTG
        Shader textShader = new LinearGradient(
                0, 0,
                tvUTG.getPaint().measureText(tvUTG.getText().toString()),
                tvUTG.getTextSize(),
                new int[]{
                        Color.parseColor("#F39A15"),
                        Color.parseColor("#EA4F0D")
                },
                new float[]{0, 1},
                Shader.TileMode.CLAMP
        );
        tvUTG.getPaint().setShader(textShader);

        // Chuyển sang màn Sign Up
        btnSignUp.setOnClickListener(v ->
                startActivity(new Intent(Lading_page.this, SignUp01.class)));

        // Chuyển sang màn Login
        btnLogIn.setOnClickListener(v ->
                startActivity(new Intent(Lading_page.this, Login.class)));

        // Mở modal Google → chuyển sang SignUp05
        btnSignUpWithG.setOnClickListener(v -> {
            Intent intentModal = new Intent(Lading_page.this, GoogleModal.class);
            startActivity(intentModal);

            // Delay rồi chuyển tiếp sang SignUp05
            new Handler().postDelayed(() -> {
                Intent intentSignup = new Intent(Lading_page.this, SignUp05.class);
                startActivity(intentSignup);
            }, 1000);
        });
    }
}
