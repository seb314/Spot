package nrdzs.cs465.illinois.edu.spot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PhotoGallery extends FragmentActivity {

    ViewPager viewPager;
    FloorSwipeAdapter adapter;

    String selected_area; //0, 1, 2 for left, center, right; -1 for none
    ImageButton left, center, right;
    ImageButton[] buttonsAsArray = new ImageButton[3];
    Map<String, ImageButton> buttonsByName = new HashMap<>();
    Map<ImageButton, String> buttonNames = new HashMap<>();
    Button cameraButton, nextPhotoButton, prevPhotoButton;
    CameraButtonHandler cameraButtonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);
        viewPager = (ViewPager) findViewById(R.id.view_pager_slider);
        adapter = new FloorSwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        left = (ImageButton) findViewById(R.id.button_left);
        center = (ImageButton) findViewById(R.id.button_center);
        right = (ImageButton) findViewById(R.id.button_right);
        buttonsByName.put("left", left);
        buttonsByName.put("center", center);
        buttonsByName.put("right", right);
        buttonNames.put(left, "left");
        buttonNames.put(center, "center");
        buttonNames.put(right, "right");

        setupButtonOnclicklisteners(new HashSet<>(buttonsByName.values()));

        selected_area = "center";
        setNewAdapter(selected_area);

        updateButtonVisibilities();

        cameraButtonHandler = new CameraButtonHandler(this);
        cameraButton = cameraButtonHandler.setupCameraButton();

        setupNextPhotoButton();
        setupPrevPhotoButton();

        Common.makeFullScreen(this);
    }

    private void setupNextPhotoButton(){
        nextPhotoButton = Common.setupButton(this, R.id.next_button, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true); //getItem(-1) for previous
            }
        });
    }

    private void setupPrevPhotoButton(){
        prevPhotoButton = Common.setupButton(this, R.id.prev_button, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true); //getItem(-1) for previous
            }
        });
    }

    private void setupButtonOnclicklisteners(Set<ImageButton> buttons) {
        for(final ImageButton b : buttons){
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLocationButtonTouched(b);
                }
            });
        }
    }

    private void onLocationButtonTouched(ImageButton button) {
        String name = buttonNames.get(button);
        selected_area = !name.equals(selected_area) ? name : "all";

        updateButtonVisibilities();
        setNewAdapter(selected_area);
    }

    private void updateButtonVisibilities() {
        for (ImageButton b : buttonNames.keySet()){
            String name = buttonNames.get(b);
            if (! name.equals(selected_area)){
                b.setAlpha(.2f);
            } else {
                b.setAlpha(1f);
            }
        }
    }


    public void setNewAdapter(String position) {
        viewPager.removeAllViews();
        viewPager.setAdapter(null);
        adapter = new FloorSwipeAdapter(getSupportFragmentManager(), position);
        viewPager.setAdapter(adapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cameraButtonHandler.onActivityResult(requestCode, resultCode, data);
        setNewAdapter(selected_area);
    }

}
