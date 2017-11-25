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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import nrdzs.cs465.illinois.edu.spot.R;

public class MainActivity extends CustomActivity {

    /*Expandable List View*/

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

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


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding Library Titles
        listDataHeader.add("Grainger");
        listDataHeader.add("UGL");
        listDataHeader.add("Main Library");
        listDataHeader.add("ACES");

        // Adding child data to each library
        List<String> Grainger = new ArrayList<String>();
        Grainger.add("Basement");
        Grainger.add("First Floor");
        Grainger.add("Second Floor");
        Grainger.add("Third Floor");
        Grainger.add("Fourth Floor");

        List<String> UGL = new ArrayList<String>();
        UGL.add("Lower Level");
        UGL.add("Upper Level");

        List<String> MainLibrary = new ArrayList<String>();
        MainLibrary.add("First Floor");
        MainLibrary.add("Second Floor");
        MainLibrary.add("Third Floor");
        MainLibrary.add("Fourth Floor");

        List<String> ACES = new ArrayList<String>();
        ACES.add("Basement");
        ACES.add("First Floor");
        ACES.add("Second Floor");
        ACES.add("Third Floor");
        ACES.add("Fourth Floor");
        ACES.add("Fifth Floor");


        listDataChild.put(listDataHeader.get(0), Grainger); // Header, Child data
        listDataChild.put(listDataHeader.get(1), UGL);
        listDataChild.put(listDataHeader.get(2), MainLibrary);
        listDataChild.put(listDataHeader.get(3), ACES);
    }


}
