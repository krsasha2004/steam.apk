package com.google.android.gms.internal;

import com.valvesoftware.android.steam.community.C0151R;

public final class cj {
    public static Boolean m229a(byte b) {
        switch (b) {
            case C0151R.styleable.View_android_focusable /*0*/:
                return Boolean.FALSE;
            case C0151R.styleable.View_paddingStart /*1*/:
                return Boolean.TRUE;
            default:
                return null;
        }
    }

    public static byte m230b(Boolean bool) {
        return bool != null ? bool.booleanValue() ? (byte) 1 : (byte) 0 : (byte) -1;
    }
}
