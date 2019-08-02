package com.lovelycoding.intent.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Permission {
    public static int MY_PERMISSIONS_REQUEST_READ_CONTACTS=20;
    public static  int MY_PERMISSION_REQUEST_WRITE_CONTACTS=30;
    public static  int MY_PERMISSION_REQUEST_PHONE_CALL=40;
    public static  int MY_PERMISSION_REQUEST_WEB_VIEW=60;
    public static  int MY_PHOTO_TAGGING_PERMISSIONS =50;

    public static boolean isGALLERY_PERMISSION_GRANTED=false;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void getPhotoPermission(Context context)
    {
        if(context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            isGALLERY_PERMISSION_GRANTED=true;
        }
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions((Activity)context,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PHOTO_TAGGING_PERMISSIONS);


            } else {
                ActivityCompat.requestPermissions((Activity)context,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PHOTO_TAGGING_PERMISSIONS);
            }
        }
    }
}
