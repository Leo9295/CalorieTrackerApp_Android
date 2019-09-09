package com.fit5046.SQLiteDB;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;

import com.fit5046.calorietrackerapp.R;

import java.util.List;


public class DailyStepDB extends AppCompatActivity {

    DailyStepDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daliy_step_db);

        db = Room.databaseBuilder(getApplicationContext(), DailyStepDatabase.class, "DailyStepDatabase").fallbackToDestructiveMigration().build();

    }

    private class ReadDatabase extends AsyncTask<Void, Void, Integer>{
        @Override
        protected Integer doInBackground(Void... params) {
            List<DailyStepEntity> dailyStepEntityList = db.dailyStepDao().getAll();
            if(!(dailyStepEntityList.isEmpty() || dailyStepEntityList == null)){
                return getTotalStepNumForOneDay(dailyStepEntityList);
            } else
                return 0;
        }
        @Override
        protected void onPostExecute(Integer result){

        }
    }

    // If current time is 23:59:59
    public boolean currentTime(){
        Time currentTime = new Time();
        currentTime.setToNow();
        int currentHour = currentTime.hour;
        int currentMinute = currentTime.minute;
        int currentSecond = currentTime.second;

        if(currentHour == 23 && currentMinute == 59 && currentSecond == 59){
            return true;
        }
        return false;
    }

    public int getTotalStepNumForOneDay(List<DailyStepEntity> fullDaySteps){
        int totalStepNum = 0;
        for(DailyStepEntity d : fullDaySteps){
            totalStepNum += d.getStepNum();
        }
        return totalStepNum;
    }

    public boolean delAllStepsRecord(){
        db.dailyStepDao().deleteAll();
        return true;
    }
}
