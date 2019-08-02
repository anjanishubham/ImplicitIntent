package com.lovelycoding.intent.handler;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.lovelycoding.intent.SMS;
import com.lovelycoding.intent.contact.Contact;
import com.lovelycoding.intent.MainActivity;
import com.lovelycoding.intent.permission.Permission;
import com.lovelycoding.intent.phone.Calling;
import com.lovelycoding.intent.websearch.WebSearch;

import static android.Manifest.*;

public class MySelectorHandler {
    Context context;

    public MySelectorHandler(Context context) {
        this.context = context;
    }

    public void webSearch()
    {
        MainActivity.inputData.setInput("");
        Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();
        context.startActivity(new Intent(context,WebSearch.class));
    }

    public void webView()
    {
        MainActivity.inputData.setInput("");

        context.startActivity(new Intent(context,WebSearch.class));

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void viewContact()
    {

       context.startActivity(new Intent(context.getApplicationContext(),Contact.class));


    }

    public void editContact() {

        Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();

    }

    public void phoneCall() {
        MainActivity.inputData.setInput("");

        context.startActivity(new Intent(context, Calling.class));


    }

    public void dailNumber() {
        MainActivity.inputData.setInput("");

        context.startActivity(new Intent(context, Calling.class));

       // Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendSms()
    {

        context.startActivity(new Intent(context, SMS.class));
        Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();



    }

    public void sentEmail() {
        Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openPhoto()
    {
        if(!Permission.isGALLERY_PERMISSION_GRANTED)
        {
           Permission.getPhotoPermission(context);

        }
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        context.startActivity(intent);

    }
}
