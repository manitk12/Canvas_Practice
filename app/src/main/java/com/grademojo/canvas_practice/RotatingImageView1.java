package com.grademojo.canvas_practice;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

public class RotatingImageView1 extends ImageView {

    private int rotationDegrees = 0;
    private float scale ;
    private int directionScale ;
    private int count = 0;

    private int countScaleChange = 0;



    public RotatingImageView1(Context context) {
        super(context);
        init();

    }

    public RotatingImageView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotatingImageView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        setImageBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        float scaleFactor = scale(0.01f);
        canvas.scale(scaleFactor, scaleFactor);
        canvas.rotate(rotation(3));
        canvas.translate(-canvas.getWidth() / 2, -canvas.getHeight() / 2);


        if(scale == 1){
            countScaleChange++;
        }

        if(countScaleChange < 2){
            postInvalidateOnAnimation();
        }


        super.onDraw(canvas);
    }

    private float scale(float delta) {
        scale = (scale + delta * directionScale);
        if (scale <= 0) {
            directionScale = 1;
            scale = 0;

        } else if (scale >= 1) {
            directionScale = -1;
            scale = 1;
        }
        return scale;
    }

    private int rotation(int delta) {

        rotationDegrees = (rotationDegrees + delta) % 360;

        return rotationDegrees;
    }
}