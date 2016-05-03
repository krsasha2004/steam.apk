package com.valvesoftware.android.steam.community.views;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.Config;
import com.valvesoftware.android.steam.community.Config.SteamUniverse;
import com.valvesoftware.android.steam.community.NetErrorTranslator;
import com.valvesoftware.android.steam.community.SettingInfo;
import com.valvesoftware.android.steam.community.SettingInfo.AccessRight;
import com.valvesoftware.android.steam.community.SettingInfo.RadioSelectorItem;
import com.valvesoftware.android.steam.community.SteamAppUri;
import com.valvesoftware.android.steam.community.SteamCommunityApplication;
import com.valvesoftware.android.steam.community.SteamUriHandler.Command;
import com.valvesoftware.android.steam.community.SteamUriHandler.Result;
import com.valvesoftware.android.steam.community.SteamguardState;
import com.valvesoftware.android.steam.community.SteamguardState.Completion;
import com.valvesoftware.android.steam.community.activity.ActivityHelper;
import com.valvesoftware.android.steam.community.activity.BaseActivity;
import com.valvesoftware.android.steam.community.activity.MainActivity;
import com.valvesoftware.android.steam.community.activity.PaypalUriActivity;
import com.valvesoftware.android.steam.community.fragment.SettingsFragment.RadioSelectorItemOnClickListener;
import com.valvesoftware.android.steam.community.fragment.WebViewFragment;
import java.lang.ref.WeakReference;

public class SteamWebView extends WebView {
    private String mJavascriptAsyncReturnCode;
    private String mJavascriptAsyncReturnStatus;
    private String mJavascriptAsyncReturnValue;
    private SteamguardJavascriptHandler m_steamguardJavascriptHandler;
    private Object owner;

