package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0120a;
import com.google.android.gms.internal.eq.C0123b;
import com.google.android.gms.internal.eq.C0124c;
import com.google.android.gms.internal.eq.C0125d;
import com.google.android.gms.internal.eq.C0126e;
import com.google.android.gms.internal.eq.C0127g;
import com.google.android.gms.internal.eq.C0128h;
import com.google.android.gms.internal.eq.C0129i;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class er implements Creator<eq> {
    static void m355a(eq eqVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = eqVar.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, eqVar.m354u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m84a(parcel, 2, eqVar.getAboutMe(), true);
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m83a(parcel, 3, eqVar.bU(), i, true);
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m84a(parcel, 4, eqVar.getBirthday(), true);
        }
        if (bz.contains(Integer.valueOf(5))) {
            ad.m84a(parcel, 5, eqVar.getBraggingRights(), true);
        }
        if (bz.contains(Integer.valueOf(6))) {
            ad.m94c(parcel, 6, eqVar.getCircledByCount());
        }
        if (bz.contains(Integer.valueOf(7))) {
            ad.m83a(parcel, 7, eqVar.bV(), i, true);
        }
        if (bz.contains(Integer.valueOf(8))) {
            ad.m84a(parcel, 8, eqVar.getCurrentLocation(), true);
        }
        if (bz.contains(Integer.valueOf(9))) {
            ad.m84a(parcel, 9, eqVar.getDisplayName(), true);
        }
        if (bz.contains(Integer.valueOf(10))) {
            ad.m93b(parcel, 10, eqVar.bW(), true);
        }
        if (bz.contains(Integer.valueOf(11))) {
            ad.m84a(parcel, 11, eqVar.bX(), true);
        }
        if (bz.contains(Integer.valueOf(12))) {
            ad.m94c(parcel, 12, eqVar.getGender());
        }
        if (bz.contains(Integer.valueOf(13))) {
            ad.m87a(parcel, 13, eqVar.isHasApp());
        }
        if (bz.contains(Integer.valueOf(14))) {
            ad.m84a(parcel, 14, eqVar.getId(), true);
        }
        if (bz.contains(Integer.valueOf(15))) {
            ad.m83a(parcel, 15, eqVar.bY(), i, true);
        }
        if (bz.contains(Integer.valueOf(16))) {
            ad.m87a(parcel, 16, eqVar.isPlusUser());
        }
        if (bz.contains(Integer.valueOf(19))) {
            ad.m83a(parcel, 19, eqVar.bZ(), i, true);
        }
        if (bz.contains(Integer.valueOf(18))) {
            ad.m84a(parcel, 18, eqVar.getLanguage(), true);
        }
        if (bz.contains(Integer.valueOf(21))) {
            ad.m94c(parcel, 21, eqVar.getObjectType());
        }
        if (bz.contains(Integer.valueOf(20))) {
            ad.m84a(parcel, 20, eqVar.getNickname(), true);
        }
        if (bz.contains(Integer.valueOf(23))) {
            ad.m93b(parcel, 23, eqVar.cb(), true);
        }
        if (bz.contains(Integer.valueOf(22))) {
            ad.m93b(parcel, 22, eqVar.ca(), true);
        }
        if (bz.contains(Integer.valueOf(25))) {
            ad.m94c(parcel, 25, eqVar.getRelationshipStatus());
        }
        if (bz.contains(Integer.valueOf(24))) {
            ad.m94c(parcel, 24, eqVar.getPlusOneCount());
        }
        if (bz.contains(Integer.valueOf(27))) {
            ad.m84a(parcel, 27, eqVar.getUrl(), true);
        }
        if (bz.contains(Integer.valueOf(26))) {
            ad.m84a(parcel, 26, eqVar.getTagline(), true);
        }
        if (bz.contains(Integer.valueOf(29))) {
            ad.m87a(parcel, 29, eqVar.isVerified());
        }
        if (bz.contains(Integer.valueOf(28))) {
            ad.m93b(parcel, 28, eqVar.cc(), true);
        }
        ad.m75C(parcel, d);
    }

    public eq m356F(Parcel parcel) {
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str = null;
        C0120a c0120a = null;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        C0123b c0123b = null;
        String str4 = null;
        String str5 = null;
        List list = null;
        String str6 = null;
        int i3 = 0;
        boolean z = false;
        String str7 = null;
        C0125d c0125d = null;
        boolean z2 = false;
        String str8 = null;
        C0126e c0126e = null;
        String str9 = null;
        int i4 = 0;
        List list2 = null;
        List list3 = null;
        int i5 = 0;
        int i6 = 0;
        String str10 = null;
        String str11 = null;
        List list4 = null;
        boolean z3 = false;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    C0120a c0120a2 = (C0120a) ac.m40a(parcel, b, C0120a.CREATOR);
                    hashSet.add(Integer.valueOf(3));
                    c0120a = c0120a2;
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    str2 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    str3 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    i2 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    C0123b c0123b2 = (C0123b) ac.m40a(parcel, b, C0123b.CREATOR);
                    hashSet.add(Integer.valueOf(7));
                    c0123b = c0123b2;
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    str4 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    str5 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    list = ac.m47c(parcel, b, C0124c.CREATOR);
                    hashSet.add(Integer.valueOf(10));
                    break;
                case C0151R.styleable.Toolbar_titleMargins /*11*/:
                    str6 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(11));
                    break;
                case C0151R.styleable.Toolbar_titleMarginStart /*12*/:
                    i3 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(12));
                    break;
                case C0151R.styleable.Toolbar_titleMarginEnd /*13*/:
                    z = ac.m48c(parcel, b);
                    hashSet.add(Integer.valueOf(13));
                    break;
                case C0151R.styleable.Toolbar_titleMarginTop /*14*/:
                    str7 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(14));
                    break;
                case C0151R.styleable.Toolbar_titleMarginBottom /*15*/:
                    C0125d c0125d2 = (C0125d) ac.m40a(parcel, b, C0125d.CREATOR);
                    hashSet.add(Integer.valueOf(15));
                    c0125d = c0125d2;
                    break;
                case C0151R.styleable.Toolbar_maxButtonHeight /*16*/:
                    z2 = ac.m48c(parcel, b);
                    hashSet.add(Integer.valueOf(16));
                    break;
                case C0151R.styleable.Toolbar_collapseIcon /*18*/:
                    str8 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(18));
                    break;
                case C0151R.styleable.Toolbar_collapseContentDescription /*19*/:
                    C0126e c0126e2 = (C0126e) ac.m40a(parcel, b, C0126e.CREATOR);
                    hashSet.add(Integer.valueOf(19));
                    c0126e = c0126e2;
                    break;
                case C0151R.styleable.Toolbar_navigationIcon /*20*/:
                    str9 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(20));
                    break;
                case C0151R.styleable.Toolbar_navigationContentDescription /*21*/:
                    i4 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(21));
                    break;
                case C0151R.styleable.Theme_actionMenuTextColor /*22*/:
                    list2 = ac.m47c(parcel, b, C0127g.CREATOR);
                    hashSet.add(Integer.valueOf(22));
                    break;
                case C0151R.styleable.Theme_actionModeStyle /*23*/:
                    list3 = ac.m47c(parcel, b, C0128h.CREATOR);
                    hashSet.add(Integer.valueOf(23));
                    break;
                case C0151R.styleable.Theme_actionModeCloseButtonStyle /*24*/:
                    i5 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(24));
                    break;
                case C0151R.styleable.Theme_actionModeBackground /*25*/:
                    i6 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(25));
                    break;
                case C0151R.styleable.Theme_actionModeSplitBackground /*26*/:
                    str10 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(26));
                    break;
                case C0151R.styleable.Theme_actionModeCloseDrawable /*27*/:
                    str11 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(27));
                    break;
                case C0151R.styleable.Theme_actionModeCutDrawable /*28*/:
                    list4 = ac.m47c(parcel, b, C0129i.CREATOR);
                    hashSet.add(Integer.valueOf(28));
                    break;
                case C0151R.styleable.Theme_actionModeCopyDrawable /*29*/:
                    z3 = ac.m48c(parcel, b);
                    hashSet.add(Integer.valueOf(29));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new eq(hashSet, i, str, c0120a, str2, str3, i2, c0123b, str4, str5, list, str6, i3, z, str7, c0125d, z2, str8, c0126e, str9, i4, list2, list3, i5, i6, str10, str11, list4, z3);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public eq[] aa(int i) {
        return new eq[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m356F(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return aa(x0);
    }
}
