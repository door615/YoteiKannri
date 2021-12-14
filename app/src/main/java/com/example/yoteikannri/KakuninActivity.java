package com.example.yoteikannri;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            new databaseExecutor(db, kakunin, listView, addData, 0).Execute();
        }else {
            new databaseExecutor(db, kakunin, listView, null, 0).Execute();
        }

        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        buttonSecond.setOnClickListener(v ->{
            new databaseExecutor(db, kakunin, listView, "削除", 0).Execute();
        });

        listView.setDismissCallback((listview, position) -> {


            /// 消す処理
            new DataStoreAsyncTask(db, kakunin, listView, "削除", position).execute();
            return new EnhancedListView.Undoable() {
                @Override
                public void undo() {
                    // 元に戻す処理
                }
            };
        });
        listView.enableSwipeToDismiss();

    }


























    private static class DataStoreAsyncTask extends AsyncTask<Void, Void, Integer> {
        private final String addData;
        private AppDatabase db;
        private List<AccessTime> atList;
        private List finalList = new ArrayList();
        private final EnhancedListView listView;
        private final Activity kakunin;
        private ArrayAdapter adapter;
        private final int position;



        public DataStoreAsyncTask(AppDatabase db, Activity activity, EnhancedListView list, String addData, int position) {
            this.db = db;
            kakunin = activity;
            this.addData = addData;
            this.listView = list;
            this.position = position;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            AccessTimeDao accessTimeDao = db.accessTimeDao();

            if (addData != null && !addData.equals("削除")) {
                accessTimeDao.insert(new AccessTime(addData));
            }

            atList = accessTimeDao.getAll();

            if (Objects.equals(addData, "削除")) {
                AccessTime data = atList.get(position);
                accessTimeDao.delete(data);
                atList.remove(position);
                //accessTimeDao.deleteAll();
            }

            for (AccessTime at: atList) {
                finalList.add(at.getAccessTime());
            }








            return 0;
        }

        @Override
        protected void onPostExecute(Integer code) {

            adapter = new ArrayAdapter<>(kakunin, android.R.layout.simple_list_item_1, finalList);
            listView.setAdapter(adapter);




    }

}



}
