package elsuper.david.com.fragmentos.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import elsuper.david.com.fragmentos.model.ModelItem;

/**
 * Created by Andrés David García Gómez
 */
public class ItemDataSource {

    private final SQLiteDatabase db;

    public ItemDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    public void saveItem(ModelItem modelItem){
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySqliteHelper.COLUMN_ITEM_NAME,modelItem.title);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_DESC,modelItem.description);
        contentValues.put(MySqliteHelper.COLUMN_ITEM_RESOURCE,modelItem.resourceId);

        db.insert(MySqliteHelper.TABLENAME_ITEM,null,contentValues);
    }

    public List<ModelItem> getAllItems(){
        List<ModelItem> modelItemList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLENAME_ITEM,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            ModelItem model = new ModelItem();
            model.id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            model.title = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_NAME));
            model.description = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_DESC));
            model.resourceId = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ITEM_RESOURCE));
            modelItemList.add(model);
        }

        return modelItemList;
    }

    public void deleteItem(ModelItem modelItem){
        //db.delete(MySqliteHelper.TABLE_NAME,MySqliteHelper.COLUMN_ID + "=? and var2 =?",) //Para otra columna
        db.delete(MySqliteHelper.TABLENAME_ITEM,MySqliteHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(modelItem.id)});
    }
}