package arudanovsky.com.currencyexchange;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class AppPreferences {
    static final String KEY_PREFS_FROM_SUM = "from_sum";
    static final String KEY_PREFS_FROM_POSITION = "from_position";
    static final String KEY_PREFS_TO_POSITION = "to_position";

    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName();
    private SharedPreferences mSharedPrefs;
    private SharedPreferences.Editor mPrefsEditor;

    public AppPreferences(Context context) {
        this.mSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.mPrefsEditor = mSharedPrefs.edit();
    }

    public String getFromSum() {
        return mSharedPrefs.getString(KEY_PREFS_FROM_SUM, "0.00");
    }

    public void setFromSum(String sum) {
        mPrefsEditor.putString(KEY_PREFS_FROM_SUM, sum);
        mPrefsEditor.commit();
    }
    public int getFromPosition() {
        return mSharedPrefs.getInt(KEY_PREFS_FROM_POSITION, 0);
    }

    public void setFromPosition(int position) {
        mPrefsEditor.putInt(KEY_PREFS_FROM_POSITION, position);
        mPrefsEditor.commit();
    }
    public int getToPosition() {
        return mSharedPrefs.getInt(KEY_PREFS_TO_POSITION, 0);
    }

    public void setToPosition(int position) {
        mPrefsEditor.putInt(KEY_PREFS_TO_POSITION, position);
        mPrefsEditor.commit();
    }
}
