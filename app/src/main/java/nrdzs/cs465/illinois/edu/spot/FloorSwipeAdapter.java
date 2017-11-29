package nrdzs.cs465.illinois.edu.spot;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

/**
 * Created by zuyi chen on 11/28/2017.
 */

public class FloorSwipeAdapter extends PagerAdapter {

    private int [] curResources;
    private int [] imageResources = {R.drawable.grainger_image_1, R.drawable.grainger_image_2,
            R.drawable.grainger_image_3, R.drawable.grainger_image_4, R.drawable.grainger_image_5};

    private int [] leftImageResources = {R.drawable.grainger_image_1};
    private int [] middleImageResources = {R.drawable.grainger_image_3, R.drawable.grainger_image_4,
            R.drawable.grainger_image_5};
    private int [] rightImageResources = {R.drawable.grainger_image_2};

    private Context ctx;
    private LayoutInflater layoutInflater;

    public void init(String position){



        if (position.equals("left")){
            curResources = leftImageResources;
        }
        else if (position.equals("middle")){
            curResources = middleImageResources;
        }
        else if (position.equals("right")){
            curResources = rightImageResources;
        }

        Log.i("size of array", Integer.toString(curResources.length));
    }

    public FloorSwipeAdapter(Context ctx){
        this.ctx = ctx;
        curResources = imageResources;
    }

    public FloorSwipeAdapter(Context ctx, String position){
        this.ctx = ctx;
        init(position);

    }

    @Override
    public int getCount() {
        return curResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        layoutInflater =  (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.swipe_layout_zuyi, container, false);
        ImageView imageView = itemView.findViewById(R.id.image_view_all);

        imageView.setImageResource(curResources[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchDetailedView = new Intent(ctx, DetailedPhotoActivity.class);
                ctx.startActivity(launchDetailedView);
            }
        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
