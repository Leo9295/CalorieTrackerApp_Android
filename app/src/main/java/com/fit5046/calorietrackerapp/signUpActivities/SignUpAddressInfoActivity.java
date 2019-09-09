package com.fit5046.calorietrackerapp.signUpActivities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fit5046.calorietrackerapp.R;

public class SignUpAddressInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_address_info);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("sp_config", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        final EditText et_address = (EditText) findViewById(R.id.et_address);
        final EditText et_postcode = (EditText) findViewById(R.id.et_postcode);
        final Button nextBtn = (Button) findViewById(R.id.nextBtnSign3);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_address.getText()) || TextUtils.isEmpty(et_postcode.getText())){
                    Toast.makeText(SignUpAddressInfoActivity.this, "Please fill every item", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(SignUpAddressInfoActivity.this, SignUpFitInfoActivity.class);

                    editor.putString("address", et_address.getText().toString());
                    editor.putInt("postcode", Integer.parseInt(et_postcode.getText().toString()));
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
}
