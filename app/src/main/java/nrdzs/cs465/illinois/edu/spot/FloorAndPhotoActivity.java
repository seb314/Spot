package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nrdzs.cs465.illinois.edu.spot.R;

public class FloorAndPhotoActivity extends CustomActivity {

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
                launch_detailed_photo_activity(v);
            }
        });
        button_go_to_detailed_image_view.setTypeface(mFontAwesomeTypeface);
    }
}
