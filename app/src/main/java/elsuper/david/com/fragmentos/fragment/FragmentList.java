package elsuper.david.com.fragmentos.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import elsuper.david.com.fragmentos.Model.ModelItem;
import elsuper.david.com.fragmentos.R;
import elsuper.david.com.fragmentos.SummaryActivity;
import elsuper.david.com.fragmentos.adapter.AdapterItemList;

/**
 * Created by Andrés David García Gómez
 */
public class FragmentList extends Fragment {

    private ListView listView;
    private List<ModelItem> array = new ArrayList<>();
    private int counter;
    private boolean isWifi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflamos la vista
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        //Obtenemos el control de lista
        listView = (ListView)view.findViewById(R.id.fraglist_listItems);
        //Manejamos su evento click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Accedemos a la información del item
                AdapterItemList adapter = (AdapterItemList)parent.getAdapter();
                ModelItem modelItem = adapter.getItem(position);
                ModelItem modelItem2 = array.get(position);
                //Toast.makeText(getActivity(),modelItem2.title, Toast.LENGTH_SHORT).show();

                //Lo mandamos a la activity de Resumen
                Intent intent = new Intent(getActivity().getApplicationContext(), SummaryActivity.class);
                intent.putExtra("key_title", modelItem2.title);
                intent.putExtra("key_description", modelItem2.description);
                //intent.putExtra("key_resourceId", modelItem2.title);
                startActivity(intent);
            }
        });

        final EditText etItemText = (EditText)view.findViewById(R.id.fraglist_ItemText);
        view.findViewById(R.id.fraglist_btnAddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemData = etItemText.getText().toString();

                //Si tiene texto lo mandamos al adapterList como un registro más
                if(!TextUtils.isEmpty(itemData)){
                   ModelItem item = new ModelItem();
                    item.title =  "Desc " + counter;
                    item.description = itemData;
                    item.resourceId = isWifi ? R.mipmap.ic_launcher : R.mipmap.ic_launcher2;
                    array.add(item);
                    listView.setAdapter(new AdapterItemList(getActivity(),array));
                    isWifi = !isWifi;
                    counter++;
                    etItemText.setText("");
                }
            }
        });


        return view;
    }
}