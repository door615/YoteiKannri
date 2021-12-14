package com.example.yoteikannri;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.timroes.android.listview.EnhancedListView;

public class databaseExecutor {
    private final String addData;
    private AppDatabase db;
    private List<AccessTime> atList;
    private List finalList = new ArrayList();
    private final EnhancedListView listView;
    private final Activity kakunin;
    private ArrayAdapter adapter;
    private final int position;



    public databaseExecutor(AppDatabase db, Activity activity, EnhancedListView list, String addData, int position) {
        this.db = db;
        kakunin = activity;
        this.addData = addData;
        this.listView = list;
        this.position = position;
    }

    public void Execute() {
        Runnable dataStore = () -> {
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

            for (AccessTime at : atList) {
                finalList.add(at.getAccessTime());
            }
        };

        Runnable recyclerViewAdapt = () -> {
            adapter = new ArrayAdapter<>(kakunin, android.R.layout.simple_list_item_1, finalList);
            listView.setAdapter(adapter);
        };

        ExecutorService executor = Executors.newWorkStealingPool(2);
        try {
            executor.execute(dataStore);
            executor.execute(recyclerViewAdapt);
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();}
            } catch (InterruptedException e) {
                e.printStackTrace();
                executor.shutdownNow();
            }
        }

    }
}