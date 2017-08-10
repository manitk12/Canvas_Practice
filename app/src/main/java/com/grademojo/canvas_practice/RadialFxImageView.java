package com.grademojo.canvas_practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RadialFxImageView extends ImageView {
    private static final int STROKE_WIDTH_DP = 6;
    private Paint paintBorder;
    private Bitmap bitmap;
    private int strokeWidthPx;
    private RectF rectF;
    private RadialGradient radialGradient;

    public RadialFxImageView(Context context) {
        super(context);
        init();
    }




    public RadialFxImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadialFxImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }



    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);
        strokeWidthPx = (int) (STROKE_WIDTH_DP * getResources().getDisplayMetrics().density);
        int halfStrokeWidthPx = strokeWidthPx / 2;

        paintBorder = new Paint();
        paintBorder.setStyle(Paint.Style.FILL);

        int totalWidth = bitmap.getWidth() + strokeWidthPx * 2;
        int totalHeight = bitmap.getHeight() + strokeWidthPx * 2;
        radialGradient = new RadialGradient(totalWidth /2, totalHeight /2, totalWidth /2, new int[] {Color.BLACK, Color.GREEN}, null, Shader.TileMode.MIRROR);
        paintBorder.setShader(radialGradient);
        setImageBitmap(Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888));

        rectF = new RectF(halfStrokeWidthPx, halfStrokeWidthPx, totalWidth - halfStrokeWidthPx, totalHeight - halfStrokeWidthPx);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rectF, 40, 40, paintBorder);
        canvas.drawBitmap(bitmap,strokeWidthPx, strokeWidthPx, null);
    }
}