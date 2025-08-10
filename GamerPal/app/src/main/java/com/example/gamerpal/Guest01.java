package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Guest01 extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 giây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_flow_01); 

        // Tự động chuyển sang màn hình Landing_page sau 3 giây
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Guest01.this, Guest02.class);
            startActivity(intent);
            finish(); // để đóng màn hình hiện tại khỏi stack
        }, SPLASH_DELAY);
    }
}
