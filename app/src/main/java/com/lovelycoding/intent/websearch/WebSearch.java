package com.lovelycoding.intent.websearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.lovelycoding.intent.MainActivity;
import com.lovelycoding.intent.R;
import com.lovelycoding.intent.databinding.ActivityWebSearchBinding;
import com.lovelycoding.intent.handler.MyHandler;

public class WebSearch extends AppCompatActivity
{

    private ActivityWebSearchBinding searchBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_web_search);
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_search);
        MyHandler handler=new MyHandler(this);
        searchBinding.setMyHandler(handler);
        searchBinding.setInputData(MainActivity.inputData);
    }
}
