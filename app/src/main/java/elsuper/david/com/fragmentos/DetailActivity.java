package elsuper.david.com.fragmentos;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import elsuper.david.com.fragmentos.fragment.FragmentList;
import elsuper.david.com.fragmentos.fragment.FragmentProfile;

/**
 * Created by Andrés David García Gómez
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Obtenemos el Extra que contiene el username
        username = getIntent().getExtras().getString("keyUser");

        findViewById(R.id.detail_btnFragmentA).setOnClickListener(this);
        findViewById(R.id.detail_btnFragmentB).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_btnFragmentA:
                changeFragmentA();
                break;
            case R.id.detail_btnFragmentB:
                changeFragmentB();
                break;
        }
    }

    //region Métodos
    private void changeFragmentB() {
        //Mostramos el Fragment de Lista
        getFragmentManager().beginTransaction().replace(R.id.detail_flFragmentFolder, new FragmentList()).commit();
    }

    private void changeFragmentA() {
        //Mostramos el Fragmen de Perfil y le pasamos el username
        FragmentProfile f = FragmentProfile.newInstance(username);
        getFragmentManager().beginTransaction().replace(R.id.detail_flFragmentFolder, f).commit();
    }
    //endregion
}
