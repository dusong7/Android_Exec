//fist right click res,  choose new ,-> android  resource directory , select resorce type raw, then copy mpe file to this fold.
//https://www.youtube.com/watch?v=V1ocJmXeQ28
package com.example.admin.testaudio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mySound;
    //在此处右击选择generate 选择override 选择onPause(), home键后声音不在播放
    //在xml文件中onclick后点击灯泡形状可以自动添加onclick事件。
   @Override
    protected void onPause() {
        super.onPause();
        mySound.release();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySound = MediaPlayer.create(this,R.raw.demo);
        mySound.start();
    }
}
