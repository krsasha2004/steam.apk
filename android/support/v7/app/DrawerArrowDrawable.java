package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.C0057R;

abstract class DrawerArrowDrawable extends Drawable {
    private static final float ARROW_HEAD_ANGLE;
    private final float mBarGap;
    private final float mBarSize;
    private final float mBarThickness;
    private final float mMiddleArrowSize;
    private final Paint mPaint;
    private final Path mPath;
    private float mProgress;
    private final int mSize;
    private final boolean mSpin;
    private final float mTopBottomArrowSize;
    private boolean mVerticalMirror;

    abstract boolean isLayoutRtl();

    static {
        ARROW_HEAD_ANGLE = (float) Math.toRadians(45.0d);
    }

    DrawerArrowDrawable(Context context) {
        this.mPaint = new Paint();
        this.mPath = new Path();
        this.mVerticalMirror = false;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(null, C0057R.styleable.DrawerArrowToggle, C0057R.attr.drawerArrowStyle, C0057R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(typedArray.getColor(C0057R.styleable.DrawerArrowToggle_color, 0));
        this.mSize = typedArray.getDimensionPixelSize(C0057R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.mBarSize = typedArray.getDimension(C0057R.styleable.DrawerArrowToggle_barSize, 0.0f);
        this.mTopBottomArrowSize = typedArray.getDimension(C0057R.styleable.DrawerArrowToggle_topBottomBarArrowSize, 0.0f);
        this.mBarThickness = typedArray.getDimension(C0057R.styleable.DrawerArrowToggle_thickness, 0.0f);
        this.mBarGap = typedArray.getDimension(C0057R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f);
        this.mSpin = typedArray.getBoolean(C0057R.styleable.DrawerArrowToggle_spinBars, true);
        this.mMiddleArrowSize = typedArray.getDimension(C0057R.styleable.DrawerArrowToggle_middleBarArrowSize, 0.0f);
        typedArray.recycle();
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeJoin(Join.ROUND);
        this.mPaint.setStrokeCap(Cap.SQUARE);
        this.mPaint.setStrokeWidth(this.mBarThickness);
    }

    protected void setVerticalMirror(boolean verticalMirror) {
        this.mVerticalMirror = verticalMirror;
    }

    public void draw(Canvas canvas) {
        float f;
        float f2;
        Rect bounds = getBounds();
        boolean isRtl = isLayoutRtl();
        float arrowSize = lerp(this.mBarSize, this.mTopBottomArrowSize, this.mProgress);
        float middleBarSize = lerp(this.mBarSize, this.mMiddleArrowSize, this.mProgress);
        float middleBarCut = lerp(0.0f, this.mBarThickness / 2.0f, this.mProgress);
        float rotation = lerp(0.0f, ARROW_HEAD_ANGLE, this.mProgress);
        if (isRtl) {
            f = 0.0f;
        } else {
            f = -180.0f;
        }
        if (isRtl) {
            f2 = 180.0f;
        } else {
            f2 = 0.0f;
        }
        float canvasRotate = lerp(f, f2, this.mProgress);
        float topBottomBarOffset = lerp(this.mBarGap + this.mBarThickness, 0.0f, this.mProgress);
        this.mPath.rewind();
        float arrowEdge = (-middleBarSize) / 2.0f;
        this.mPath.moveTo(arrowEdge + middleBarCut, 0.0f);
        this.mPath.rLineTo(middleBarSize - middleBarCut, 0.0f);
        float arrowWidth = (float) Math.round(((double) arrowSize) * Math.cos((double) rotation));
        float arrowHeight = (float) Math.round(((double) arrowSize) * Math.sin((double) rotation));
        this.mPath.moveTo(arrowEdge, topBottomBarOffset);
        this.mPath.rLineTo(arrowWidth, arrowHeight);
        this.mPath.moveTo(arrowEdge, -topBottomBarOffset);
        this.mPath.rLineTo(arrowWidth, -arrowHeight);
        this.mPath.moveTo(0.0f, 0.0f);
        this.mPath.close();
        canvas.save();
        if (this.mSpin) {
            canvas.rotate(((float) ((this.mVerticalMirror ^ isRtl) != 0 ? -1 : 1)) * canvasRotate, (float) bounds.centerX(), (float) bounds.centerY());
        } else if (isRtl) {
            canvas.rotate(180.0f, (float) bounds.centerX(), (float) bounds.centerY());
        }
        canvas.translate((float) bounds.centerX(), (float) bounds.centerY());
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public boolean isAutoMirrored() {
        return true;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getIntrinsicHeight() {
        return this.mSize;
    }

    public int getIntrinsicWidth() {
        return this.mSize;
    }

    public int getOpacity() {
        return -3;
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidateSelf();
    }

    private static float lerp(float a, float b, float t) {
        return ((b - a) * t) + a;
    }
}
