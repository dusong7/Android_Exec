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

///////////////////NEW
package com.example.admin.testaudio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mySound;
    boolean isReleased = false;
    boolean isPaused = false;
    @Override
    protected void onPause() {
        super.onPause();
//        mySound.release();
//        isReleased= true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySound = MediaPlayer.create(this,R.raw.demo);
        getSupportActionBar().hide();
    }

    public void playMusic(View view) {

           Button btn = (Button)this.findViewById(R.id.button);


        String strCurBtn = btn.getText().toString();
        switch (strCurBtn)
        {
            case "Pause":
                btn.setText("Play");
                mySound.pause();
                break;
            case "Play":
                btn.setText("Pause");
                mySound.start();
                break;
            default:
                break;
        }

    }

}

///New Way
package com.example.admin.testaudio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mySound;
    boolean isReleased = false;
    boolean isPaused = false;
    int nClickTimes = 0;
    @Override
    protected void onPause() {
        super.onPause();
//        mySound.release();
//        isReleased= true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySound = MediaPlayer.create(this, R.raw.demo);
        getSupportActionBar().hide();
    }

    public void playMusic(View view) {
        ImageButton btn = (ImageButton)this.findViewById(R.id.button);

//        String strCurBtn = btn.getText().toString();
        Boolean isBtnClicked = btn.isPressed();
        if (isBtnClicked)
        {
            nClickTimes++;
        }

        if (nClickTimes%2 == 1)
        {
            mySound.start();
            btn.setImageDrawable(getResources().getDrawable(R.drawable.pause));;
         }
        else
        {
            mySound.pause();
            btn.setImageDrawable(getResources().getDrawable(R.drawable.play));
        }
//        switch (isBtnClicked)
//        {
//            case false:
//
//                btn.setImageDrawable(getResources().getDrawable(R.drawable.play));
//                mySound.pause();
//                break;
//            case "Play":
//
//                btn.setImageDrawable(getResources().getDrawable(R.drawable.pause));
//                mySound.start();
//                break;
//            default:
//                break;
//        }


    }
}
