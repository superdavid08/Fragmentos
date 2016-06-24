package elsuper.david.com.fragmentos;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import elsuper.david.com.fragmentos.fragment.FragmentList;
import elsuper.david.com.fragmentos.fragment.FragmentProfile;
import elsuper.david.com.fragmentos.service.ServiceTimer;
import elsuper.david.com.fragmentos.util.PreferenceUseApp;

/**
 * Created by Andrés David García Gómez
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private String username;
    private TextView txtTimer;
    private TextView txtLastSession; //Ejercicio 2
    private String session; //Ejercicio 2
    private static final long MINUTE = 60; //Cantidad de segundos //Ejercicio 2
    private static final long HOUR = 3600; //Ejercicio 2
    private static final long DAY = 86400; //Ejercicio 2
    private long counter; //Ejercicio 2
    private PreferenceUseApp preferenceUseApp; //Ejercicio 2

    //Para usar el service
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            counter = intent.getExtras().getInt("timer");
            //txtTimer.setText(String.format(getString(R.string.detail_txtSessionLenght), counter)); //Ejercicio 2
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Obtenemos el Extra que contiene el username
        username = getIntent().getExtras().getString("keyUser");

        //Obtenemos el Extra que contiene el último inicio de sesión. //Ejercicio 2
        session = getIntent().getExtras().getString("keyLastSession");

        findViewById(R.id.detail_btnFragmentA).setOnClickListener(this);
        findViewById(R.id.detail_btnFragmentB).setOnClickListener(this);

        //Para usar el service
        txtTimer = (TextView)findViewById(R.id.detail_txtTimer);


        //Para poner el último inicio de sesión. //Ejercicio 2
        if(TextUtils.isEmpty(session))
            session = getString(R.string.detail_txtFirstSession);
        else
            session = String.format(getString(R.string.detail_txtLastSession), session);

        txtLastSession = (TextView)findViewById(R.id.detail_txtLastSession);
        txtLastSession.setText(session);

        //Ejercicio 2
        preferenceUseApp = new PreferenceUseApp(getApplicationContext());
        String useTimeApp = preferenceUseApp.getUseTime();
        txtTimer.setText(getString(R.string.detail_txtUseTime) + useTimeFormat(useTimeApp));
    }

    //Ejercicio 2
    private String useTimeFormat(String useTimeApp) {
        Long seconds = Long.parseLong(useTimeApp);
        int day = 0;
        int hour = 0;
        int minute = 0;

        day = (int)(seconds/DAY);
        seconds = seconds % DAY;
        hour = (int)(seconds/HOUR);
        seconds = seconds % HOUR;
        minute = (int)(seconds/MINUTE);
        seconds = seconds% MINUTE;

        return String.format(" %sd, %sh, %sm, %ss", day, hour, minute, seconds);
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

    //region Eventos para usar el service
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceTimer.ACTION_SEND_TIMER);
        //filter.addAction(BatteryManager.ACTION_CHARGING);
        registerReceiver(broadcastReceiver,filter);
        //Log.d(ServiceTimer.TAG,"OnResume, se reinicia boradcast");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d(ServiceTimer.TAG,"onPause quitando broadcast");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Log.d(ServiceTimer.TAG,"OnDestroy, terminando servicio");

        //Guardamos el valor del contador en las preferencias. Ejercicio 2
        String newCounter = Long.toString(Long.parseLong(preferenceUseApp.getUseTime()) + counter);
        preferenceUseApp.saveUseTime(newCounter);

        stopService(new Intent(getApplicationContext(),ServiceTimer.class));
    }
    //endregion
}
