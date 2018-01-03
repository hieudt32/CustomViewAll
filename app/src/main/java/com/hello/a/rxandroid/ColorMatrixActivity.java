package com.hello.a.rxandroid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ColorMatrixActivity extends AppCompatActivity {

    Spinner mSpinner;
    ImageView mImageView;
    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);
        mImageView = findViewById(R.id.color_matrix_imv);
        mSpinner = findViewById(R.id.choose_color_spn);
        mList.add("Gray Scale");
        mList.add("Sepia");
        mList.add("Binary");
        mList.add("Invert");
        mList.add("Alpha Blue");
        mList.add("Alpha Pink");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, mList);
        mSpinner.setAdapter(adapter);
        final Bitmap origin = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mImageView.setImageBitmap(getColorGrayScale(origin, getGrayColorMatrix()));
                        break;
                    case 1:
                        mImageView.setImageBitmap(getColorGrayScale(origin, getSepiaColorMatrix()));
                        break;
                    case 2:
                        mImageView.setImageBitmap(getColorGrayScale(origin, getBinaryColorMatrix()));
                        break;
                    case 3:
                        mImageView.setImageBitmap(getColorGrayScale(origin, getInvertColorMatrix()));
                        break;
                    case 4:
                        mImageView.setImageBitmap(getColorGrayScale(origin, getAlphaBlueColorMatrix()));
                        break;
                    case 5:
                        mImageView.setImageBitmap(getColorGrayScale(origin, getAlphaPinkColorMatrix()));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private Bitmap getColorGrayScale(Bitmap original, ColorMatrix colorMatrix) {
        Bitmap bitmap = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(
                colorMatrix));
        canvas.drawBitmap(original, 0, 0, paint);

        return bitmap;
    }

    private ColorMatrix getGrayColorMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        return colorMatrix;
    }

    private ColorMatrix getSepiaColorMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrix colorScale = new ColorMatrix();
        colorScale.setScale(1, 1, 0.8f, 1);

        // Convert to grayscale, then apply brown color
        colorMatrix.postConcat(colorScale);

        return colorMatrix;
    }

    private ColorMatrix getBinaryColorMatrix() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        float m = 255f;
        float t = -255 * 128f;
        ColorMatrix threshold = new ColorMatrix(new float[]{
                m, 0, 0, 1, t,
                0, m, 0, 1, t,
                0, 0, m, 1, t,
                0, 0, 0, 1, 0
        });

        // Convert to grayscale, then scale and clamp
        colorMatrix.postConcat(threshold);

        return colorMatrix;
    }

    private ColorMatrix getInvertColorMatrix() {
        return new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });
    }

    private ColorMatrix getAlphaBlueColorMatrix() {
        return new ColorMatrix(new float[]{
                0, 0, 0, 0, 0,
                0.3f, 0, 0, 0, 50,
                0, 0, 0, 0, 255,
                0.2f, 0.4f, 0.4f, 0, -30
        });
    }

    private ColorMatrix getAlphaPinkColorMatrix() {
        return new ColorMatrix(new float[]{
                0, 0, 0, 0, 255,
                0, 0, 0, 0, 0,
                0.2f, 0, 0, 0, 50,
                0.2f, 0.2f, 0.2f, 0, -20
        });
    }


}
