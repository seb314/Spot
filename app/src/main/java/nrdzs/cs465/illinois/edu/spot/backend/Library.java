package nrdzs.cs465.illinois.edu.spot.backend;

import java.util.List;

/**
 * Created by sebastian on 28.11.17.
 */

public class Library {
    private String name;
    private List<LibraryFloor> floors;

    public String getName(){
        return name;
    }

    public List<LibraryFloor> getFloors(){
        return floors;
    }
}
