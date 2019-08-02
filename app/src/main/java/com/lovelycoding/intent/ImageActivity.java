package com.lovelycoding.intent;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.lovelycoding.intent.databinding.ActivityImageBinding;
import com.lovelycoding.intent.permission.Permission;

public class ImageActivity extends AppCompatActivity
{
    static int IMAGE_RESULT_SET=1;
    ActivityImageBinding imageBinding;
    AppCompatImageView imageView;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image);
        imageBinding= DataBindingUtil.setContentView(this,R.layout.activity_image);

        imageView=imageBinding.imgView;
        bt=imageBinding.buttonLoadPicture;

        bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view)
            {
                if(!Permission.isGALLERY_PERMISSION_GRANTED) {
                    Permission.getPhotoPermission(ImageActivity.this);
                }
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,IMAGE_RESULT_SET);
              //  contextstartActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_RESULT_SET && data!=null)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
                Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            //  ImageView imageView = (ImageView) findViewById(R.id.imgView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imageView.setImageBitmap(bitmap);
        }
    }
}
