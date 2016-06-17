package com.example.admin.testdb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FindPasswdActivity extends AppCompatActivity {

    EditText editFindUserName;
    EditText editFindSafeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_passwd);

        Intent intent=getIntent();
        String StringFindFromMain=intent.getStringExtra("extra");
        TextView textFoundFromMain=(TextView)findViewById(R.id.editTextFindUserName);
        textFoundFromMain.setText(StringFindFromMain);

    }

    public void FindPasswd(View view)
    {
        SQLiteDatabase db = openOrCreateDatabase("info.db", Context.MODE_PRIVATE, null);

        editFindUserName = (EditText) findViewById(R.id.editTextFindUserName);
        editFindSafeCode = (EditText) findViewById(R.id.editTextFindSafeCode);

        String strFindUserName = editFindUserName.getText().toString();
        String strFindSafeCode = editFindSafeCode.getText().toString();
        int nNoName = 0;
        Cursor c = db.rawQuery("SELECT * FROM person", null);
        while (c.moveToNext())
        {
            int _id = c.getInt(c.getColumnIndex("_id"));
            String name = c.getString(c.getColumnIndex("name"));
            String safecode = c.getString(c.getColumnIndex("safecode"));
            String password = c.getString(c.getColumnIndex("password"));
            if (name.equals(strFindUserName))
            {
                if (safecode.equals(strFindSafeCode))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(password);

                    builder.setTitle("你的密码：");

                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    builder.create().show();
                }
                else
                {
                    Toast.makeText(this, "安全码错误！", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                nNoName++;
                if (nNoName == c.getCount())
                {
                    Toast.makeText(this, "输入用户不存在！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