    /* renamed from: com.valvesoftware.android.steam.community.views.SteamWebView.1 */
    class C02731 implements OnTouchListener {
        C02731() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case C0151R.styleable.View_android_focusable /*0*/:
                case C0151R.styleable.View_paddingStart /*1*/:
                    if (!v.hasFocus()) {
                        if (!(v instanceof SteamWebView)) {
                            v.requestFocus();
                            break;
                        }
                        ((SteamWebView) v).requestFocusWrapper();
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.views.SteamWebView.2 */
    static /* synthetic */ class C02742 {
        static final /* synthetic */ int[] f70xa5d2b4a1;

        static {
            f70xa5d2b4a1 = new int[Command.values().length];
            try {
                f70xa5d2b4a1[Command.opencategoryurl.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f70xa5d2b4a1[Command.openurl.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f70xa5d2b4a1[Command.openexternalurl.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f70xa5d2b4a1[Command.mobileloginsucceeded.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f70xa5d2b4a1[Command.reloadpage.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f70xa5d2b4a1[Command.login.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f70xa5d2b4a1[Command.closethis.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f70xa5d2b4a1[Command.notfound.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f70xa5d2b4a1[Command.settitle.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f70xa5d2b4a1[Command.chat.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f70xa5d2b4a1[Command.twofactorcode.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardset.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardvalidate.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardsendemail.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f70xa5d2b4a1[Command.getjsresult.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardgetgid.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardsuppresstwofactorgid.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardgetrevocation.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardconfrefresh.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f70xa5d2b4a1[Command.steamguardconfcount.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                f70xa5d2b4a1[Command.currentuser.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f70xa5d2b4a1[Command.logout.ordinal()] = 22;
            } catch (NoSuchFieldError e22) {
            }
            try {
                f70xa5d2b4a1[Command.livetokens.ordinal()] = 23;
            } catch (NoSuchFieldError e23) {
            }
            try {
                f70xa5d2b4a1[Command.steamguard.ordinal()] = 24;
            } catch (NoSuchFieldError e24) {
            }
            try {
                f70xa5d2b4a1[Command.lostauth.ordinal()] = 25;
            } catch (NoSuchFieldError e25) {
            }
        }
    }

    private class SteamWebChromeClient extends WebChromeClient {
        private SteamWebChromeClient() {
        }

        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            "onProgressChanged: " + newProgress;
            if (newProgress > 70) {
                SteamWebView.this.requestFocusWrapper();
            }
            if (newProgress > 99) {
                SteamWebView.this.hideProgressIndicator();
            }
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    }

    private class SteamWebViewClient extends WebViewClient {
        private Handler m_backgroundHandler;
        private Result m_loginContext;
        private WeakReference<WebView> m_urlWebView;

        /* renamed from: com.valvesoftware.android.steam.community.views.SteamWebView.SteamWebViewClient.1 */
        class C02751 extends Completion {
            C02751() {
            }

            public void success() {
                SteamWebView.this.m_steamguardJavascriptHandler.setJavascriptResultOkay(null);
            }

            public void failure(int errorCode, String optDescription) {
                SteamWebView.this.m_steamguardJavascriptHandler.setJavascriptResultError(optDescription, errorCode);
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.views.SteamWebView.SteamWebViewClient.2 */
        class C02762 extends Completion {
            C02762() {
            }

            public void success() {
                SteamWebView.this.m_steamguardJavascriptHandler.setJavascriptResultOkay(null);
            }

            public void failure(int errorCode, String optDescription) {
                SteamWebView.this.m_steamguardJavascriptHandler.setJavascriptResultError(optDescription, errorCode);
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.views.SteamWebView.SteamWebViewClient.3 */
        class C02773 extends RadioSelectorItemOnClickListener {
            private SslErrorHandler m_hdlrDelayed;
            final /* synthetic */ SslErrorHandler val$hdlrDelayed;

            C02773(Activity x0, SettingInfo x1, TextView x2, SslErrorHandler sslErrorHandler) {
                this.val$hdlrDelayed = sslErrorHandler;
                super(x0, x1, x2);
                this.m_hdlrDelayed = this.val$hdlrDelayed;
            }

            public void onSettingChanged(RadioSelectorItem sel) {
                if (sel.value != 1) {
                    this.m_hdlrDelayed.proceed();
                } else {
                    this.m_hdlrDelayed.cancel();
                }
            }
        }

        /* renamed from: com.valvesoftware.android.steam.community.views.SteamWebView.SteamWebViewClient.4 */
        class C02784 implements Runnable {
            C02784() {
            }

            public void run() {
                SteamWebView.this.setBackgroundColor(-1);
            }
        }

        private SteamWebViewClient() {
            this.m_backgroundHandler = new Handler();
        }

        private void launchPayPalAuth(String redirectUrl) {
            Activity activity = SteamWebView.this.getActivity();
            if ((activity instanceof MainActivity) && ActivityHelper.fragmentOrActivityIsActive(activity)) {
                if (SteamWebView.this.owner instanceof WebViewFragment) {
                    ((WebViewFragment) SteamWebView.this.owner).setInMiddleOfProcessing(true);
                }
                "Launching PayPal auth intent " + redirectUrl;
                ((MainActivity) activity).readyForPaypalComplete(SteamWebView.this);
                SteamWebView.this.getActivity().startActivityForResult(new Intent().setData(SteamAppUri.createSteamAppWebUri(redirectUrl)).setAction("android.intent.action.VIEW").setClass(SteamWebView.this.getActivity(), PaypalUriActivity.class), 0);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r29, java.lang.String r30) {
            /*
            r28 = this;
            r24 = "steammobile://";
            r0 = r30;
            r1 = r24;
            r24 = r0.startsWith(r1);
            if (r24 == 0) goto L_0x001b;
        L_0x000c:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getContext();
            if (r24 != 0) goto L_0x001b;
        L_0x0018:
            r24 = 1;
        L_0x001a:
            return r24;
        L_0x001b:
            r24 = "otpauth://";
            r0 = r30;
            r1 = r24;
            r24 = r0.startsWith(r1);
            if (r24 == 0) goto L_0x002a;
        L_0x0027:
            r24 = 0;
            goto L_0x001a;
        L_0x002a:
            r24 = "steammobile://";
            r0 = r30;
            r1 = r24;
            r24 = r0.startsWith(r1);
            if (r24 == 0) goto L_0x0599;
        L_0x0036:
            r22 = android.net.Uri.parse(r30);
            r10 = com.valvesoftware.android.steam.community.SteamUriHandler.HandleSteamURI(r22);
            r0 = r10.handled;
            r24 = r0;
            if (r24 == 0) goto L_0x0053;
        L_0x0044:
            r24 = com.valvesoftware.android.steam.community.views.SteamWebView.C02742.f70xa5d2b4a1;
            r0 = r10.command;
            r25 = r0;
            r25 = r25.ordinal();
            r24 = r24[r25];
            switch(r24) {
                case 1: goto L_0x0056;
                case 2: goto L_0x0056;
                case 3: goto L_0x008c;
                case 4: goto L_0x00b5;
                case 5: goto L_0x00da;
                case 6: goto L_0x00e5;
                case 7: goto L_0x0117;
                case 8: goto L_0x0163;
                case 9: goto L_0x0192;
                case 10: goto L_0x01e6;
                case 11: goto L_0x0211;
                case 12: goto L_0x02b6;
                case 13: goto L_0x02ff;
                case 14: goto L_0x0329;
                case 15: goto L_0x0053;
                case 16: goto L_0x034d;
                case 17: goto L_0x038f;
                case 18: goto L_0x0409;
                case 19: goto L_0x042c;
                case 20: goto L_0x0449;
                case 21: goto L_0x0466;
                case 22: goto L_0x0483;
                case 23: goto L_0x04a0;
                case 24: goto L_0x04ca;
                case 25: goto L_0x058e;
                default: goto L_0x0053;
            };
        L_0x0053:
            r24 = 1;
            goto L_0x001a;
        L_0x0056:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.url;
            r0 = r24;
            r17 = r10.getProperty(r0);
            r4 = android.net.Uri.decode(r17);
            r24 = com.valvesoftware.android.steam.community.views.SteamWebView.isStoreLaunchAuthPage(r4);
            if (r24 == 0) goto L_0x006e;
        L_0x0068:
            r0 = r28;
            r0.launchPayPalAuth(r4);
            goto L_0x0053;
        L_0x006e:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getContext();
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r25 = r0;
            r25 = r25.getContext();
            r0 = r25;
            r25 = com.valvesoftware.android.steam.community.SteamAppIntents.viewWebPage(r0, r4);
            r24.startActivity(r25);
            goto L_0x0053;
        L_0x008c:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.url;	 Catch:{ Exception -> 0x00b3 }
            r0 = r24;
            r17 = r10.getProperty(r0);	 Catch:{ Exception -> 0x00b3 }
            r8 = new android.content.Intent;	 Catch:{ Exception -> 0x00b3 }
            r24 = "android.intent.action.VIEW";
            r25 = android.net.Uri.parse(r17);	 Catch:{ Exception -> 0x00b3 }
            r0 = r24;
            r1 = r25;
            r8.<init>(r0, r1);	 Catch:{ Exception -> 0x00b3 }
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;	 Catch:{ Exception -> 0x00b3 }
            r24 = r0;
            r24 = r24.getActivity();	 Catch:{ Exception -> 0x00b3 }
            r0 = r24;
            r0.startActivity(r8);	 Catch:{ Exception -> 0x00b3 }
            goto L_0x0053;
        L_0x00b3:
            r24 = move-exception;
            goto L_0x0053;
        L_0x00b5:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            r0 = r24;
            r0 = r0 instanceof com.valvesoftware.android.steam.community.fragment.LoginFragment;
            r24 = r0;
            if (r24 == 0) goto L_0x0053;
        L_0x00c7:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            r24 = (com.valvesoftware.android.steam.community.fragment.LoginFragment) r24;
            r0 = r24;
            r0.OnMobileLoginSucceeded(r10);
            goto L_0x0053;
        L_0x00da:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24.reload();
            goto L_0x0053;
        L_0x00e5:
            r24 = new java.lang.ref.WeakReference;
            r0 = r24;
            r1 = r29;
            r0.<init>(r1);
            r0 = r24;
            r1 = r28;
            r1.m_urlWebView = r0;
            r0 = r28;
            r0.m_loginContext = r10;
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getActivity();
            r8 = com.valvesoftware.android.steam.community.SteamAppIntents.loginIntent(r24);
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getActivity();
            r0 = r24;
            r0.startActivity(r8);
            goto L_0x0053;
        L_0x0117:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getActivity();
            r11 = r24.getIntent();
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.call;
            r24 = r24.toString();
            r25 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.call;
            r0 = r25;
            r25 = r10.getProperty(r0);
            r0 = r24;
            r1 = r25;
            r11.putExtra(r0, r1);
            r24 = "com.valvesoftware.android.steam.community.intent.action.WEBVIEW_RESULT";
            r0 = r24;
            r11.setAction(r0);
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getActivity();
            r25 = -1;
            r0 = r24;
            r1 = r25;
            r0.setResult(r1, r11);
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getActivity();
            r24.finish();
            goto L_0x0053;
        L_0x0163:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r25 = new java.lang.StringBuilder;
            r25.<init>();
            r26 = "steammobile://";
            r25 = r25.append(r26);
            r26 = com.valvesoftware.android.steam.community.SteamUriHandler.Command.reloadpage;
            r26 = r26.toString();
            r25 = r25.append(r26);
            r25 = r25.toString();
            r26 = com.valvesoftware.android.steam.community.SteamCommunityApplication.GetInstance();
            r27 = 2131165313; // 0x7f070081 float:1.794484E38 double:1.052935567E-314;
            r26 = r26.getString(r27);
            r24.setViewContentsShowFailure(r25, r26);
            goto L_0x0053;
        L_0x0192:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.title;
            r0 = r24;
            r18 = r10.getProperty(r0);
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            if (r24 == 0) goto L_0x01ca;
        L_0x01a6:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            r0 = r24;
            r0 = r0 instanceof android.support.v4.app.Fragment;
            r24 = r0;
            if (r24 == 0) goto L_0x01ca;
        L_0x01b8:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            r24 = (android.support.v4.app.Fragment) r24;
            r24 = com.valvesoftware.android.steam.community.activity.ActivityHelper.fragmentIsActive(r24);
            if (r24 == 0) goto L_0x0053;
        L_0x01ca:
            if (r18 == 0) goto L_0x0053;
        L_0x01cc:
            r19 = java.net.URLDecoder.decode(r18);	 Catch:{ Exception -> 0x01e3 }
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;	 Catch:{ Exception -> 0x01e3 }
            r24 = r0;
            r24 = r24.getActivity();	 Catch:{ Exception -> 0x01e3 }
            r0 = r24;
            r1 = r19;
            r0.setTitle(r1);	 Catch:{ Exception -> 0x01e3 }
            goto L_0x0053;
        L_0x01e3:
            r24 = move-exception;
            goto L_0x0053;
        L_0x01e6:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.steamid;
            r0 = r24;
            r16 = r10.getProperty(r0);
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getContext();
            r0 = r24;
            r1 = r16;
            r3 = com.valvesoftware.android.steam.community.SteamAppIntents.chatIntent(r0, r1);
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getContext();
            r0 = r24;
            r0.startActivity(r3);
            goto L_0x0053;
        L_0x0211:
            r24 = new java.lang.StringBuilder;
            r24.<init>();
            r25 = "Page wants two-factor code for GID ";
            r24 = r24.append(r25);
            r25 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.gid;
            r0 = r25;
            r25 = r10.getProperty(r0);
            r24 = r24.append(r25);
            r24.toString();
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isLoginPage();
            if (r24 != 0) goto L_0x024f;
        L_0x0237:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isManagePhonePage();
            if (r24 != 0) goto L_0x024f;
        L_0x0243:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isManageTwofactorPage();
            if (r24 == 0) goto L_0x0053;
        L_0x024f:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.gid;
            r0 = r24;
            r6 = r10.getProperty(r0);
            r14 = com.valvesoftware.android.steam.community.SteamguardState.steamguardStateForGID(r6);
            if (r14 == 0) goto L_0x02a3;
        L_0x025d:
            r21 = r14.getTwoFactorToken();
            if (r21 == 0) goto L_0x0290;
        L_0x0263:
            r20 = r21.generateSteamGuardCode();
            r24 = new java.lang.StringBuilder;
            r24.<init>();
            r25 = "Returning two-factor code ";
            r24 = r24.append(r25);
            r0 = r24;
            r1 = r20;
            r24 = r0.append(r1);
            r24.toString();
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r0 = r24;
            r1 = r20;
            r0.setJavascriptResultOkay(r1);
            goto L_0x0053;
        L_0x0290:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = 0;
            r26 = -1;
            r24.setJavascriptResultError(r25, r26);
            goto L_0x0053;
        L_0x02a3:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = 0;
            r26 = -1;
            r24.setJavascriptResultError(r25, r26);
            goto L_0x0053;
        L_0x02b6:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isSteamguardPage();
            if (r24 == 0) goto L_0x0053;
        L_0x02c2:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.scheme;
            r0 = r24;
            r13 = r10.getProperty(r0);
            r14 = com.valvesoftware.android.steam.community.SteamguardState.steamguardStateForLoggedInUser();
            r12 = com.valvesoftware.android.steam.community.SteamguardState.stringToScheme(r13);
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.ph;
            r0 = r24;
            r15 = r10.getProperty(r0);
            if (r12 != 0) goto L_0x02ef;
        L_0x02dc:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = "Internal error, bad Steamguard token type";
            r26 = -1;
            r24.setJavascriptResultError(r25, r26);
            goto L_0x0053;
        L_0x02ef:
            r24 = new com.valvesoftware.android.steam.community.views.SteamWebView$SteamWebViewClient$1;
            r0 = r24;
            r1 = r28;
            r0.<init>();
            r0 = r24;
            r14.startSetScheme(r12, r15, r0);
            goto L_0x0053;
        L_0x02ff:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isSteamguardPage();
            if (r24 == 0) goto L_0x0053;
        L_0x030b:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.code;
            r0 = r24;
            r23 = r10.getProperty(r0);
            r14 = com.valvesoftware.android.steam.community.SteamguardState.steamguardStateForLoggedInUser();
            r24 = new com.valvesoftware.android.steam.community.views.SteamWebView$SteamWebViewClient$2;
            r0 = r24;
            r1 = r28;
            r0.<init>();
            r0 = r23;
            r1 = r24;
            r14.finalizeAddTwoFactor(r0, r1);
            goto L_0x0053;
        L_0x0329:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isSteamguardPage();
            if (r24 == 0) goto L_0x0053;
        L_0x0335:
            r14 = com.valvesoftware.android.steam.community.SteamguardState.steamguardStateForLoggedInUser();
            r14.sendActivationCodeEmail();
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = 0;
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x034d:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isSteamguardPage();
            if (r24 != 0) goto L_0x0365;
        L_0x0359:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isLoginPage();
            if (r24 == 0) goto L_0x0053;
        L_0x0365:
            r14 = com.valvesoftware.android.steam.community.SteamguardState.steamguardStateForLoggedInUser();
            if (r14 == 0) goto L_0x037e;
        L_0x036b:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = r14.getTokenGID();
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x037e:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = 0;
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x038f:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isSteamguardPage();
            if (r24 != 0) goto L_0x03a7;
        L_0x039b:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isLoginPage();
            if (r24 == 0) goto L_0x0053;
        L_0x03a7:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.gid;
            r0 = r24;
            r6 = r10.getProperty(r0);
            r24 = "hide";
            r0 = r24;
            r24 = r6.equals(r0);
            if (r24 != 0) goto L_0x03c3;
        L_0x03b9:
            r24 = "show";
            r0 = r24;
            r24 = r6.equals(r0);
            if (r24 == 0) goto L_0x03f8;
        L_0x03c3:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            if (r24 == 0) goto L_0x03f8;
        L_0x03cf:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            r0 = r24;
            r0 = r0 instanceof com.valvesoftware.android.steam.community.fragment.SteamguardFragmentWeb;
            r24 = r0;
            if (r24 == 0) goto L_0x03f8;
        L_0x03e1:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.owner;
            r24 = (com.valvesoftware.android.steam.community.fragment.SteamguardFragmentWeb) r24;
            r25 = "show";
            r0 = r25;
            r25 = r6.equals(r0);
            r24.setTwoFactorVisible(r25);
        L_0x03f8:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = 0;
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x0409:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isSteamguardPage();
            if (r24 == 0) goto L_0x0053;
        L_0x0415:
            r14 = com.valvesoftware.android.steam.community.SteamguardState.steamguardStateForLoggedInUser();
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = r14.getRevocationCode();
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x042c:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isCommunityPage();
            if (r24 == 0) goto L_0x0053;
        L_0x0438:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = 0;
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x0449:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isCommunityPage();
            if (r24 == 0) goto L_0x0053;
        L_0x0455:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = "0";
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x0466:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isCommunityPage();
            if (r24 == 0) goto L_0x0053;
        L_0x0472:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = "0";
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x0483:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isCommunityPage();
            if (r24 == 0) goto L_0x0053;
        L_0x048f:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = 0;
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x04a0:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isCommunityPage();
            if (r24 == 0) goto L_0x0053;
        L_0x04ac:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r25 = r24.m_steamguardJavascriptHandler;
            r24 = com.valvesoftware.android.steam.community.SteamguardState.hasLiveSteamguardStates();
            if (r24 == 0) goto L_0x04c7;
        L_0x04bc:
            r24 = "1";
        L_0x04be:
            r0 = r25;
            r1 = r24;
            r0.setJavascriptResultOkay(r1);
            goto L_0x0053;
        L_0x04c7:
            r24 = "0";
            goto L_0x04be;
        L_0x04ca:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isLoginPage();
            if (r24 != 0) goto L_0x04e2;
        L_0x04d6:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isCommunityPage();
            if (r24 == 0) goto L_0x0053;
        L_0x04e2:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.op;
            r0 = r24;
            r9 = r10.getProperty(r0);
            if (r9 == 0) goto L_0x052d;
        L_0x04ec:
            r24 = "setsecret";
            r0 = r24;
            r24 = r9.compareTo(r0);
            if (r24 != 0) goto L_0x052d;
        L_0x04f6:
            r5 = -1;
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.arg1;
            r0 = r24;
            r2 = r10.getProperty(r0);
            if (r2 == 0) goto L_0x0505;
        L_0x0501:
            r5 = com.valvesoftware.android.steam.community.SteamguardState.installSecret(r2);
        L_0x0505:
            if (r5 != 0) goto L_0x0518;
        L_0x0507:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = "";
            r24.setJavascriptResultOkay(r25);
            goto L_0x0053;
        L_0x0518:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = "";
            r0 = r24;
            r1 = r25;
            r0.setJavascriptResultError(r1, r5);
            goto L_0x0053;
        L_0x052d:
            if (r9 == 0) goto L_0x057b;
        L_0x052f:
            r24 = "conftag";
            r0 = r24;
            r24 = r9.compareTo(r0);
            if (r24 != 0) goto L_0x057b;
        L_0x0539:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.isMobileConfirmationPage();
            if (r24 == 0) goto L_0x057b;
        L_0x0545:
            r24 = com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty.arg1;
            r0 = r24;
            r24 = r10.getProperty(r0);
            r0 = r28;
            r1 = r24;
            r7 = r0.HandleGetConfirmationTag(r1);
            if (r7 == 0) goto L_0x0568;
        L_0x0557:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r0 = r24;
            r0.setJavascriptResultOkay(r7);
            goto L_0x0053;
        L_0x0568:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = "";
            r26 = -1;
            r24.setJavascriptResultError(r25, r26);
            goto L_0x0053;
        L_0x057b:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.m_steamguardJavascriptHandler;
            r25 = "";
            r26 = -1;
            r24.setJavascriptResultError(r25, r26);
            goto L_0x0053;
        L_0x058e:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            com.valvesoftware.android.steam.community.LoggedInUserAccountInfo.reacquireWGTokenFromServer(r24);
            goto L_0x0053;
        L_0x0599:
            r24 = com.valvesoftware.android.steam.community.Config.URL_COMMUNITY_BASE;
            r0 = r30;
            r1 = r24;
            r24 = r0.startsWith(r1);
            if (r24 != 0) goto L_0x05db;
        L_0x05a5:
            r24 = com.valvesoftware.android.steam.community.Config.URL_COMMUNITY_BASE_INSECURE;
            r0 = r30;
            r1 = r24;
            r24 = r0.startsWith(r1);
            if (r24 != 0) goto L_0x05db;
        L_0x05b1:
            r24 = com.valvesoftware.android.steam.community.Config.URL_STORE_BASE;
            r0 = r30;
            r1 = r24;
            r24 = r0.startsWith(r1);
            if (r24 != 0) goto L_0x05db;
        L_0x05bd:
            r24 = com.valvesoftware.android.steam.community.Config.URL_STORE_BASE_INSECURE;
            r0 = r30;
            r1 = r24;
            r24 = r0.startsWith(r1);
            if (r24 != 0) goto L_0x05db;
        L_0x05c9:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24 = r24.getActivity();
            r0 = r24;
            r0 = r0 instanceof com.valvesoftware.android.steam.community.activity.PaypalUriActivity;
            r24 = r0;
            if (r24 == 0) goto L_0x0618;
        L_0x05db:
            r24 = com.valvesoftware.android.steam.community.views.SteamWebView.isStoreLaunchAuthPage(r30);
            if (r24 == 0) goto L_0x0602;
        L_0x05e1:
            r24 = new java.lang.StringBuilder;
            r24.<init>();
            r25 = "Launching PayPal auth ";
            r24 = r24.append(r25);
            r0 = r24;
            r1 = r30;
            r24 = r0.append(r1);
            r24.toString();
            r0 = r28;
            r1 = r30;
            r0.launchPayPalAuth(r1);
        L_0x05fe:
            r24 = 1;
            goto L_0x001a;
        L_0x0602:
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24.requestFocusWrapper();
            r29.loadUrl(r30);
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;
            r24 = r0;
            r24.showProgressIndicator();
            goto L_0x05fe;
        L_0x0618:
            r24 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0656 }
            r24.<init>();	 Catch:{ Exception -> 0x0656 }
            r25 = "Opening URL ";
            r24 = r24.append(r25);	 Catch:{ Exception -> 0x0656 }
            r0 = r24;
            r1 = r30;
            r24 = r0.append(r1);	 Catch:{ Exception -> 0x0656 }
            r25 = " in external browser.";
            r24 = r24.append(r25);	 Catch:{ Exception -> 0x0656 }
            r24.toString();	 Catch:{ Exception -> 0x0656 }
            r8 = new android.content.Intent;	 Catch:{ Exception -> 0x0656 }
            r24 = "android.intent.action.VIEW";
            r25 = android.net.Uri.parse(r30);	 Catch:{ Exception -> 0x0656 }
            r0 = r24;
            r1 = r25;
            r8.<init>(r0, r1);	 Catch:{ Exception -> 0x0656 }
            r0 = r28;
            r0 = com.valvesoftware.android.steam.community.views.SteamWebView.this;	 Catch:{ Exception -> 0x0656 }
            r24 = r0;
            r24 = r24.getActivity();	 Catch:{ Exception -> 0x0656 }
            r0 = r24;
            r0.startActivity(r8);	 Catch:{ Exception -> 0x0656 }
        L_0x0652:
            r24 = 1;
            goto L_0x001a;
        L_0x0656:
            r24 = move-exception;
            goto L_0x0652;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.valvesoftware.android.steam.community.views.SteamWebView.SteamWebViewClient.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }

        private String HandleGetConfirmationTag(String tag) {
            SteamguardState sgState = SteamguardState.steamguardStateForLoggedInUser();
            if (sgState == null || tag == null || tag.length() == 0) {
                return null;
            }
            return sgState.getTaggedConfirmationUrlParams(tag);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            "onReceivedError: " + description + " : " + failingUrl;
            if (SteamWebView.this.getContext() != null) {
                SteamWebView.this.setViewContentsShowFailure(failingUrl, description);
            }
        }

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            "onReceivedSslError: " + error.toString();
            if (Config.STEAM_UNIVERSE_WEBPHP == SteamUniverse.Dev) {
                handler.proceed();
                return;
            }
            SettingInfo settingInfo = SteamCommunityApplication.GetInstance().GetSettingInfoDB().m_settingSslUntrustedPrompt;
            if (settingInfo.m_access == AccessRight.NONE) {
                handler.cancel();
            } else if (settingInfo.getRadioSelectorItemValue(SteamCommunityApplication.GetInstance().getApplicationContext()).value == -1) {
                handler.proceed();
            } else {
                SslErrorHandler hdlrDelayed = handler;
                new C02773(SteamWebView.this.getActivity(), settingInfo, null, hdlrDelayed).onClick(null);
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            SteamWebView.this.setBlackBackground();
            if (url.startsWith(Config.URL_COMMUNITY_BASE + "/mobileconf/")) {
                ((NotificationManager) SteamWebView.this.getContext().getSystemService("notification")).cancel(4);
            }
        }

        public void onPageFinished(WebView view, String url) {
            this.m_backgroundHandler.postDelayed(new C02784(), 500);
            SteamWebView.this.hideProgressIndicator();
        }
    }

    public class SteamguardJavascriptHandler {
        @JavascriptInterface
        public String getResultStatus() {
            return SteamWebView.this.mJavascriptAsyncReturnStatus;
        }

        @JavascriptInterface
        public String getResultValue() {
            String result = SteamWebView.this.mJavascriptAsyncReturnValue;
            setJavascriptResultBusy();
            return result;
        }

        @JavascriptInterface
        public String getResultCode() {
            return SteamWebView.this.mJavascriptAsyncReturnCode;
        }

        @JavascriptInterface
        public void setJavascriptResultBusy() {
            SteamWebView.this.mJavascriptAsyncReturnValue = "";
            SteamWebView.this.mJavascriptAsyncReturnStatus = "busy";
        }

        @JavascriptInterface
        public void setJavascriptResultOkay(String data) {
            if (data == null) {
                data = "";
            }
            SteamWebView.this.mJavascriptAsyncReturnValue = data;
            SteamWebView.this.mJavascriptAsyncReturnStatus = "ok";
        }

        @JavascriptInterface
        public void setJavascriptResultError(String message, int code) {
            if (message == null) {
                message = "";
            }
            SteamWebView.this.mJavascriptAsyncReturnValue = message;
            SteamWebView.this.mJavascriptAsyncReturnStatus = "error";
            SteamWebView.this.mJavascriptAsyncReturnCode = String.format("%d", new Object[]{Integer.valueOf(code)});
        }
    }

    public SteamWebView(Context context) {
        this(context, null);
    }

    public SteamWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SteamWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    private Activity getActivity() {
        return (Activity) getContext();
    }

    private boolean isLoginPage() {
        String url = getUrl();
        if (url == null) {
            return false;
        }
        return url.startsWith(Config.URL_COMMUNITY_BASE + "/mobilelogin");
    }

    private boolean isSteamguardPage() {
        String url = getUrl();
        if (url == null) {
            return false;
        }
        return url.startsWith(Config.URL_COMMUNITY_BASE + "/steamguard");
    }

    private boolean isManageTwofactorPage() {
        String url = getUrl();
        if (url == null) {
            return false;
        }
        return url.startsWith(Config.URL_STORE_BASE + "/twofactor/");
    }

    private boolean isManagePhonePage() {
        String url = getUrl();
        if (url == null) {
            return false;
        }
        url + "startsWith " + Config.URL_STORE_BASE + "/phone/";
        return url.startsWith(Config.URL_STORE_BASE + "/phone/");
    }

    private boolean isCommunityPage() {
        String url = getUrl();
        if (url == null) {
            return false;
        }
        if (url.startsWith(Config.URL_COMMUNITY_BASE) || getUrl().startsWith(Config.URL_COMMUNITY_BASE_INSECURE)) {
            return true;
        }
        return false;
    }

    private static boolean isStoreLaunchAuthPage(String url) {
        return (url.startsWith(Config.URL_STORE_BASE) || url.startsWith(Config.URL_STORE_BASE_INSECURE)) && (url.contains("/paypal/launchauth") || url.contains("/paypal/launchauth"));
    }

    public boolean isMobileConfirmationPage() {
        String url = getUrl();
        if (url == null) {
            return false;
        }
        return url.startsWith(Config.URL_COMMUNITY_BASE + "/mobileconf/");
    }

    public String getURL() {
        return super.getUrl();
    }

    protected void setTitle(String title) {
    }

    public void setBlackBackground() {
        if (VERSION.SDK_INT < 16) {
            setBackgroundColor(0);
        } else {
            setBackgroundColor(Color.argb(1, 0, 0, 0));
        }
    }

    protected void setupView() {
        setBlackBackground();
        setWebViewClient(new SteamWebViewClient());
        setWebChromeClient(new SteamWebChromeClient());
        if (!isInEditMode()) {
            getSettings().setJavaScriptEnabled(true);
            getSettings().setDomStorageEnabled(true);
            getSettings().setSupportZoom(true);
            getSettings().setBuiltInZoomControls(true);
            if (VERSION.SDK_INT >= 11) {
                getSettings().setDisplayZoomControls(false);
            }
            if (VERSION.SDK_INT >= 21) {
                getSettings().setMixedContentMode(2);
            }
            if (Config.STEAM_UNIVERSE_WEBPHP != SteamUniverse.Public && VERSION.SDK_INT >= 19) {
                WebView.setWebContentsDebuggingEnabled(true);
            }
            setScrollBarStyle(0);
            setHorizontalScrollBarEnabled(false);
            setFocusable(true);
            setFocusableInTouchMode(true);
            setOnTouchListener(new C02731());
            this.m_steamguardJavascriptHandler = new SteamguardJavascriptHandler();
            addJavascriptInterface(this.m_steamguardJavascriptHandler, "SGHandler");
            requestFocusWrapper();
        }
    }

    public void reload() {
        showProgressIndicator();
        super.reload();
    }

    private void requestFocusWrapper() {
        if (this.owner == null || ActivityHelper.fragmentOrActivityIsActive(this.owner)) {
            requestFocus();
        }
    }

    public void loadUrl(String sUrl) {
        if (sUrl != null) {
            "loadUrl: " + sUrl;
            if (sUrl.startsWith("javascript:")) {
                super.loadUrl(sUrl);
            } else if (this.owner == null || ActivityHelper.fragmentOrActivityIsActive(this.owner)) {
                requestFocusWrapper();
                super.loadUrl(sUrl);
                if (sUrl != null) {
                    showProgressIndicator();
                }
            }
        }
    }

    public void setViewContentsShowFailure(String hrefRetry, String description) {
        loadData("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body bgcolor=\"#181818\" text=\"#D6D6D6\" link=\"#FFFFFF\" alink=\"#FFFFFF\" vlink=\"#FFFFFF\"><br/><h2 align=\"center\">" + SteamCommunityApplication.GetInstance().getString(C0151R.string.Web_Error_Title) + "</h2>" + "<p align=\"left\">" + NetErrorTranslator.translateError(description) + "</p>" + "<p align=\"left\"><small>" + description + "</small></p>" + "<p align=\"center\">" + "<a href=\"" + hrefRetry + "\">" + SteamCommunityApplication.GetInstance().getString(C0151R.string.Web_Error_Retry_Now) + "</a></p>" + "</body></html>", "text/html", "utf-8");
    }

    public static String extractUrlFromOpenUrlUri(Uri uri) {
        String uriString = uri.toString();
        String keyPattern = "openurl?url=";
        if (uriString.indexOf(keyPattern) != -1) {
            return uriString.substring(uriString.indexOf(keyPattern) + keyPattern.length());
        }
        return null;
    }

    protected void showProgressIndicator() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgressIndicator();
        }
    }

    protected void hideProgressIndicator() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideProgressIndicator();
        }
    }
}
