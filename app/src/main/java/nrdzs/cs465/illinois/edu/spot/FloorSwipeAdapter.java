package nrdzs.cs465.illinois.edu.spot;

import android.content.Context;
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
    private int [] curResources;
    private int [] imageResources = {R.drawable.grainger_image_1, R.drawable.grainger_image_2,
            R.drawable.grainger_image_3, R.drawable.grainger_image_4, R.drawable.grainger_image_5};

    private int [] leftImageResources = {R.drawable.grainger_image_1};
    private int [] middleImageResources = {R.drawable.grainger_image_3, R.drawable.grainger_image_4,
            R.drawable.grainger_image_5};
    private int [] rightImageResources = {R.drawable.grainger_image_2};

    private Context ctx;
    private LayoutInflater layoutInflater;

    public void init(String position){



        if (position.equals("left")){
            curResources = leftImageResources;
        }
        else if (position.equals("center")){
            curResources = middleImageResources;
        }
        else if (position.equals("right")){
            curResources = rightImageResources;
        } else if (position.equals("all")) {
            curResources = imageResources;
        }

        Log.i("size of array", Integer.toString(curResources.length));
    }

    public FloorSwipeAdapter(FragmentManager fm){
        super(fm);
        curResources = imageResources;
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
