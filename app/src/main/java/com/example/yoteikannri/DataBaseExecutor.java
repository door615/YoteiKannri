package com.example.yoteikannri;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataBaseExecutor {
    private final String addData;
    private final AppDatabase db;
    private List<AccessTime> atList;
    private final List<String> finalList = new ArrayList<>();
    private final RecyclerView listView;
    private final Activity kakunin;
    private CustomAdapter adapter;
    private final int position;


    public DataBaseExecutor(AppDatabase db, Activity activity, RecyclerView list, String addData, int position) {
        this.db = db;
        kakunin = activity;
        this.addData = addData;
        this.listView = list;
        this.position = position;

    }

    public void execute() {

        Runnable dataSaveAndDataDeleteAndMakeList = () -> {
            AccessTimeDao accessTimeDao = db.accessTimeDao();
            if (addData != null && !addData.equals("削除")) {
                accessTimeDao.insert(new AccessTime(addData));
            }

            atList = accessTimeDao.getAll();



            if (Objects.equals(addData, "削除")) {
                AccessTime data = atList.get(position);
                accessTimeDao.delete(data);
                for (AccessTime at : atList) {
                    finalList.add(at.getAccessTime());
                }
                finalList.remove(position);
            }else {
                for (AccessTime at : atList) {
                    finalList.add(at.getAccessTime());
                }
            }


        };

        Runnable recyclerViewAdapt = () -> {
            listView.setHasFixedSize(true);

            // use a linear layout manager
            RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(kakunin.getApplicationContext());

            listView.setLayoutManager(rLayoutManager);
            adapter = new CustomAdapter(finalList);
            listView.setAdapter(adapter);
        };

        ExecutorService executor = Executors.newWorkStealingPool(2);
        try {
            executor.execute(dataSaveAndDataDeleteAndMakeList);
            executor.execute(recyclerViewAdapt);
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(20, TimeUnit.SECONDS)) {
                    executor.shutdownNow();}
            } catch (InterruptedException e) {
                e.printStackTrace();
                executor.shutdownNow();
            }
        }
    }
}
