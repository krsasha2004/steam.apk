package com.valvesoftware.android.steam.community.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.valvesoftware.android.steam.community.AndroidUtils;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.GcmRegistrar;
import com.valvesoftware.android.steam.community.LoggedInUserAccountInfo;
import com.valvesoftware.android.steam.community.NotificationCountUpdateListener;
import com.valvesoftware.android.steam.community.PersonaRepository;
import com.valvesoftware.android.steam.community.RepositoryCallback;
import com.valvesoftware.android.steam.community.SettingInfoDB;
import com.valvesoftware.android.steam.community.SteamAppIntents;
import com.valvesoftware.android.steam.community.SteamCommunityApplication;
import com.valvesoftware.android.steam.community.UmqCommunicator;
import com.valvesoftware.android.steam.community.fragment.NavDrawerItem;
import com.valvesoftware.android.steam.community.fragment.NavDrawerListAdapter;
import com.valvesoftware.android.steam.community.fragment.NavDrawerNotificationItem;
import com.valvesoftware.android.steam.community.fragment.NavDrawerNotificationItem.NavDrawerGroupItem;
import com.valvesoftware.android.steam.community.fragment.SearchBarFragment;
import com.valvesoftware.android.steam.community.model.Persona;
import com.valvesoftware.android.steam.community.model.UserNotificationCounts;
import com.valvesoftware.android.steam.community.model.UserNotificationCounts.EUserNotification;
import com.valvesoftware.android.steam.community.views.MenuBar;
import com.valvesoftware.android.steam.community.views.SteamMenuItem;
import com.valvesoftware.android.steam.community.webrequests.Endpoints;
import com.valvesoftware.android.steam.community.webrequests.ImageRequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.ImageResponseListener;
import com.valvesoftware.android.steam.community.webrequests.RequestErrorInfo;
import com.valvesoftware.android.steam.community.webrequests.ResponseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

public abstract class BaseActivity extends ActionBarActivity {
    protected int activityLayoutId;
    private DrawerLayout drawerLayout;
    private ExpandableListView expandableListView;
    private SteamMenuItem extraMenuItem;
    private String limitedMenuBarSettingKey;
    protected Persona loggedInUser;
    List<Pair<EUserNotification, NavDrawerNotificationItem>> m_listNotificationNavItems;
    private MenuBar menuBar;
    private NavDrawerListAdapter navDrawerListAdapter;
    private LinearLayout navigationHeadersLayout;
    private View progressView;
    private OnClickListener refreshClickListener;
    private MenuItem refreshItem;
    private TextWatcher searchTextWatcher;
    private Handler signOutHandler;
    private Boolean useLimitedMenuBar;
    protected final UserNotificationCounts userNotificationCounts;

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.12 */
    class AnonymousClass12 implements OnClickListener {
        final /* synthetic */ SearchBarFragment val$searchBar;
        final /* synthetic */ TextWatcher val$searchTextWatcher;

        AnonymousClass12(SearchBarFragment searchBarFragment, TextWatcher textWatcher) {
            this.val$searchBar = searchBarFragment;
            this.val$searchTextWatcher = textWatcher;
        }

