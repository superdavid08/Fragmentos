package elsuper.david.com.fragmentos.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import elsuper.david.com.fragmentos.model.ModelUser;

/**
 * Created by Andrés David García Gómez //Ejercicio 2
 */
public class PreferenceUseApp {
    private  static final String FILE_NAME = "fragmentos_use_app";
    private final SharedPreferences sp;

    public PreferenceUseApp(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
    }

    public boolean saveUseTime(String seconds){
        if(!TextUtils.isEmpty(seconds)){
            sp.edit().putString("app_seconds", seconds).apply();
            return true;
        }
        else
            return false;
    }

    public String getUseTime(){
        String seconds = sp.getString("app_seconds",null);

        if(TextUtils.isEmpty(seconds))
            return  "0";

        return seconds;
    }
}
