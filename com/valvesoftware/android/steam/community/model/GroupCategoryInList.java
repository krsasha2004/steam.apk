package com.valvesoftware.android.steam.community.model;

import com.valvesoftware.android.steam.community.C0151R;

public enum GroupCategoryInList {
    REQUEST_INVITE,
    PRIVATE,
    PUBLIC,
    OFFICIAL,
    SEARCH_ALL;

    /* renamed from: com.valvesoftware.android.steam.community.model.GroupCategoryInList.1 */
    static /* synthetic */ class C02701 {
        static final /* synthetic */ int[] f67xcbe0abd1;

        static {
            f67xcbe0abd1 = new int[GroupCategoryInList.values().length];
            try {
                f67xcbe0abd1[GroupCategoryInList.REQUEST_INVITE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f67xcbe0abd1[GroupCategoryInList.OFFICIAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f67xcbe0abd1[GroupCategoryInList.PRIVATE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f67xcbe0abd1[GroupCategoryInList.PUBLIC.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f67xcbe0abd1[GroupCategoryInList.SEARCH_ALL.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public int getDisplayNumber() {
        switch (C02701.f67xcbe0abd1[ordinal()]) {
            case C0151R.styleable.View_paddingStart /*1*/:
                return C0151R.string.Group_Invites;
            case C0151R.styleable.View_paddingEnd /*2*/:
                return C0151R.string.Official_Groups;
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                return C0151R.string.Private_Groups;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return C0151R.string.Public_Groups;
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                return C0151R.string.Search;
            default:
                return C0151R.string.Unknown;
        }
    }
}
