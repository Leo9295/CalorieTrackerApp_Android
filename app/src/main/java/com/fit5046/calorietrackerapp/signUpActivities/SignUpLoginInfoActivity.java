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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fit5046.calorietrackerapp.R;
import com.fit5046.commonTools.BuildMD5;
import com.fit5046.commonTools.ConnectToRESTWS;

public class SignUpLoginInfoActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_password;
    EditText et_passwordConfirm;
    EditText et_email;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login_info);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sp_config", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_passwordConfirm = (EditText) findViewById(R.id.et_passwordConfirm);
        et_email = (EditText) findViewById(R.id.et_email);
        nextBtn = (Button) findViewById(R.id.nextBtnSign1);

        et_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    UsernameCheckAsyncTask usernameCheckAsyncTask = new UsernameCheckAsyncTask();
                    usernameCheckAsyncTask.execute();
                }
            }
        });

        et_passwordConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    if (!et_password.getText().toString().trim().equals(et_passwordConfirm.getText().toString().trim())) {
                        Toast.makeText(SignUpLoginInfoActivity.this, "Two password entries are not the same", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    EmailCheckAsyncTask emailCheckAsyncTask = new EmailCheckAsyncTask();
                    emailCheckAsyncTask.execute();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_username.getText())
                    || TextUtils.isEmpty(et_password.getText())
                    || TextUtils.isEmpty(et_passwordConfirm.getText())
                    || TextUtils.isEmpty(et_email.getText())) {
                    Toast.makeText(SignUpLoginInfoActivity.this, "Please fill every item", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(SignUpLoginInfoActivity.this, SignUpPersonalInfoActivity.class);


                    editor.putString("username",et_username.getText().toString());
                    editor.putString("password",et_password.getText().toString());
                    editor.putString("email", et_email.getText().toString());
                    editor.commit();

                    startActivityForResult(intent, 1);
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

    private class UsernameCheckAsyncTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... params) {
            String username = et_username.getText().toString();
            if(!(username.isEmpty() || username == null))
                return ConnectToRESTWS.findUserByUsername(username);
            else
                return "";
        }

        @Override
        protected void onPostExecute(String result){
            if(result.length() > 10){
                Toast.makeText(SignUpLoginInfoActivity.this, "Username has existed! Please retry", Toast.LENGTH_LONG).show();
                nextBtn.setEnabled(Boolean.FALSE);
            } else {
                nextBtn.setEnabled(Boolean.TRUE);
            }
        }
    }

    private class EmailCheckAsyncTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... params){
            String email = et_email.getText().toString();
            if(!(email.isEmpty() || email == null))
                return ConnectToRESTWS.findUserByEmail(email);
            else
                return "";
        }

        @Override
        protected void onPostExecute(String result){
            if(result.length() > 10){
                Toast.makeText(SignUpLoginInfoActivity.this, "Email has existed! Please retry", Toast.LENGTH_LONG).show();
                nextBtn.setEnabled(Boolean.FALSE);
            } else {
                nextBtn.setEnabled(Boolean.TRUE);
            }
        }
    }

}
