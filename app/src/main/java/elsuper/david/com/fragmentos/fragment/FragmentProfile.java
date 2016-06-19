package elsuper.david.com.fragmentos.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import elsuper.david.com.fragmentos.R;

/**
 * Created by Andrés David García Gómez
 */
public class FragmentProfile extends Fragment {

    private ImageView imgProfile;

    public static FragmentProfile newInstance(String name)
    {
        FragmentProfile f = new FragmentProfile();
        Bundle b = new Bundle();
        b.putString("user_key",name);
        f.setArguments(b);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflamos la vista
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        //Obtenemos el textview para poner el nombre del usuario
        TextView txtUser = (TextView)view.findViewById(R.id.fragProfile_txtUsername);
        //Obtenemos el nombre del usuario y lo mostramos (en minúsculas)
        String user = getArguments().getString("user_key");
        txtUser.setText(user.toLowerCase());

        //Dependiendo de la inicial del nombre del usuario asignamos la imagen
        //A-M muestra imagen ic_Launcher
        imgProfile = (ImageView)view.findViewById(R.id.fragProfile_imgProfile);
        if(user.matches("^[a-mA-M].*")){
            imgProfile.setImageResource(R.mipmap.ic_launcher);
        }
        //N-Z muestra imagen ic_Launcher3
        else if(user.matches("^[n-zN-Z].*")){
            imgProfile.setImageResource(R.mipmap.ic_launcher3);
        }

        return view;
    }
}
