package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewParent;
import java.util.WeakHashMap;

public class ViewCompat {
    static final ViewCompatImpl IMPL;

    interface ViewCompatImpl {
        ViewPropertyAnimatorCompat animate(View view);

        boolean canScrollHorizontally(View view, int i);

        boolean getFitsSystemWindows(View view);

        int getImportantForAccessibility(View view);

        int getLayerType(View view);

        int getLayoutDirection(View view);

        int getMeasuredState(View view);

        int getMinimumHeight(View view);

        int getOverScrollMode(View view);

        ViewParent getParentForAccessibility(View view);

        float getTranslationY(View view);

        int getWindowSystemUiVisibility(View view);

        boolean isOpaque(View view);

        void jumpDrawablesToCurrentState(View view);

        void postInvalidateOnAnimation(View view);

        void postInvalidateOnAnimation(View view, int i, int i2, int i3, int i4);

        void postOnAnimation(View view, Runnable runnable);

        void postOnAnimationDelayed(View view, Runnable runnable, long j);

        void requestApplyInsets(View view);

        int resolveSizeAndState(int i, int i2, int i3);

        void setAccessibilityDelegate(View view, AccessibilityDelegateCompat accessibilityDelegateCompat);

        void setAlpha(View view, float f);

        void setElevation(View view, float f);

        void setImportantForAccessibility(View view, int i);

        void setLayerPaint(View view, Paint paint);

        void setLayerType(View view, int i, Paint paint);

        void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener onApplyWindowInsetsListener);

        void setScaleY(View view, float f);

        void setTranslationX(View view, float f);

