package com.valvesoftware.android.steam.community.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.GcmRegistrar;
import com.valvesoftware.android.steam.community.LoggedInUserAccountInfo;
import com.valvesoftware.android.steam.community.SettingInfoDB.StartScreen;
import com.valvesoftware.android.steam.community.SteamAppIntents;
import com.valvesoftware.android.steam.community.SteamAppUri;
import com.valvesoftware.android.steam.community.SteamCommunityApplication;
import com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty;
import com.valvesoftware.android.steam.community.SteamguardState;
import com.valvesoftware.android.steam.community.UmqCommunicator;
import com.valvesoftware.android.steam.community.fragment.ChatFragment;
import com.valvesoftware.android.steam.community.fragment.FriendSearchFragment;
import com.valvesoftware.android.steam.community.fragment.FriendsListFragment;
import com.valvesoftware.android.steam.community.fragment.GroupListFragment;
import com.valvesoftware.android.steam.community.fragment.GroupSearchFragment;
import com.valvesoftware.android.steam.community.fragment.IBackButtonSupport;
import com.valvesoftware.android.steam.community.fragment.LoginFragment;
import com.valvesoftware.android.steam.community.fragment.SettingsFragment;
import com.valvesoftware.android.steam.community.fragment.SteamguardFragmentWeb;
import com.valvesoftware.android.steam.community.fragment.TabbedSteamWebViewFragment;
import com.valvesoftware.android.steam.community.fragment.WebViewFragment;
import com.valvesoftware.android.steam.community.model.Persona;
import com.valvesoftware.android.steam.community.views.SteamWebView;
import com.valvesoftware.android.steam.community.webrequests.Endpoints;
import com.valvesoftware.android.steam.community.webrequests.RequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.RequestErrorInfo;
import com.valvesoftware.android.steam.community.webrequests.ResponseListener;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {
    private long lastLoginFragmentLoadTime;
    private final long loginFragmentRefreshDelay;
    public final SteamData steamData;
    private SteamWebView steamWebViewClient;

    /* renamed from: com.valvesoftware.android.steam.community.activity.MainActivity.1 */
    class C01951 implements LoginChangedListener {
        final /* synthetic */ Intent val$intentAfterLoginCompletes;

        C01951(Intent intent) {
            this.val$intentAfterLoginCompletes = intent;
        }

        public void onLoginChangedSuccessfully() {
            new GcmRegistrar().registerWithGcm();
            MainActivity.this.userNotificationCounts.Clear();
            MainActivity.this.refreshUserNotificationCounts();
            MainActivity.this.refreshNavDrawer();
            MainActivity.this.getSupportFragmentManager().popBackStack();
            MainActivity.this.showMenuAndActionBar();
            UmqCommunicator.getInstance().start();
            if (this.val$intentAfterLoginCompletes == null) {
                MainActivity.this.loadUserDefaultFragment();
            } else {
                MainActivity.this.onNewIntent(this.val$intentAfterLoginCompletes);
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.MainActivity.2 */
    class C01962 extends ResponseListener {
        C01962() {
        }

        public void onSuccess(JSONObject response) {
            MainActivity.this.userNotificationCounts.Clear();
            JSONArray rgJsonNotifications = response.optJSONArray("notifications");
            if (rgJsonNotifications != null) {
                for (int i = 0; i < rgJsonNotifications.length(); i++) {
                    JSONObject oJsonNotification = rgJsonNotifications.optJSONObject(i);
                    if (oJsonNotification != null) {
                        MainActivity.this.userNotificationCounts.SetNotificationCount(oJsonNotification.optInt("user_notification_type", 0), oJsonNotification.optInt("count", 0));
                    }
                }
            }
            MainActivity.this.onNotificationCountsChanged();
        }

        public void onError(RequestErrorInfo errorInfo) {
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.activity.MainActivity.3 */
    static /* synthetic */ class C01973 {
        static final /* synthetic */ int[] f64xa71f8632;

        static {
            f64xa71f8632 = new int[StartScreen.values().length];
            try {
                f64xa71f8632[StartScreen.Catalog.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f64xa71f8632[StartScreen.Groups.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f64xa71f8632[StartScreen.SteamGuard.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f64xa71f8632[StartScreen.SteamNews.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f64xa71f8632[StartScreen.FriendActivity.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f64xa71f8632[StartScreen.Library.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f64xa71f8632[StartScreen.Unknown.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f64xa71f8632[StartScreen.Friends.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static class SteamData {
        private final Map<String, Persona> steamIdToFriendsMap;

        public SteamData() {
            this.steamIdToFriendsMap = new HashMap();
        }

        public Map<String, Persona> getSteamIdToFriendsMap() {
            return this.steamIdToFriendsMap;
        }

        public void saveFriends(Map<String, Persona> mostRecentFriendsMap) {
            this.steamIdToFriendsMap.clear();
            this.steamIdToFriendsMap.putAll(mostRecentFriendsMap);
        }
    }

    public MainActivity() {
        this.lastLoginFragmentLoadTime = 0;
        this.loginFragmentRefreshDelay = 120000;
        this.steamData = new SteamData();
    }

    public void onBackPressed() {
        if (!closeDrawer()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return;
            }
            clearTitleLabel();
            hideSearchBar();
            clearRefreshButtonListener();
            clearSearchButtonListener();
            clearExtraMenuItems();
            super.onBackPressed();
        }
    }

    protected void onNewIntent(Intent intent) {
        "onNewIntent: " + intent.getAction();
        if (intent.hasCategory("android.intent.category.LAUNCHER") && getIntent() != null) {
            return;
        }
        if (isSpecialNonLoggedInUri(intent) || LoggedInUserAccountInfo.isLoggedIn()) {
            super.onNewIntent(intent);
            setIntent(intent);
            doIntent(intent);
            return;
        }
        loadLoginFragment(intent);
    }

    private void loadLoginFragment() {
        loadLoginFragment(null);
    }

    private void loadLoginFragment(Intent intentAfterLoginCompletes) {
        "loadLoginFragment: " + getCurrentBackStackEntryName() + " " + this.lastLoginFragmentLoadTime;
        if (!getCurrentBackStackEntryName().equals(LoginFragment.class.getSimpleName()) || System.currentTimeMillis() - this.lastLoginFragmentLoadTime >= 120000) {
            new GcmRegistrar().unregister();
            getSupportFragmentManager().popBackStack(null, 1);
            this.lastLoginFragmentLoadTime = System.currentTimeMillis();
            hideMenuAndActionBar();
            LoginFragment loginFragment = new LoginFragment();
            loginFragment.setLoginChangedListener(new C01951(intentAfterLoginCompletes));
            loadFragment(loginFragment, LoginFragment.class.getSimpleName(), true);
        }
    }

    private void refreshUserNotificationCounts() {
        RequestBuilder notificationCountRequest = Endpoints.getUserNotificationCounts();
        notificationCountRequest.setResponseListener(new C01962());
        SteamCommunityApplication.GetInstance().sendRequest(notificationCountRequest);
    }

    private boolean isSpecialNonLoggedInUri(Intent intent) {
        if (intent == null) {
            return false;
        }
        Uri uri = intent.getData();
        if (uri == null || uri.getHost() == null || !uri.getHost().equals("openurl") || uri.getQuery() == null) {
            return false;
        }
        if (uri.getQuery().indexOf(SteamAppUri.steamHelpUriPrefix()) == 4) {
            return true;
        }
        if (uri.getQuery().indexOf(SteamAppUri.steamSubscriberAgreementUriPrefix()) == 4) {
            return true;
        }
        if (uri.getQuery().indexOf(SteamAppUri.steamPrivacyPolicyUriPrefix()) == 4) {
            return true;
        }
        return false;
    }

    protected void onResume() {
        super.onResume();
        SteamCommunityApplication.switchingToForeground(this);
    }

    protected void onResumeFragments() {
        super.onResumeFragments();
        if (isSpecialNonLoggedInUri(getIntent()) || LoggedInUserAccountInfo.isLoggedIn()) {
            if (this.loggedInUser == null) {
                refreshNavDrawer();
            }
            SteamCommunityApplication.GetInstance().getLocalDb().clearNotifications();
            if (LoggedInUserAccountInfo.isLoggedIn()) {
                refreshUserNotificationCounts();
            }
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if (getIntent() != null) {
                    doIntent(getIntent());
                } else {
                    loadUserDefaultFragment();
                }
            }
            UmqCommunicator umqCommunicator = UmqCommunicator.getInstance();
            if (!LoggedInUserAccountInfo.dontLoginToChat() && LoggedInUserAccountInfo.isLoggedIn()) {
                umqCommunicator.start();
                return;
            }
            return;
        }
        loadLoginFragment();
    }

    private String getCurrentBackStackEntryName() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            return getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
        }
        return "";
    }

    private void loadFragment(Fragment fragment, String newEntryName) {
        loadFragment(fragment, newEntryName, false);
    }

    private void loadFragment(Fragment fragment, String newEntryName, boolean bForce) {
        "loadFragment: " + newEntryName;
        if (getSupportFragmentManager().getBackStackEntryCount() > 0 && !bForce) {
            BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1);
            "loadFragment has backstack " + backStackEntry.getName();
            if (newEntryName != null && newEntryName.equals(backStackEntry.getName())) {
                return;
            }
        }
        clearTitleLabel();
        hideProgressIndicator(0);
        hideSearchBar();
        clearRefreshButtonListener();
        clearSearchButtonListener();
        clearExtraMenuItems();
        invalidateOptionsMenu();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(C0151R.id.fragment_placeholder, fragment);
        fragmentTransaction.addToBackStack(newEntryName);
        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    private void doIntent(Intent intent) {
        hideKeyboard();
        Uri u = intent.getData();
        if (u == null) {
            loadUserDefaultFragment();
            return;
        }
        String resource = u.getHost();
        "doIntent: " + resource;
        if (resource == null) {
            loadUserDefaultFragment();
        } else if (resource.equals("appsettings")) {
            loadSettingsFragment();
        } else if (resource.equals("login")) {
            loadLoginFragment();
        } else if (resource.equals("deletenotification")) {
            ((NotificationManager) SteamCommunityApplication.GetInstance().getSystemService("notification")).cancel(intent.getIntExtra(SteamAppIntents.notificationId, 0));
        } else if (resource.equals("friends")) {
            query = u.getQueryParameter("search");
            if (query == null) {
                loadFriendsFragment();
            } else {
                loadFriendsSearchFragment(query);
            }
        } else if (resource.equals("chat")) {
            String chatPartnerSteamId = u.getQueryParameter("steamid");
            if (chatPartnerSteamId != null) {
                loadChatFragment(chatPartnerSteamId, intent);
            }
        } else if (resource.equals("groups")) {
            query = u.getQueryParameter("search");
            if (query == null) {
                loadGroupsFragment();
            } else {
                loadGroupsSearchFragment(query);
            }
        } else if (resource.equals("openurl")) {
            url = SteamWebView.extractUrlFromOpenUrlUri(u);
            if (url != null) {
                loadWebViewFragment(url);
            }
        } else if (resource.equals("opencategoryurl")) {
            url = u.getQueryParameter("url");
            if (url != null) {
                loadTabbedWebViewFragment(url);
            }
        } else if (resource.equals("steamguard")) {
            loadSteamGuardWebFragment(SteamAppUri.STEAMGUARD_PRECHANGE);
        } else if (resource.equals("steamguardweb")) {
            loadSteamGuardWebFragment();
        } else if (resource.equals("steamguardviewrcode")) {
            loadSteamGuardWebFragment(SteamAppUri.STEAMGUARD_RCODE);
        } else if (resource.equals("confirmation")) {
            SteamguardState sgState = SteamguardState.steamguardStateForLoggedInUser();
            if (sgState == null || sgState.getTwoFactorToken() == null) {
                loadWebViewFragment(String.format("%s?p=%s&a=%s&m=android", new Object[]{SteamAppUri.CONFIRMATION_WEB, SteamguardState.getUniqueIdForPhone(), LoggedInUserAccountInfo.getLoginSteamID()}));
                return;
            }
            url = sgState.getConfirmationUrl();
            if (url != null) {
                loadWebViewFragment(url);
            }
        } else {
            loadUserDefaultFragment();
        }
    }

    private void loadUserDefaultFragment() {
        switch (C01973.f64xa71f8632[StartScreen.fromInt(SteamCommunityApplication.GetInstance().GetSettingInfoDB().m_startScreen.getIntegerValue(this)).ordinal()]) {
            case C0151R.styleable.View_paddingStart /*1*/:
                startActivity(SteamAppIntents.viewCatalog(this));
            case C0151R.styleable.View_paddingEnd /*2*/:
                startActivity(SteamAppIntents.viewGroupsList(this));
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                startActivity(SteamAppIntents.viewSteamGuard(this));
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                startActivity(SteamAppIntents.viewSteamNews(this));
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                startActivity(SteamAppIntents.viewFriendActivity(this));
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                startActivity(SteamAppIntents.viewLibrary(this));
            default:
                startActivity(SteamAppIntents.viewFriendsList(this));
        }
    }

    private void loadSettingsFragment() {
        loadFragment(new SettingsFragment(), SettingsFragment.class.getSimpleName());
    }

    private void loadSteamGuardWebFragment() {
        loadFragment(new SteamguardFragmentWeb(), SteamguardFragmentWeb.class.getSimpleName(), true);
    }

    private void loadSteamGuardWebFragment(String url) {
        SteamguardFragmentWeb steamguardFragmentWeb = new SteamguardFragmentWeb();
        Bundle args = new Bundle();
        args.putString("defaultUrl", url);
        steamguardFragmentWeb.setArguments(args);
        loadFragment(steamguardFragmentWeb, SteamguardFragmentWeb.class.getSimpleName(), true);
    }

    private void loadWebViewFragment(String url) {
        loadFragment(getWebViewFragment(url), WebViewFragment.class.getSimpleName() + url, true);
    }

    private void loadTabbedWebViewFragment(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        WebViewFragment webViewFragment = new TabbedSteamWebViewFragment();
        webViewFragment.setArguments(args);
        loadFragment(webViewFragment, WebViewFragment.class.getSimpleName() + url);
    }

    private WebViewFragment getWebViewFragment(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setArguments(args);
        return webViewFragment;
    }

    private void loadGroupsSearchFragment(String query) {
        loadFragment(getGroupsSearchFragment(query), GroupSearchFragment.class.getSimpleName() + query);
    }

    private void loadGroupsFragment() {
        loadFragment(new GroupListFragment(), GroupListFragment.class.getSimpleName());
    }

    private void loadFriendsSearchFragment(String query) {
        FriendSearchFragment searchFragment = new FriendSearchFragment();
        Bundle args = new Bundle();
        args.putString("friends", query);
        searchFragment.setArguments(args);
        loadFragment(searchFragment, FriendSearchFragment.class.getSimpleName() + query);
    }

    private Fragment getGroupsSearchFragment(String query) {
        GroupSearchFragment searchFragment = new GroupSearchFragment();
        Bundle args = new Bundle();
        args.putString("groups", query);
        searchFragment.setArguments(args);
        return searchFragment;
    }

    private void loadChatFragment(String chatPartnerSteamId, Intent intent) {
        ChatFragment chatFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString("chatPartnerSteamIdKey", chatPartnerSteamId);
        args.putString("chatPartnerAvatarUrl", intent.getStringExtra(SteamAppIntents.chatPartnerAvatarUrlKey));
        args.putString("chatPartnerPersonaNameKey", intent.getStringExtra(SteamAppIntents.chatPartnerPersonaNameKey));
        args.putString("loggedInUserAvatarUrl", this.loggedInUser != null ? this.loggedInUser.mediumAvatarUrl : null);
        chatFragment.setArguments(args);
        loadFragment(chatFragment, ChatFragment.class.getSimpleName() + chatPartnerSteamId);
    }

    private void loadFriendsFragment() {
        loadFragment(new FriendsListFragment(), FriendsListFragment.class.getSimpleName());
    }

    public void readyForPaypalComplete(SteamWebView steamWebViewClient) {
        this.steamWebViewClient = steamWebViewClient;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (result != null) {
            String sCall = result.getStringExtra(CommandProperty.call.toString());
            if (sCall != null && sCall.length() > 0) {
                this.steamWebViewClient.loadUrl("javascript:(function(){" + sCall + ";})()");
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, result);
    }

    public void onPause() {
        super.onPause();
        SteamCommunityApplication.switchingToBackground();
        UmqCommunicator.getInstance().stop();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(C0151R.id.fragment_placeholder);
            if (fragment != null && (fragment instanceof IBackButtonSupport)) {
                IBackButtonSupport backButtonSupportingFragment = (IBackButtonSupport) fragment;
                if (backButtonSupportingFragment.canGoBack()) {
                    backButtonSupportingFragment.goBack();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean refreshConfirmationsPageIfActive() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(C0151R.id.fragment_placeholder);
        if (fragment == null || !(fragment instanceof WebViewFragment)) {
            return false;
        }
        return ((WebViewFragment) fragment).refreshConfirmationsPageIfActive();
    }
}
