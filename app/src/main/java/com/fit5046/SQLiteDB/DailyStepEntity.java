package com.fit5046.SQLiteDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DailyStepEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "stepNum")
    private int stepNum;
    @ColumnInfo(name = "updateTime")
    private String updateTime;

    public DailyStepEntity(int stepNum, String updateTime) {
        this.stepNum = stepNum;
        this.updateTime = updateTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
