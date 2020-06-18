package sam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Mydatabase extends SQLiteOpenHelper {

    public static final String dbname ="Usersdatabase.db";
    public static final String tablename ="Users";
    public static final String columnname ="Name";
    private static final String createtable = "create table " + tablename + "(" + columnname + " TEXT NOT NULL" + ")";
    SQLiteDatabase db;

    public Mydatabase(Context context) {
        super(context, dbname, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createtable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
    }
    public void addNames(String username)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(columnname,username);
        db.insert(tablename,null,contentValues);
        db.close();
        getUsers();

    }
    private void getUsers() {
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+tablename,null);
        Log.d( "//CURSOR: ", String.valueOf(cursor.getCount()));
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            cursor.getString(cursor.getColumnIndex(columnname));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
    }
  //  public  static int deleteUsername(String name) {
    //   db.delete(tablename, columnnameclient+"="+, new String[]{(columnnameclient)});
    //}


}
