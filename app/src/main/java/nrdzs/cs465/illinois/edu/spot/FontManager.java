package nrdzs.cs465.illinois.edu.spot;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by sebastian on 27.11.17.
 *
 * Helper to setup fontawesome typeface for TextViews /icons
 *
 * from https://code.tutsplus.com/tutorials/how-to-use-fontawesome-in-an-android-app--cms-24167
 */

public class FontManager {

    public static final String FONTAWESOME = "fontawesome-webfont.ttf";

    public static final String RALEWAY = "Raleway-Medium.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

}
