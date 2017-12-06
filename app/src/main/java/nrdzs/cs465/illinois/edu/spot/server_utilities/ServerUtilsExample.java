package com.example.nsg.testspotserver;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServerUtilsExample {
    // a floor location
    public int floorLocation = 2;

    // the index corresponding to the currently displayed image
    public int index = 0;

    // the raw JSON object that comes back when downloading from the server
    public JSONArray jsonArray;

    // the raw decoded bitmap data
    public ArrayList<Bitmap> bmArray = new ArrayList<>();

    // the timestamp info, saved as strings
    public ArrayList<String> dateInfo;

    // the filename that the server will save the image as
    public String filename;

    // the file that will be uploaded
    File imgFile;

    public void uploadAPhoto(int floorLocation) {
        this.floorLocation = floorLocation;
        
        try {
            // create a temp file to upload
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            filename = "JPEG_" + timestamp + "_";
            Log.d("FILENAME:", filename);
            imgFile = File.createTempFile(filename, ".jpg",
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES));

            // write into the file here with bitmap data from camera, etc.

            // upload the photo
            new UploadOperation().execute();
        } catch (IOException e) {
            Log.e("ERROR", "IOException from uploading images!");
        }
    }

    public void downloadPhotos(int floorLocation) {
        this.floorLocation = floorLocation;
        new DownloadOperation().execute();
    }

    private class DownloadOperation extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // do some stuff with ServerUtilsExample's UI before downloading
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // grab photos from the Right Side of the library
                jsonArray = ServerUtils.downloadImagesFromServer(floorLocation);
            } catch (IOException e) {
                Log.e("ERROR", "Downloading images caused IOException!");
                Log.e("MESSAGE", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // get the image data and parse it correctly
            bmArray = ServerUtils.extractBitmapData(jsonArray);
            dateInfo = ServerUtils.extractDateInfo(jsonArray);
            Log.e("DATE INFO", dateInfo.toString());

            // do some stuff with ServerUtilsExample's UI after downloading
        }
    }

    private class UploadOperation extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // post a photo to the Right Side of the library
                ServerUtils.uploadImageToServer(imgFile, filename, floorLocation);
            } catch (IOException e) {
                Log.e("ERROR", "IOException from uploading images!");
            }
            return null;
        }
    }
}
