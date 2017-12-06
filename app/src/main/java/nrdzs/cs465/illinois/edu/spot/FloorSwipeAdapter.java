package nrdzs.cs465.illinois.edu.spot;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.util.Log;

/**
 * Created by zuyi chen on 11/28/2017.
 */

public class FloorSwipeAdapter extends FragmentStatePagerAdapter {

    private String position = "all";
    public static final String POSITION = "position";

    private Context ctx;
    private LayoutInflater layoutInflater;
    private GlobalApplicationVaribles glob = GlobalApplicationVaribles.getInstance(ctx);


    public FloorSwipeAdapter(FragmentManager fm){
        super(fm);
    }

    public FloorSwipeAdapter(FragmentManager fm, String position){
        super(fm);
        this.position = position;
    }

    @Override
    public int getCount() {
        return glob.numPhotosForArea(position);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new FloorSwipeFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(FloorSwipeFragment.INDEX, i);
        args.putString(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }
}
