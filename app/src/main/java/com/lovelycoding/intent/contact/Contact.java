package com.lovelycoding.intent.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.lovelycoding.intent.MainActivity;
import com.lovelycoding.intent.R;
import com.lovelycoding.intent.databinding.ActivityContactBinding;
import com.lovelycoding.intent.handler.MyHandler;

public class Contact extends AppCompatActivity
{
    ActivityContactBinding contactBinding;
    Toolbar toolbar;
    TextInputEditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact);
        contactBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact);
        toolbar = contactBinding.contactToolbar;
        editText=contactBinding.tiet;
        setSupportActionBar(toolbar);
        toolbar.setTitle("phone");
        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                editText.setText("");
                startActivity(new Intent(view.getContext(), MainActivity.class));
                finish();
            }
        });

        contactBinding.setInputData(MainActivity.inputData);
        MyHandler myHandler=new MyHandler(this);
        contactBinding.setMyHandler(myHandler);

    }
}
