//fist right click res,  choose new ,-> android  resource directory , select resorce type raw, then copy mpe file to this fold.
//
package com.example.admin.testaudio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mySound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySound = MediaPlayer.create(this,R.raw.demo);
        mySound.start();
    }
}
