package com.hxg.idiom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * 相框view
 *
 * @author hxg
 */
public class ImageFrameView extends View {
    private int mWidth, mHeight;
    private float radius;

    private int coverColor;

    @NonNull
    private final Path path = new Path();

    public ImageFrameView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ImageFrameView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageFrameView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        coverColor = ContextCompat.getColor(context, R.color.black);
        radius = 50;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void setCoverColor(@ColorRes int color) {
        coverColor = ContextCompat.getColor(getContext(), color);
        invalidate();
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    private void setPath() {
        final int left = getPaddingLeft(), right = getPaddingRight();
        final int top = getPaddingTop(), bottom = getPaddingBottom();

        path.moveTo(radius + left, top);
        path.lineTo(mWidth - radius - right, top);
        path.quadTo(mWidth - right, top, mWidth - right, radius + top);
        path.lineTo(mWidth - right, mHeight - radius - bottom);
        path.quadTo(mWidth - right, mHeight - bottom, mWidth - radius - right, mHeight - bottom);
        path.lineTo(radius + left, mHeight - bottom);
        path.quadTo(left, mHeight - bottom, left, mHeight - radius - bottom);
        path.lineTo(left, radius + top);
        path.quadTo(left, top, radius + left, top);
        path.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        setPath();
        canvas.clipPath(path, Region.Op.DIFFERENCE);
        canvas.drawColor(coverColor);
        canvas.restore();
    }
}
