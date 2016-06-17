package com.example.admin.testdb;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends AppCompatActivity {

    EditText editRegUserName;
    EditText editRegUserPassword;
    EditText editRegSafeCode;
    Person info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        Intent intent=getIntent();
        String StringRegFromMain=intent.getStringExtra("extra");
        TextView textRegFromMain=(TextView)findViewById(R.id.editTextRegUserName);
        textRegFromMain.setText(StringRegFromMain);
    }

    public void Regist(View view)
    {
        SQLiteDatabase db = openOrCreateDatabase("info.db", Context.MODE_PRIVATE, null);

        editRegUserName = (EditText) findViewById(R.id.editTextRegUserName);
        editRegUserPassword = (EditText) findViewById(R.id.editTextRegUserPasswd);
        editRegSafeCode = (EditText) findViewById(R.id.editTextRegSafeCode);
        String strRegUserName = editRegUserName.getText().toString();
        String strRegUserPassword = editRegUserPassword.getText().toString();
        String strRegSafeCode = editRegSafeCode.getText().toString();
        int nIsExistName = 0;


        Cursor c = db.rawQuery("SELECT * FROM person", null);
        while (c.moveToNext())
        {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            String password = c.getString(c.getColumnIndex("password"));

            if (strRegUserName.equals(name))
            {
                Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show();
            }
            else
            {
                nIsExistName++;
                if (nIsExistName == c.getCount())
                {
                    if (strRegUserName.equals(""))
                    {
                        Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    }

                    if (strRegUserPassword.equals(""))
                    {
                        Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                    if (strRegSafeCode.equals(""))
                    {
                        Toast.makeText(this, "安全码不能为空", Toast.LENGTH_SHORT).show();
                    }
                    if (strRegSafeCode.length() != 6)
                    {
                        Toast.makeText(this, "安全码长度不符合规则", Toast.LENGTH_SHORT).show();
                    }
                    if (!(strRegUserName.equals("")) && !(strRegUserPassword.equals("")) && !(strRegSafeCode.equals("")) &&(strRegSafeCode.length() == 6))
                    {
                        db.execSQL("INSERT INTO person VALUES (NULL, ?, ?, ?)", new Object[]{strRegUserName, strRegUserPassword, strRegSafeCode});
                        Toast.makeText(this, "注册完成", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
//        db.close();
    }
}
