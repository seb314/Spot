package nrdzs.cs465.illinois.edu.spot;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nrdzs.cs465.illinois.edu.spot.backend.Photo;

/**
 * Created by zuyi chen on 12/5/2017.
 */

public class GlobalApplicationVaribles{
    private Map<String, Set<Photo>> imagesForAreas = new HashMap<>();

    private Photo[] allHardcodedPhotos = {
            new Photo(R.drawable.grainger_image_1, "left", "Dec 05 2017 10:11:52.454 CDT"),
            new Photo(R.drawable.grainger_image_2, "center", "Dec 05 2017 18:11:52.454 CDT"),
            new Photo(R.drawable.grainger_image_3, "center", "Dec 05 2017 09:11:52.454 CDT"),
            new Photo(R.drawable.grainger_image_4, "center", "Dec 05 2017 13:11:52.454 CDT"),
            new Photo(R.drawable.grainger_image_5, "right", "Dec 05 2017 15:11:52.454 CDT"),
    };

    private Context ctx;

    private static GlobalApplicationVaribles instance;

    private GlobalApplicationVaribles(Context ctx){
        this.ctx = ctx;
    };

    public void initVariables(){
        for(int i = 0; i < allHardcodedPhotos.length; i++){
            Photo p = allHardcodedPhotos[i];
            addPhoto(p);
        }
    }


    public void addPhoto(Photo photo){
        String area = photo.getLocation();
        if (!imagesForAreas.containsKey(area)){
            imagesForAreas.put(area, new HashSet<Photo>());
        }
        imagesForAreas.get(area).add(photo);

        if (!imagesForAreas.containsKey("all")){
            imagesForAreas.put("all", new HashSet<Photo>());
        }
        imagesForAreas.get("all").add(photo);
    }


    public Set<Photo> getPhotosForArea(String area){
        return new HashSet<>(imagesForAreas.get(area));
    }

    public Photo getPhotoForAreaAndIndex(String area, int index){
        List<Photo> photosForArea = new ArrayList(getPhotosForArea(area));
        Collections.sort(photosForArea);
        return photosForArea.get(index);
    }

    public int numPhotosForArea(String area){
        return getPhotosForArea(area).size();
    }


    public static synchronized GlobalApplicationVaribles getInstance(Context ctx){
        if (instance==null){
            instance = new GlobalApplicationVaribles(ctx);
            instance.initVariables();
        }

        return instance;
    }
}
