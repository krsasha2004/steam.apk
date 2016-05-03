package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

public class SearchViewCompat {
    private static final SearchViewCompatImpl IMPL;

    public static abstract class OnCloseListenerCompat {
        final Object mListener;

        public OnCloseListenerCompat() {
            this.mListener = SearchViewCompat.IMPL.newOnCloseListener(this);
        }

        public boolean onClose() {
            return false;
        }
    }

    public static abstract class OnQueryTextListenerCompat {
        final Object mListener;

        public OnQueryTextListenerCompat() {
            this.mListener = SearchViewCompat.IMPL.newOnQueryTextListener(this);
        }

        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }

    interface SearchViewCompatImpl {
        Object newOnCloseListener(OnCloseListenerCompat onCloseListenerCompat);

        Object newOnQueryTextListener(OnQueryTextListenerCompat onQueryTextListenerCompat);

        View newSearchView(Context context);

        void setOnCloseListener(Object obj, Object obj2);

        void setOnQueryTextListener(Object obj, Object obj2);
    }

    static class SearchViewCompatStubImpl implements SearchViewCompatImpl {
        SearchViewCompatStubImpl() {
        }

        public View newSearchView(Context context) {
            return null;
        }

        public Object newOnQueryTextListener(OnQueryTextListenerCompat listener) {
            return null;
        }

        public void setOnQueryTextListener(Object searchView, Object listener) {
        }

        public Object newOnCloseListener(OnCloseListenerCompat listener) {
            return null;
        }

        public void setOnCloseListener(Object searchView, Object listener) {
        }
    }

    static class SearchViewCompatHoneycombImpl extends SearchViewCompatStubImpl {

        /* renamed from: android.support.v4.widget.SearchViewCompat.SearchViewCompatHoneycombImpl.1 */
        class C00421 implements OnQueryTextListenerCompatBridge {
            final /* synthetic */ OnQueryTextListenerCompat val$listener;

            C00421(OnQueryTextListenerCompat onQueryTextListenerCompat) {
                this.val$listener = onQueryTextListenerCompat;
            }

            public boolean onQueryTextSubmit(String query) {
                return this.val$listener.onQueryTextSubmit(query);
            }

            public boolean onQueryTextChange(String newText) {
                return this.val$listener.onQueryTextChange(newText);
            }
        }

        /* renamed from: android.support.v4.widget.SearchViewCompat.SearchViewCompatHoneycombImpl.2 */
        class C00432 implements OnCloseListenerCompatBridge {
            final /* synthetic */ OnCloseListenerCompat val$listener;

            C00432(OnCloseListenerCompat onCloseListenerCompat) {
                this.val$listener = onCloseListenerCompat;
            }

            public boolean onClose() {
                return this.val$listener.onClose();
            }
        }

        SearchViewCompatHoneycombImpl() {
        }

        public View newSearchView(Context context) {
            return SearchViewCompatHoneycomb.newSearchView(context);
        }

        public Object newOnQueryTextListener(OnQueryTextListenerCompat listener) {
            return SearchViewCompatHoneycomb.newOnQueryTextListener(new C00421(listener));
        }

        public void setOnQueryTextListener(Object searchView, Object listener) {
            SearchViewCompatHoneycomb.setOnQueryTextListener(searchView, listener);
        }

        public Object newOnCloseListener(OnCloseListenerCompat listener) {
            return SearchViewCompatHoneycomb.newOnCloseListener(new C00432(listener));
        }

        public void setOnCloseListener(Object searchView, Object listener) {
            SearchViewCompatHoneycomb.setOnCloseListener(searchView, listener);
        }
    }

    static class SearchViewCompatIcsImpl extends SearchViewCompatHoneycombImpl {
        SearchViewCompatIcsImpl() {
        }

        public View newSearchView(Context context) {
            return SearchViewCompatIcs.newSearchView(context);
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            IMPL = new SearchViewCompatIcsImpl();
        } else if (VERSION.SDK_INT >= 11) {
            IMPL = new SearchViewCompatHoneycombImpl();
        } else {
            IMPL = new SearchViewCompatStubImpl();
        }
    }

    public static View newSearchView(Context context) {
        return IMPL.newSearchView(context);
    }

    public static void setOnQueryTextListener(View searchView, OnQueryTextListenerCompat listener) {
        IMPL.setOnQueryTextListener(searchView, listener.mListener);
    }

    public static void setOnCloseListener(View searchView, OnCloseListenerCompat listener) {
        IMPL.setOnCloseListener(searchView, listener.mListener);
    }
}
