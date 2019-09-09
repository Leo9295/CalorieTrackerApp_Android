package com.fit5046.SQLiteDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DailyStepDao {

    @Query("SELECT * FROM DailyStepEntity")
    List<DailyStepEntity> getAll();

    @Insert
    long insert(DailyStepEntity dailyStepEntity);

    @Query("DELETE FROM DailyStepEntity")
    void deleteAll();
}
