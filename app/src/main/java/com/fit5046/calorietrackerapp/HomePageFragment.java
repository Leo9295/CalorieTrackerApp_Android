package com.fit5046.calorietrackerapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fit5046.RESTfulWSEntities.Appuser;
import com.fit5046.RESTfulWSEntities.Report;
import com.fit5046.commonTools.ConnectToRESTWS;
import com.fit5046.commonTools.ObjectSave;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePageFragment extends Fragment implements View.OnClickListener {

    private View vHome;
    private Appuser loginUser;
    private EditText et_calorie_goal;
    private TextView tv_userFirstName;
    private TextView tv_home_page_currentDateTime;
    private String currentDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vHome = inflater.inflate(R.layout.fragment_home_page, container, false);

        loginUser = (Appuser) ObjectSave.readObject(getActivity().getApplicationContext(), "currentUser");
        tv_userFirstName = (TextView) vHome.findViewById(R.id.tv_home_page_welcomeText);
        tv_home_page_currentDateTime = (TextView) vHome.findViewById(R.id.tv_home_page_currentDateTime);
        et_calorie_goal = (EditText)  vHome.findViewById(R.id.et_calorie_goal);

        Button set_calorie_goal_btn = (Button) vHome.findViewById(R.id.set_calorie_goal_btn);
        set_calorie_goal_btn.setOnClickListener(this);

        tv_userFirstName.setText("Welcome, " + loginUser.getFirstname());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tv_home_page_currentDateTime.setText(sdf.format(new Date()));

        currentDate = sdf.format(new Date());

        return vHome;
    }

    @Override
    public void onClick(View v) {
        CalorieAsyncTask calorieAsyncTask = new CalorieAsyncTask();
        calorieAsyncTask.execute();
    }

    private class CalorieAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            switch (s){
                case "c":
                    Toast.makeText(getActivity().getApplicationContext(), "Your new Daily Goal has been created", Toast.LENGTH_LONG);
                    break;
                case "u":
                    Toast.makeText(getActivity().getApplicationContext(), "Your new Daily Goal has been updated", Toast.LENGTH_LONG);
                    break;
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            String check = ConnectToRESTWS.findRecordByUseridAndDate(loginUser.getUserid(), currentDate.substring(0,10));
            if (check.length() > 4) {
                try {
                    JSONArray jsonArray = new JSONArray(check);
                    String str = jsonArray.optString(0);
                    JSONObject jsonObject = new JSONObject(str);
                    Report newReport = new Report();
                    newReport.setUserid(loginUser);
                    newReport.setReportid(jsonObject.getLong("reportid"));
                    newReport.setReportdate(new Date());
                    newReport.setClaoriegoal(Integer.parseInt(et_calorie_goal.getText().toString()));

                    if(jsonObject.has("caloriesburned"))
                        newReport.setCaloriesburned(jsonObject.getInt("caloriesburned"));
                    if(jsonObject.has("caloriesconsumed"))
                        newReport.setCaloriesconsumed(jsonObject.getInt("caloriesconsumed"));
                    if(jsonObject.has("totalstepsnum"))
                        newReport.setTotalstepsnum(jsonObject.getInt("totalstepsnum"));

                    ConnectToRESTWS.updateReport(newReport.getReportid(), newReport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "u";
            } else {
                try {
                    int reportId = Integer.parseInt(ConnectToRESTWS.reportCount()) + 50001;
                    Report newReport = new Report(reportId, loginUser, new Date());
                    newReport.setClaoriegoal(Integer.parseInt(et_calorie_goal.getText().toString()));
                    ConnectToRESTWS.createReport(newReport);
                    Toast.makeText(getActivity().getApplicationContext(), "New report has been created", Toast.LENGTH_LONG);
                } catch (Exception e){
                    e.printStackTrace();
                }
                return "c";
            }
        }
    }
}