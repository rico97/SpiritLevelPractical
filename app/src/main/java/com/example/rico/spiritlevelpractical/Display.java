package com.example.rico.spiritlevelpractical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class Display extends View {
    private String message;
    private Paint paint;
    private float angle;
    public Display(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        angle = 0;
        message = "Display";
        paint = new Paint();
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);

    }


    private PointF getCenter() {
        return new PointF(getWidth()/2f,getHeight()/2f);
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setAngle(float angle){
        this.angle = angle;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        PointF center = getCenter();


        canvas.save();
        canvas.rotate(-angle,getWidth()/2,getHeight()/2);
        paint.setColor(Color.BLACK);
        canvas.drawText(message,center.x,center.y,paint);
        paint.setColor(Color.BLUE);
        canvas.drawRect(-getWidth(),center.y,getWidth()*2,getHeight()*2,paint);
        canvas.restore();
    }
}
