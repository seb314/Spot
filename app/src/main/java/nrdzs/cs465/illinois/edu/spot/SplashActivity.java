package nrdzs.cs465.illinois.edu.spot;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;


/**
 * Created by dash on 12/3/17.
 */

public class SplashActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 1 second
                final Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
