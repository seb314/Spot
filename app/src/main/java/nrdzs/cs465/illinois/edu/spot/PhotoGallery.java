package nrdzs.cs465.illinois.edu.spot;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import android.widget.RadioButton;


public class PhotoGallery extends FragmentActivity {

    ViewPager viewPager;
    FloorSwipeAdapter adapter;



    public void rightButtonClick(View v){
        RadioButton rightButton = (RadioButton) findViewById(R.id.right_button);
        RadioButton middleButton = (RadioButton) findViewById(R.id.middle_button);
        RadioButton leftButton = (RadioButton) findViewById(R.id.left_button);

        setNewAdapter("right");
        rightButton.setButtonDrawable(R.drawable.spot_mark);
        middleButton.setButtonDrawable(R.drawable.trans);
        leftButton.setButtonDrawable(R.drawable.trans);
    }

    public void middleButtonClick(View v){
        RadioButton rightButton = (RadioButton) findViewById(R.id.right_button);
        RadioButton middleButton = (RadioButton) findViewById(R.id.middle_button);
        RadioButton leftButton = (RadioButton) findViewById(R.id.left_button);

        setNewAdapter("middle");
        middleButton.setButtonDrawable(R.drawable.spot_mark);
        leftButton.setButtonDrawable(R.drawable.trans);
        rightButton.setButtonDrawable(R.drawable.trans);
    }

    public void leftButtonClick(View v){
        RadioButton rightButton = (RadioButton) findViewById(R.id.right_button);
        RadioButton middleButton = (RadioButton) findViewById(R.id.middle_button);
        RadioButton leftButton = (RadioButton) findViewById(R.id.left_button);

        setNewAdapter("left");
        leftButton.setButtonDrawable(R.drawable.spot_mark);
        rightButton.setButtonDrawable(R.drawable.trans);
        middleButton.setButtonDrawable(R.drawable.trans);
    }

    public void setNewAdapter(String position){
        viewPager.removeAllViews();
        viewPager.setAdapter(null);
        adapter = new FloorSwipeAdapter(getSupportFragmentManager(), position);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        viewPager = (ViewPager) findViewById(R.id.view_pager_slider);
        adapter = new FloorSwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        Common.makeFullScreen(this);
    }
}
