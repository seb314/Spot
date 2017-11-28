package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import nrdzs.cs465.illinois.edu.spot.R;

public class MainActivity extends CustomActivity {

    /*Expandable Listview Global Vars*/
    List<String> libraryNames;
    HashMap<String, List<String>> floorNamesMap;

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


        // Expandable ListView of Libraries
       ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // create library lists
        prepareListData();

        // setting list adapter
        final ExpandableListAdapter adapter = new ExpandableListAdapter(this, libraryNames, floorNamesMap);
        expListView.setAdapter(adapter);

        // create onclick handler for Grainger 2nd floor
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                final String library = (String) adapter.getGroup(groupPosition);
                final String floor = (String) adapter.getChild(groupPosition, childPosition);

                // store selected child
                final String selected = (String) adapter.getChild(groupPosition, childPosition);

                if (library.equals("Grainger") && floor.equals("Second Floor")) {
                    // change screens when child is clicked
                    Intent intent = new Intent(MainActivity.this, FloorAndPhotoActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });

    }


    // Populate the library list
    private void prepareListData() {
        libraryNames = new ArrayList<String>();
        floorNamesMap = new HashMap<String, List<String>>();

        // Adding Library Titles
        libraryNames.add("UGL");
        libraryNames.add("Grainger");
        libraryNames.add("Main Library");
        libraryNames.add("ACES");
        libraryNames.add("Law");
        libraryNames.add("Communications");

        // Adding child data to each library
        List<String> graingerFloors = new ArrayList<String>();
        graingerFloors.add("Basement");
        graingerFloors.add("First Floor");
        graingerFloors.add("Second Floor");
        graingerFloors.add("Third Floor");
        graingerFloors.add("Fourth Floor");

        List<String> uglFloors = new ArrayList<String>();
        uglFloors.add("Lower Level");
        uglFloors.add("Upper Level");

        List<String> mainlibFloors = new ArrayList<String>();
        mainlibFloors.add("First Floor");
        mainlibFloors.add("Second Floor");
        mainlibFloors.add("Third Floor");
        mainlibFloors.add("Fourth Floor");

        List<String> acesFloors = new ArrayList<String>();
        acesFloors.add("Basement");
        acesFloors.add("First Floor");
        acesFloors.add("Second Floor");
        acesFloors.add("Third Floor");
        acesFloors.add("Fourth Floor");
        acesFloors.add("Fifth Floor");

        List<String> lawFloors = new ArrayList<String>();
        lawFloors.add("First Floor");

        List<String> commFloors = new ArrayList<String>();
        commFloors.add("Basement");
        commFloors.add("First Floor");
        commFloors.add("Second Floor");

        floorNamesMap.put(libraryNames.get(0), uglFloors); // Header, Child data
        floorNamesMap.put(libraryNames.get(1), graingerFloors);
        floorNamesMap.put(libraryNames.get(2), mainlibFloors);
        floorNamesMap.put(libraryNames.get(3), acesFloors);
        floorNamesMap.put(libraryNames.get(4), lawFloors);
        floorNamesMap.put(libraryNames.get(5), commFloors);
    }


}
