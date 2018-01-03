package com.hello.a.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by akai on 1/3/2018.
 */

public class PeekThroughImageView extends ImageView {

    private final float radius = 100;
    private Paint paint = null;
    private float x;
    private float y;
    private boolean shouldDrawOpening = false;

    public PeekThroughImageView(Context context) {
        super(context);
    }

    public PeekThroughImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PeekThroughImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        shouldDrawOpening = (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE);
        x = event.getX();
        y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (paint == null) {
            Bitmap origin = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas originCanvas = new Canvas(origin);
            super.onDraw(originCanvas);
            Shader shader = new BitmapShader(origin, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint = new Paint();
            paint.setShader(shader);
        }

        canvas.drawColor(Color.GRAY);
        if (shouldDrawOpening) {
            canvas.drawCircle(x, y - radius, radius, paint);
        }

    }
}
