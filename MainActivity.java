package com.example.admini.apptestdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edt1,edt2;
    Myhelper myhelper;
    CheckBox cBox;
    SharedPreferences sp;
    Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=(EditText) findViewById(R.id.editText1);
        edt2=(EditText) findViewById(R.id.editText2);
        cBox=(CheckBox) findViewById(R.id.checkBox1);
        sp=getPreferences(MODE_PRIVATE);
        editor=sp.edit();
        check();
        cBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if (arg0.isChecked()) {
                    editor.putBoolean("chb1", true);
                    editor.commit();
                    cBox.setChecked(true);
                } else {
                    editor.putBoolean("chb1", false);
                    editor.clear();
                    editor.commit();
                    cBox.setChecked(false);
                }
            }
        });
    }

    public void bt(View v){
        switch (v.getId()) {
            case R.id.butd:
                String ed1=edt1.getText().toString();
                String ed2=edt2.getText().toString();
                myhelper = new Myhelper(this,"QQ", null, 1);
                String sqlQuery="select * from QQ";
                Cursor cursor=myhelper.getReadableDatabase().rawQuery(sqlQuery, null);
                if (cursor !=null) {
                    int frog=0;
                    while(cursor.moveToNext()){
                        if (cursor.getString(cursor.getColumnIndex("name")).equals(ed1)&&
                                cursor.getString(cursor.getColumnIndex("password")).equals(ed2)) {
                            editor.putString("name", ed1);
                            editor.putString("password", ed2);
                            editor.commit();
                            frog=1;
                            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                            //Intent intent=new Intent(MainActivity.this,DlSuccess.class);
                            //startActivity(intent);
                        }
                       // break; 解决第二个用户不能登陆
                    }
                    if (frog==0) {
                        Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
            case R.id.butz:
                Toast.makeText(this, "注册新账号", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
    private void check(){
        cBox.setChecked(sp.getBoolean("chb1", false));
        if (cBox.isChecked()) {
            //edt1.setText(sp.getString("name", "").toString());
            //edt2.setText(sp.getString("password", "").toString());

        }
    }
}
