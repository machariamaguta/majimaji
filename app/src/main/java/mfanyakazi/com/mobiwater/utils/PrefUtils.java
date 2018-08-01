package mfanyakazi.com.mobiwater.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {

    /*
        SharedPreference
     */

    private SharedPreferences sharedPreferences;
    /*
        Editor for sharedPreference
     */
    private SharedPreferences.Editor editor;

    /*
        Shared preference mode
     */
    private int PRIVATE_MODE = 0;

    /**
     * Shared preferences file name
     */
    private static final String PREF_NAME = "majimajiPrefs";

    private static String KEY_PHONENUMBER = "phonenumber";
    private static String KEY_PHONENUMBER_SET = "hasSetPhoneNumber";
    private static String KEY_APPTOKEN = "appToken";
    private Context mContext;


    public PrefUtils(Context context){

        this.mContext = context;
        this.sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = sharedPreferences.edit();

    };

    public void setPhoneNumber(String phoneNumber){
        editor.putString(KEY_PHONENUMBER, phoneNumber);
        editor.commit();
    }

    public String getPhoneNumber(){
        return sharedPreferences.getString(KEY_PHONENUMBER, null);
    }

    public void setHasSetPhoneNumber(Boolean hasSetPhoneNumber){
        editor.putBoolean(KEY_PHONENUMBER_SET, hasSetPhoneNumber);
        editor.commit();
    }

    public Boolean getHasSetPhoneNumber(){
        return sharedPreferences.getBoolean(KEY_PHONENUMBER_SET, false);
    }


    public void setAppToken(String appToken){
        editor.putString(KEY_APPTOKEN, appToken);
        editor.commit();
    }

    public String getAppToken(){
        return sharedPreferences.getString(KEY_APPTOKEN, null);
    }


}
