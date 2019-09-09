package com.fit5046.SQLiteDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {DailyStepEntity.class}, version = 2, exportSchema = false)
public abstract class DailyStepDatabase extends RoomDatabase {
    public abstract DailyStepDao dailyStepDao();

    private static volatile DailyStepDatabase INSTANCE;

    static DailyStepDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (DailyStepDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DailyStepDatabase.class, "dailyStep_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
