package com.kacper.smoothcamerabutton;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by kacper on 02/08/2017.
 */

public class SmoothCameraButton extends AppCompatButton {
    private static final float ANIM_FINISH = 10;
    private static final float ANIM_START = 0;
    private long animDuration = 300;
    private float strokeWidth = 18;
    private boolean touched;
    private float innerCirclePadding;

    Paint whitePaint = new Paint();
    Paint redPaint = new Paint();

    public SmoothCameraButton(Context context) {
        super(context);
        init();
    }

    public SmoothCameraButton(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.borderlessButtonStyle);
        init();
    }

    public SmoothCameraButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, android.R.attr.borderlessButtonStyle);
        init();
    }

    public long getAnimDuration() {
        return animDuration;
    }

    public void setAnimDuration(long animDuration) {
        this.animDuration = animDuration;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                touched = true;
                break;
            case MotionEvent.ACTION_UP:
                touched = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        whitePaint.setColor(Color.WHITE);
        whitePaint.setStrokeWidth(strokeWidth);
        whitePaint.setAntiAlias(true);
        whitePaint.setStyle(Paint.Style.STROKE);

        redPaint.setAntiAlias(true);
        redPaint.setColor(getResources().getColor(
                touched ? R.color.camera_active_red : R.color.camera_inactive_red
        ));
        redPaint.setStyle(Paint.Style.FILL_AND_STROKE);

//          canvas.drawOval(0, 0, 0, 0, whitePaint);

        float size = getHeight() > getWidth() ? getHeight() : getWidth();
        float centerY = getHeight() / 2;
        float centerX = getWidth() / 2;
        float radius = (size / 2) - 10 ;

        canvas.drawCircle(centerX, centerY, radius, whitePaint);

        float innerRadius = radius - strokeWidth - (innerCirclePadding * 3);

        RectF rect = new RectF(centerX - innerRadius, centerY + innerRadius, centerX + innerRadius, centerY - innerRadius);

        float rounding = getRounding(innerRadius, innerCirclePadding);

        canvas.drawRoundRect(
                rect,
                rounding,
                rounding,
                redPaint
        );
    }

    private float getRounding(float innerRadius, float innerCirclePadding) {
        float minimizeFactor = (float) Math.pow(innerCirclePadding / 1.45, 2);

        return innerRadius - minimizeFactor;
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        ValueAnimator va;
        if (selected) {
            va = ValueAnimator.ofFloat(ANIM_START, ANIM_FINISH);
        } else {
            va = ValueAnimator.ofFloat(ANIM_FINISH, ANIM_START);
        }
        va.setDuration(animDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                doMorphing((float)animation.getAnimatedValue());
            }
        });

        va.start();
    }

    private void doMorphing(float animatedValue) {
        innerCirclePadding = animatedValue;
        invalidate();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }


}
