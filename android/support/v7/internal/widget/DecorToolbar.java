package android.support.v7.internal.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.app.WindowCallback;
import android.support.v7.internal.view.menu.MenuPresenter.Callback;
import android.view.Menu;
import android.view.ViewGroup;

public interface DecorToolbar {
    void animateToVisibility(int i);

    boolean canShowOverflowMenu();

    void collapseActionView();

    void dismissPopupMenus();

    Context getContext();

    int getDisplayOptions();

    int getNavigationMode();

    CharSequence getTitle();

    ViewGroup getViewGroup();

    boolean hasExpandedActionView();

    boolean hideOverflowMenu();

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    boolean isSplit();

    void setCollapsible(boolean z);

    void setDisplayOptions(int i);

    void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView);

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setLogo(int i);

    void setMenu(Menu menu, Callback callback);

    void setMenuPrepared();

    void setNavigationContentDescription(int i);

    void setNavigationIcon(Drawable drawable);

    void setWindowCallback(WindowCallback windowCallback);

    void setWindowTitle(CharSequence charSequence);

    boolean showOverflowMenu();
}
