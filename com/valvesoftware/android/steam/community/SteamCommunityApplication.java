package com.valvesoftware.android.steam.community;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.valvesoftware.android.steam.community.Config.SteamUniverse;
import com.valvesoftware.android.steam.community.LoggedInUserAccountInfo.LoginInformation;
import com.valvesoftware.android.steam.community.SteamDBDiskCache.IndefiniteCache;
import com.valvesoftware.android.steam.community.activity.MainActivity;
import com.valvesoftware.android.steam.community.webrequests.RequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.RequestErrorInfo;
import com.valvesoftware.android.steam.community.webrequests.ResponseListener;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SteamCommunityApplication extends Application {
    public static final Handler confirmationRefreshHandler;
    private static final Handler foregroundStatusHandler;
    public static boolean isInForeground;
    public static MainActivity mMainActivity;
    public static CrashHandler m_CrashHandler;
    private static SteamCommunityApplication m_singleton;
    private Handler backgroundThreadHandler;
    private final HandlerThread handlerThread;
    public ImageLoader imageLoader;
    private LocalDb localDb;
    private IndefiniteCache m_diskCacheIndefinite;
    private SettingInfoDB m_settingInfoDB;
    public RequestQueue requestQueue;

    /* renamed from: com.valvesoftware.android.steam.community.SteamCommunityApplication.1 */
    class C01541 extends ResponseListener {
        final /* synthetic */ ResponseListener val$originalResponseListener;

        C01541(ResponseListener responseListener) {
            this.val$originalResponseListener = responseListener;
        }

        public void onSuccess(JSONObject response) {
            this.val$originalResponseListener.onSuccess(response);
        }

        public void onError(RequestErrorInfo errorInfo) {
            if (errorInfo != null) {
                Log.e("SteamApp", "Request error HTTP " + errorInfo.getStatusCode() + " " + errorInfo.getMessage());
                if (errorInfo.getStatusCode() == 401 || (errorInfo.getMessage() != null && errorInfo.getMessage().toLowerCase().contains("no authentication challenges"))) {
                    LoggedInUserAccountInfo.logOut();
                    if (SteamCommunityApplication.mMainActivity != null) {
                        SteamCommunityApplication.mMainActivity.finish();
                    }
                    Intent loginIntent = SteamAppIntents.loginIntent(SteamCommunityApplication.this);
                    loginIntent.addFlags(268435456);
                    SteamCommunityApplication.this.startActivity(loginIntent);
                }
            }
            this.val$originalResponseListener.onError(errorInfo);
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.SteamCommunityApplication.2 */
    static class C01552 implements Runnable {
        C01552() {
        }

        public void run() {
            SteamCommunityApplication.isInForeground = false;
        }
    }

    public SteamCommunityApplication() {
        this.m_diskCacheIndefinite = null;
        this.m_settingInfoDB = null;
        this.handlerThread = new HandlerThread("SteamCommunityApplication.BackgroundHandler");
    }

    static {
        m_CrashHandler = new CrashHandler();
        m_singleton = null;
        foregroundStatusHandler = new Handler();
        confirmationRefreshHandler = new Handler();
    }

    public static SteamCommunityApplication GetInstance() {
        return m_singleton;
    }

    public final void onCreate() {
        super.onCreate();
        this.handlerThread.start();
        this.backgroundThreadHandler = new Handler(this.handlerThread.getLooper());
        m_singleton = this;
        this.requestQueue = Volley.newRequestQueue(this);
        this.imageLoader = new ImageLoader(this.requestQueue, new LruBitmapCache(LruBitmapCache.getCacheSize(this)));
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), 0);
            Config.APP_VERSION = pi.versionName;
            Config.APP_VERSION_ID = pi.versionCode;
        } catch (NameNotFoundException e) {
        }
        m_CrashHandler.register();
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().setAcceptCookie(true);
        LoggedInUserAccountInfo.resetAllCookies();
        this.m_diskCacheIndefinite = new IndefiniteCache(getBaseContext().getDir("cache_i", 0));
        this.m_settingInfoDB = new SettingInfoDB();
        this.localDb = new LocalDb(getApplicationContext());
        SteamguardState.initializeSteamguardState(this);
        PreemptivelyLoginBasedOnCachedLoginDocument();
        LoggedInUserAccountInfo.syncAllCookies();
    }

    public IndefiniteCache GetDiskCacheIndefinite() {
        return this.m_diskCacheIndefinite;
    }

    public SettingInfoDB GetSettingInfoDB() {
        return this.m_settingInfoDB;
    }

    public LocalDb getLocalDb() {
        return this.localDb;
    }

    public void sendRequest(RequestBuilder requestBuilder) {
        if (Config.STEAM_UNIVERSE_WEBAPI == SteamUniverse.Dev) {
            DevHttpsTrustManager.allowSslToValveDev();
        }
        requestBuilder.setResponseListener(new C01541(requestBuilder.getResponseListener()));
        requestBuilder.setAccessToken(LoggedInUserAccountInfo.getAccessToken());
        this.requestQueue.add(requestBuilder.toRequest());
    }

    public void PreemptivelyLoginBasedOnCachedLoginDocument() {
        byte[] logindata = GetInstance().GetDiskCacheIndefinite().Read("login.json");
        if (logindata != null) {
            LoginInformation loginInformationFinal = null;
            try {
                Object doc = new JSONTokener(new String(logindata)).nextValue();
                if (JSONObject.class.isInstance(doc)) {
                    JSONObject jsonDoc = (JSONObject) doc;
                    if (jsonDoc.has("access_token") && jsonDoc.has("x_steamid")) {
                        loginInformationFinal = new LoginInformation(jsonDoc);
                    }
                }
            } catch (JSONException e) {
            }
            if (loginInformationFinal != null) {
                LoggedInUserAccountInfo.setLoginInformation(loginInformationFinal);
            }
        }
    }

    public void runOnBackgroundThread(Runnable runnable) {
        this.backgroundThreadHandler.post(runnable);
    }

    public void UpdateCachedLoginInformation() {
        JSONObject doc = new JSONObject();
        try {
            LoggedInUserAccountInfo.getLoginInformation().SerializeToJSONDoc(doc);
            GetDiskCacheIndefinite().Write("login.json", doc.toString().getBytes());
        } catch (JSONException e) {
        }
    }

    public static void switchingToBackground() {
        foregroundStatusHandler.postDelayed(new C01552(), 500);
        mMainActivity = null;
    }

    public static void switchingToForeground(MainActivity activity) {
        foregroundStatusHandler.removeCallbacksAndMessages(null);
        isInForeground = true;
        mMainActivity = activity;
    }
}
