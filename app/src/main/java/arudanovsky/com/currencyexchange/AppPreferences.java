package arudanovsky.com.currencyexchange;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class AppPreferences {
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName();
    private SharedPreferences mSharedPrefs;
    private SharedPreferences.Editor mPrefsEditor;

    public AppPreferences(Context context) {
        this.mSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.mPrefsEditor = mSharedPrefs.edit();
    }
}
