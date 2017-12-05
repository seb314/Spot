package nrdzs.cs465.illinois.edu.spot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;

public class ConfirmPhotoLocationActivity extends CustomActivity {

    public final String USER_SELECTION = "USER_SELECTION";
    int selected_button; //0, 1, 2 for left, center, right
    ImageButton left, center, right;
    ImageButton[] buttonsAsArray = new ImageButton[3];

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

        setupOkButton();

        left = (ImageButton) findViewById(R.id.button_left);
        center = (ImageButton) findViewById(R.id.button_center);
        right = (ImageButton) findViewById(R.id.button_right);
        buttonsAsArray[0] = left;
        buttonsAsArray[1] = center;
        buttonsAsArray[2] = right;
        setupButtonOnclicklisteners(buttonsAsArray);

        selected_button = 1; // init with center for now
        updateButtonVisibilities();

        // set to fullscreen
        Common.makeFullScreen(this);
    }



    private void updateButtonVisibilities(){
        for(int i = 0; i < buttonsAsArray.length; i++){
            if (i != selected_button){
                buttonsAsArray[i].setAlpha(0f);
            } else {
                buttonsAsArray[i].setAlpha(1f);
            }
        }
    }



    private void onLocationButtonTouched(ImageButton button){
        for (int i = 0; i<buttonsAsArray.length; i++){
            if (button == buttonsAsArray[i]){
                selected_button = i;
                break;
            }
        }
        updateButtonVisibilities();
    }


    private void setupButtonOnclicklisteners(ImageButton[] buttonsAsArray) {
        for (int i = 0; i<buttonsAsArray.length; i++){
            final ImageButton b = buttonsAsArray[i];
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLocationButtonTouched(b);
                }
            });
        }
    }

    private void setupOkButton(){
        Button okButton = findViewById(R.id.ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(USER_SELECTION, Integer.toString(selected_button));
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}
