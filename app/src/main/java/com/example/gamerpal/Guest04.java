package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Guest04 extends AppCompatActivity {

    private LinearLayout btnContinue;
    private TextView txtSkipIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_flow_04);

        btnContinue = findViewById(R.id.roz7lejsdhme);
        txtSkipIntro = findViewById(R.id.txtSkipIntro);

        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(Guest04.this, HomeActivity.class);
            startActivity(intent);
            finish(); // đóng Guest04 khỏi backstack
        });

        txtSkipIntro.setOnClickListener(v -> {
            Intent intent = new Intent(Guest04.this, HomeActivity.class);
            startActivity(intent);
            finish(); // cũng đóng Guest04
        });
    }
}
