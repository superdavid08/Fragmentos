package elsuper.david.com.fragmentos.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import elsuper.david.com.fragmentos.model.ModelUser;

/**
 * Created by Andrés David García Gómez
 */
public class PreferenceUtil {
    private  static final String FILE_NAME = "com.david.elsuper_pref";
    private final SharedPreferences sp;

    public PreferenceUtil(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
    }

    //region Métodos de User
    public boolean saveUser(ModelUser modelUser){
        if(modelUser != null){
            sp.edit().putString(Key.KEY_PREF_USER_NAME, modelUser.userName).apply();
            sp.edit().putString(Key.KEY_PREF_USER_PASS, modelUser.password).apply();
            return true;
        }
        else
            return false;
    }

    public ModelUser getUser(){
        String user = sp.getString(Key.KEY_PREF_USER_NAME,null);
        String password = sp.getString(Key.KEY_PREF_USER_PASS,null);

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password))
            return  null;

        return new ModelUser(user,password);
    }

    //Ejercicio 2
    public void deleteUser(){
        sp.edit().putString(Key.KEY_PREF_USER_NAME, "").apply();
        sp.edit().putString(Key.KEY_PREF_USER_PASS, "").apply();
    }
    //endregion

    //Ejercicio 2
    //region Métodos de uso de la App
    public boolean saveUseTime(String seconds){
        if(!TextUtils.isEmpty(seconds)){
            sp.edit().putString(Key.KEY_PREF_APP_SECONDS, seconds).apply();
            return true;
        }
        else
            return false;
    }

    public String getUseTime(){
        String seconds = sp.getString(Key.KEY_PREF_APP_SECONDS,null);

        if(TextUtils.isEmpty(seconds))
            return  "0";

        return seconds;
    }
    //endregion
}

