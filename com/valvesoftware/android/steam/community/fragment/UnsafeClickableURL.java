package com.valvesoftware.android.steam.community.fragment;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentActivity;
import android.text.style.URLSpan;
import android.view.View;
import com.valvesoftware.android.steam.community.C0151R;

public class UnsafeClickableURL extends URLSpan {
    private final FragmentActivity activity;
    private boolean m_bShowUnsafeWarning;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.UnsafeClickableURL.1 */
    class C02681 implements OnClickListener {
        final /* synthetic */ View val$finalView;

        C02681(View view) {
            this.val$finalView = view;
        }

        public void onClick(DialogInterface dialog, int which) {
            UnsafeClickableURL.this.HandleUserProcceedSelected(this.val$finalView);
        }
    }

    public UnsafeClickableURL(URLSpan other, boolean bShowUnsafeWarning, FragmentActivity activity) {
        super(other.getURL());
        this.m_bShowUnsafeWarning = false;
        this.m_bShowUnsafeWarning = bShowUnsafeWarning;
        this.activity = activity;
    }

    public void HandleUserProcceedSelected(View v) {
        try {
            super.onClick(v);
        } catch (Exception e) {
        }
    }

    public void onClick(View v) {
        if (this.m_bShowUnsafeWarning) {
            View finalView = v;
            Builder builder = new Builder(this.activity);
            builder.setTitle(C0151R.string.nonsteam_link_title);
            builder.setMessage(this.activity.getString(C0151R.string.nonsteam_link_text) + "\n\n" + getURL());
            builder.setPositiveButton(C0151R.string.nonsteam_link_ok, new C02681(finalView));
            builder.setNegativeButton(C0151R.string.Cancel, null);
            builder.create().show();
            return;
        }
        HandleUserProcceedSelected(v);
    }
}
