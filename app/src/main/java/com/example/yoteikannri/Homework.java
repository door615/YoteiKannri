package com.example.yoteikannri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Homework {
    @PrimaryKey(autoGenerate = true)
    private int id;

    //宿題の日時と科目を記録するデータのカラムを作る
    @ColumnInfo(name = "homework_day")
    private String homework;

    public Homework(String homework) {
        this.homework = homework;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getHomework() {
        return homework;
    }


}
