package com.grademojo.canvas_practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RotatingImageView extends ImageView {

    // Initial position.
    private int rotationDegrees = 0;

    public RotatingImageView(Context context) {
        super(context);
        init();
    }

    public RotatingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotatingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        setImageBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Translate rotation axe to the center.
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        // Rotate!
        canvas.rotate(rotation(3));
        // Put back to its original place.
        canvas.translate(-canvas.getWidth()/2, -canvas.getHeight()/2);        // Invalidate the view.
        postInvalidateOnAnimation();
        super.onDraw(canvas);
    }

    private int rotation(int delta) {
        rotationDegrees = (rotationDegrees + delta) % 360;
        return rotationDegrees;
    }
}