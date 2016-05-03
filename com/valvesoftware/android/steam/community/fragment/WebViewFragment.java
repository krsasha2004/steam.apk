package com.valvesoftware.android.steam.community.fragment;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.activity.MainActivity;
import com.valvesoftware.android.steam.community.views.SteamWebView;

public class WebViewFragment extends Fragment implements IBackButtonSupport {
    private boolean inMiddleOfProcessing;
    private boolean m_bWebViewPaused;
    protected SteamWebView m_webView;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.WebViewFragment.1 */
    class C02691 implements OnClickListener {
        C02691() {
        }

        public void onClick(View view) {
            WebViewFragment.this.m_webView.reload();
        }
    }

    public WebViewFragment() {
        this.inMiddleOfProcessing = false;
        this.m_bWebViewPaused = false;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout view = (LinearLayout) inflater.inflate(C0151R.layout.webview_fragment, container, false);
        this.m_webView = (SteamWebView) view.findViewById(C0151R.id.webView);
        this.m_webView.setOwner(this);
        return view;
    }

    public void onPause() {
        super.onPause();
        if (VERSION.SDK_INT >= 11) {
            this.m_webView.onPause();
            this.m_bWebViewPaused = true;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.m_bWebViewPaused) {
            if (VERSION.SDK_INT >= 11) {
                this.m_webView.onResume();
            }
            this.m_bWebViewPaused = false;
        }
        String url = null;
        Bundle args = getArguments();
        if (args != null) {
            url = args.getString("url");
        }
        if (!(url == null || this.inMiddleOfProcessing || this.m_webView.getURL() != null)) {
            this.m_webView.loadUrl(url);
        }
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setRefreshButtonClickListener(new C02691());
        }
    }

    public boolean canGoBack() {
        boolean bRet = this.m_webView.canGoBack();
        "canGoBack is returning " + bRet + " for URL " + this.m_webView.getUrl();
        return bRet;
    }

    public void goBack() {
        this.m_webView.goBack();
    }

    public void loadUrl(String url) {
        if (url != null) {
            this.m_webView.loadUrl(url);
        }
    }

    public void setInMiddleOfProcessing(boolean inMiddleOfProcessing) {
        this.inMiddleOfProcessing = inMiddleOfProcessing;
    }

    public boolean refreshConfirmationsPageIfActive() {
        if (!this.m_webView.isMobileConfirmationPage()) {
            return false;
        }
        this.m_webView.reload();
        return true;
    }
}
