package com.fit5046.calorietrackerapp.signUpActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fit5046.RESTfulWSEntities.Appuser;
import com.fit5046.RESTfulWSEntities.Usercredential;
import com.fit5046.calorietrackerapp.HomePageActivity;
import com.fit5046.calorietrackerapp.R;
import com.fit5046.commonTools.BuildMD5;
import com.fit5046.commonTools.ConnectToRESTWS;
import com.fit5046.commonTools.ObjectSave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignUpFitInfoActivity extends AppCompatActivity {

    private int userCount = 0;
    private int userCredentialCount = 0;
    private Spinner activeLevel;
    private EditText et_stepPerMile;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_fit_info);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sp_config", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        activeLevel = (Spinner) findViewById(R.id.sp_activeLevel);
        et_stepPerMile = (EditText) findViewById(R.id.et_stepPerMile);
        nextBtn = (Button) findViewById(R.id.nextBtnSign4);

        List<String> activeLevelList = new ArrayList<String>();
        activeLevelList.add("Please select one type from below");
        activeLevelList.add("Little/No exercise (Sedentary)");
        activeLevelList.add("Lightly active (Exercise 1-3 days/week)");
        activeLevelList.add("Moderately active (Exercise 3-5 days/week)");
        activeLevelList.add("Very active (Exercise 6-7 days/week)");
        activeLevelList.add("Extra active (very hard exercise or training)");

        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, activeLevelList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activeLevel.setAdapter(spinnerAdapter);
        activeLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Toast.makeText(SignUpFitInfoActivity.this, "Please select your activeity level", Toast.LENGTH_LONG).show();
                } else {
                    editor.putInt("newUserActiveLevel", position);
                    editor.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_stepPerMile.getText()) || activeLevel.getSelectedItemPosition() == 0){
                    Toast.makeText(SignUpFitInfoActivity.this, "Please fill every item", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = getIntent();

                    CreateUserAndCredentialAsyncTask createUserAndCredentialAsyncTask = new CreateUserAndCredentialAsyncTask();
                    createUserAndCredentialAsyncTask.execute();

                    Intent intentPass = new Intent(SignUpFitInfoActivity.this, HomePageActivity.class);
                    startActivityForResult(intentPass, 1);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class findUserIdAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params){
            return ConnectToRESTWS.userCount();
        }
        @Override
        protected void onPostExecute(String result){
            userCount = Integer.parseInt(result);
        }
    }

    private class findUserCredentialIdAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params){
            return ConnectToRESTWS.userCredentialCount();
        }
        @Override
        protected void onPostExecute(String result){
            userCredentialCount = Integer.parseInt(result);
        }
    }

    private class CreateUserAndCredentialAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params){
            userCount = Integer.parseInt(ConnectToRESTWS.userCount());
            userCredentialCount = Integer.parseInt(ConnectToRESTWS.userCredentialCount());

            SharedPreferences spLoad = getSharedPreferences("sp_config", Context.MODE_PRIVATE);
            Appuser newUser = new Appuser();

            newUser.setUserid(userCount + 10001);
            newUser.setEmail(spLoad.getString("email", ""));
            newUser.setFirstname(spLoad.getString("firstname", ""));
            newUser.setSurname(spLoad.getString("lastname", ""));
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = sdf.parse(spLoad.getString("dob", ""));
                newUser.setDateofbirth(d);
            }catch (Exception e){
                e.printStackTrace();
            }
            newUser.setHeight(spLoad.getInt("height", 0));
            newUser.setWeight(spLoad.getInt("weight", 0));
            newUser.setAddress(spLoad.getString("address", ""));
            newUser.setPostcode(spLoad.getInt("postcode", 0));
            newUser.setGender(spLoad.getString("gender", ""));
            newUser.setStepsnumber(Integer.parseInt(et_stepPerMile.getText().toString()));

            newUser.setActivitylevel(spLoad.getInt("newUserActiveLevel", 0));

            // POST new user to DB
            ConnectToRESTWS.createUser(newUser);

            // GET new user's userid
            Usercredential newUc = new Usercredential();
            newUc.setCredentialid(userCredentialCount + 20001);
            newUc.setUserid(newUser);
            newUc.setDateofsignup(new Date());
            newUc.setUsername(spLoad.getString("username", ""));
            newUc.setPasswordhash(BuildMD5.getMD5(spLoad.getString("password", "")));

            // POST new user credential to DB
            ConnectToRESTWS.createUserCredential(newUc);

            ObjectSave.saveObject(getApplicationContext(), newUser, "currentUser");

            return "";
        }

        @Override
        protected void onPostExecute(String result){

        }
    }
}
