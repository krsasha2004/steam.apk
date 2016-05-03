package com.valvesoftware.android.steam.community;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.util.concurrent.atomic.AtomicInteger;

public class GcmRegistrar {
    private static final Handler gcmHandler;
    private final Context context;
    private AtomicInteger numberOfFailedRegistrationAttempts;

    /* renamed from: com.valvesoftware.android.steam.community.GcmRegistrar.1 */
    class C01431 extends AsyncTask {

        /* renamed from: com.valvesoftware.android.steam.community.GcmRegistrar.1.1 */
        class C01421 implements Runnable {
            C01421() {
            }

            public void run() {
                GcmRegistrar.this.registerWithGcm();
            }
        }

        C01431() {
        }

        protected String doInBackground(Object... params) {
            String msg = "";
            try {
                String regId = GoogleCloudMessaging.getInstance(GcmRegistrar.this.context).register("963091912489");
                msg = "Device registered, registration ID=" + regId;
                GcmRegistrar.this.storeRegistrationIdAndSendToServer(regId);
                GcmRegistrar.this.numberOfFailedRegistrationAttempts.set(0);
                return msg;
            } catch (Exception ex) {
                int n = GcmRegistrar.this.numberOfFailedRegistrationAttempts.incrementAndGet();
                if (n < 6) {
                    GcmRegistrar.gcmHandler.postDelayed(new C01421(), (long) ((1 << n) * 1000));
                    return msg;
                }
                msg = "Error :" + ex.getMessage();
                Log.e("GCMRegError", ex.toString());
                return msg;
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.GcmRegistrar.2 */
    class C01452 extends AsyncTask {
        final /* synthetic */ boolean val$withRetry;

        /* renamed from: com.valvesoftware.android.steam.community.GcmRegistrar.2.1 */
        class C01441 implements Runnable {
            C01441() {
            }

            public void run() {
                GcmRegistrar.this.unregister(false);
            }
        }

        C01452(boolean z) {
            this.val$withRetry = z;
        }

        protected Object doInBackground(Object[] params) {
            String msg = "";
            try {
                GoogleCloudMessaging.getInstance(GcmRegistrar.this.context).unregister();
                GcmRegistrar.this.clearStoredRegistrationId();
                return msg;
            } catch (Exception e) {
                if (!this.val$withRetry) {
                    return "Could not unregister with GCM";
                }
                GcmRegistrar.gcmHandler.postDelayed(new C01441(), 2000);
                return msg;
            }
        }
    }

    static {
        gcmHandler = new Handler();
    }

    public GcmRegistrar() {
        this.numberOfFailedRegistrationAttempts = new AtomicInteger(0);
        this.context = SteamCommunityApplication.GetInstance().getApplicationContext();
    }

    public void registerWithGcm() {
        if (LoggedInUserAccountInfo.getLoginSteamID() != null && checkPlayServices()) {
            new C01431().execute(new Object[]{null, null, null});
        }
    }

    public String getStoredRegistrationId() {
        return getGcmPreferences(this.context).getString("registration_id", "");
    }

    public void storeRegistrationIdAndSendToServer(String regId) {
        if (regId != null && regId.length() != 0) {
            SharedPreferences prefs = getGcmPreferences(this.context);
            int appVersion = getAppVersion(this.context);
            Editor editor = prefs.edit();
            editor.putString("registration_id", regId);
            editor.putInt("appVersion", appVersion);
            editor.putLong("lastRegTime", System.currentTimeMillis());
            editor.commit();
            UmqCommunicator.getInstance().setServerPushStateBasedOnUserPreference();
        }
    }

    public void clearStoredRegistrationId() {
        Editor editor = getGcmPreferences(this.context).edit();
        editor.remove("registration_id");
        editor.commit();
    }

    private SharedPreferences getGcmPreferences(Context context) {
        return context.getSharedPreferences(GcmRegistrar.class.getSimpleName(), 0);
    }

    private static int getAppVersion(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    private boolean checkPlayServices() {
        try {
            if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.context) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void unregister() {
        unregister(true);
    }

    private void unregister(boolean withRetry) {
        if (checkPlayServices()) {
            new C01452(withRetry).execute(new Object[]{null, null, null});
        }
    }
}
