package elsuper.david.com.fragmentos.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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

import java.util.List;

import elsuper.david.com.fragmentos.model.ModelItem;
import elsuper.david.com.fragmentos.R;
import elsuper.david.com.fragmentos.SummaryActivity;
import elsuper.david.com.fragmentos.adapter.AdapterItemList;
import elsuper.david.com.fragmentos.sql.ItemDataSource;
import elsuper.david.com.fragmentos.util.Key;

/**
 * Created by Andrés David García Gómez
 */
public class FragmentList extends Fragment {

    private ListView listView;
    private int counter;
    private boolean isWifi;
    //Para usar la tabla item_table
    private ItemDataSource itemDataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Instanciamos para usar la tabla item_table
        itemDataSource = new ItemDataSource(getActivity());
    }

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

                //ModelItem modelItem2 = array.get(position); Para obtener los datos del Ejercicio1
                //Toast.makeText(getActivity(),modelItem.title, Toast.LENGTH_SHORT).show();

                //Lo mandamos a la activity de Resumen y agregamos Extras
                Intent intent = new Intent(getActivity().getApplicationContext(), SummaryActivity.class);
                intent.putExtra(Key.KEY_TITLE, modelItem.title);
                intent.putExtra(Key.KEY_DESCRIPTION, modelItem.description);
                //intent.putExtra("key_resourceId", modelItem2.title);
                startActivity(intent);
            }
        });

        //Obtenemos los items de la tabla item_table
        List<ModelItem> modelItemList = itemDataSource.getAllItems();
        isWifi = !(modelItemList.size() %2 == 0);
        counter = modelItemList.size();
        listView.setAdapter(new AdapterItemList(getActivity(),modelItemList));

        //Bloque para borrar el registro seleccionado al dejar mantener el click sobre el elemento
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter = (AdapterItemList)parent.getAdapter();
                final ModelItem modelItem = adapter.getItem(position);

                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.fraglist_txtDeleteTitle)
                        .setMessage(String.format(getString(R.string.fraglist_txtQuestionDelete), modelItem.title))
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemDataSource.deleteItem((modelItem));
                                listView.setAdapter(new AdapterItemList(getActivity(),
                                        itemDataSource.getAllItems()));
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                }).setCancelable(false).create().show();
                return true;
            }
        });


        final EditText etItemText = (EditText)view.findViewById(R.id.fraglist_ItemText);

        //Bloque para agregar un item a la lista
        view.findViewById(R.id.fraglist_btnAddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemData = etItemText.getText().toString();

                //Si tiene texto lo mandamos al adapterList como un registro más
                if(!TextUtils.isEmpty(itemData)){
                    ModelItem item = new ModelItem();
                    item.title =  itemData;
                    item.description = String.format(getString(R.string.fraglist_txtDescription), counter);
                    item.resourceId = isWifi ? R.mipmap.ic_launcher : R.mipmap.ic_launcher2;
                    //Guardamos en la tabla item_table
                    itemDataSource.saveItem(item);
                    //Le ponemos los datos actuales
                    listView.setAdapter(new AdapterItemList(getActivity(),itemDataSource.getAllItems()));

                    isWifi = !isWifi;
                    counter++;
                    etItemText.setText("");
                }
            }
        });
        return view;
    }
}