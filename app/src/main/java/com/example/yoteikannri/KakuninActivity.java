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
        //データベースのインスタンスを作る
        AppDatabase db = AppDatabaseSingleton.getInstance(getApplicationContext());
        //NyuryokuActivityからデータを取得する
        String addHomework = getIntent().getStringExtra("data");
        int addDate = getIntent().getIntExtra("data2", 0);
        RecyclerView listView = findViewById(R.id.recyclerView);
        Activity kakunin = KakuninActivity;
        //ホーム画面に戻るボタン
        Button button = findViewById(R.id.buttonReturn);

        //RecyclerViewにスワイプ削除の機能を追加する
        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.ACTION_STATE_IDLE,
                        ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return true;
                    }

                    //スワイプしたときにRecyclerViewと紐づいているリストとデータベースからデータを削除する
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAbsoluteAdapterPosition();
                        //削除する処理
                        //DataBeseExecutorは、RecyclerViewを作りつつ、
                        //RecyclerViewと紐づいているリストとデータベースへのデータの操作をする
                        new DataBaseExecutor(db, kakunin, listView, null, 0, position, true).myExecute();
                    }
                });

        touchHelper.attachToRecyclerView(listView);


        if (addHomework != null && addDate != 0 ) {
            //NyuryokuActivityから画面遷移したときは、もらったaddDataを
            // RecyclerViewと紐づいているリストとデータベースに追加する
            new DataBaseExecutor(db, kakunin, listView, addHomework, addDate, 0, false).myExecute();
        } else {
            //ホーム画面から画面遷移したときは、追加はしない
            new DataBaseExecutor(db, kakunin, listView, null,0, 0, false).myExecute();
        }

        //ホーム画面に戻る処理
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            //戻るボタンで戻りすぎないように、アクティビティのフラグを消す
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });


    }

}
