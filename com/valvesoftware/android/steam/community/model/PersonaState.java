package com.valvesoftware.android.steam.community.model;

import com.valvesoftware.android.steam.community.C0151R;

public enum PersonaState {
    OFFLINE,
    ONLINE,
    BUSY,
    AWAY,
    SNOOZE;

    /* renamed from: com.valvesoftware.android.steam.community.model.PersonaState.1 */
    static /* synthetic */ class C02711 {
        static final /* synthetic */ int[] f68x2c6613f4;

        static {
            f68x2c6613f4 = new int[PersonaState.values().length];
            try {
                f68x2c6613f4[PersonaState.OFFLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f68x2c6613f4[PersonaState.ONLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f68x2c6613f4[PersonaState.BUSY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f68x2c6613f4[PersonaState.AWAY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f68x2c6613f4[PersonaState.SNOOZE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static PersonaState FromInteger(int status) {
        switch (status) {
            case C0151R.styleable.View_android_focusable /*0*/:
                return OFFLINE;
            case C0151R.styleable.View_paddingStart /*1*/:
                return ONLINE;
            case C0151R.styleable.View_paddingEnd /*2*/:
                return BUSY;
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return AWAY;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return SNOOZE;
            default:
                return OFFLINE;
        }
    }

    public int GetDisplayString() {
        switch (C02711.f68x2c6613f4[ordinal()]) {
            case C0151R.styleable.View_paddingStart /*1*/:
                return C0151R.string.Offline;
            case C0151R.styleable.View_paddingEnd /*2*/:
                return C0151R.string.Online;
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return C0151R.string.Busy;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return C0151R.string.Away;
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                return C0151R.string.Snooze;
            default:
                return C0151R.string.Unknown;
        }
    }
}
