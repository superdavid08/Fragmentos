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

        username = getIntent().getExtras().getString("keyUser");

        findViewById(R.id.detail_btnFragmentA).setOnClickListener(this);
        findViewById(R.id.detail_btnFragmentB).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_btnFragmentA:
                changeFragmentA();
                //setFragment(FragmentProfile.newInstance(user));
                break;
            case R.id.detail_btnFragmentB:
                //setFragment(new FragmentItem());
                changeFragmentB();
                break;
        }
    }

    //region Métodos
    private void changeFragmentB() {
        getFragmentManager().beginTransaction().replace(R.id.detail_flFragmentFolder, new FragmentList()).commit();
    }

    private void changeFragmentA() {
        FragmentProfile f = FragmentProfile.newInstance(username);
        getFragmentManager().beginTransaction().replace(R.id.detail_flFragmentFolder, f).commit();
    }
    //endregion
}
