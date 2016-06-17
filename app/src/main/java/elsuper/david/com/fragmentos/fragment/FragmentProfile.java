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
    private boolean change=true;

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
        //Obtenemos el nombre del usuario y lo asignamos
        String user = getArguments().getString("user_key");
        txtUser.setText(user);

        //Dependiendo de la inicial del nombre del usuario aignamos la imagen
        if(user.matches("[A-Ma-m]{1}.*")){
            ImageView imgProfile = (ImageView)view.findViewById(R.id.fragProfile_imgProfile);
            imgProfile.setImageResource(R.mipmap.ic_launcher);
        }
        else if(user.matches("[N-Zn-z]{1}.*")){
            ImageView imgProfile = (ImageView)view.findViewById(R.id.fragProfile_imgProfile);
            imgProfile.setImageResource(R.mipmap.ic_launcher2);
        }

        return view;
    }
}
