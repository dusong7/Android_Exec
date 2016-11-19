package com.example.admin.surface_;

/**
 * Created by Admin on 11/17/2016.
 */



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

public class surView extends SurfaceView  implements Callback{

    LoopThread thread;
    int nCirclePosX;
    int nCirclePosY;
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
                        nCirclePosY += 10;
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
            c.drawColor(Color.WHITE);

            DrawScore(c);

            Paint paintT = new Paint();
            paintT.setColor(Color.BLACK);
            paintT.setTextSize(50);
            String strPosY = "";
            strPosY = nCirclePosY + "";
            c.drawText(strPosY,100,200,paintT);

            c.drawCircle(nCirclePosX,nCirclePosY,100, paint);

            if(radius > 100){
                radius = 10f;
            }

//            if(nCirclePosY > 800){
//                nCirclePosY = 20;
//            }
        }

        public void DrawScore(Canvas c)
        {
            c.drawColor(Color.WHITE);

            c.translate(300, 100);

            Paint paintT = new Paint();
            paintT.setColor(Color.BLACK);
            paintT.setTextSize(50);
            String strPosY = "";
            strPosY = nCirclePosY + "";
            c.drawText(strPosY,100,200,paintT);
        }

    }

}
