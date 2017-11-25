package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

import nrdzs.cs465.illinois.edu.spot.R;

public class DetailedPhotoActivity extends CustomActivity {
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_photo);

        photoView = (PhotoView) findViewById(R.id.detailed_photo_view);
        photoView.setImageResource(R.drawable.grainger_image_1);
    }
}
