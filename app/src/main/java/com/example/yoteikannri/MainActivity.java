package com.example.yoteikannri;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonNyuuryoku = findViewById(R.id.buttonNyuuryoku);
        Button buttonKakunin = findViewById(R.id.buttonKakunin);
        buttonNyuuryoku.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), NyuuryokuActivity.class);
            startActivity(intent);
        });

        buttonKakunin.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplication(), KakuninActivity.class);
            startActivity(intent2);
        });
    }
}