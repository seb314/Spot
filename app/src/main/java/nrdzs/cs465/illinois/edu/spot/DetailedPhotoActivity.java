package nrdzs.cs465.illinois.edu.spot;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class DetailedPhotoActivity extends FragmentActivity {
    // When requested, this adapter returns a DetailedPhotoFragment,
    // representing an object in the collection.
    DetailedPhotoPagerAdapter mDetailedPhotoPagerAdapter;
    ViewPager mViewPager;
    Button cameraButton;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_photo);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDetailedPhotoPagerAdapter =
                new DetailedPhotoPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.detailed_photo_pager);
        mViewPager.setAdapter(mDetailedPhotoPagerAdapter);


        Common.setupCameraButton(this);
        setupBackButton();
        setupNextPhotoButton();
        setupPrevPhotoButton();

        Common.makeFullScreen(this);
    }

    private void setupBackButton(){
        Common.setupButton(this, R.id.back_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupNextPhotoButton(){
        Common.setupButton(this, R.id.next_button, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true); //getItem(-1) for previous
            }
        });
    }

    private void setupPrevPhotoButton(){
        Common.setupButton(this, R.id.prev_button, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true); //getItem(-1) for previous
            }
        });
    }
}


