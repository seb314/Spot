package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import nrdzs.cs465.illinois.edu.spot.R;

public class ConfirmPhotoLocationActivity extends CustomActivity
        implements AdapterView.OnItemSelectedListener {

    public final String USER_SELECTION = "USER_SELECTION";
    protected int mUserSelection;

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

        setupOkButton();
    }

    private void setupOkButton(){
        Button okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(USER_SELECTION, Integer.toString(mUserSelection));
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
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
                mUserSelection = 0;

                left_butt.setButtonDrawable(R.drawable.left_side_checked);
                center_butt.setButtonDrawable(R.drawable.left_side);
                right_butt.setButtonDrawable(R.drawable.left_side);

            } else {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.left_side);
                right_butt.setButtonDrawable(R.drawable.left_side);
            }
        } else if (butt_id == R.id.center_butt) {
            if (checked) {
                mUserSelection = 1;

                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.left_side_checked);
                right_butt.setButtonDrawable(R.drawable.left_side);
            } else {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.left_side);
                right_butt.setButtonDrawable(R.drawable.left_side);
            }
        } else if (butt_id == R.id.right_butt) {
            if (checked) {
                mUserSelection = 2;

                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.left_side);
                right_butt.setButtonDrawable(R.drawable.left_side_checked);
            } else {
                left_butt.setButtonDrawable(R.drawable.left_side);
                center_butt.setButtonDrawable(R.drawable.left_side);
                right_butt.setButtonDrawable(R.drawable.left_side);
            }
        }
    }
}
