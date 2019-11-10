package com.example.login;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Move2 extends View {
    private Paint paint;
    private float fontSize;
    private String text;
    private float dx;
    private float dy;


    public Move2(Context context, String text) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        fontSize = 32 * getResources().getDisplayMetrics().density;
        paint.setTextSize(fontSize);
        this.text = text;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(this.text,dx,dy,paint);
    }

    public boolean onTouchEvent(MotionEvent event){
        dx = event.getX();
        dy = event.getY();

        invalidate();
        return true;
    }
}
