package nrdzs.cs465.illinois.edu.spot;

/**
 * Created by dash on 11/24/17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.lang.String;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _libraryNames; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _floors;

    public ExpandableListAdapter(Context context, List<String> libraryNames,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._libraryNames = libraryNames;
        this._floors = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._floors.get(this._libraryNames.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String floorNumber = (String) getChild(groupPosition, childPosition);

        // Inflate floor_info view
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.floor_info, null);
        }

        // Set floor names
        TextView floorNumberView = (TextView) convertView.findViewById(R.id.floorNumber);
        floorNumberView.setText(floorNumber);


        // Set noise level data
        String soundIcon;
        if (childPosition % 3 == 0){
            soundIcon = _context.getString(R.string.icon_loud_volume);
        }
        else if (childPosition % 2 == 0){
            soundIcon = _context.getString(R.string.icon_loud_volume);
        }
        else {
            soundIcon = _context.getString(R.string.icon_no_volume);
        }
        if (childPosition == 2){
            soundIcon = _context.getString(R.string.icon_no_volume);
        }


        TextView soundView = (TextView) convertView.findViewById(R.id.sound_icon);
        soundView.setText(soundIcon);
        soundView.setTypeface(FontManager.getTypeface(_context, FontManager.FONTAWESOME));


        // Simulate and set capacity level data
        final TextView capacityView = (TextView) convertView.findViewById(R.id.capacity_icon);

        if (Math.floor(Math.random() * 10) > 7){
            capacityView.setTextColor(_context.getResources().getColor(R.color.capacityRed));
        }
        else if (Math.floor(Math.random() * 10) < 3){
            capacityView.setTextColor(_context.getResources().getColor(R.color.capacityYellow));
        }
        else {
            capacityView.setTextColor(_context.getResources().getColor(R.color.capacityGreen));
        }
        capacityView.setTypeface(FontManager.getTypeface(_context, FontManager.FONTAWESOME));

        TextView floorInfoArray = (TextView) convertView.findViewById(R.id.floor_info_arrow);
        floorInfoArray.setTypeface(FontManager.getTypeface(_context, FontManager.FONTAWESOME));


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._floors.get(this._libraryNames.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._libraryNames.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._libraryNames.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        // Check if view is expanded
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.library_name, null);
        }

        // Set library names, distance, and hours
        String name = (String) getGroup(groupPosition);
        TextView libraryName = (TextView) convertView.findViewById(R.id.libraryName);
        libraryName.setText(name);

        String dist;
        switch (name){
            case "UGL":
                dist = _context.getString(R.string.uglDist);
                break;
            case "Grainger":
                dist = _context.getString(R.string.graingerDist);
                break;
            case "Main Library":
                dist = _context.getString(R.string.mainlibDist);
                break;
            case "ACES":
                dist = _context.getString(R.string.acesDist);
                break;
            case "Law":
                dist = _context.getString(R.string.lawDist);
                break;
            case "Communications":
                dist = _context.getString(R.string.commDist);
                break;
            default: dist = "Distance Not Available";
        }
        TextView distance = (TextView) convertView.findViewById(R.id.distance);
        distance.setText(dist);

        String hours;
        switch (name){
            case "UGL":
                hours = _context.getString(R.string.uglHours);
                break;
            case "Grainger":
                hours = _context.getString(R.string.graingerHours);
                break;
            case "Main Library":
                hours = _context.getString(R.string.mainlibHours);
                break;
            case "ACES":
                hours = _context.getString(R.string.acesHours);
                break;
            case "Law":
                hours = _context.getString(R.string.lawHours);
                break;
            case "Communications":
                hours = _context.getString(R.string.commHours);
                break;
            default: hours = "Hours Not Available";
        }

        TextView libraryHours = (TextView) convertView.findViewById(R.id.libraryHours);
        libraryHours.setText(hours);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}