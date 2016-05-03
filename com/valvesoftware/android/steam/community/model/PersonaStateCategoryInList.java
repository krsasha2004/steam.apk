package com.valvesoftware.android.steam.community.model;

import com.valvesoftware.android.steam.community.C0151R;

public enum PersonaStateCategoryInList {
    REQUEST_INCOMING,
    CHATS,
    INGAME,
    ONLINE,
    OFFLINE,
    REQUEST_SENT,
    SEARCH_ALL;

    /* renamed from: com.valvesoftware.android.steam.community.model.PersonaStateCategoryInList.1 */
    static /* synthetic */ class C02721 {
        static final /* synthetic */ int[] f69x80696f15;

        static {
            f69x80696f15 = new int[PersonaStateCategoryInList.values().length];
            try {
                f69x80696f15[PersonaStateCategoryInList.REQUEST_INCOMING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f69x80696f15[PersonaStateCategoryInList.CHATS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f69x80696f15[PersonaStateCategoryInList.INGAME.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f69x80696f15[PersonaStateCategoryInList.ONLINE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f69x80696f15[PersonaStateCategoryInList.REQUEST_SENT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f69x80696f15[PersonaStateCategoryInList.OFFLINE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f69x80696f15[PersonaStateCategoryInList.SEARCH_ALL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public int GetDisplayString() {
        switch (C02721.f69x80696f15[ordinal()]) {
            case C0151R.styleable.View_paddingStart /*1*/:
                return C0151R.string.Friend_Requests;
            case C0151R.styleable.View_paddingEnd /*2*/:
                return C0151R.string.Chats;
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return C0151R.string.In_Game;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return C0151R.string.Online;
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                return C0151R.string.Offline;
            case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                return C0151R.string.Search;
            default:
                return C0151R.string.Unknown;
        }
    }
}
