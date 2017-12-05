package nrdzs.cs465.illinois.edu.spot;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Float;

import android.widget.ExpandableListView;
import android.widget.TextView;

public class MainActivity extends CustomActivity {


    /*Expandable Listview Global Vars*/
    List<String> libraryNames;
    HashMap<String, List<String>> floorNamesMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // change header font to Raleway
//        TextView find_spot = (TextView) findViewById(R.id.find_a_spott);
//        find_spot.setTypeface(FontManager.getTypeface(this, FontManager.RALEWAY));

        // binding the camera button to the dispatch picture intent
        mCameraButton = (Button) findViewById(R.id.camera_button);
        mCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        // set camera button font to font awesome
        mCameraButton.setTypeface(mFontAwesomeTypeface);

        // get battery percentage for header
        String battery_percentage = getBatteryPercentage(this) + "%";
        TextView bp_textview = (TextView) findViewById(R.id.battery_percentage);
        bp_textview.setText(battery_percentage);

        // set battery icon based on percentage
        TextView battery_icon = (TextView) findViewById(R.id.battery_icon);
        battery_icon.setTypeface(mFontAwesomeTypeface);
        battery_icon.setText(getBatteryIcon(getBatteryPercentage(this)));

        // Expandable ListView of Libraries
       ExpandableListView expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // create library lists
        prepareListData();

        // setting list adapter
        final ExpandableListAdapter adapter = new ExpandableListAdapter(this, libraryNames, floorNamesMap);
        expListView.setAdapter(adapter);

        // create onclick handler for Grainger 2nd floor
        secondFloorOnClick(expListView, adapter);

        // set to fullscreen
        Common.makeFullScreen(this);

    }

    private int getBatteryIcon(int percentage){
        int icon;

        if (percentage > 80){
            icon = R.string.icon_battery_full;
        }
        else if (percentage > 60){
            icon = R.string.icon_battery_75;
        }
        else if (percentage > 40){
            icon = R.string.icon_battery_50;
        }
        else {
            icon = R.string.icon_battery_25;
        }

        return icon;
    }

    private int getBatteryPercentage(Context context){

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        int percentage = (int) Math.floor((level / (double)scale) * 100);

        return percentage;
    }


    // helper function to create onclick handler for Grainger 2nd floor
    private void secondFloorOnClick(ExpandableListView expListView, final ExpandableListAdapter adapter) {

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                final String library = (String) adapter.getGroup(groupPosition);
                final String floor = (String) adapter.getChild(groupPosition, childPosition);

                // store selected child
                final String selected = (String) adapter.getChild(groupPosition, childPosition);

                if (library.equals("Grainger") && floor.equals("Second Floor")) {
                    // change screens when child is clicked
                    Intent intent = new Intent(MainActivity.this, PhotoGallery.class);
                    startActivity(intent);
                }

                return true;
            }
        });
    }

    // Helper function to  populate the library list
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
