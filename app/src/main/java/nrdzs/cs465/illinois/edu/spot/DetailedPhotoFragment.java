package nrdzs.cs465.illinois.edu.spot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.github.chrisbanes.photoview.PhotoView;

// Instances of this class are fragments representing a single
// object in our collection.
public class DetailedPhotoFragment extends Fragment {
    public static final String ARG_OBJECT = "index";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_detailed_photo_frame, container, false);

        SubsamplingScaleImageView photoView =
                (SubsamplingScaleImageView) rootView.findViewById(R.id.detailed_photo_view);

        Bundle args = getArguments();
        int photo_index = args.getInt(ARG_OBJECT);
        int imageResource;
        String titleText;
        switch (photo_index) {
            case 1:
                imageResource = R.drawable.grainger_image_3;
                titleText = "12:05 pm";
                break;
            case 2:
                imageResource = R.drawable.grainger_image_4;
                titleText = "10:30 am";
                break;
            case 3:
                imageResource = R.drawable.grainger_image_5;
                titleText = "Yesterday";
                break;
            default:
                Log.d("debug","number of hardcoded detailed photos inconsistent:"+photo_index);
                imageResource = R.drawable.grainger_image_2;
                titleText = "Monday";

        }

        photoView.setMaximumDpi(160);
        photoView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);

        photoView.setImage(ImageSource.resource(imageResource));



        //TODO set real tile once we have real photos
        TextView title = (TextView) rootView.findViewById(R.id.photo_detail_title);
        title.setText(titleText);

        return rootView;
    }
}
