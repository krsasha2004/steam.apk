package android.support.v4.widget;

import android.content.Context;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;

class SearchViewCompatHoneycomb {

    interface OnQueryTextListenerCompatBridge {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    interface OnCloseListenerCompatBridge {
        boolean onClose();
    }

    /* renamed from: android.support.v4.widget.SearchViewCompatHoneycomb.1 */
    static class C00441 implements OnQueryTextListener {
        final /* synthetic */ OnQueryTextListenerCompatBridge val$listener;

        C00441(OnQueryTextListenerCompatBridge onQueryTextListenerCompatBridge) {
            this.val$listener = onQueryTextListenerCompatBridge;
        }

        public boolean onQueryTextSubmit(String query) {
            return this.val$listener.onQueryTextSubmit(query);
        }

        public boolean onQueryTextChange(String newText) {
            return this.val$listener.onQueryTextChange(newText);
        }
    }

    /* renamed from: android.support.v4.widget.SearchViewCompatHoneycomb.2 */
    static class C00452 implements OnCloseListener {
        final /* synthetic */ OnCloseListenerCompatBridge val$listener;

        C00452(OnCloseListenerCompatBridge onCloseListenerCompatBridge) {
            this.val$listener = onCloseListenerCompatBridge;
        }

        public boolean onClose() {
            return this.val$listener.onClose();
        }
    }

    public static View newSearchView(Context context) {
        return new SearchView(context);
    }

    public static Object newOnQueryTextListener(OnQueryTextListenerCompatBridge listener) {
        return new C00441(listener);
    }

    public static void setOnQueryTextListener(Object searchView, Object listener) {
        ((SearchView) searchView).setOnQueryTextListener((OnQueryTextListener) listener);
    }

    public static Object newOnCloseListener(OnCloseListenerCompatBridge listener) {
        return new C00452(listener);
    }

    public static void setOnCloseListener(Object searchView, Object listener) {
        ((SearchView) searchView).setOnCloseListener((OnCloseListener) listener);
    }
}
