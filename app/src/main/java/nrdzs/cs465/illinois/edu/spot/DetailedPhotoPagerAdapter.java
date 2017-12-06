package nrdzs.cs465.illinois.edu.spot;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DetailedPhotoPagerAdapter extends FragmentStatePagerAdapter {
    String area;
    GlobalApplicationVaribles glob;

    public DetailedPhotoPagerAdapter(FragmentManager fm, String area, Context ctx) {
        super(fm);
        this.area = area;
        this.glob = GlobalApplicationVaribles.getInstance(ctx);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DetailedPhotoFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P

        args.putString(FloorSwipeAdapter.POSITION, area);
        args.putInt(FloorSwipeFragment.INDEX, i);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return glob.numPhotosForArea(area);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "INDEX " + (position + 1);
    }
}
