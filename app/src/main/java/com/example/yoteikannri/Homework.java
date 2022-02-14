package com.example.yoteikannri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Homework {
    @PrimaryKey(autoGenerate = true)
    private int id;

    //宿題の日付と科目を記録するデータのカラムを作る
    @ColumnInfo(name = "homework_day")
    private String homework;

    //宿題の日付を記録するカラム(日付順にソートするときに使用)
    @ColumnInfo(defaultValue = "0")
    private int date;

    public Homework(String homework, int date) {
        this.homework = homework;
        this.date = date;
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

    public void setDate(int date) { this.date = date;}

    public int getDate() { return date;}


}
