package android.support.v7.internal.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.support.v7.internal.widget.DecorToolbar;
import android.support.v7.internal.widget.ToolbarWidgetWrapper;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.support.v7.widget.WindowCallbackWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;

public class ToolbarActionBar extends ActionBar {
    private DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    private ListMenuPresenter mListMenuPresenter;
    private boolean mMenuCallbackSet;
    private final OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList<Object> mMenuVisibilityListeners;
    private Toolbar mToolbar;
    private boolean mToolbarMenuPrepared;
    private Window mWindow;
    private WindowCallback mWindowCallback;

    /* renamed from: android.support.v7.internal.app.ToolbarActionBar.1 */
    class C00581 implements Runnable {
        C00581() {
        }

        public void run() {
            ToolbarActionBar.this.populateOptionsMenu();
        }
    }

    /* renamed from: android.support.v7.internal.app.ToolbarActionBar.2 */
    class C00592 implements OnMenuItemClickListener {
        C00592() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, item);
        }
    }

    private final class ActionMenuPresenterCallback implements Callback {
        private boolean mClosingActionMenu;

        private ActionMenuPresenterCallback() {
        }

        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            if (ToolbarActionBar.this.mWindowCallback == null) {
                return false;
            }
            ToolbarActionBar.this.mWindowCallback.onMenuOpened(8, subMenu);
            return true;
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            if (!this.mClosingActionMenu) {
                this.mClosingActionMenu = true;
                ToolbarActionBar.this.mToolbar.dismissPopupMenus();
                if (ToolbarActionBar.this.mWindowCallback != null) {
                    ToolbarActionBar.this.mWindowCallback.onPanelClosed(8, menu);
                }
                this.mClosingActionMenu = false;
            }
        }
    }

    private final class MenuBuilderCallback implements MenuBuilder.Callback {
        private MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menu) {
            if (ToolbarActionBar.this.mWindowCallback == null) {
                return;
            }
            if (ToolbarActionBar.this.mToolbar.isOverflowMenuShowing()) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(8, menu);
            } else if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, menu)) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(8, menu);
            }
        }
    }

    private final class PanelMenuPresenterCallback implements Callback {
        private PanelMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
            if (ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(0, menu);
            }
            ToolbarActionBar.this.mWindow.closePanel(0);
        }

        public boolean onOpenSubMenu(MenuBuilder subMenu) {
            if (subMenu == null && ToolbarActionBar.this.mWindowCallback != null) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(0, subMenu);
            }
            return true;
        }
    }

    private class ToolbarCallbackWrapper extends WindowCallbackWrapper {
        public ToolbarCallbackWrapper(WindowCallback wrapped) {
            super(wrapped);
        }

        public boolean onPreparePanel(int featureId, View view, Menu menu) {
            boolean result = super.onPreparePanel(featureId, view, menu);
            if (result && !ToolbarActionBar.this.mToolbarMenuPrepared) {
                ToolbarActionBar.this.mDecorToolbar.setMenuPrepared();
                ToolbarActionBar.this.mToolbarMenuPrepared = true;
            }
            return result;
        }

        public View onCreatePanelView(int featureId) {
            switch (featureId) {
                case C0151R.styleable.View_android_focusable /*0*/:
                    if (!ToolbarActionBar.this.mToolbarMenuPrepared) {
                        ToolbarActionBar.this.populateOptionsMenu();
                        ToolbarActionBar.this.mToolbar.removeCallbacks(ToolbarActionBar.this.mMenuInvalidator);
                    }
                    if (ToolbarActionBar.this.mToolbarMenuPrepared && ToolbarActionBar.this.mWindowCallback != null) {
                        Menu menu = ToolbarActionBar.this.getMenu();
                        if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(featureId, null, menu) && ToolbarActionBar.this.mWindowCallback.onMenuOpened(featureId, menu)) {
                            return ToolbarActionBar.this.getListMenuView(menu);
                        }
                    }
            }
            return super.onCreatePanelView(featureId);
        }
    }

    public ToolbarActionBar(Toolbar toolbar, CharSequence title, Window window, WindowCallback windowCallback) {
        this.mMenuVisibilityListeners = new ArrayList();
        this.mMenuInvalidator = new C00581();
        this.mMenuClicker = new C00592();
        this.mToolbar = toolbar;
        this.mDecorToolbar = new ToolbarWidgetWrapper(toolbar, false);
        this.mWindowCallback = new ToolbarCallbackWrapper(windowCallback);
        this.mDecorToolbar.setWindowCallback(this.mWindowCallback);
        toolbar.setOnMenuItemClickListener(this.mMenuClicker);
        this.mDecorToolbar.setWindowTitle(title);
        this.mWindow = window;
    }

    public WindowCallback getWrappedWindowCallback() {
        return this.mWindowCallback;
    }

    public void setElevation(float elevation) {
        ViewCompat.setElevation(this.mToolbar, elevation);
    }

    public Context getThemedContext() {
        return this.mToolbar.getContext();
    }

    public void setHomeAsUpIndicator(Drawable indicator) {
        this.mToolbar.setNavigationIcon(indicator);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
    }

    public void setHomeActionContentDescription(int resId) {
        this.mDecorToolbar.setNavigationContentDescription(resId);
    }

    public void setShowHideAnimationEnabled(boolean enabled) {
    }

    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        return this.mWindowCallback.startActionMode(callback);
    }

    public void setWindowTitle(CharSequence title) {
        this.mDecorToolbar.setWindowTitle(title);
    }

    public void setDisplayOptions(int options, int mask) {
        this.mDecorToolbar.setDisplayOptions((options & mask) | ((mask ^ -1) & this.mDecorToolbar.getDisplayOptions()));
    }

    public void setDisplayShowHomeEnabled(boolean showHome) {
        setDisplayOptions(showHome ? 2 : 0, 2);
    }

    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public void show() {
        this.mToolbar.setVisibility(0);
    }

    public void hide() {
        this.mToolbar.setVisibility(8);
    }

    public boolean invalidateOptionsMenu() {
        this.mToolbar.removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mToolbar, this.mMenuInvalidator);
        return true;
    }

    public boolean collapseActionView() {
        if (!this.mToolbar.hasExpandedActionView()) {
            return false;
        }
        this.mToolbar.collapseActionView();
        return true;
    }

    void populateOptionsMenu() {
        MenuBuilder mb = null;
        Menu menu = getMenu();
        if (menu instanceof MenuBuilder) {
            mb = (MenuBuilder) menu;
        }
        if (mb != null) {
            mb.stopDispatchingItemsChanged();
        }
        try {
            menu.clear();
            if (!(this.mWindowCallback.onCreatePanelMenu(0, menu) && this.mWindowCallback.onPreparePanel(0, null, menu))) {
                menu.clear();
            }
            if (mb != null) {
                mb.startDispatchingItemsChanged();
            }
        } catch (Throwable th) {
            if (mb != null) {
                mb.startDispatchingItemsChanged();
            }
        }
    }

    public void dispatchMenuVisibilityChanged(boolean isVisible) {
        if (isVisible != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = isVisible;
            int count = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < count; i++) {
                this.mMenuVisibilityListeners.get(i);
            }
        }
    }

    private View getListMenuView(Menu menu) {
        if (menu == null || this.mListMenuPresenter == null || this.mListMenuPresenter.getAdapter().getCount() <= 0) {
            return null;
        }
        return (View) this.mListMenuPresenter.getMenuView(this.mToolbar);
    }

    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return this.mToolbar.getMenu();
    }

    public void setListMenuPresenter(ListMenuPresenter listMenuPresenter) {
        Menu menu = getMenu();
        if (menu instanceof MenuBuilder) {
            MenuBuilder mb = (MenuBuilder) menu;
            if (this.mListMenuPresenter != null) {
                this.mListMenuPresenter.setCallback(null);
                mb.removeMenuPresenter(this.mListMenuPresenter);
            }
            this.mListMenuPresenter = listMenuPresenter;
            if (listMenuPresenter != null) {
                listMenuPresenter.setCallback(new PanelMenuPresenterCallback());
                mb.addMenuPresenter(listMenuPresenter);
            }
        }
    }
}