        public void onClick(View item) {
            this.val$searchBar.setSearchTextChangedListener(this.val$searchTextWatcher);
            this.val$searchBar.openSearch();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.16 */
    class AnonymousClass16 extends NavDrawerItem {

        /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.16.1 */
        class C01831 extends ResponseListener {
            C01831() {
            }

            public void onSuccess(JSONObject response) {
                LoggedInUserAccountInfo.setDontLoginToChat(!LoggedInUserAccountInfo.dontLoginToChat());
                if (BaseActivity.this.navDrawerListAdapter != null) {
                    BaseActivity.this.navDrawerListAdapter.notifyDataSetChanged();
                }
            }

            public void onError(RequestErrorInfo errorInfo) {
            }
        }

        AnonymousClass16(int x0, int x1, int x2, Intent x3, DrawerLayout x4) {
            super(x0, x1, x2, x3, x4);
        }

        public void onClick() {
            ResponseListener listener = new C01831();
            UmqCommunicator umqCommunicator = UmqCommunicator.getInstance();
            if (LoggedInUserAccountInfo.dontLoginToChat()) {
                umqCommunicator.loginToUmq(listener);
                umqCommunicator.start();
                return;
            }
            umqCommunicator.logOffFromUmq(listener);
        }

        public int getNameId() {
            return LoggedInUserAccountInfo.dontLoginToChat() ? C0151R.string.chat_go_online : C0151R.string.chat_go_offline;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.17 */
    class AnonymousClass17 extends NavDrawerItem {

        /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.17.1 */
        class C01851 implements DialogInterface.OnClickListener {

            /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.17.1.1 */
            class C01841 implements Runnable {
                C01841() {
                }

                public void run() {
                    UmqCommunicator.getInstance().signOutOfAppCompletely();
                    LoggedInUserAccountInfo.logOut();
                    BaseActivity.this.loggedInUser = null;
                    BaseActivity.this.refreshNavDrawer();
                    BaseActivity.this.finish();
                    Intent loginIntent = SteamAppIntents.loginIntent(SteamCommunityApplication.GetInstance());
                    loginIntent.addFlags(268435456);
                    BaseActivity.this.startActivity(loginIntent);
                }
            }

            C01851() {
            }

            public void onClick(DialogInterface dialog, int whichButton) {
                new GcmRegistrar().unregister();
                BaseActivity.this.signOutHandler.postDelayed(new C01841(), 500);
            }
        }

        AnonymousClass17(int x0, int x1, int x2, Intent x3, DrawerLayout x4) {
            super(x0, x1, x2, x3, x4);
        }

        public void onClick() {
            new Builder(BaseActivity.this).setTitle(C0151R.string.Sign_Out).setIcon(17301543).setPositiveButton(17039379, new C01851()).setNegativeButton(17039369, null).show();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.18 */
    class AnonymousClass18 implements RepositoryCallback<Persona> {
        final /* synthetic */ ImageView val$avatarView;
        final /* synthetic */ TextView val$nameView;

        AnonymousClass18(TextView textView, ImageView imageView) {
            this.val$nameView = textView;
            this.val$avatarView = imageView;
        }

        public void dataAvailable(Persona persona) {
            BaseActivity.this.loggedInUser = persona;
            AndroidUtils.setTextViewText(this.val$nameView, BaseActivity.this.loggedInUser.personaName);
            BaseActivity.this.loadAvatar(BaseActivity.this.loggedInUser, this.val$avatarView);
        }

        public void end() {
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.19 */
    class AnonymousClass19 extends ImageResponseListener {
        final /* synthetic */ ImageView val$avatarView;

        AnonymousClass19(ImageView imageView) {
            this.val$avatarView = imageView;
        }

        public void onSuccess(Bitmap bitmap) {
            this.val$avatarView.setImageBitmap(bitmap);
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.1 */
    class C01861 implements NotificationCountUpdateListener {
        C01861() {
        }

        public void notificationCountsChanged(UserNotificationCounts notificationCounts) {
            BaseActivity.this.userNotificationCounts.UpdateNotificationCounts(notificationCounts);
            BaseActivity.this.onNotificationCountsChanged();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.2 */
    class C01872 implements OnClickListener {
        C01872() {
        }

        public void onClick(View v) {
            BaseActivity.this.drawerLayout.openDrawer(8388611);
            BaseActivity.this.closeKeyboard();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.3 */
    class C01883 extends ActionBarDrawerToggle {
        C01883(Activity x0, DrawerLayout x1, Toolbar x2, int x3, int x4) {
            super(x0, x1, x2, x3, x4);
        }

        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            BaseActivity.this.closeKeyboard();
        }

        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, 0.0f);
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.4 */
    class C01894 implements OnClickListener {
        C01894() {
        }

        public void onClick(View view) {
            if (BaseActivity.this.drawerLayout.isDrawerOpen(BaseActivity.this.expandableListView)) {
                BaseActivity.this.startActivity(SteamAppIntents.visitProfileIntent(BaseActivity.this, LoggedInUserAccountInfo.getLoginSteamID()));
                BaseActivity.this.drawerLayout.closeDrawers();
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.5 */
    class C01905 extends OnQueryTextListenerCompat {
        C01905() {
        }

        public boolean onQueryTextChange(String newText) {
            if (BaseActivity.this.searchTextWatcher != null) {
                if (newText == null) {
                    newText = "";
                }
                BaseActivity.this.searchTextWatcher.onTextChanged(newText, 0, 0, newText.length());
            }
            return true;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.6 */
    class C01916 extends OnCloseListenerCompat {
        C01916() {
        }

        public boolean onClose() {
            ActivityHelper.hideKeyboard(BaseActivity.this);
            return false;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.7 */
    class C01927 implements OnMenuItemClickListener {
        final /* synthetic */ SearchBarFragment val$searchBar;

        C01927(SearchBarFragment searchBarFragment) {
            this.val$searchBar = searchBarFragment;
        }

        public boolean onMenuItemClick(MenuItem item) {
            this.val$searchBar.setSearchTextChangedListener(BaseActivity.this.searchTextWatcher);
            this.val$searchBar.openSearch();
            return true;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.8 */
    class C01938 implements OnMenuItemClickListener {
        C01938() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            if (BaseActivity.this.refreshClickListener != null) {
                BaseActivity.this.refreshClickListener.onClick(null);
            }
            return true;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.BaseActivity.9 */
    class C01949 implements OnMenuItemClickListener {
        C01949() {
        }

        public boolean onMenuItemClick(MenuItem item) {
            if (BaseActivity.this.extraMenuItem.onClickListener != null) {
                BaseActivity.this.extraMenuItem.onClickListener.onClick(null);
            }
            return true;
        }
    }

    public BaseActivity() {
        this.activityLayoutId = C0151R.layout.main_activity;
        this.limitedMenuBarSettingKey = "limitedMenuBarNeeded";
        this.signOutHandler = new Handler();
        this.userNotificationCounts = new UserNotificationCounts();
        this.useLimitedMenuBar = null;
        this.m_listNotificationNavItems = new ArrayList();
    }

    private boolean useLimitedMenuBar() {
        if (this.useLimitedMenuBar == null) {
            this.useLimitedMenuBar = Boolean.valueOf(getSharedPreferences(BaseActivity.class.getSimpleName(), 0).getBoolean(this.limitedMenuBarSettingKey, false));
        }
        return this.useLimitedMenuBar.booleanValue();
    }

    private void setUseLimitedMenuBar(boolean value) {
        Editor editor = getSharedPreferences(BaseActivity.class.getSimpleName(), 0).edit();
        editor.putBoolean(this.limitedMenuBarSettingKey, value);
        editor.commit();
        this.useLimitedMenuBar = Boolean.valueOf(value);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.locale == null) {
            config.locale = Locale.getDefault();
        }
        if (isTaskRoot()) {
            UmqCommunicator.getInstance().setNotificationCountUpdateListener(new C01861());
            LoggedInUserAccountInfo.updateLanguage();
            setContentView(this.activityLayoutId);
            this.navigationHeadersLayout = new LinearLayout(this);
            this.navigationHeadersLayout.setOrientation(1);
            setupView();
            if (LoggedInUserAccountInfo.getLoginSteamID() != null && LoggedInUserAccountInfo.getLoginSteamID().length() > 0) {
                new GcmRegistrar().registerWithGcm();
                return;
            }
            return;
        }
        finish();
    }

    @SuppressLint({"NewApi"})
    protected void setupView() {
        this.drawerLayout = (DrawerLayout) findViewById(C0151R.id.drawer_layout);
        this.expandableListView = (ExpandableListView) findViewById(C0151R.id.navigation);
        Toolbar toolbar = (Toolbar) findViewById(C0151R.id.steam_toolbar);
        this.menuBar = (MenuBar) findViewById(C0151R.id.menu_bar);
        this.progressView = ((LayoutInflater) getSystemService("layout_inflater")).inflate(C0151R.layout.progress_layout, null);
        ((ProgressBar) this.progressView.findViewById(C0151R.id.progressBar)).getIndeterminateDrawable().setColorFilter(C0151R.color.holo_gray_light, Mode.DST);
        if (useLimitedMenuBar()) {
            toolbar.setVisibility(8);
            this.menuBar.setVisibility(0);
            this.menuBar.setHamburgerClickedListener(new C01872());
        } else {
            try {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar();
                ActionBarDrawerToggle mDrawerToggle = new C01883(this, this.drawerLayout, toolbar, C0151R.string.app_name, C0151R.string.app_name);
                this.drawerLayout.setDrawerListener(mDrawerToggle);
                mDrawerToggle.syncState();
            } catch (Throwable t) {
                if (VERSION.SDK_INT > 15) {
                    setUseLimitedMenuBar(true);
                    recreate();
                    return;
                }
                RuntimeException runtimeException = new RuntimeException(t);
            }
        }
        setupNavDrawer();
        View v = findViewById(C0151R.id.nav_header_view);
        if (v != null) {
            v.setOnClickListener(new C01894());
        }
        String key = "NavigationDrawerShown";
        SharedPreferences sp = getSharedPreferences(MainActivity.class.getSimpleName(), 0);
        if (!sp.getBoolean(key, false)) {
            this.drawerLayout.openDrawer(8388611);
            Editor editor = sp.edit();
            editor.putBoolean(key, true);
            editor.commit();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (useLimitedMenuBar()) {
            if (this.searchTextWatcher != null) {
                setupSearchBarForLimitedMenuBar(this.searchTextWatcher);
            } else {
                this.menuBar.setSearchClickedListener(null);
            }
            this.menuBar.setRefreshClickedListener(this.refreshClickListener);
            if (this.extraMenuItem != null) {
                this.menuBar.setExtraMenuItem(this.extraMenuItem);
            }
        } else {
            menu.clear();
            if (this.searchTextWatcher != null) {
                MenuItem searchViewMenuItem = menu.add("");
                searchViewMenuItem.setIcon(C0151R.drawable.ic_action_search);
                MenuItemCompat.setShowAsAction(searchViewMenuItem, 2);
                View searchView = SearchViewCompat.newSearchView(this);
                if (searchView != null) {
                    View v = searchView.findViewById(getResources().getIdentifier("android:id/search_button", null, null));
                    if (v != null && (v instanceof ImageView)) {
                        ((ImageView) v).setImageResource(C0151R.drawable.ic_action_search);
                    }
                    SearchViewCompat.setOnQueryTextListener(searchView, new C01905());
                    SearchViewCompat.setOnCloseListener(searchView, new C01916());
                    MenuItemCompat.setActionView(searchViewMenuItem, searchView);
                } else {
                    SearchBarFragment searchBar = getSearchBar();
                    if (searchBar != null) {
                        searchViewMenuItem.setOnMenuItemClickListener(new C01927(searchBar));
                    }
                }
            }
            if (this.refreshClickListener != null) {
                this.refreshItem = menu.add("");
                this.refreshItem.setIcon(C0151R.drawable.ic_action_refresh);
                MenuItemCompat.setShowAsAction(this.refreshItem, 2);
                this.refreshItem.setOnMenuItemClickListener(new C01938());
            }
            if (this.extraMenuItem != null) {
                MenuItem item = menu.add("");
                item.setIcon(this.extraMenuItem.iconResourceId);
                MenuItemCompat.setShowAsAction(item, 2);
                item.setOnMenuItemClickListener(new C01949());
            }
        }
        return true;
    }

    protected boolean closeDrawer() {
        if (!this.drawerLayout.isDrawerOpen(8388611)) {
            return false;
        }
        this.drawerLayout.closeDrawers();
        return true;
    }

    public void showProgressIndicator() {
        if (!isFinishing()) {
            if (useLimitedMenuBar() && this.menuBar != null) {
                this.menuBar.showProgressIndicator();
            } else if (this.refreshItem != null && MenuItemCompat.getActionView(this.refreshItem) != this.progressView) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        MenuItemCompat.setActionView(BaseActivity.this.refreshItem, BaseActivity.this.progressView);
                    }
                });
            }
        }
    }

    public void hideProgressIndicator() {
        hideProgressIndicator(500);
    }

    protected void hideProgressIndicator(int delayMs) {
        if (!isFinishing()) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    if (!BaseActivity.this.isFinishing()) {
                        if (BaseActivity.this.useLimitedMenuBar() && BaseActivity.this.menuBar != null) {
                            BaseActivity.this.menuBar.hideProgressIndicator();
                        } else if (BaseActivity.this.refreshItem != null) {
                            MenuItemCompat.setActionView(BaseActivity.this.refreshItem, null);
                        }
                    }
                }
            }, (long) delayMs);
        }
    }

    public void setRefreshButtonClickListener(OnClickListener refreshClickListener) {
        if (!useLimitedMenuBar() || this.menuBar == null) {
            this.refreshClickListener = refreshClickListener;
        } else {
            this.menuBar.setRefreshClickedListener(refreshClickListener);
        }
        invalidateOptionsMenu();
    }

    public void setSearchTextListener(TextWatcher searchTextWatcher) {
        this.searchTextWatcher = searchTextWatcher;
        if (useLimitedMenuBar()) {
            setupSearchBarForLimitedMenuBar(searchTextWatcher);
        } else {
            invalidateOptionsMenu();
        }
    }

    private void setupSearchBarForLimitedMenuBar(TextWatcher searchTextWatcher) {
        if (searchTextWatcher == null) {
            this.menuBar.setSearchClickedListener(null);
            return;
        }
        SearchBarFragment searchBar = getSearchBar();
        if (searchBar != null) {
            this.menuBar.setSearchClickedListener(new AnonymousClass12(searchBar, searchTextWatcher));
        }
    }

    public void setExtraToolbarItem(SteamMenuItem toolbarItem) {
        this.extraMenuItem = toolbarItem;
        if (useLimitedMenuBar()) {
            this.menuBar.setExtraMenuItem(this.extraMenuItem);
        } else {
            invalidateOptionsMenu();
        }
    }

    public void setTitle(CharSequence t) {
        if (!useLimitedMenuBar() || this.menuBar == null) {
            super.setTitle(t);
        } else {
            this.menuBar.setTitle(t);
        }
    }

    protected void clearExtraMenuItems() {
        this.extraMenuItem = null;
        if (useLimitedMenuBar()) {
            this.menuBar.setExtraMenuItem(null);
        }
    }

    protected void clearTitleLabel() {
        setTitle("");
    }

    protected void clearSearchButtonListener() {
        this.searchTextWatcher = null;
        if (this.menuBar != null) {
            this.menuBar.setSearchClickedListener(null);
        }
    }

    protected void clearRefreshButtonListener() {
        setRefreshButtonClickListener(null);
    }

    protected void hideKeyboard() {
        ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(findViewById(16908290).getWindowToken(), 0);
    }

    protected SearchBarFragment getSearchBar() {
        Fragment f = getSupportFragmentManager().findFragmentById(C0151R.id.searchbar);
        if (f instanceof SearchBarFragment) {
            return (SearchBarFragment) f;
        }
        return null;
    }

    protected void hideSearchBar() {
        SearchBarFragment searchBar = getSearchBar();
        if (searchBar != null) {
            searchBar.closeSearch();
        }
    }

    private void setupNavHeader() {
        this.navigationHeadersLayout.removeAllViews();
        this.navigationHeadersLayout.addView(getNavigationDrawerHeader());
    }

    protected void setupNavDrawer() {
        setupNavHeader();
        this.expandableListView.addHeaderView(this.navigationHeadersLayout);
        refreshNavDrawer();
    }

    protected void refreshNavDrawer() {
        setupNavHeader();
        this.navDrawerListAdapter = new NavDrawerListAdapter(this, getNavigationItems());
        this.expandableListView.setAdapter(this.navDrawerListAdapter);
        this.expandableListView.setOnGroupExpandListener(new CustomOnGroupExpandListener(this.expandableListView));
        this.expandableListView.setOnChildClickListener(new OnChildClickListener() {
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                v.setSelected(true);
                return false;
            }
        });
        this.expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                v.setSelected(true);
                return false;
            }
        });
    }

    private View getNavigationDrawerHeader() {
        View headerView = View.inflate(this, C0151R.layout.nav_header, null);
        ImageView avatarView = (ImageView) headerView.findViewById(C0151R.id.avatar);
        TextView nameView = (TextView) headerView.findViewById(C0151R.id.name);
        headerView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                BaseActivity.this.startActivity(SteamAppIntents.visitProfileIntent(BaseActivity.this, LoggedInUserAccountInfo.getLoginSteamID()));
                BaseActivity.this.closeDrawer();
            }
        });
        loadUserInfo(avatarView, nameView);
        return headerView;
    }

    private List<NavDrawerItem> getNavigationItems() {
        int i;
        Context context = SteamCommunityApplication.GetInstance();
        List<NavDrawerItem> navDrawerItems = new ArrayList();
        navDrawerItems.add(new NavDrawerItem(-1, C0151R.drawable.ic_action_steamguard, C0151R.string.Steam_Guard, SteamAppIntents.viewSteamGuard(context), this.drawerLayout));
        navDrawerItems.add(new NavDrawerItem(-1, C0151R.drawable.ic_action_steamguard, C0151R.string.Confirmations, SteamAppIntents.viewConfirmations(context), this.drawerLayout));
        navDrawerItems.add(new NavDrawerItem(C0151R.string.menu_chat, 0, C0151R.string.menu_chat, SteamAppIntents.viewFriendsList(context), this.drawerLayout));
        navDrawerItems.add(getNotificationNavigationItems(this.drawerLayout));
        NavDrawerItem storeGroup = new NavDrawerItem(C0151R.string.Store_Caps, 0, C0151R.string.Store, null, this.drawerLayout);
        storeGroup.add(new NavDrawerItem(C0151R.string.Store_Caps, 0, C0151R.string.Catalog, SteamAppIntents.viewCatalog(context), this.drawerLayout));
        storeGroup.add(new NavDrawerItem(C0151R.string.Store_Caps, 0, C0151R.string.Cart, SteamAppIntents.viewShoppingCart(context), this.drawerLayout));
        storeGroup.add(new NavDrawerItem(C0151R.string.Store_Caps, 0, C0151R.string.Search, SteamAppIntents.searchSteam(context), this.drawerLayout));
        storeGroup.add(new NavDrawerItem(C0151R.string.Store_Caps, 0, C0151R.string.Wishlist, SteamAppIntents.viewWishList(context), this.drawerLayout));
        storeGroup.add(new NavDrawerItem(C0151R.string.Store_Caps, 0, C0151R.string.Steam_News, SteamAppIntents.viewSteamNews(context), this.drawerLayout));
        storeGroup.add(new NavDrawerItem(C0151R.string.Settings_Caps, 0, C0151R.string.Account_Details, SteamAppIntents.viewAccountDetails(context), this.drawerLayout));
        navDrawerItems.add(storeGroup);
        NavDrawerItem communityGroup = new NavDrawerItem(C0151R.string.Community_Caps, 0, C0151R.string.menu_community, null, this.drawerLayout);
        communityGroup.add(new NavDrawerItem(C0151R.string.Community_Caps, 0, C0151R.string.menu_community_home, SteamAppIntents.communityURLIntent(context, "/"), this.drawerLayout));
        communityGroup.add(new NavDrawerItem(C0151R.string.Community_Caps, 0, C0151R.string.menu_community_discussions, SteamAppIntents.communityURLIntent(context, "/discussions/"), this.drawerLayout));
        communityGroup.add(new NavDrawerItem(C0151R.string.Community_Caps, 0, C0151R.string.menu_community_market, SteamAppIntents.communityURLIntent(context, "/market/"), this.drawerLayout));
        communityGroup.add(new NavDrawerItem(C0151R.string.Community_Caps, 0, C0151R.string.menu_community_broadcasts, SteamAppIntents.communityURLIntent(context, "/?subsection=broadcasts"), this.drawerLayout));
        navDrawerItems.add(communityGroup);
        NavDrawerItem personalGroup = new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal, null, this.drawerLayout);
        personalGroup.add(new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal_activity, SteamAppIntents.profileURLIntent(context, "/home/"), this.drawerLayout));
        personalGroup.add(new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal_profile, SteamAppIntents.profileURLIntent(context, "/"), this.drawerLayout));
        personalGroup.add(new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal_friends, SteamAppIntents.profileURLIntent(context, "/friends/"), this.drawerLayout));
        personalGroup.add(new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal_groups, SteamAppIntents.profileURLIntent(context, "/groups/"), this.drawerLayout));
        personalGroup.add(new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal_content, SteamAppIntents.profileURLIntent(context, "/screenshots/"), this.drawerLayout));
        personalGroup.add(new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal_badges, SteamAppIntents.profileURLIntent(context, "/badges/"), this.drawerLayout));
        personalGroup.add(new NavDrawerItem(C0151R.string.menu_personal, 0, C0151R.string.menu_personal_inventory, SteamAppIntents.profileURLIntent(context, "/inventory/"), this.drawerLayout));
        navDrawerItems.add(personalGroup);
        navDrawerItems.add(new NavDrawerItem(C0151R.string.Library, 0, C0151R.string.Library, SteamAppIntents.viewLibrary(context), this.drawerLayout));
        navDrawerItems.add(new NavDrawerItem(C0151R.string.menu_support, 0, C0151R.string.menu_support, SteamAppIntents.helpURLIntent(context, "/"), this.drawerLayout));
        NavDrawerItem settingsGroup = new NavDrawerItem(C0151R.string.Settings_Caps, 0, C0151R.string.Settings, null, this.drawerLayout);
        settingsGroup.add(new NavDrawerItem(C0151R.string.Settings_Caps, 0, C0151R.string.ApplicationPreferences, SteamAppIntents.viewSettings(context), this.drawerLayout));
        if (LoggedInUserAccountInfo.dontLoginToChat()) {
            i = C0151R.string.chat_go_online;
        } else {
            i = C0151R.string.chat_go_offline;
        }
        settingsGroup.add(new AnonymousClass16(C0151R.string.Settings_Caps, 0, i, null, this.drawerLayout));
        settingsGroup.add(new AnonymousClass17(C0151R.string.Settings_Caps, 0, C0151R.string.Sign_Out, null, this.drawerLayout));
        navDrawerItems.add(settingsGroup);
        return navDrawerItems;
    }

