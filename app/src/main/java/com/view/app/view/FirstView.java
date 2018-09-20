package com.view.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangyibo on 2018/9/14.
 */
public class FirstView extends View{

    private Paint mPaint;

    public FirstView(Context context) {
        super(context);
        init();
    }

    public FirstView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FirstView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(36);
        mPaint.setStrokeMiter(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        canvas.drawCircle(200,200,100,mPaint);

    }
}
