package nrdzs.cs465.illinois.edu.spot.backend;

import java.util.List;

/**
 * Created by sebastian on 28.11.17.
 */

public class FloorArea {
    private String name;
    private List<Photo> photos;

    public void addPhoto(Photo newPhoto){
        photos.add(0, newPhoto); // insert at the begin for now to mimic chronological order
    }

    public String getName(){
        return name;
    }

    public List<Photo> getPhotos(){
        return photos;
    }
}
