package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nrdzs.cs465.illinois.edu.spot.R;

public class FloorAndPhotoActivity extends CustomActivity {

    Activity this_alias = this; // aarrg sry, I can't simply use 'this' in the onlicklistener
    // because that points just to the onclicklistener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_and_photo);

        // TODO remove this block once the correct link to detailed image view exists
        Button button_go_to_detailed_image_view =
                (Button) findViewById(R.id.shortcut_to_detailed_photo_screen_button);
        button_go_to_detailed_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.launch_detailed_photo_activity(this_alias);
            }
        });
        button_go_to_detailed_image_view.setTypeface(mFontAwesomeTypeface);
    }
}
