package nrdzs.cs465.illinois.edu.spot;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.HashSet;
import java.util.Set;

// Instances of this class are fragments representing a single
// object in our collection.
public class DetailedPhotoFragment extends Fragment {
    public static final String ARG_OBJECT = "index";
    private TextView title;
    private SubsamplingScaleImageView photoView;
    private Set<ControlVisibilitySetter> controlVisibiltiySetters = new HashSet<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_detailed_photo_frame, container, false);

        photoView =
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

;



        //TODO set real tile once we have real photos
        title = (TextView) rootView.findViewById(R.id.photo_detail_title);
        title.setText(titleText);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleControlAndTitleVisibility();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context a){
        super.onAttach(a);
        controlVisibiltiySetters.add((ControlVisibilitySetter) a);
        //setControlAndTitleVisibility(View.VISIBLE); somehow this doesn't work
    }

    private void toggleControlAndTitleVisibility(){
        int newVisibiltiy = title.getVisibility() == View.GONE ? View.VISIBLE : View.GONE;

        setControlAndTitleVisibility(newVisibiltiy);
    }

    private void setControlAndTitleVisibility(int newVisibility){
        title.setVisibility(newVisibility);

        // change the visibilty of the
        for(ControlVisibilitySetter v: controlVisibiltiySetters){
            v.setControlVisibiltiy(newVisibility);
        }
    }
}

