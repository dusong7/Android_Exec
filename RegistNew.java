package com.example.admin.testdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        Intent intent=getIntent();
        String StringRegFromMain=intent.getStringExtra("extra");
        TextView textRegFromMain=(TextView)findViewById(R.id.editTextRegUserName);
        textRegFromMain.setText(StringRegFromMain);
    }
}
