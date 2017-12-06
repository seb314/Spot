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
    private int[] curResources;

    private Context ctx;
    private LayoutInflater layoutInflater;
    private GlobalApplicationVaribles glob = GlobalApplicationVaribles.getInstance(ctx);

    public void init(String position){



        if (position.equals("left")){
            curResources = glob.getLeftImageResources();//leftImageResources;

        }
        else if (position.equals("center")){
            curResources = glob.getMiddleImageResources(); //middleImageResources;
        }
        else if (position.equals("right")){
            curResources = glob.getRightImageResources();//rightImageResources;
        } else if (position.equals("all")) {

            curResources = glob.getImageResources();//imageResources;
        }

        Log.i("size of array", Integer.toString(curResources.length));
    }

    public FloorSwipeAdapter(FragmentManager fm){

        super(fm);
        curResources = glob.getImageResources(); //imageResources;

    }

    public FloorSwipeAdapter(FragmentManager fm, String position){
        super(fm);
        init(position);
        this.position = position;
    }

    @Override
    public int getCount() {
        return curResources.length;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new FloorSwipeFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(FloorSwipeFragment.INDEX, i);
        args.putIntArray(FloorSwipeFragment.CUR_RESOURCES, curResources);
        fragment.setArguments(args);
        return fragment;
    }
}
