package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import nrdzs.cs465.illinois.edu.spot.R;

public class MainActivity extends CustomActivity {

    public void startGallery(){

        Button but = (Button) findViewById(R.id.button2);
        but.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(MainActivity.this, photo_gallary.class);
                startActivity(nextIntent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraButton = (Button) findViewById(R.id.camera_button);
        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        mCameraButton.setTypeface(mFontAwesomeTypeface);

        mImageView = (ImageView) findViewById(R.id.image_taken);

        startGallery();


    }


}