        void setTranslationY(View view, float f);
    }

    static class BaseViewCompatImpl implements ViewCompatImpl {
        WeakHashMap<View, ViewPropertyAnimatorCompat> mViewPropertyAnimatorCompatMap;

        BaseViewCompatImpl() {
            this.mViewPropertyAnimatorCompatMap = null;
        }

        public boolean canScrollHorizontally(View v, int direction) {
            return false;
        }

        public int getOverScrollMode(View v) {
            return 2;
        }

        public void setAccessibilityDelegate(View v, AccessibilityDelegateCompat delegate) {
        }

        public void postInvalidateOnAnimation(View view) {
            view.invalidate();
        }

        public void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
            view.invalidate(left, top, right, bottom);
        }

        public void postOnAnimation(View view, Runnable action) {
            view.postDelayed(action, getFrameTime());
        }

        public void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
            view.postDelayed(action, getFrameTime() + delayMillis);
        }

        long getFrameTime() {
            return 10;
        }

        public int getImportantForAccessibility(View view) {
            return 0;
        }

        public void setImportantForAccessibility(View view, int mode) {
        }

        public void setLayerType(View view, int layerType, Paint paint) {
        }

        public int getLayerType(View view) {
            return 0;
        }

        public void setLayerPaint(View view, Paint p) {
        }

        public int getLayoutDirection(View view) {
            return 0;
        }

        public ViewParent getParentForAccessibility(View view) {
            return view.getParent();
        }

        public boolean isOpaque(View view) {
            Drawable bg = view.getBackground();
            if (bg == null || bg.getOpacity() != -1) {
                return false;
            }
            return true;
        }

        public int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
            return View.resolveSize(size, measureSpec);
        }

        public int getMeasuredState(View view) {
            return 0;
        }

        public float getTranslationY(View view) {
            return 0.0f;
        }

        public int getMinimumHeight(View view) {
            return 0;
        }

        public ViewPropertyAnimatorCompat animate(View view) {
            return new ViewPropertyAnimatorCompat(view);
        }

        public void setTranslationX(View view, float value) {
        }

        public void setTranslationY(View view, float value) {
        }

        public void setAlpha(View view, float value) {
        }

        public void setScaleY(View view, float value) {
        }

        public int getWindowSystemUiVisibility(View view) {
            return 0;
        }

        public void requestApplyInsets(View view) {
        }

        public void setElevation(View view, float elevation) {
        }

        public boolean getFitsSystemWindows(View view) {
            return false;
        }

        public void jumpDrawablesToCurrentState(View view) {
        }

        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener listener) {
        }
    }

    static class EclairMr1ViewCompatImpl extends BaseViewCompatImpl {
        EclairMr1ViewCompatImpl() {
        }

        public boolean isOpaque(View view) {
            return ViewCompatEclairMr1.isOpaque(view);
        }
    }

    static class GBViewCompatImpl extends EclairMr1ViewCompatImpl {
        GBViewCompatImpl() {
        }

        public int getOverScrollMode(View v) {
            return ViewCompatGingerbread.getOverScrollMode(v);
        }
    }

    static class HCViewCompatImpl extends GBViewCompatImpl {
        HCViewCompatImpl() {
        }

        long getFrameTime() {
            return ViewCompatHC.getFrameTime();
        }

        public void setLayerType(View view, int layerType, Paint paint) {
            ViewCompatHC.setLayerType(view, layerType, paint);
        }

        public int getLayerType(View view) {
            return ViewCompatHC.getLayerType(view);
        }

        public void setLayerPaint(View view, Paint paint) {
            setLayerType(view, getLayerType(view), paint);
            view.invalidate();
        }

        public int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
            return ViewCompatHC.resolveSizeAndState(size, measureSpec, childMeasuredState);
        }

        public int getMeasuredState(View view) {
            return ViewCompatHC.getMeasuredState(view);
        }

        public float getTranslationY(View view) {
            return ViewCompatHC.getTranslationY(view);
        }

        public void setTranslationX(View view, float value) {
            ViewCompatHC.setTranslationX(view, value);
        }

        public void setTranslationY(View view, float value) {
            ViewCompatHC.setTranslationY(view, value);
        }

        public void setAlpha(View view, float value) {
            ViewCompatHC.setAlpha(view, value);
        }

        public void setScaleY(View view, float value) {
            ViewCompatHC.setScaleY(view, value);
        }

        public void jumpDrawablesToCurrentState(View view) {
            ViewCompatHC.jumpDrawablesToCurrentState(view);
        }
    }

    static class ICSViewCompatImpl extends HCViewCompatImpl {
        static boolean accessibilityDelegateCheckFailed;

        ICSViewCompatImpl() {
        }

        static {
            accessibilityDelegateCheckFailed = false;
        }

        public boolean canScrollHorizontally(View v, int direction) {
            return ViewCompatICS.canScrollHorizontally(v, direction);
        }

        public void setAccessibilityDelegate(View v, AccessibilityDelegateCompat delegate) {
            ViewCompatICS.setAccessibilityDelegate(v, delegate.getBridge());
        }

        public ViewPropertyAnimatorCompat animate(View view) {
            if (this.mViewPropertyAnimatorCompatMap == null) {
                this.mViewPropertyAnimatorCompatMap = new WeakHashMap();
            }
            ViewPropertyAnimatorCompat vpa = (ViewPropertyAnimatorCompat) this.mViewPropertyAnimatorCompatMap.get(view);
            if (vpa != null) {
                return vpa;
            }
            vpa = new ViewPropertyAnimatorCompat(view);
            this.mViewPropertyAnimatorCompatMap.put(view, vpa);
            return vpa;
        }
    }

    static class JBViewCompatImpl extends ICSViewCompatImpl {
        JBViewCompatImpl() {
        }

        public void postInvalidateOnAnimation(View view) {
            ViewCompatJB.postInvalidateOnAnimation(view);
        }

        public void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
            ViewCompatJB.postInvalidateOnAnimation(view, left, top, right, bottom);
        }

        public void postOnAnimation(View view, Runnable action) {
            ViewCompatJB.postOnAnimation(view, action);
        }

        public void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
            ViewCompatJB.postOnAnimationDelayed(view, action, delayMillis);
        }

        public int getImportantForAccessibility(View view) {
            return ViewCompatJB.getImportantForAccessibility(view);
        }

        public void setImportantForAccessibility(View view, int mode) {
            if (mode == 4) {
                mode = 2;
            }
            ViewCompatJB.setImportantForAccessibility(view, mode);
        }

        public ViewParent getParentForAccessibility(View view) {
            return ViewCompatJB.getParentForAccessibility(view);
        }

        public int getMinimumHeight(View view) {
            return ViewCompatJB.getMinimumHeight(view);
        }

        public void requestApplyInsets(View view) {
            ViewCompatJB.requestApplyInsets(view);
        }

        public boolean getFitsSystemWindows(View view) {
            return ViewCompatJB.getFitsSystemWindows(view);
        }
    }

    static class JbMr1ViewCompatImpl extends JBViewCompatImpl {
        JbMr1ViewCompatImpl() {
        }

        public void setLayerPaint(View view, Paint paint) {
            ViewCompatJellybeanMr1.setLayerPaint(view, paint);
        }

        public int getLayoutDirection(View view) {
            return ViewCompatJellybeanMr1.getLayoutDirection(view);
        }

        public int getWindowSystemUiVisibility(View view) {
            return ViewCompatJellybeanMr1.getWindowSystemUiVisibility(view);
        }
    }

    static class KitKatViewCompatImpl extends JbMr1ViewCompatImpl {
        KitKatViewCompatImpl() {
        }

        public void setImportantForAccessibility(View view, int mode) {
            ViewCompatJB.setImportantForAccessibility(view, mode);
        }
    }

    static class Api21ViewCompatImpl extends KitKatViewCompatImpl {
        Api21ViewCompatImpl() {
        }

        public void requestApplyInsets(View view) {
            ViewCompatApi21.requestApplyInsets(view);
        }

        public void setElevation(View view, float elevation) {
            ViewCompatApi21.setElevation(view, elevation);
        }

        public void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener listener) {
            ViewCompatApi21.setOnApplyWindowInsetsListener(view, listener);
        }
    }

    static {
        int version = VERSION.SDK_INT;
        if (version >= 21) {
            IMPL = new Api21ViewCompatImpl();
        } else if (version >= 19) {
            IMPL = new KitKatViewCompatImpl();
        } else if (version >= 17) {
            IMPL = new JbMr1ViewCompatImpl();
        } else if (version >= 16) {
            IMPL = new JBViewCompatImpl();
        } else if (version >= 14) {
            IMPL = new ICSViewCompatImpl();
        } else if (version >= 11) {
            IMPL = new HCViewCompatImpl();
        } else if (version >= 9) {
            IMPL = new GBViewCompatImpl();
        } else if (version >= 7) {
            IMPL = new EclairMr1ViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    public static boolean canScrollHorizontally(View v, int direction) {
        return IMPL.canScrollHorizontally(v, direction);
    }

    public static int getOverScrollMode(View v) {
        return IMPL.getOverScrollMode(v);
    }

    public static void setAccessibilityDelegate(View v, AccessibilityDelegateCompat delegate) {
        IMPL.setAccessibilityDelegate(v, delegate);
    }

    public static void postInvalidateOnAnimation(View view) {
        IMPL.postInvalidateOnAnimation(view);
    }

    public static void postInvalidateOnAnimation(View view, int left, int top, int right, int bottom) {
        IMPL.postInvalidateOnAnimation(view, left, top, right, bottom);
    }

    public static void postOnAnimation(View view, Runnable action) {
        IMPL.postOnAnimation(view, action);
    }

    public static void postOnAnimationDelayed(View view, Runnable action, long delayMillis) {
        IMPL.postOnAnimationDelayed(view, action, delayMillis);
    }

    public static int getImportantForAccessibility(View view) {
        return IMPL.getImportantForAccessibility(view);
    }

    public static void setImportantForAccessibility(View view, int mode) {
        IMPL.setImportantForAccessibility(view, mode);
    }

    public static void setLayerType(View view, int layerType, Paint paint) {
        IMPL.setLayerType(view, layerType, paint);
    }

    public static int getLayerType(View view) {
        return IMPL.getLayerType(view);
    }

    public static void setLayerPaint(View view, Paint paint) {
        IMPL.setLayerPaint(view, paint);
    }

    public static int getLayoutDirection(View view) {
        return IMPL.getLayoutDirection(view);
    }

    public static ViewParent getParentForAccessibility(View view) {
        return IMPL.getParentForAccessibility(view);
    }

    public static boolean isOpaque(View view) {
        return IMPL.isOpaque(view);
    }

    public static int resolveSizeAndState(int size, int measureSpec, int childMeasuredState) {
        return IMPL.resolveSizeAndState(size, measureSpec, childMeasuredState);
    }

    public static int getMeasuredState(View view) {
        return IMPL.getMeasuredState(view);
    }

    public static float getTranslationY(View view) {
        return IMPL.getTranslationY(view);
    }

    public static int getMinimumHeight(View view) {
        return IMPL.getMinimumHeight(view);
    }

    public static ViewPropertyAnimatorCompat animate(View view) {
        return IMPL.animate(view);
    }

    public static void setTranslationX(View view, float value) {
        IMPL.setTranslationX(view, value);
    }

    public static void setTranslationY(View view, float value) {
        IMPL.setTranslationY(view, value);
    }

    public static void setAlpha(View view, float value) {
        IMPL.setAlpha(view, value);
    }

    public static void setScaleY(View view, float value) {
        IMPL.setScaleY(view, value);
    }

    public static void setElevation(View view, float elevation) {
        IMPL.setElevation(view, elevation);
    }

    public static int getWindowSystemUiVisibility(View view) {
        return IMPL.getWindowSystemUiVisibility(view);
    }

    public static void requestApplyInsets(View view) {
        IMPL.requestApplyInsets(view);
    }

    public static boolean getFitsSystemWindows(View v) {
        return IMPL.getFitsSystemWindows(v);
    }

    public static void jumpDrawablesToCurrentState(View v) {
        IMPL.jumpDrawablesToCurrentState(v);
    }

    public static void setOnApplyWindowInsetsListener(View v, OnApplyWindowInsetsListener listener) {
        IMPL.setOnApplyWindowInsetsListener(v, listener);
    }
}
