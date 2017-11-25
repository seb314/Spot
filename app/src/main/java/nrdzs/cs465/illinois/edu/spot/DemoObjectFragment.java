package nrdzs.cs465.illinois.edu.spot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;

// Instances of this class are fragments representing a single
// object in our collection.
public class DemoObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "index";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_detailed_photo_frame, container, false);


        Bundle args = getArguments();
        int photo_index = args.getInt(ARG_OBJECT);


        PhotoView photoView = (PhotoView) rootView.findViewById(R.id.detailed_photo_view);

        switch (photo_index) {
            case 1:
                photoView.setImageResource(R.drawable.grainger_image_1);
                break;
            case 2:
                photoView.setImageResource(R.drawable.grainger_image_2);
                break;
            case 3:
                photoView.setImageResource(R.drawable.grainger_image_3);
                break;
            default:
                Log.d("debug","number of hardcoded detailed photos inconsistent:"+photo_index);
                photoView.setImageResource(R.drawable.grainger_image_1);

        }

        return rootView;
    }
}
