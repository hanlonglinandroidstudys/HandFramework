/*
 * Copyright (c) 2019/4/12
 * Create at 10:6:52
 * Wriitten by hanlonglin
 */

package hanlonglin.com.animationframwork.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    Paint mPaint;
    Path mPath;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(7);

        mPath=new Path();
        mPath.moveTo(100,100);
        mPath.quadTo(200,300,400,150);
        mPath.lineTo(500,500);

        canvas.drawPath(mPath,mPaint);
    }
}
