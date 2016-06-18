package elsuper.david.com.fragmentos;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

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

        //Les ponemos sus valores
        String title = getIntent().getExtras().getString("key_title");
        String description = getIntent().getExtras().getString("key_description");

        txtTitle.setText(title);
        txtDescription.setText(description);

        //Para la imagen, generamos un número aleatorio
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
