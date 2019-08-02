package com.lovelycoding.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.lovelycoding.intent.data.SmsData;
import com.lovelycoding.intent.databinding.ActivitySmsBinding;
import com.lovelycoding.intent.handler.MyHandler;

public class SMS extends AppCompatActivity
{
    ActivitySmsBinding smsBinding;
    Toolbar smsToolbar;
    TextInputEditText phoneNumer,smsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sms);
        smsBinding = DataBindingUtil.setContentView(this, R.layout.activity_sms);
        phoneNumer=smsBinding.tiePhoneNumber;
        smsText=smsBinding.tieSmsString;
        smsToolbar=smsBinding.smsToolbar;
        setSupportActionBar(smsToolbar);

        MyHandler myHandler = new MyHandler(this);
        smsBinding.setMyHandler(myHandler);
        smsBinding.setSmsData(MainActivity.smsData);






        smsToolbar.setTitle("SMS");
        smsToolbar.setTitleTextColor(Color.WHITE);
        smsToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        smsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }
}
