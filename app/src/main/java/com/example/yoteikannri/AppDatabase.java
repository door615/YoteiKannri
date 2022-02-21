package com.example.yoteikannri;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Homework.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HomeworkDao homeworkDao();

    //データベースとカラムの名前を変更したのでマイグレーションをする
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Homework` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`homework_day` TEXT)");
            database.execSQL("INSERT INTO Homework SELECT*FROM AccessTime");
            database.execSQL("DROP TABLE Accesstime");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Homework ADD date INTEGER NOT NULL DEFAULT 0");
        }
    };
}