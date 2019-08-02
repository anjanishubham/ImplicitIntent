package com.lovelycoding.intent.handler;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;
import com.lovelycoding.intent.MainActivity;
import com.lovelycoding.intent.R;
import com.lovelycoding.intent.data.InputData;
import com.lovelycoding.intent.data.SmsData;

import static android.content.Intent.ACTION_CALL;
import static android.content.Intent.ACTION_DIAL;
import static android.content.Intent.ACTION_EDIT;
import static android.content.Intent.ACTION_MAIN;
import static android.content.Intent.ACTION_SENDTO;
import static android.content.Intent.ACTION_VIEW;
import static android.content.Intent.ACTION_WEB_SEARCH;

public class MyHandler {
    private final String TAG = MyHandler.class.getName();
    Context context;
    private final int CALL_PERMISSION = 10;
    private final int CONTACT_PERMISSION=20;
    private final int SMS_PERMISSION=30;
    private boolean isContactPermissionGranted=false;
    private boolean isSMSPermissionGranted=false;

    public MyHandler(Context context) {
        this.context = context;
    }

    public void webViewSearch() {
        try {
            String str = (MainActivity.inputData.getInput());
            // Toast.makeText(context, "searchstring is "+str, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, str);
            //Intent intent = new Intent(ACTION_VIEW, Uri.parse("https://www.learn2crack.com/2016/06/android-data-binding-example.html"));
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "search string is ", Toast.LENGTH_SHORT).show();
            e.getMessage();
        }
    }

    public void webView() {
        String str = (MainActivity.inputData.getInput());
        // Toast.makeText(context, "searchstring is "+str, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ACTION_VIEW);
        intent.putExtra(SearchManager.QUERY, str);
        //Intent intent = new Intent(ACTION_VIEW, Uri.parse("https://www.learn2crack.com/2016/06/android-data-binding-example.html"));
        context.startActivity(intent);

    }

    public void dailNumber() {
        String str = "tel:" + (MainActivity.inputData.getInput());
        Intent intent = new Intent(ACTION_DIAL, Uri.parse(str));
        context.startActivity(intent);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void viewSMS()
    {

        if(isContactPermissionGranted)
        {
            Intent intent=new Intent();
            intent.setAction(ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
            //intent.setData(Uri.parse("sms:"+""));
            context.startActivity(intent);
        }
        else {
            Intent intent=new Intent();
            intent.setAction(ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
            //intent.setData(Uri.parse("sms:"+""));
            context.startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sentSMS()
    {
        if(isContactPermissionGranted)
        {
            String phone=MainActivity.smsData.getPhoneNumber();
            String text= MainActivity.smsData.getSmsText();
            Intent intent=new Intent();
            intent.setAction(ACTION_SENDTO);
            intent.setData(Uri.parse("sms:"+""));
            intent.putExtra("address",phone);
            intent.putExtra("sms_body",text);
            context.startActivity(intent);
        }
        else {
            getSMSPermission();
            String phone=MainActivity.smsData.getPhoneNumber();
            String text= MainActivity.smsData.getSmsText();
            Intent intent=new Intent();
            intent.setAction(ACTION_SENDTO);
            intent.setData(Uri.parse("sms:"+""));
            intent.putExtra("address",phone);
            intent.putExtra("sms_body",text);
            intent.putExtra("sms_title","anjani");
            intent.putExtra("sms_subject","testing");
            context.startActivity(intent);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void callNumber(View view)
    {
        try {
            String str = "tel:" + (MainActivity.inputData.getInput());
            Intent intent = new Intent(ACTION_CALL, Uri.parse(str));
            if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                // permission is granted
                Log.i(TAG, "permission granted ");
                context.startActivity(intent);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CALL_PHONE))
                {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION);

                    // permission is refused and it come to 2nd time
                   /* ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION);
                         Snackbar.make(view, "Need permission for phone call ", Snackbar.LENGTH_LONG).setAction("enable", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION);
                              }
                    });*/
                } else {
                    // ask for permission
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION);
                }
            }
        }catch (Exception e)
        {
            Log.e(TAG,e.getLocalizedMessage());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void viewContact()
    {
        if(isContactPermissionGranted)
        {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(ContactsContract.Contacts.CONTENT_URI);
            context.startActivity(intent);
        }
        else {
            getContactPerminssion();
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(ContactsContract.Contacts.CONTENT_URI);
            context.startActivity(intent);
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void editContact()
    {
        if(isContactPermissionGranted)
        {
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_EDIT);
            intent.putExtra(ContactsContract.Intents.Insert.PHONE,new InputData().getInput().toString());
            context.startActivity(intent);
        }
        else {
            getContactPerminssion();
            Intent intent=new Intent();
            intent.setAction(ACTION_EDIT);
           // intent.putExtra(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,_ID));
            context.startActivity(intent);
        }



    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getContactPerminssion()
    {
        if(context.checkSelfPermission(Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
        {
            isContactPermissionGranted=true;


            Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();
        }
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_CONTACTS))
            {
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_CONTACTS},CONTACT_PERMISSION);

            }
            else
            {
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_CONTACTS},CONTACT_PERMISSION);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getSMSPermission()
    {
        if(context.checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
        {
            isSMSPermissionGranted=true;


            Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();
        }
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.SEND_SMS))
            {
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.SEND_SMS},SMS_PERMISSION);

            }
            else
            {
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.SEND_SMS},SMS_PERMISSION);
            }
        }
    }
@RequiresApi(api = Build.VERSION_CODES.M)
    public void getSMSVIewPermission()
    {
        if(context.checkSelfPermission(Manifest.permission.READ_SMS)== PackageManager.PERMISSION_GRANTED)
        {
            isSMSPermissionGranted=true;


            Toast.makeText(context, "webSearch clicked ", Toast.LENGTH_SHORT).show();
        }
        else {
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_SMS))
            {
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_SMS},SMS_PERMISSION);

            }
            else
            {
                ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.READ_SMS},SMS_PERMISSION);
            }
        }
    }


}
