package com.grademojo.canvas_practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
public class RadialFxImageView1 extends ImageView {
    private static final int STROKE_WIDTH_DP = 6;
    private Paint paintBorder;
    private Bitmap bitmap;
    private int strokeWidthPx;
    private RectF rectF;
    private RadialGradient radialGradient;

    private float radialScaleDirection;
    private Matrix matrix;
    private float radialScale;
    private int totalWidth;
    private int totalHeight;

    public RadialFxImageView1(Context context) {
        super(context);
        init();
    }

    public RadialFxImageView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadialFxImageView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);

        strokeWidthPx = (int) (STROKE_WIDTH_DP * getResources().getDisplayMetrics().density);
        int halfStrokeWidthPx = strokeWidthPx / 2;

        paintBorder = new Paint();
        paintBorder.setStyle(Paint.Style.FILL);

        totalWidth = bitmap.getWidth() + strokeWidthPx * 2;
        totalHeight = bitmap.getHeight() + strokeWidthPx * 2;
        radialGradient = new RadialGradient(totalWidth /2, totalHeight /2, totalWidth /2, new int[] {Color.BLACK, Color.BLUE}, null, Shader.TileMode.MIRROR);
        paintBorder.setShader(radialGradient);
        matrix = new Matrix();

        setImageBitmap(Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888));

        rectF = new RectF(halfStrokeWidthPx, halfStrokeWidthPx, totalWidth - halfStrokeWidthPx, totalHeight - halfStrokeWidthPx);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scale = incScale(0.01f);
        matrix.setScale(scale, scale, totalWidth/2, totalHeight/2);
        radialGradient.setLocalMatrix(matrix);

    //    canvas.drawRect(rectF, paintBorder);




       canvas.drawRoundRect(rectF, 40, 40, paintBorder);
        canvas.drawBitmap(bitmap,strokeWidthPx, strokeWidthPx, null);
        postInvalidateOnAnimation();
    }

    private float incScale(float delta) {
        radialScale = (radialScale + delta * radialScaleDirection);
        if (radialScale <= 0.2f) {
            radialScaleDirection = 1;
            radialScale = 0.2f;
        } else if (radialScale >= 1) {
            radialScaleDirection = -1;
            radialScale = 1;
        }

        return radialScale;
    }
}
