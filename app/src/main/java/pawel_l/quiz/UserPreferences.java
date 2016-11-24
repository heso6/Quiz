package pawel_l.quiz;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gosc on 23.11.2016.
 */

public class UserPreferences {

    public static final String PREF_LEVEL = "level";
    public static final String PREF_USERNAME = "username";
    private final SharedPreferences mPreferences;

    public UserPreferences(Context context){
        this.mPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);


    }

    public String getUsername() {
       return mPreferences.getString(PREF_USERNAME, "");
    }

    public void setUsername(String username) {
        mPreferences.edit().putString(PREF_USERNAME,username).apply();
    }

    public int getLevel() {
      return mPreferences.getInt(PREF_LEVEL, 0);

    }

    public void setLevel(int level) {
     mPreferences.edit().putInt(PREF_LEVEL, level).apply();
    }
}
