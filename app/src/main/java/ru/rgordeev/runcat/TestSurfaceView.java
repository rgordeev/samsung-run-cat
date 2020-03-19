package ru.rgordeev.runcat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Worker worker;

    public TestSurfaceView (Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.worker = new Worker(holder);
        worker.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        worker.interrupt();
        try {
            worker.join();
        } catch (InterruptedException e) {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.worker.moveTo(event.getX(), event.getY());
        return false;
    }
}

class Worker extends Thread {

    private final SurfaceHolder surfaceHolder;
    private float x;
    private float y;
    private float r;

    public Worker(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.x = -1000000;
        this.y = -1000000;
        this.r = 0;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
        this.r = 0;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            try {
                Paint paint = new Paint();
                paint.setColor(Color.BLUE);
                canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
                paint.setColor(Color.YELLOW);
                canvas.drawCircle(x, y, r, paint);
                r = r + 5;
            } finally {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
