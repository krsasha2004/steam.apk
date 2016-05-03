package com.valvesoftware.android.steam.community.activity;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.SteamUriHandler;
import com.valvesoftware.android.steam.community.SteamUriHandler.Command;
import com.valvesoftware.android.steam.community.SteamUriHandler.CommandProperty;
import com.valvesoftware.android.steam.community.SteamUriHandler.Result;
import com.valvesoftware.android.steam.community.fragment.WebViewFragment;

public class PaypalUriActivity extends FragmentActivity {
    private static int s_NextActivityID;
    private String m_CategoriesUrl;
    protected int m_residActivityLayout;
    private String m_url;

    public PaypalUriActivity() {
        this.m_url = null;
        this.m_CategoriesUrl = null;
        this.m_residActivityLayout = C0151R.layout.webview_activity;
    }

    static {
        s_NextActivityID = 0;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction())) {
            Result result = SteamUriHandler.HandleSteamURI(intent.getData());
            if (result.command == Command.openurl) {
                this.m_url = result.getProperty(CommandProperty.url);
            }
        }
        setContentView(this.m_residActivityLayout);
        ((WebViewFragment) getSupportFragmentManager().findFragmentById(C0151R.id.webview)).loadUrl(this.m_url);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (result != null) {
            String sCall = result.getStringExtra(CommandProperty.call.toString());
            if (sCall == null || sCall.length() <= 0) {
                String sUrl = result.getStringExtra(CommandProperty.url.toString());
                if (sUrl == null || sUrl.length() <= 0) {
                    String sDlgText = result.getStringExtra("dialogtext");
                    if (sDlgText != null && sDlgText.length() > 0) {
                        new Builder(this).setMessage(sDlgText).show();
                        return;
                    }
                    return;
                }
                ((WebViewFragment) getSupportFragmentManager().findFragmentById(C0151R.id.webview)).loadUrl(sUrl);
                return;
            }
            ((WebViewFragment) getSupportFragmentManager().findFragmentById(C0151R.id.webview)).loadUrl("javascript:(function(){" + sCall + ";})()");
        }
    }
}
