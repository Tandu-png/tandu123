package com.example.gamerpal;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class SignUp05 extends AppCompatActivity {

    private static final int DELAY_MILLIS = 2500; // 2.5 giây

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign05);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageSplash = findViewById(R.id.imageSplash); // ánh xạ ảnh nếu có

        // Delay 2.5 giây rồi tự động chuyển tiếp
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SignUp05.this, SignUpSocial01.class);
            startActivity(intent);
            finish();
        }, DELAY_MILLIS);
    }
}
