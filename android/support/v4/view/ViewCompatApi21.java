package android.support.v4.view;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class ViewCompatApi21 {

    /* renamed from: android.support.v4.view.ViewCompatApi21.1 */
    static class C00261 implements OnApplyWindowInsetsListener {
        final /* synthetic */ OnApplyWindowInsetsListener val$listener;

        C00261(OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
            this.val$listener = onApplyWindowInsetsListener;
        }

        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            return ((WindowInsetsCompatApi21) this.val$listener.onApplyWindowInsets(view, new WindowInsetsCompatApi21(windowInsets))).unwrap();
        }
    }

    public static void requestApplyInsets(View view) {
        view.requestApplyInsets();
    }

    public static void setElevation(View view, float elevation) {
        view.setElevation(elevation);
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListener listener) {
        view.setOnApplyWindowInsetsListener(new C00261(listener));
    }
}
