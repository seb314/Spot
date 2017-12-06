package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import java.util.Date;

import nrdzs.cs465.illinois.edu.spot.backend.Photo;

import static nrdzs.cs465.illinois.edu.spot.CustomActivity.REQUEST_PHOTO_LOCATION;


public class DetailedPhotoActivity extends FragmentActivity implements ControlVisibilitySetter {
    // When requested, this adapter returns a DetailedPhotoFragment,
    // representing an object in the collection.
    DetailedPhotoPagerAdapter mDetailedPhotoPagerAdapter;
    ViewPager mViewPager;
    Button cameraButton, nextPhotoButton, prevPhotoButton, backButton;
    String position;
    CameraButtonHandler camButtonHandler = new CameraButtonHandler(this);


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_photo);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        position = getIntent().getStringExtra(FloorSwipeAdapter.POSITION);
        int initIndex = getIntent().getIntExtra(FloorSwipeFragment.INDEX, 0);

        mDetailedPhotoPagerAdapter =
                new DetailedPhotoPagerAdapter(
                        getSupportFragmentManager(), position, this);
        mViewPager = (ViewPager) findViewById(R.id.detailed_photo_pager);
        mViewPager.setAdapter(mDetailedPhotoPagerAdapter);


        cameraButton = camButtonHandler.setupCameraButton();
//        setupBackButton();
        setupNextPhotoButton();
        setupPrevPhotoButton();

        Common.makeFullScreen(this);
    }

//    private void setupBackButton(){
//        backButton = Common.setupButton(this, R.id.back_button, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }

    private void setupNextPhotoButton(){
        nextPhotoButton = Common.setupButton(this, R.id.next_button, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true); //getItem(-1) for previous
            }
        });
    }

    private void setupPrevPhotoButton(){
        prevPhotoButton = Common.setupButton(this, R.id.prev_button, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true); //getItem(-1) for previous
            }
        });
    }

    @Override
    public void setControlVisibiltiy(int visibiltiy) {
//        backButton.setVisibility(visibiltiy);
        nextPhotoButton.setVisibility(visibiltiy);
        prevPhotoButton.setVisibility(visibiltiy);
        cameraButton.setVisibility(visibiltiy);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        camButtonHandler.onActivityResult(requestCode, resultCode, data);
        mViewPager.setAdapter(mDetailedPhotoPagerAdapter);
    }
}


