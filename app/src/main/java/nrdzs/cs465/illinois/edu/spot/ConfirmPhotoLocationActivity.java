package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import nrdzs.cs465.illinois.edu.spot.R;

public class ConfirmPhotoLocationActivity extends CustomActivity
        implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_photo_location);

        // set the floor options
        Spinner floorSpinner = (Spinner) findViewById(R.id.floor_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.floor_level_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinner.setAdapter(adapter);
        floorSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onButtTouched(View view) {
        // get all the butts
        RadioButton left_butt = (RadioButton) findViewById(R.id.left_butt);
        RadioButton center_butt = (RadioButton) findViewById(R.id.center_butt);
        RadioButton right_butt = (RadioButton) findViewById(R.id.right_butt);

        boolean checked = ((RadioButton) view).isChecked();

        // figure out which butt the user touched
        int butt_id = view.getId();
        if(butt_id == R.id.left_butt) {
            if (checked) {
                left_butt.setButtonDrawable(R.drawable.left_side_checked);
                center_butt.setButtonDrawable(R.drawable.center_side);
                right_butt.setButtonDrawable(R.drawable.right_side);

            } else {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.center_side);
                right_butt.setButtonDrawable(R.drawable.right_side);
            }
        } else if (butt_id == R.id.center_butt) {
            if (checked) {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.center_side_checked);
                right_butt.setButtonDrawable(R.drawable.right_side);
            } else {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.center_side);
                right_butt.setButtonDrawable(R.drawable.right_side);
            }
        } else if (butt_id == R.id.right_butt) {
            if (checked) {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.center_side);
                right_butt.setButtonDrawable(R.drawable.right_side_checked);
            } else {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.center_side);
                right_butt.setButtonDrawable(R.drawable.right_side);
            }
        }
    }
}
