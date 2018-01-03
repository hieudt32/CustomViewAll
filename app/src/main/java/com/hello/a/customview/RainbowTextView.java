package com.hello.a.customview;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hello.a.rxandroid.R;

/**
 * Created by akai on 1/3/2018.
 */

public class RainbowTextView extends TextView {
    public RainbowTextView(Context context) {
        super(context);
    }

    public RainbowTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RainbowTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int[] rainBow = getRainBowColor();
        Shader shader = new LinearGradient(0, 0, 0, w, rainBow, null, Shader.TileMode.MIRROR);

        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        shader.setLocalMatrix(matrix);
        getPaint().setShader(shader);
    }

    private int[] getRainBowColor() {
        return new int[]{
                getResources().getColor(R.color.red),
                getResources().getColor(R.color.yellow),
                getResources().getColor(R.color.green),
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.purple)
        };
    }
}
