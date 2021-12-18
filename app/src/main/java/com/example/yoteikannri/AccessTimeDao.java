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

    @Insert
    void insert(AccessTime accessTime);

    @Delete
    void delete(AccessTime accessTime);

}
