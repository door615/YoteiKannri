package com.example.yoteikannri;

import android.app.Activity;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataBaseExecutor {
    private final String addHomework;
    private final int addDate;
    private final AppDatabase db;
    private final RecyclerView recyclerView;
    private final Activity kakunin;
    private final int position;
    private List<Homework> homeworkList = new ArrayList<>();
    private CustomAdapter adapter;
    private final boolean isDelete;

    //コンストラクタ
    public DataBaseExecutor(AppDatabase db, Activity activity, RecyclerView list, String addHomework, int addDate, int position, boolean isDelete) {
        this.db = db;
        kakunin = activity;
        this.addHomework = addHomework;
        this.addDate = addDate;
        this.recyclerView = list;
        this.position = position;
        this.isDelete = isDelete;
    }

    //データベースへのデータの追加と削除、リサイクラ―ビューの製作をマルチスレッドで実行する
    public void myExecute() {
        //メインのUIスレッド以外でもこのメソッドは実行されるのでHandlerを使う
        Handler handler = new Handler();

        //データベースへのデータの追加と削除、リサイクラ―ビューに使うリストを作る
        Runnable dataSaveAndDataDeleteAndMakeList = () -> {
            HomeworkDao homeworkDao = db.homeworkDao();
            if (addHomework != null) {
                homeworkDao.insert(new Homework(addHomework, addDate));
            }

            homeworkList = homeworkDao.getAll();

            if (isDelete) {
                Homework data = homeworkList.get(position);
                homeworkDao.delete(data);
                homeworkList.remove(position);
                }
        };

        //リサイクラ―ビューにレイアウトとアダプターを適用する
        Runnable recyclerViewAdapt = () -> {
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(kakunin.getApplicationContext());
            recyclerView.setLayoutManager(rLayoutManager);
            adapter = new CustomAdapter(homeworkList);
            recyclerView.setAdapter(adapter);
        };

        //上のRunnableをマルチスレッドで実行する
        ExecutorService executor = Executors.newWorkStealingPool(2);
        try {
            //実行
            executor.execute(dataSaveAndDataDeleteAndMakeList);
            handler.post(recyclerViewAdapt);
        } finally {
            //正常に実行できたらシャットダウンする
            executor.shutdown();
            //実行に20秒かかったら強制的にシャットダウンする
            try {
                if (!executor.awaitTermination(20, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                executor.shutdownNow();
            }
        }
    }
}
