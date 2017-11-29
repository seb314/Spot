package nrdzs.cs465.illinois.edu.spot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sebastian on 28.11.17.
 */

public class FloorSwipeFragment  extends Fragment {
    public static final String INDEX = "index";
    public static final String CUR_RESOURCES = "cur_resources";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.swipe_layout_zuyi, container, false);
        Bundle args = getArguments();
        int index = args.getInt(INDEX);
        int[] curResources = args.getIntArray(CUR_RESOURCES);

        ImageView imageView = rootView.findViewById(R.id.image_view_all);
        int reqHeight = 100;//imageView.getHeight();
        int reqWidth = 100;//imageView.getWidth();

        imageView.setImageBitmap(Common.decodeSampledBitmapFromResource(getResources(), curResources[index], reqWidth, reqHeight));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchDetailedView = new Intent(getContext(), DetailedPhotoActivity.class);
                getContext().startActivity(launchDetailedView);
            }
        });

        return rootView;
    }


}