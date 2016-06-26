package elsuper.david.com.fragmentos;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import elsuper.david.com.fragmentos.model.ModelUser;
import elsuper.david.com.fragmentos.service.ServiceTimer;
import elsuper.david.com.fragmentos.sql.UserDataSource;
import elsuper.david.com.fragmentos.util.Key;
import elsuper.david.com.fragmentos.util.PreferenceUtil;

/**
 * Created by Andrés David García Gómez
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private View pbLoading;
    private PreferenceUtil preferenceUtil;
    //Para usar la tabla user_table //Ejercicio 2
    private UserDataSource userDataSource;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos la instancia para acceder a la tabla user_table //Ejercicio 2
        userDataSource = new UserDataSource(getApplicationContext());

        //Obtenemos los controles y seteamos el escucha del botón
        etUsername =  (EditText)findViewById(R.id.main_etUsername);
        etPassword =  (EditText)findViewById(R.id.main_etPassword);
        findViewById(R.id.main_btnLogin).setOnClickListener(this);
        pbLoading=findViewById(R.id.main_pbLoading);

        //Seteamos el escucha para el botón de registro y creamos la instancia
        //para las preferencias Ejercicio 2
        findViewById(R.id.main_btnRegister).setOnClickListener(this);
        preferenceUtil = new PreferenceUtil(getApplicationContext());

        //Obtenemos el control del checkbox de recordar credenciales. //Ejercicio 2
        cbRemember = (CheckBox)findViewById(R.id.main_cbRemember);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btnLogin:
                processData();
                break;
            //Ejercicio 2
            case R.id.main_btnRegister:
                launchRegister();
                break;
        }
    }

    //Lanzamos la Activity de registro. //Ejercicio 2
    private void launchRegister() {
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
    }

    private void processData() {

        //Guardamos la cadena de las cajas de texto
        final String user = etUsername.getText().toString();
        final String pass = etPassword.getText().toString();

        //Mostramos el loading
        pbLoading.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pbLoading.setVisibility(View.GONE);

                if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass)){
                    //Consultamos en la BD para validar sus credenciales. //Ejercicio 2
                    ModelUser modelUser = userDataSource.getUser(user,pass);

                    if(modelUser != null){
                        //Mostramos mensaje de éxito
                        Toast.makeText(getApplicationContext(), R.string.main_authenticated, Toast.LENGTH_SHORT).show();
                        //Enviamos al usuario a la Activity de Perfil y agregamos su nombre como parámetro
                        Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                        intent.putExtra(Key.KEY_USER, user);

                        //Enviamos el dato del último inicio de sesión y guardamos el nuevo. //Ejercicio 2
                        intent.putExtra(Key.KEY_LAST_SESSION, modelUser.lastLogin);
                        modelUser.lastLogin = new SimpleDateFormat("dd-MMM-yy hh:mm:ss").format(new Date());
                        userDataSource.updateUser(modelUser);

                        startActivity(intent);

                        //Para usar el servicio de conteo
                        startService(new Intent(getApplicationContext(), ServiceTimer.class));

                        //Si el checkbox de recordar credenciales está seleccionado, guardamos datos en preferencias
                        //TODO

                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.main_errorLogin, Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.main_txtRegisterNeed, Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000 * 1);
    }
}

