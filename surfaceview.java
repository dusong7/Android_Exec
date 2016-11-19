package com.example.admin.surface_;

/**
 * Created by Admin on 11/17/2016.
 */


//    surView view_;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        view_ = (surView) findViewById(R.id.surView_);
//    }

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.io.Console;
import java.util.Random;

public class surView extends SurfaceView  implements Callback{

    LoopThread thread;
    int nCirclePosX;
    int nCirclePosY;

    int nReached = 0;
    int nOriRecX = 600;
    int nOriRecY = 500;
    int nRecPosX;
    int nRecPosY;
    int nTabX[] = {2,6,2,2,9,2,7,3,8,2,7,7,5,8,5,3,10,6,4,8,3,10,3,9,3,7,9,2,6,7,5,9,4,8,10,8,2,};
    int nTabY[] = {10,5,1,6,6,10,7,9,1,3,5,9,4,8,10,9,0,3,5,9,10,3,2,10,4,1,8,3,1,4,10,7,7,10,5,1};
    Canvas c = null;

    public surView(Context context, AttributeSet attrs) {
        super(context);

        init(); //初始化,设置生命周期回调方法

    }

    private void init(){

        SurfaceHolder holder = getHolder();
        holder.addCallback(this); //设置Surface生命周期回调
        thread = new LoopThread(holder, getContext());


        nCirclePosX = 100;
        nCirclePosY = 100;
        nRecPosX = 600;
        nRecPosY = 300;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRunning = true;
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Toast.makeText(getContext(), "默认Toast样式", Toast.LENGTH_SHORT).show();
        //nCirclePosX += 10;
        nCirclePosY -= 100;
        return super.onTouchEvent(event);
    }

    /**
     * 执行绘制的绘制线程
     * @author Administrator
     *
     */
    class LoopThread extends Thread{

        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRunning;
        float radius = 10f;
        Paint paint;

        public LoopThread(SurfaceHolder surfaceHolder,Context context){

            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;

            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public void run() {
            //Canvas c = null;

            while(isRunning){

                try{
                    synchronized (surfaceHolder) {

                        c = surfaceHolder.lockCanvas(null);
                        doDraw(c);

                        //通过它来控制帧数执行一次绘制后休息50ms
                        Thread.sleep(50);
                        nCirclePosY = nCirclePosY + nReached/5 + 10;

                        nRecPosX = nRecPosX - nReached/2 -  5;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }

            }

        }

        public void doDraw(Canvas c){

            //这个很重要，清屏操作，清楚掉上次绘制的残留图像
            c.drawColor(Color.CYAN);

            DrawScore(c);


            c.drawCircle(nCirclePosX,nCirclePosY,50, paint);

            if(radius > 100){
                radius = 10f;
            }

//            if(nCirclePosY > 800){
//                nCirclePosY = 20;
//            }
        }

        public void DrawScore(Canvas c)
        {
            //c.drawColor(Color.WHITE);

            Paint paintT = new Paint();
            paintT.setColor(Color.BLACK);
            paintT.setTextSize(50);
            String strPosY = "";
            strPosY ="当前Y距离 "  + nCirclePosY;
            c.drawText(strPosY,300,50,paintT);
            drawRectangle(c);
        }

        public void drawRectangle(Canvas canvas)
        {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);

            canvas.drawRect(nRecPosX-50,nRecPosY-50,nRecPosX,nRecPosY,paint);

            //canvas.drawRect(nRecPosX-50,350,nRecPosX,400,paint);
            //canvas.drawArc(300,300,350,350,10,100,false,paint);
            //System.out.println(nRecPosX);

            if (nRecPosX < 0)
            {
                nReached += 1;

                int nNumber;
                Random random = new Random();
                nNumber = random.nextInt(11);

                nRecPosX = nOriRecX - 20 * nNumber;
                nRecPosY = nOriRecY + 50 * nNumber;
                System.out.println(nReached);
                System.out.println(nRecPosX);
                System.out.println(nRecPosY);
            }
        }
    }

}
