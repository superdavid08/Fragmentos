package elsuper.david.com.fragmentos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import elsuper.david.com.fragmentos.model.ModelUser;
import elsuper.david.com.fragmentos.sql.UserDataSource;

/**
 * Created by Andrés David García Gómez
 */
public class RegisterActivity extends AppCompatActivity {

    //Para usar la tabla user_table //Ejercicio 2
    private UserDataSource userDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Creamos la instancia para acceder a la tabla user_table //Ejercicio 2
        userDataSource = new UserDataSource(getApplicationContext());

        final EditText etUser = (EditText)findViewById(R.id.reg_etUser);
        final EditText etPassword = (EditText)findViewById(R.id.reg_etPassword);

        //Registrar al usuario
        findViewById(R.id.reg_btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String password = etPassword.getText().toString();

                //Validamos que no tenga valores nulos
                if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){

                    //Validamos que no exista el username (login) en la BD. //Ejercicio 2
                    if(userDataSource.getUser(user) != null) {
                        Toast.makeText(getApplicationContext(),
                                String.format(getString(R.string.reg_txtUserExist), user),
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Guardamos al usuario en DB Ejercicio 2
                    long success = userDataSource.saveUser(new ModelUser(user,password,""));

                    if(success != -1)
                        Toast.makeText(getApplicationContext(),R.string.reg_txtSuccess, Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),R.string.reg_txtError, Toast.LENGTH_SHORT).show();

                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.reg_txtEmpty, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
