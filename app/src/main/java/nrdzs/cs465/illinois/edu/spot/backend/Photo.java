package nrdzs.cs465.illinois.edu.spot.backend;

import android.support.annotation.NonNull;

import java.net.URL;
import java.time.Instant;
import java.util.Date;

import nrdzs.cs465.illinois.edu.spot.Common;

/**
 * Created by sebastian on 28.11.17.
 */

public class Photo implements Comparable<Photo>{
    private URL path;
    private long timestamp;
    private String location;

    public Photo(URL url, String location, long timestamp){
        this.path = url;
        this.location = location;
        this.timestamp = timestamp;
    }

    public Photo(String urlInDrawableNodpi, String location, String timestampString){
        this(Common.getPathInDrawableNodpi(urlInDrawableNodpi),
                location,
                Common.epochMillisecsFromDateTimeString(timestampString)
                );
    }

    @Override
    public int compareTo(@NonNull Photo o) {
        return (int) (this.timestamp - o.timestamp);
    }

    public String getLocation() {
        return location;
    }

    public URL getPath(){
        return path;
    }

    public String getTitleText(){
        long now = new Date().getTime();
        double hoursAgo = (now - timestamp)/(1000. * 3600.);
        return String.valueOf((int) hoursAgo);
        // TODO make nice date string
    }
}
