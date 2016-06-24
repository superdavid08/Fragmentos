package elsuper.david.com.fragmentos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import java.util.List;

import elsuper.david.com.fragmentos.model.ModelItem;
import elsuper.david.com.fragmentos.R;

/**
 * Created by Andrés David García Gómez
 */
public class AdapterItemList extends ArrayAdapter<ModelItem>{

    //urls para probar picasso
    //private final String url1="https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Biblioteca_Central_UNAM_M%C3%A9xico.jpg/250px-Biblioteca_Central_UNAM_M%C3%A9xico.jpg";
    //private final String url2="https://www.unam.mx/sites/default/files/images/menu/library-345273_1280.jpg";

    public AdapterItemList(Context context, List<ModelItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            //Inflamos la vista del adaptador
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_list,parent,false);
        }

        //Obtenemos los controles
        TextView txtItemDescription = (TextView)convertView.findViewById(R.id.adapter_txtItemDescription);
        TextView txtItemTitle = (TextView)convertView.findViewById(R.id.adapter_textItemTitle);
        ImageView imgItem = (ImageView)convertView.findViewById(R.id.adapter_rowImage);

        //Seteamos los datos en el row
        ModelItem modelItem = getItem(position);

        //urls para probar picasso
        /*Picasso.with(getContext()).load(modelItem.resourceId==R.mipmap.ic_launcher2?
                url1:url2).into(imgItem);*/


        txtItemTitle.setText(modelItem.title);
        txtItemDescription.setText(modelItem.description);
        imgItem.setImageResource(modelItem.resourceId);
        return convertView;
    }
}


