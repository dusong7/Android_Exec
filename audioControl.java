////Test Timer
import java.util.Timer;
import java.util.TimerTask;


public class test{
	public static void main(String[] args){
		Timer timer;
		timer = new Timer();
		String str = new String("Han");
		
		TimerTask task = new TimerTask(){
			int tick = 0;
			@Override
			public void run()
			{
				if(tick%2 == 0)
					System.out.println("Tick");
				else
					System.out.println("Tock");
			}
		};
		timer.schedule(task, 0,1000);
	}
}

////Audio Control
package com.example.admini.testaudioseekbar;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.util.TimeUtils;
import android.widget.Toast;
import android.media.AudioManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mySound;
    Context m_Context;
    SeekBar skbMusic;
    SeekBar skbVolum;
    Timer mTimer;
    int nCurVolum;
    boolean isPause = false;
    boolean isChangeing = false;
    AudioManager audioManager;
    SeekBar.OnSeekBarChangeListener sChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        mySound = MediaPlayer.create(this, R.raw.demo);
        skbMusic=(SeekBar)findViewById(R.id.seekBar);
        skbVolum=(SeekBar)findViewById(R.id.seekBar2);
        nCurVolum = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        skbVolum.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        String strw  = nCurVolum + "Volume";
        System.out.println(strw);
        skbVolum.setOnSeekBarChangeListener(new SeekBarChangeEvent("volume"));

        skbVolum.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        skbMusic.setOnSeekBarChangeListener(new SeekBarChangeEvent("audio"));
        //skbMusic.setOnSeekBarChangeListener(sChangeListener);
        skbMusic.setMax(mySound.getDuration());
        Timer timer;
        timer = new Timer();
        String str = new String("Han");

        TimerTask task = new TimerTask(){
            int tick = 0;
            int CurPosition;
            int nTotalStep;
            @Override
            public void run() {
                    CurPosition = mySound.getCurrentPosition();
                    skbMusic.setProgress(CurPosition);
                }
        };
        timer.schedule(task, 0,100);
    }

    public void PlayMusic(View view) {
        mySound.start();

    }


    public void PauseMusic(View view) {
        mySound.pause();
    }

     class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener
     {
         boolean isAudio = true;

         SeekBarChangeEvent(String strType)
         {
             if (strType.equals("audio"))
             {
                 isAudio = true;
             }
             if (strType.equals("volume"))
             {
                 isAudio = false;
             }
         }
         @Override
         public void onStopTrackingTouch(SeekBar seekBar)
         {
             if (isAudio)
             {
                 mySound.seekTo(seekBar.getProgress());
             }
             else
             {
                 audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,seekBar.getProgress(),0);
             }

             isChangeing = false;
         }
         @Override
         public void onStartTrackingTouch(SeekBar seekBar)
         {
            isChangeing = true;
         }
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
         {
            //
         }
     }
}
