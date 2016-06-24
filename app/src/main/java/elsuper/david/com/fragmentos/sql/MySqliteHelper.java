package elsuper.david.com.fragmentos.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Andrés David García Gómez
 */
public class MySqliteHelper extends SQLiteOpenHelper{

    private final static String DATABASE_NAME = "unamsqlite";
    private final static int DATABASE_VERSION = 1;
    public final static String COLUMN_ID = BaseColumns._ID;

    //Tabla item
    public final static String TABLENAME_ITEM = "item_table";
    public final static String COLUMN_ITEM_NAME = "name";
    public final static String COLUMN_ITEM_DESC = "description";
    public final static String COLUMN_ITEM_RESOURCE = "resource_id";
    private final static String CREATE_TABLE_ITEM = "create table " + TABLENAME_ITEM +
            "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_ITEM_NAME + " text not null, " +
            COLUMN_ITEM_DESC + " text not null, " +
            COLUMN_ITEM_RESOURCE + " integer not null)";

    //Tabla user
    public final static String TABLENAME_USER = "user_table";
    public final static String COLUMN_USER_LOGIN = "login";
    public final static String COLUMN_USER_PASSWORD = "password";
    public final static String COLUMN_USER_LASTLOGIN = "last_login";
    private final static String CREATE_TABLE_USER = "create table " + TABLENAME_USER +
            "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_USER_LOGIN + " text not null, " +
            COLUMN_USER_PASSWORD + " text not null, " +
            COLUMN_USER_LASTLOGIN + " text not null)";

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d(ServiceTimer.TAG,"OnUpgrade SQL from "+oldVersion+ " to "+newVersion);
    }
}
