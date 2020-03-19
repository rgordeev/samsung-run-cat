//package ru.rgordeev.runcat;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.view.SurfaceHolder;
//
//public class Worker extends Thread {
//
//    private final SurfaceHolder surfaceHolder;
//    private final Context context;
//    private float x;
//    private float y;
//    private float r;
//
//    public Worker(Context context, SurfaceHolder surfaceHolder) {
//        this.context = context;
//        this.surfaceHolder = surfaceHolder;
//        this.x = 0;
//        this.y = 0;
//        this.r = 0;
//    }
//
//    public void moveTo(float x, float y) {
//        this.x = x;
//        this.y = y;
//        this.r = 0;
//    }
//
//    @Override
//    public void run() {
//        while (!isInterrupted()) {
//            Canvas canvas = surfaceHolder.lockCanvas();
//            try {
//                Paint paint = new Paint();
//                paint.setColor(Color.BLUE);
//                paint.setStyle(Paint.Style.FILL);
//                canvas.drawPaint(paint);
//                paint.setColor(Color.YELLOW);
//                canvas.drawCircle(x, y, r, paint);
//                r = r + 5;
//            } finally {
//                surfaceHolder.unlockCanvasAndPost(canvas);
//            }
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                //
//            }
//        }
//    }
//}
