package com.melborp.teflawn.activity.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.balysv.materialripple.MaterialRippleLayout;
import com.melborp.teflawn.R;

import utils.Tools;

public class PhotoActivity extends AppCompatActivity {

    ImageView uploaded;
    MaterialRippleLayout nxtBtn;
    TextView txtPhoto;

    int CAMERA_PIC_REQUEST = 123;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(this, "welcome to photo activity", Toast.LENGTH_SHORT).show();
        Tools.setSystemBarColor(this, R.color.grey_10);
        setContentView(R.layout.fragment_upload_photo);


        uploaded = findViewById(R.id.img_upload);
        nxtBtn = findViewById(R.id.rippleBook);
        txtPhoto = findViewById(R.id.txtPhotoUpload);

        uploaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

            }
        });

        nxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView imageview = findViewById(R.id.img_upload); //sets imageview as the bitmap
            imageview.setImageBitmap(image);
            txtPhoto.setVisibility(View.GONE);

        }

    }
}
