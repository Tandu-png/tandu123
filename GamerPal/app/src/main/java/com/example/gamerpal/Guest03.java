package com.example.gamerpal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Guest03 extends AppCompatActivity {

    private LinearLayout btnContinue;
    private TextView txtSkipIntro;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_flow_03); // Đảm bảo đúng layout file

        btnContinue = findViewById(R.id.roz7lejsdhme);
        txtSkipIntro = findViewById(R.id.txtSkipIntro);

        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(Guest03.this, Guest04.class);
            startActivity(intent);
            finish();
        });

        txtSkipIntro.setOnClickListener(v -> {
            Intent intent = new Intent(Guest03.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
