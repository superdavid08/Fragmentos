package elsuper.david.com.fragmentos.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import elsuper.david.com.fragmentos.model.ModelUser;

/**
 * Created by Andrés David García Gómez
 */
public class PreferenceUtil {
    private  static final String FILE_NAME = "unam_pref";
    private final SharedPreferences sp;

    public PreferenceUtil(Context context) {
        sp = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
    }

    public boolean saveUser(ModelUser modelUser){
        if(modelUser != null){
            sp.edit().putString("user_name", modelUser.userName).apply();
            sp.edit().putString("user_password", modelUser.password).apply();
            return true;
        }
        else
            return false;
    }

    public  ModelUser getUser(){
        String user = sp.getString("user_name",null);
        String password = sp.getString("user_password",null);

        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password))
            return  null;

        return  new ModelUser(user,password);
    }
}

