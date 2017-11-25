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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // example for binding the camera button to the dispatch picture intent
        mCameraButton = (Button) findViewById(R.id.camera_button);
        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        mCameraButton.setTypeface(mFontAwesomeTypeface);    // set to use font awesome

        // bind an imageview so we could see the taken picture
        mImageView = (ImageView) findViewById(R.id.image_taken);

        // TODO remove this block once the correct link to detailed image view exists
        Button button_go_to_detailed_image_view =
                (Button) findViewById(R.id.shortcut_to_detailed_photo_screen_button);
        button_go_to_detailed_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch_detailed_photo_activity(v);
            }
        });
        button_go_to_detailed_image_view.setTypeface(mFontAwesomeTypeface);
    }






}
