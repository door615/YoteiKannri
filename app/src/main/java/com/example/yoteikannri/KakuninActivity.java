package com.example.yoteikannri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import de.timroes.android.listview.EnhancedListView;

public class KakuninActivity extends AppCompatActivity {
    private final Activity KakuninActivity = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kakunin);
        AppDatabase db = AppDatabaseSingleton.getInstance(getApplicationContext());
        String addData = getIntent().getStringExtra("data");
        EnhancedListView listView = (EnhancedListView) findViewById(R.id.listView);
        Activity kakunin = KakuninActivity;
        Button button = findViewById(R.id.buttonReturn);
        Button buttonSecond = findViewById(R.id.deleteButton);


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

        buttonSecond.setOnClickListener(v -> new DataBaseExecutor(db, kakunin, listView, "削除", 0).execute());

        listView.setDismissCallback((listview, position) -> {


            /// 消す処理
            new DataBaseExecutor(db, kakunin, listView, "削除", position).execute();
            return new EnhancedListView.Undoable() {
                @Override
                public void undo() {
                    // 元に戻す処理
                }
            };
        });
        listView.enableSwipeToDismiss();

    }

}
