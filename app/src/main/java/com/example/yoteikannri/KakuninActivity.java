package com.example.yoteikannri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class KakuninActivity extends AppCompatActivity {
    private final Activity KakuninActivity = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kakunin);
        AppDatabase db = AppDatabaseSingleton.getInstance(getApplicationContext());
        String addData = getIntent().getStringExtra("data");
        RecyclerView listView = findViewById(R.id.recyclerView);
        Activity kakunin = KakuninActivity;
        Button button = findViewById(R.id.buttonReturn);


        if (addData != null) {
            new DataBaseExecutor(db, kakunin, listView, addData, 0).execute();
        }else {
            new DataBaseExecutor(db, kakunin, listView, null, 0).execute();
        }

        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }

}
