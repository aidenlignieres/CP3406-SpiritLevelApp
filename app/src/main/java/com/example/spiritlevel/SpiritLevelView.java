package com.example.spiritlevel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SpiritLevelView extends View {

    private static final int BUBBLE_SIZE = 100;
    private final PointF bubble;
    private final Point center;
    private int width, height;
    private final Paint paint;

    public SpiritLevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bubble = new PointF();
        center = new Point();
        paint = new Paint();

        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
        setBackgroundColor(Color.BLACK);
    }

    public void setBubble(float x, float y) {
        bubble.x = center.x + x / 9.81f * width;
        bubble.y = center.y + y / 9.81f * height;
        Log.i("SpiritLevelView", bubble.toString());

        invalidate();
    }

    private boolean bubbleCentered() {
        // float maths is imprecise
        // so we check to see if the bubble
        // is close to the center

        float xDiff = Math.abs(bubble.x - center.x);
        float yDiff = Math.abs(bubble.y - center.y);

        return xDiff < 0.5f && yDiff < 0.5f;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center.x = w / 2;
        center.y = h / 2;
        width = w;
        height = h;
        setBubble(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(center.x, 0, center.x, height, paint);
        canvas.drawLine(0, center.y, width, center.y, paint);
        canvas.drawCircle(bubble.x, bubble.y, BUBBLE_SIZE, paint);
    }
}

