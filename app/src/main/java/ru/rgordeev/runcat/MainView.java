package ru.rgordeev.runcat;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback {

    private Worker worker;

    public MainView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.worker = new Worker(getContext(), holder);
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
            Log.e("ERROR", "error", e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.worker.moveTo(event.getX(), event.getY());
        return false;
    }
}
