package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sebastian on 24.11.17.
 */

public class Common {
    /**
     * ugly hack, just to avoid re-typing while things are not wired together yet
     */
    public static final int REQUEST_TAKE_PHOTO = 1;




    static public void setupCameraButton(final Activity a){
        Button cameraButton = (Button) a.findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(a);
            }
        });

        Typeface mFontAwesomeTypeface = Typeface.createFromAsset( a.getAssets(), "fontawesome-webfont.ttf" );


        cameraButton.setTypeface(mFontAwesomeTypeface);    // set to use font awesome
    }

    static public void setupBackButton(final Activity a){
        Button cameraButton = (Button) a.findViewById(R.id.back_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.finish();
            }
        });

        Typeface mFontAwesomeTypeface = Typeface.createFromAsset( a.getAssets(), "fontawesome-webfont.ttf" );


        cameraButton.setTypeface(mFontAwesomeTypeface);    // set to use font awesome
    }


    static protected void dispatchTakePictureIntent(Activity a) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(a.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(a);
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(a,
                        "edu.illinois.cs465.nrdzs.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                a.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    static private File createImageFile(Activity a) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = a.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }
}
