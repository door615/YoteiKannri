package com.example.yoteikannri;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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

        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_IDLE,
                        ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAbsoluteAdapterPosition();
                        new DataBaseExecutor(db, kakunin, listView, "削除", position ).myexecute();
                    }
                });

        touchHelper.attachToRecyclerView(listView);


        if (addData != null) {
            new DataBaseExecutor(db, kakunin, listView, addData, 0).myexecute();
        }else {
            new DataBaseExecutor(db, kakunin, listView, null, 0).myexecute();
        }

        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }

}
