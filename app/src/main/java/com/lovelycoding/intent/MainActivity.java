package com.lovelycoding.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;

import com.lovelycoding.intent.data.InputData;
import com.lovelycoding.intent.data.SmsData;
import com.lovelycoding.intent.databinding.ActivityMainBinding;
import com.lovelycoding.intent.handler.MyHandler;
import com.lovelycoding.intent.handler.MySelectorHandler;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding dataBinding;
    public static InputData inputData;
    public static SmsData smsData;
    public static MyHandler myHandler;
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
       // dataBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        dataBinding=DataBindingUtil.setContentView(this,R.layout.activity_main);
       // myHandler=new MyHandler(this);
        toolbar=dataBinding.toolbar;
        setSupportActionBar(toolbar);
        toolbar.setTitle("Intent Event List");
        toolbar.setTitleTextColor(Color.WHITE);
        inputData=new InputData();
        smsData=new SmsData("","");
        MySelectorHandler selectorHandler=new MySelectorHandler(this);
        dataBinding.setMySelectorHandler(selectorHandler);



        /* inputData=new InputData();
        MyHandler handler= new MyHandler(this);
        dataBinding.setInputData(inputData);
        dataBinding.setHandler(handler);
*/
    }
}
