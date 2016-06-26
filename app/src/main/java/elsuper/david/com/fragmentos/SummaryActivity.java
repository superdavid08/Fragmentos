package elsuper.david.com.fragmentos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import elsuper.david.com.fragmentos.util.Key;

/**
 * Created by Andrés David García Gómez
 */
public class SummaryActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtDescription;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //Obtenemos los controles
        txtTitle = (TextView)findViewById(R.id.summary_textTitle);
        txtDescription = (TextView)findViewById(R.id.summary_textDescription);
        img = (ImageView)findViewById(R.id.summary_img);

        //Les ponemos sus valores con la información que viene en los Extras
        String title = getIntent().getExtras().getString(Key.KEY_TITLE);
        String description = getIntent().getExtras().getString(Key.KEY_DESCRIPTION);

        txtTitle.setText(title);
        txtDescription.setText(description);

        //Para la imagen, generamos un número aleatorio y, según el número, se muestra una imagen diferente
        Random random = new Random();
        int num = (int)(random.nextDouble() * 6);

        switch (num) {
            case 1:case 0:
                img.setImageResource(R.mipmap.ic_launcher);
                break;
            case 2:
                img.setImageResource(R.mipmap.ic_launcher2);
                break;
            case 3:
                img.setImageResource(R.mipmap.ic_launcher3);
                break;
            case 4:
                img.setImageResource(R.mipmap.ic_launcher4);
                break;
            case 5:
                img.setImageResource(R.mipmap.ic_launcher5);
                break;
        }
    }
}
