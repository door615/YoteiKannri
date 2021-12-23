package com.example.yoteikannri;

import static com.example.yoteikannri.AppDatabase.MIGRATION_1_2;

import android.content.Context;
import androidx.room.Room;

public class AppDatabaseSingleton {
    private static AppDatabase instance = null;

    //データベースのインスタンスが必ず一つしかないようにする
    public static AppDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;

        }

        instance = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "database_name")
                .addMigrations(MIGRATION_1_2)
                .build();
        return instance;
    }
}
