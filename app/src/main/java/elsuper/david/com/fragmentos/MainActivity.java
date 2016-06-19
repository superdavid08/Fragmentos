package elsuper.david.com.fragmentos;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Andrés David García Gómez
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUsername;
    private EditText etPassword;
    private View pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtenemos los controles y seteamos el escucha del botón
        etUsername =  (EditText)findViewById(R.id.main_etUsername);
        etPassword =  (EditText)findViewById(R.id.main_etPassword);
        findViewById(R.id.main_btnLogin).setOnClickListener(this);
        pbLoading=findViewById(R.id.main_pbLoading);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btnLogin:
                processData();
                break;
        }
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
                //Validamos con algunos usuarios específicos
                if((user.toLowerCase().equals("unam") || user.toLowerCase().equals("david") ||
                        user.toLowerCase().equals("giselle") || user.toLowerCase().equals("norma") ||
                        user.toLowerCase().equals("andres")) && pass.equals("curso")){
                    //Mostramos mensaje de éxito
                    Toast.makeText(getApplicationContext(), R.string.main_authenticated, Toast.LENGTH_SHORT).show();
                    //Enviamos al usuario a la Activity de Perfil y agregamos su nombre como parámetro
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    intent.putExtra("keyUser", user);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), R.string.main_errorLogin, Toast.LENGTH_SHORT).show();
            }
        }, 1000 * 1);
    }
}

