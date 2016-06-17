package com.example.admin.testdb;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextUserPasswd;
    boolean isCurPasswdForget = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //打开或创建test.db数据库
        SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS person");
        //创建person表
        db.execSQL("CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR UNIQUE, age SMALLINT)");
        Person person = new Person();
        person.name = "john";
        person.age = 30;
        //插入数据
        db.execSQL("INSERT INTO person VALUES (NULL, ?, ?)", new Object[]{person.name, person.age});

        person.name = "david";
        person.age = 33;
        //ContentValues以键值对的形式存放数据
        ContentValues cv = new ContentValues();
        cv.put("name", person.name);
        cv.put("age", person.age);
        //插入ContentValues中的数据
        db.insert("person", null, cv);

        cv = new ContentValues();
        cv.put("age", 35);
        //更新数据
        db.update("person", cv, "name = ?", new String[]{"john"});

        Cursor c = db.rawQuery("SELECT * FROM person WHERE age >= ?", new String[]{"33"});
        while (c.moveToNext()) {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            Log.i("db", "_id=>" + _id + ", name=>" + name + ", age=>" + age);
        }
        c.close();

        //删除数据
//        db.delete("person", "age < ?", new String[]{"35"});

        //关闭当前数据库
        db.close();

        //删除test.db数据库
//		deleteDatabase("test.db");
    }

    public void Regist(View view)
    {
        Intent intent = new Intent(this,RegistActivity.class);
        startActivity(intent);
    }


    public void FoudPasswd(View v) {
        if (isCurPasswdForget)
        {
            editTextUserName = (EditText) findViewById(R.id.editTextUserName);
            String strUserName = editTextUserName.getText().toString();
            Intent intent=new Intent();
            intent.putExtra("extra",strUserName );
            intent.setClass(MainActivity.this, FoudPasswdActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this,FoudPasswdActivity.class);
            startActivity(intent);
        }
    }

    public void login(View view) {
        boolean isRightUser = false;
        boolean isExistUser = false;
        int nNoName = 0;
        SQLiteDatabase db = openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextUserPasswd = (EditText) findViewById(R.id.editTextUserPasswd);
        String strUserName = editTextUserName.getText().toString();
        String strUserPassword = editTextUserPasswd.getText().toString();

        if (strUserName.equals("")) {
            Toast.makeText(this, "请输入登录账号", Toast.LENGTH_SHORT).show();
        } else {
            if (strUserPassword.equals("")) {
                Toast.makeText(this, "请输入登录密码", Toast.LENGTH_SHORT).show();
            } else {
                Cursor c = db.rawQuery("SELECT * FROM person WHERE age >= ?", new String[]{"33"});
                while (c.moveToNext()) {
                    int _id = c.getInt(c.getColumnIndex("_id"));
                    String name = c.getString(c.getColumnIndex("name"));
                    int age = c.getInt(c.getColumnIndex("age"));
                    Log.i("db", "_id=>" + _id + ", name=>" + name + ", age=>" + age);

                    if (name.equals(strUserName)) {
                        isExistUser = true;
                        String strAge = age + "";

                        if (strUserPassword.equals(strAge)) {
                            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                            isRightUser = true;
                        } else {
                            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                            isCurPasswdForget = true;
                        }
                    } else {
                        nNoName++;
                        if (nNoName == 1 && isExistUser == false) {
                            //Toast.makeText(this, "没有此账户", Toast.LENGTH_SHORT).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setMessage("没有此账户,确认创建？");

                            builder.setTitle("提示");

                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    //Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                                    String strToRegist = editTextUserName.getText().toString();
                                    Intent intent=new Intent();
                                    intent.putExtra("extra",strToRegist );
                                    intent.setClass(MainActivity.this, RegistActivity.class);
                                    startActivity(intent);
                                }
                            });

                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            builder.create().show();
                        }
                    }
                }

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("登录成功，是否继续");

                alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(MainActivity.this, "你选择确定", Toast.LENGTH_LONG).show();
                    }
                });

                alertDialogBuilder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                if (isRightUser) {
                    alertDialog.show();
                }
            }
        }
//        String sqlQuery = "SELECT * FROM person WHERE name=" + strUserName;
//        Cursor c= db.rawQuery(sqlQuery,null);
//        Cursor c = db.rawQuery(sqlQuery, null);
//        while (c.moveToNext()) {
//            int _id = c.getInt(c.getColumnIndex("_id"));
//            String name = c.getString(c.getColumnIndex("name"));
//            int age = c.getInt(c.getColumnIndex("age"));
//
//        }
    }
}
