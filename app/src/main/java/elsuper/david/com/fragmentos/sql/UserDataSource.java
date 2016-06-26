package elsuper.david.com.fragmentos.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import elsuper.david.com.fragmentos.model.ModelUser;

/**
 * Created by Andrés David García Gómez //Ejercicio 2
 */
public class UserDataSource {

    private final SQLiteDatabase db;

    public UserDataSource(Context context) {
        MySqliteHelper helper = new MySqliteHelper(context);
        db = helper.getWritableDatabase();
    }

    public long saveUser(ModelUser modelUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_USER_LOGIN,modelUser.userName);
        contentValues.put(MySqliteHelper.COLUMN_USER_PASSWORD,modelUser.password);
        contentValues.put(MySqliteHelper.COLUMN_USER_LASTLOGIN,modelUser.lastLogin);
        long result = db.insert(MySqliteHelper.TABLENAME_USER, null, contentValues);

        return result;
    }

    public List<ModelUser> getAllUsers(){
        List<ModelUser> modelUserList = new ArrayList<>();
        Cursor cursor = db.query(MySqliteHelper.TABLENAME_USER,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            ModelUser model = new ModelUser();
            model.id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            model.userName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LOGIN));
            model.password = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PASSWORD));
            model.lastLogin = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTLOGIN));
            modelUserList.add(model);
        }

         return  modelUserList;
    }

    public void deleteUser(ModelUser modelUser){
        db.delete(MySqliteHelper.TABLENAME_USER, MySqliteHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(modelUser.id)});
    }

    public ModelUser getUser(String login){
        Cursor cursor = db.query(MySqliteHelper.TABLENAME_USER, null, MySqliteHelper.COLUMN_USER_LOGIN + "=?",
                        new String[]{login},null,null,null);

       if(cursor.moveToFirst()){
            ModelUser modelUser = new ModelUser();
            modelUser.id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            modelUser.userName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LOGIN));
            modelUser.password = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PASSWORD));
            modelUser.lastLogin = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTLOGIN));

           return modelUser;
        }

        return null;
    }

    public ModelUser getUser(String login, String password){
        Cursor cursor = db.query(MySqliteHelper.TABLENAME_USER, null,
                MySqliteHelper.COLUMN_USER_LOGIN + "=? and " + MySqliteHelper.COLUMN_USER_PASSWORD + "=?",
                new String[]{login,password},null,null,null);

        if(cursor.moveToFirst()){
            ModelUser modelUser = new ModelUser();
            modelUser.id = cursor.getInt(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            modelUser.userName = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LOGIN));
            modelUser.password = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_PASSWORD));
            modelUser.lastLogin = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_USER_LASTLOGIN));

            return modelUser;
        }

        return null;
    }

    public boolean updateUser(ModelUser modelUser){

        boolean success;

        ContentValues contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_USER_LOGIN,modelUser.userName);
        contentValues.put(MySqliteHelper.COLUMN_USER_PASSWORD,modelUser.password);
        contentValues.put(MySqliteHelper.COLUMN_USER_LASTLOGIN,modelUser.lastLogin);

        success = db.update(MySqliteHelper.TABLENAME_USER, contentValues, MySqliteHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(modelUser.id)}) == 1 ? true : false;

        return success;
    }
}

