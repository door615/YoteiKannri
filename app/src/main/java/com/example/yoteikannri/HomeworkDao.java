package com.example.yoteikannri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HomeworkDao {

    //宿題の日時と科目のデータをリストに全て入れる
    @Query("SELECT * FROM Homework ORDER BY date ASC")
    List<Homework> getAll();

    //データベースに宿題の日時と科目のデータを追加する
    @Insert
    void insert(Homework homework);

    //データベースから指定した宿題の日時と科目のデータを削除する
    @Delete
    void delete(Homework homework);

}
