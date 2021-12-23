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

        //宿題の入力画面(NyuuryokuActivity)への画面遷移
        buttonNyuuryoku.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), NyuuryokuActivity.class);
            startActivity(intent);
        });

        //宿題の確認画面(KakuninActivity)への画面遷移
        buttonKakunin.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplication(), KakuninActivity.class);
            startActivity(intent2);
        });
    }
}