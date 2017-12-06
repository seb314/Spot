package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
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

import nrdzs.cs465.illinois.edu.spot.backend.Photo;

import static nrdzs.cs465.illinois.edu.spot.CustomActivity.REQUEST_PHOTO_LOCATION;
import static nrdzs.cs465.illinois.edu.spot.CustomActivity.REQUEST_TAKE_PHOTO;

/**
 * Created by sebastian on 06.12.17.
 */

public class CameraButtonHandler {

    Activity parent;
    String mCurrentPhotoPath;

    public CameraButtonHandler(Activity parent){
        this.parent = parent;
    }

    public Button setupCameraButton(){
        Button b = Common.setupButton(parent, R.id.camera_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        return b;
    }


    protected void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(parent.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(parent,
                        "edu.illinois.cs465.nrdzs.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                parent.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = parent.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
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
            Intent launchConfirmPhoto = new Intent(parent, ConfirmPhotoLocationActivity.class);
            parent.startActivityForResult(launchConfirmPhoto, REQUEST_PHOTO_LOCATION);
        }

        else if(requestCode == REQUEST_PHOTO_LOCATION && resultCode == Activity.RESULT_OK){
            // load the photo location, save it as the mCurrentLocation
            int mCurrentLocation = data.getIntExtra(ConfirmPhotoLocationActivity.USER_SELECTION, 1);
            String area;
            switch (mCurrentLocation){
                case 0:
                    area = "left";
                    break;
                case 1:
                    area = "center";
                    break;
                case 2:
                    area = "right";
                    break;
                default:
                    area = "center";
            }
            Photo photo = new Photo(mCurrentPhotoPath, area, new Date().getTime());
            GlobalApplicationVaribles.getInstance(parent).addPhoto(photo);

        }

    }
}
