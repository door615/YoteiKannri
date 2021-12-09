package com.example.yoteikannri;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccessTimeDao {
    @Query("SELECT * FROM accesstime")
    List<AccessTime> getAll();

    @Query("SELECT * FROM accesstime WHERE id IN (:ids)")
    List<AccessTime> loadAllByIds(int[] ids);

    @Query("DELETE FROM accessTime")
    void deleteAll();

    @Insert
    void insertAll(AccessTime... accessTimes);

    @Insert
    void insert(AccessTime accessTime);

    @Delete
    void delete(AccessTime accessTime);




}
