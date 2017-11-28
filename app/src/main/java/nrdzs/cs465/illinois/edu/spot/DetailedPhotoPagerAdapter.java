package nrdzs.cs465.illinois.edu.spot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DetailedPhotoPagerAdapter extends FragmentStatePagerAdapter {
    public DetailedPhotoPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DetailedPhotoFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DetailedPhotoFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "INDEX " + (position + 1);
    }
}
