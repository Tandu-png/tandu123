package com.example.gamerpal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Guest02 extends AppCompatActivity {

    private LinearLayout btnContinue;
    private TextView txtSkipIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_flow_02); 

        // Gán view
        btnContinue = findViewById(R.id.roz7lejsdhme);
        txtSkipIntro = findViewById(R.id.txtSkipIntro);

        // Sự kiện nút Continue → sang Guest03
        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(Guest02.this, Guest03.class);
            startActivity(intent);
            finish();
        });

        // Sự kiện Skip intro → sang HomeActivity
        txtSkipIntro.setOnClickListener(v -> {
            Intent intent = new Intent(Guest02.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
