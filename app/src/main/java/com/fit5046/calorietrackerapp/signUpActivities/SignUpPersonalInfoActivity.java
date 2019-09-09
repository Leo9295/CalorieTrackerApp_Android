package com.fit5046.calorietrackerapp.signUpActivities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fit5046.calorietrackerapp.R;

import java.util.Calendar;

public class SignUpPersonalInfoActivity extends AppCompatActivity {

    EditText et_firstname;
    EditText et_lastname;
    EditText et_dob;
    EditText et_height;
    EditText et_weight;
    RadioGroup radioGroup_genderChoose;
    RadioButton rb_maleChoose;
    RadioButton rb_famaleChoose;
    Button nextBtn;
    String genderSelectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_person_info);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sp_config", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        et_firstname = (EditText) findViewById(R.id.et_firstname);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_dob = (EditText) findViewById(R.id.et_dob);
        et_height = (EditText) findViewById(R.id.et_height);
        et_weight = (EditText) findViewById(R.id.et_weight);
        radioGroup_genderChoose = (RadioGroup) findViewById(R.id.radioGroup_genderChoose);
        rb_maleChoose = (RadioButton) findViewById(R.id.rb_maleChoose);
        rb_famaleChoose = (RadioButton) findViewById(R.id.rb_famaleChoose);
        nextBtn = (Button) findViewById(R.id.nextBtnSign2);

        et_dob.setInputType(InputType.TYPE_NULL);
        et_dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    showDatePickDialog();
            }
        });

        radioGroup_genderChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectRadioBtn();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_firstname.getText())
                    || TextUtils.isEmpty(et_lastname.getText())
                    || TextUtils.isEmpty(et_dob.getText())
                    || TextUtils.isEmpty(et_height.getText())
                    || TextUtils.isEmpty(et_weight.getText())
                    || radioGroup_genderChoose.getCheckedRadioButtonId() == -1){
                    Toast.makeText(SignUpPersonalInfoActivity.this, "Please fill every item", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(SignUpPersonalInfoActivity.this, SignUpAddressInfoActivity.class);

                    editor.putString("firstname", et_firstname.getText().toString());
                    editor.putString("lastname", et_lastname.getText().toString());
                    editor.putString("dob", et_dob.getText().toString()+" 0:00:00");
                    editor.putInt("height", Integer.parseInt(et_height.getText().toString()));
                    editor.putInt("weight", Integer.parseInt(et_weight.getText().toString()));
                    editor.putString("gender", genderSelectText.substring(0, 1));
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

    private void showDatePickDialog(){
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(SignUpPersonalInfoActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                et_dob.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void selectRadioBtn(){
        RadioButton selectRadioBtn = (RadioButton)findViewById(radioGroup_genderChoose.getCheckedRadioButtonId());
        genderSelectText = selectRadioBtn.getText().toString();
    }

}
