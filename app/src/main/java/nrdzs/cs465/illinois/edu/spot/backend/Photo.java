package nrdzs.cs465.illinois.edu.spot.backend;

import android.support.annotation.NonNull;

import java.net.URL;
import java.util.Date;

import nrdzs.cs465.illinois.edu.spot.Common;

/**
 * Created by sebastian on 28.11.17.
 */

public class Photo implements Comparable<Photo>{
    private String path;
    private int resource;
    private long timestamp;
    private String location;
    private Boolean isResourceBased;

    public Photo(String absolutePath, String location, long timestamp){
        this.path = absolutePath;
        this.location = location;
        this.timestamp = timestamp;
        isResourceBased = false;
    }

    public Photo(int resource, String location, long timestamp){
        this.resource = resource;
        this.location = location;
        this.timestamp = timestamp;
        isResourceBased = true;
    }
    public Photo(int resource, String location, String timestampString){
        this(resource,
                location,
                Common.epochMillisecsFromDateTimeString(timestampString)
                );
    }

    @Override
    public int compareTo(@NonNull Photo o) {
        return (int) (o.timestamp - this.timestamp);
    }

    public String getLocation() {
        return location;
    }

    public String getPath(){
        return path;
    }

    public int getResource(){
        return resource;
    }

    public boolean isResourceBased(){
        return isResourceBased;
    }

    public String getTitleText(){
        long now = new Date().getTime();
        double hoursAgo = (now - timestamp)/(1000. * 3600.);
        if (hoursAgo < 24){
            return (int)hoursAgo + "h ago";
        } else {
            return (int)(hoursAgo / 24) + "d ago";
        }
        // TODO make nice date string
    }
}
