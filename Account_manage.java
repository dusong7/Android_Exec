package com.example.admin.testdb;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class AccountManageActivity extends AppCompatActivity {

    SimpleAdapter listItemAdapter;
    ArrayList<HashMap<String, Object>> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manage);
        ListView list = (ListView) findViewById(R.id.listView);

        ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();
//        for(int i=0;i<30;i++)
//        {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("ItemTitle", "This is Title.....");
//            map.put("ItemText", "This is text.....");
//            mylist.add(map);
//        }

        SQLiteDatabase db = openOrCreateDatabase("info.db", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        int columnsSize = c.getColumnCount();
        while (c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            String password = c.getString(c.getColumnIndex("password"));

            HashMap<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < columnsSize; i++) {
                map.put("id", c.getString(0));
                map.put("name", c.getString(1));
                map.put("password", c.getString(2));
                map.put("safecode", c.getString(3));
            }
            mylist.add(map);
        }
            //生成适配器，数组===》ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this, //没什么解释
                mylist,//数据来源
                R.layout.activity_account_manage,//ListItem的XML实现
                //动态数组与ListItem对应的子项
                new String[] {"name", "password", "safecode"},
                //ListItem的XML文件里面的两个TextView ID
                new int[] {R.id.name,R.id.password, R.id.safecode});
        //添加并且显示
        list.setAdapter(mSchedule);
    }

}
