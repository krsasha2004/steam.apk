package com.valvesoftware.android.steam.community.model;

import com.valvesoftware.android.steam.community.C0151R;

public enum GroupType {
    PRIVATE,
    PUBLIC,
    LOCKED,
    DISABLED,
    OFFICIAL;

    public static GroupType FromInteger(int status) {
        switch (status) {
            case C0151R.styleable.View_android_focusable /*0*/:
                return PRIVATE;
            case C0151R.styleable.View_paddingStart /*1*/:
                return PUBLIC;
            case C0151R.styleable.View_paddingEnd /*2*/:
                return LOCKED;
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return DISABLED;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return OFFICIAL;
            default:
                return PRIVATE;
        }
    }
}
