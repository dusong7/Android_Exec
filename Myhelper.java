package com.example.admini.apptestdb;

/**
 * Created by Admini on 6/20/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Myhelper extends SQLiteOpenHelper{
    private SQLiteDatabase database;
    public Myhelper(Context context, String name, CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        database=getReadableDatabase();

    }
    public SQLiteDatabase getDataBase(){
        return database;

    }
    public void onCreate(SQLiteDatabase db) {
        String sql="create table QQ"
                +"(_id integer primary key autoincrement,"
                +"name text,"
                +"password real)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}