    protected NavDrawerItem getNotificationNavigationItems(DrawerLayout drawerLayout) {
        Context context = SteamCommunityApplication.GetInstance();
        NavDrawerNotificationItem notificationGroup = new NavDrawerGroupItem(C0151R.string.Notifications, C0151R.string.Notifications, drawerLayout);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationInvalid, notificationGroup);
        NavDrawerNotificationItem commentNotifications = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_comments, SteamAppIntents.notificationCommentsIntent(context), drawerLayout, false);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationComment, commentNotifications);
        notificationGroup.add(commentNotifications);
        NavDrawerNotificationItem items = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_items, SteamAppIntents.notificationItemsIntent(context), drawerLayout, false);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationItem, items);
        notificationGroup.add(items);
        NavDrawerNotificationItem invites = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_invites, SteamAppIntents.notificationInvitesIntent(context), drawerLayout, false);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationFriendInvite, invites);
        notificationGroup.add(invites);
        NavDrawerNotificationItem gifts = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_gifts, SteamAppIntents.notificationGiftsIntent(context), drawerLayout, false);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationGift, gifts);
        notificationGroup.add(gifts);
        NavDrawerNotificationItem tradeOffers = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_trade, SteamAppIntents.notificationTradeOffersIntent(context), drawerLayout, true);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationTradeOffer, tradeOffers);
        notificationGroup.add(tradeOffers);
        NavDrawerNotificationItem offlineMessages = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_chat, SteamAppIntents.viewFriendsList(context), drawerLayout, true);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationOfflineMessage, offlineMessages);
        notificationGroup.add(offlineMessages);
        NavDrawerNotificationItem asyncgame = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_asyncgameinvite, SteamAppIntents.notificationAsyncGameIntent(context), drawerLayout, true);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationAsyncGameState, asyncgame);
        notificationGroup.add(asyncgame);
        NavDrawerNotificationItem moderatormessages = new NavDrawerNotificationItem(C0151R.string.Notifications, C0151R.string.notification_moderatormessages, SteamAppIntents.notificationModeratorMessageIntent(context), drawerLayout, true);
        registerNotificationDrawerItem(EUserNotification.k_EUserNotificationModeratorMessage, moderatormessages);
        notificationGroup.add(moderatormessages);
        return notificationGroup;
    }

    protected void registerNotificationDrawerItem(EUserNotification eUserNotification, NavDrawerNotificationItem item) {
        if (eUserNotification == EUserNotification.k_EUserNotificationInvalid) {
            item.setNotificationCount(this.userNotificationCounts.GetTotalNotificationCount());
        } else {
            item.setNotificationCount(this.userNotificationCounts.GetNotificationCount(eUserNotification));
        }
        this.m_listNotificationNavItems.add(new Pair(eUserNotification, item));
    }

    protected void onNotificationCountsChanged() {
        boolean bNeedRefresh = false;
        int cTotalNotifications = this.userNotificationCounts.GetTotalNotificationCount();
        for (Pair<EUserNotification, NavDrawerNotificationItem> pair : this.m_listNotificationNavItems) {
            int count;
            if (pair.first == EUserNotification.k_EUserNotificationInvalid) {
                count = cTotalNotifications;
            } else {
                count = this.userNotificationCounts.GetNotificationCount((EUserNotification) pair.first);
            }
            boolean bWasHidden = ((NavDrawerNotificationItem) pair.second).isHidden();
            ((NavDrawerNotificationItem) pair.second).setNotificationCount(count);
            if (bWasHidden != ((NavDrawerNotificationItem) pair.second).isHidden()) {
                bNeedRefresh = true;
            }
        }
        if (bNeedRefresh) {
            this.expandableListView.setAdapter(this.navDrawerListAdapter);
        }
    }

    protected void showMenuAndActionBar() {
        if (useLimitedMenuBar()) {
            this.menuBar.setVisibility(0);
        } else {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.show();
            }
        }
        this.drawerLayout.setDrawerLockMode(0);
    }

    protected void hideMenuAndActionBar() {
        if (useLimitedMenuBar()) {
            this.menuBar.setVisibility(8);
        } else {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.hide();
            }
        }
        this.drawerLayout.setDrawerLockMode(1);
    }

    private void loadUserInfo(ImageView avatarView, TextView nameView) {
        if (this.loggedInUser != null) {
            AndroidUtils.setTextViewText(nameView, this.loggedInUser.personaName);
            loadAvatar(this.loggedInUser, avatarView);
        }
        if (LoggedInUserAccountInfo.getLoginSteamID() != null && LoggedInUserAccountInfo.getLoginSteamID().length() > 0) {
            PersonaRepository.getDetailedPersonaInfo(LoggedInUserAccountInfo.getLoginSteamID(), new AnonymousClass18(nameView, avatarView));
        }
    }

    private void loadAvatar(Persona persona, ImageView avatarView) {
        ImageRequestBuilder requestBuilder = Endpoints.getImageUrlRequestBuilder(persona.fullAvatarUrl);
        requestBuilder.setResponseListener(new AnonymousClass19(avatarView));
        SteamCommunityApplication.GetInstance().sendRequest(requestBuilder);
    }

    private void closeKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View view = getCurrentFocus();
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && requestCode == SettingInfoDB.ringToneSelectorRequestCode) {
            Uri uri = (Uri) data.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
            if (uri != null) {
                SteamCommunityApplication.GetInstance().GetSettingInfoDB().m_settingRing.setValueAndCommit(SteamCommunityApplication.GetInstance(), uri.toString());
            }
        }
    }
}
