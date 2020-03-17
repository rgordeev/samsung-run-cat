package ru.rgordeev.runcat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Worker extends Thread {

    private final Bitmap image;
    private final SurfaceHolder surfaceHolder;
    private final Context context;
    private float x;
    private float y;

    public Worker(Context context, SurfaceHolder surfaceHolder) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.cat);
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        float dx = 0, dy = 0;
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        while (!isInterrupted()) {
            Canvas canvas = surfaceHolder.lockCanvas();

            try {
                canvas.drawPaint(paint);
                canvas.drawBitmap(image, dx, dy, paint);
                dx = moveX(dx);
                dy = moveY(dy);
            } finally {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private float moveX(float dx) {
        if (dx < this.x) return dx + 5;
        else return dx - 5;
    }

    private float moveY(float dy) {
        if (dy < this.y) return dy + 5;
        else return dy - 5;
    }
}
