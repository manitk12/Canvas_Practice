package com.grademojo.canvas_practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BorderedImageView extends ImageView {
    private static final int STROKE_WIDTH_DP = 6;
    private Paint paintBorder;
    private Bitmap bitmap;
    private int strokeWidthPx;
    private RectF rectF;



    public BorderedImageView(Context context) {
        super(context);
        init();
    }

    public BorderedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BorderedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }





    private void init() {
        // The resource is embedded, but it can be easily moved in the constructor.
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android);

        // The same goes for the stroke width in dp.
        strokeWidthPx = (int) (STROKE_WIDTH_DP * getResources().getDisplayMetrics().density);
        int halfStrokeWidthPx = strokeWidthPx / 2;



        paintBorder = new Paint();
        paintBorder.setStyle(Paint.Style.STROKE);
        // Stroke width is in pixels.
        paintBorder.setStrokeWidth(strokeWidthPx);
        // Our color for the border.
        paintBorder.setColor(Color.BLUE);

        int totalWidth = bitmap.getWidth() + strokeWidthPx * 2;
        int totalHeight = bitmap.getHeight()  + strokeWidthPx * 2;



        // An empty bitmap with the same size of our resource to display, increased of the desired border width.
        setImageBitmap(Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888));

        // The rectangle that will be used for drawing the colored border.
        rectF = new RectF(halfStrokeWidthPx, halfStrokeWidthPx, totalWidth - halfStrokeWidthPx, totalHeight - halfStrokeWidthPx);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        A rounded rect will be printed.
//        canvas.drawRoundRect(rectF, 10, 10, paintBorder);
//        canvas.drawRect(0, 0, 200, 200, paintBorder);
//        canvas.drawRect(10,0,10,10,paintBorder);
//        canvas.drawRoundRect(rectF,0,0,paintBorder);





        canvas.drawLine(rectF.left - strokeWidthPx/2,rectF.top,rectF.left - strokeWidthPx/2,rectF.bottom,paintBorder);
        //top border
        canvas.drawLine(rectF.left, rectF.top - strokeWidthPx/2,rectF.right, rectF.top - strokeWidthPx/2, paintBorder);
        //right border
        canvas.drawLine(rectF.right + strokeWidthPx/2, rectF.top,rectF.right + strokeWidthPx/2,rectF.bottom, paintBorder);
        //bottom border
        canvas.drawLine(rectF.left, rectF.bottom + strokeWidthPx/2, rectF.right, rectF.bottom + strokeWidthPx/2, paintBorder);





        // The bitmap for the resource R.drawable.ANDROID_icon.
        // Note the Paint for the bitmap is null, we'll talk about this in a moment...
        canvas.drawBitmap(bitmap,strokeWidthPx, strokeWidthPx, null);
    }
}