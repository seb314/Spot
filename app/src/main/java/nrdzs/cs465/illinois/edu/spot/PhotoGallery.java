package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


public class PhotoGallery extends Activity {

    ViewPager viewPager;
    FloorSwipeAdapter adapter;

    public void buttonInit() {
        ImageButton leftButton = (ImageButton) findViewById(R.id.left_button);
        ImageButton middleButton = (ImageButton) findViewById(R.id.middle_button);
        ImageButton rightButton = (ImageButton) findViewById(R.id.right_button);



        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("zuyi", "left");

               setNewAdapter("left");


            }
        });

        middleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setNewAdapter("middle");
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setNewAdapter("right");
            }
        });

    }

    public void setNewAdapter(String position){
        viewPager.removeAllViews();
        viewPager.setAdapter(null);
        adapter = new FloorSwipeAdapter(PhotoGallery.this, position);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallary);
        viewPager = (ViewPager) findViewById(R.id.view_pager_slider);
        adapter = new FloorSwipeAdapter(PhotoGallery.this);
        viewPager.setAdapter(adapter);

        buttonInit();
        Common.makeFullScreen(this);
    }
}
