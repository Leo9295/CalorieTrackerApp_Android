package com.fit5046.calorietrackerapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fit5046.RESTfulWSEntities.Appuser;
import com.fit5046.calorietrackerapp.signUpActivities.SignUpLoginInfoActivity;
import com.fit5046.commonTools.BuildMD5;
import com.fit5046.commonTools.ConnectToRESTWS;
import com.fit5046.commonTools.ObjectSave;
import com.fit5046.commonTools.PictureWork;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_pwd;
    private ImageView iv_main_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set all buttons
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);
        // Get the username & password from view
        et_username = (EditText) findViewById(R.id.et_main_usernameInput);
        et_pwd = (EditText) findViewById(R.id.et_main_passwordInput);

        // Login button listener(Start login process)
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = et_username.getText().toString().trim();
                String pwdInput = et_pwd.getText().toString();
                // Check Username & password are not empty
                // If empty, show text to make a warning
                if (TextUtils.isEmpty(usernameInput)){
                    Toast.makeText(MainActivity.this, "Please input your username!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(pwdInput)){
                    Toast.makeText(MainActivity.this, "Please input your password!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    UserLoginAsyncTask userLoginAsyncTask = new UserLoginAsyncTask();
                    userLoginAsyncTask.execute();
                }

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpLoginInfoActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private class UserLoginAsyncTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void...params){
            String usernameInput = et_username.getText().toString().trim();
            String pwdInput = et_pwd.getText().toString();
            String hashedpwd = BuildMD5.getMD5(pwdInput);
            return ConnectToRESTWS.findUserByUsernameAndPwd(usernameInput, hashedpwd);
        }

        @Override
        protected void onPostExecute(String loginUserInfo){
            if(loginUserInfo.length() > 10){
                // Save info of current user who has login system
                // Only for userid and username---------pwd?
                Appuser loginUser = getInfoInJson(loginUserInfo);
                if (loginUser != null) {
                    ObjectSave.saveObject(getApplicationContext(), loginUser, "currentUser");
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivityForResult(intent, 1);
                } else
                    return;
            } else {
                Toast.makeText(MainActivity.this, "The input of username or password might have error!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        private Appuser getInfoInJson(String userInfo){
           try{
               JSONArray jsonArray = new JSONArray(userInfo);
               Appuser loginUser = new Appuser();
               String str = jsonArray.optString(0);
               JSONObject jsonObject = new JSONObject(str);
               JSONObject jsonUser = jsonObject.getJSONObject("userid");
               // create a new object of login user
               loginUser.setUserid(jsonUser.getLong("userid"));
               loginUser.setFirstname(jsonUser.getString("firstname"));
               loginUser.setSurname(jsonUser.getString("surname"));
               loginUser.setEmail(jsonUser.getString("email"));
               try {
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   Date d = sdf.parse(jsonUser.getString("dateofbirth"));
                   loginUser.setDateofbirth(d);
               }catch (Exception e){
                   e.printStackTrace();
               }
               loginUser.setGender(jsonUser.getString("gender"));
               loginUser.setActivitylevel(jsonUser.getInt("activitylevel"));
               loginUser.setHeight(jsonUser.getInt("height"));
               loginUser.setWeight(jsonUser.getInt("weight"));
               loginUser.setPostcode(jsonUser.getInt("postcode"));
               loginUser.setAddress(jsonUser.getString("address"));
               loginUser.setStepsnumber(jsonUser.getInt("stepsnumber"));

               return loginUser;
           } catch (Exception e){
               e.printStackTrace();
               return null;
           }

        }
    }
}
