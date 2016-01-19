package me.mikhailmustakimov.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


/**
 * Created by student on 14.01.2016.
 */
public class DrawView extends SurfaceView implements SurfaceHolder.Callback {

    DrawThread drawThread;
    public static int[][] circles;

    public static int[][] getCircles() {
        return circles;
    }


    public DrawView(Context context) {
        super(context);
        circles = new int[3][2];
        for (int i=0; i<circles.length; i++) {
            for (int j=0; j<circles[i].length; j++) {
                circles[i][j] = (int)(Math.random()*500);
            }
        }
        getHolder().addCallback(this);
        this.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                for (int i=0; i<circles.length; i++) {
                    if (event.getX() > circles[i][0]-50 && event.getX()<circles[i][0]+50) {
                        if (event.getY() > circles[i][1]-50 && event.getY()<circles[i][1]+50) {
                            circles[i][0] = (int)event.getX();
                            circles[i][1] = (int)event.getY();
                        }
                    }
                }

                return false;
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(), getHolder());
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DrawThread extends Thread {
    SurfaceHolder surfaceHolder;


    boolean running = true;

    DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    Paint paint = new Paint();
                    paint.setColor(Color.BLUE);
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    paint.setStrokeWidth(2);
                    canvas.drawColor(Color.DKGRAY);
                    for (int i=0; i<DrawView.getCircles().length; i++) {
                        canvas.drawCircle(DrawView.getCircles()[i][1], DrawView.getCircles()[i][2], 100, paint);
                    }
//                    canvas.drawCircle(circleX, circleY, 100, paint);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
        super.run();
    }
}
