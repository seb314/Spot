package nrdzs.cs465.illinois.edu.spot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nrdzs.cs465.illinois.edu.spot.backend.Photo;

/**
 * Created by sebastian on 28.11.17.
 */

public class FloorSwipeFragment  extends Fragment {
    public static final String INDEX = "index";
    private GlobalApplicationVaribles glob = GlobalApplicationVaribles.getInstance(getContext());
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.swipe_layout_zuyi, container, false);
        Bundle args = getArguments();
        final int index = args.getInt(INDEX);
        final String position = args.getString(FloorSwipeAdapter.POSITION);

        Photo photo = glob.getPhotoForAreaAndIndex(position, index);

        ImageView imageView = rootView.findViewById(R.id.image_view_all);
        int reqHeight = 300;//imageView.getHeight();
        int reqWidth = 200;//imageView.getWidth();

        if(photo.isResourceBased()){
            imageView.setImageBitmap(Common.decodeSampledBitmapFromResource(getResources(), photo.getResource(), reqWidth, reqHeight));
        } else {
            imageView.setImageBitmap(Common.decodeSampledBitmapFromURL(photo.getPath(), reqWidth, reqHeight));
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchDetailedView = new Intent(getContext(), DetailedPhotoActivity.class);
                launchDetailedView.putExtra(FloorSwipeAdapter.POSITION, position);
                launchDetailedView.putExtra(INDEX, index);
                getContext().startActivity(launchDetailedView);
            }
        });

        title = (TextView) rootView.findViewById(R.id.photo_detail_title);
        title.setText(photo.getTitleText());

        return rootView;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("Fragment", "Fragment destroyed");
    }


}