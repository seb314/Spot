package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sebastian on 24.11.17.
 *
 * Put code here that is needed by multiple activities.  Having it in the base class CustomActivity
 * does not work because extending CustomActivity is impossible if one needs to extend another
 * base class too
 *
 */

public class Common {
    /**
     * ugly hack, just to avoid re-typing while things are not wired together yet
     */
    public static final int REQUEST_TAKE_PHOTO = 1;


    public static void setupButton(final Activity parentActivity, int id, View.OnClickListener onClickListener){
        Button b = (Button) parentActivity.findViewById(id);
        b.setOnClickListener(onClickListener);

        Typeface mFontAwesomeTypeface = Typeface.createFromAsset( parentActivity.getAssets(), "fontawesome-webfont.ttf" );
        b.setTypeface(mFontAwesomeTypeface);    // set to use font awesome
    }


    static public void setupCameraButton(final Activity a){
        setupButton(a, R.id.camera_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(a);
            }
        });
    }

    // TODO this is currently code duplication with the (main?) activity class.
    // However, multiple activities need it, so its better here than there
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

    public static void launch_detailed_photo_activity(Activity parent) {
        Intent intent = new Intent(parent, DetailedPhotoActivity.class);
        parent.startActivity(intent);
    }

    public static void makeFullScreen(Activity parent){
            parent.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        try
        {
            parent.getActionBar().hide();
        }
        catch (NullPointerException e){}
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


}
