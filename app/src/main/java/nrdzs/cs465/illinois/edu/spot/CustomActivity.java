package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomActivity extends Activity {
    // constants
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_PHOTO_LOCATION = 2;

    // member data shared by activities and it's inheritors
    protected Button mCameraButton;
    protected String mCurrentPhotoPath;
    protected int mCurrentLocation;
    protected Typeface mFontAwesomeTypeface;
    protected Bitmap mCurrentPhoto;
    private GlobalApplicationVaribles glob = GlobalApplicationVaribles.getInstance(CustomActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFontAwesomeTypeface = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
    }

    protected void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "edu.illinois.cs465.nrdzs.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            // launch the floor selection activity
            Intent launchConfirmPhoto = new Intent(this, ConfirmPhotoLocationActivity.class);
            startActivityForResult(launchConfirmPhoto, REQUEST_PHOTO_LOCATION);
        }

        else if(requestCode == REQUEST_PHOTO_LOCATION && resultCode == Activity.RESULT_OK){
            // load the photo location, save it as the mCurrentLocation
            Log.d("SELECTION", data.getStringExtra("USER_SELECTION"));
            mCurrentLocation = Integer.parseInt(data.getStringExtra("USER_SELECTION"));

        }

    }
}
