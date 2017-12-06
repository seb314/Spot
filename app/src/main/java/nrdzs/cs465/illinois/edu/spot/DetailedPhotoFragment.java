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

import nrdzs.cs465.illinois.edu.spot.backend.Photo;

// Instances of this class are fragments representing a single
// object in our collection.
public class DetailedPhotoFragment extends Fragment {
    private TextView title;
    private SubsamplingScaleImageView photoView;
    private Set<ControlVisibilitySetter> controlVisibiltiySetters = new HashSet<>();
    private GlobalApplicationVaribles glob;

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
        String area = args.getString(FloorSwipeAdapter.POSITION);
        int index = args.getInt(FloorSwipeFragment.INDEX);
        glob = GlobalApplicationVaribles.getInstance(getContext());
        Photo photo = glob.getPhotoForAreaAndIndex(area, index);

        String titleText = photo.getTitleText();

        photoView.setMaximumDpi(160);
        photoView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
        photoView.setMaxScale(5); // try to allow more zooming
        photoView.setMinimumDpi(25); // try to allow more zooming

        photoView.setImage(ImageSource.uri(photo.getPath().getPath()));

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

    private String getTitleForResource(int photoRescource){
        /**
         * provides a shitty hardcoded mapping of photo's recouces to some string that represents at time
         *
         */
        // TODO implement
        return "12:34 pm";
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

