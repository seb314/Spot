package nrdzs.cs465.illinois.edu.spot.backend;

import java.util.List;

/**
 * Created by sebastian on 28.11.17.
 */

public class LibraryFloor {
    private String name;
    private List<FloorArea> areas;

    public String getName(){
        return name;
    }

    public List<FloorArea> getAreas(){
        return areas;
    }
}
