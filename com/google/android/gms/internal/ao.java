package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.an.C0112a;
import com.valvesoftware.android.steam.community.C0151R;

public class ao implements Creator<C0112a> {
    static void m167a(C0112a c0112a, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, c0112a.m154u());
        ad.m94c(parcel, 2, c0112a.m141E());
        ad.m87a(parcel, 3, c0112a.m143K());
        ad.m94c(parcel, 4, c0112a.m142F());
        ad.m87a(parcel, 5, c0112a.m144L());
        ad.m84a(parcel, 6, c0112a.m145M(), false);
        ad.m94c(parcel, 7, c0112a.m146N());
        ad.m84a(parcel, 8, c0112a.m148P(), false);
        ad.m83a(parcel, 9, c0112a.m150R(), i, false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m168i(x0);
    }

    public C0112a m168i(Parcel parcel) {
        ai aiVar = null;
        int i = 0;
        int c = ac.m46c(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i4 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    i3 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    z2 = ac.m48c(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    i2 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    z = ac.m48c(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    str2 = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    str = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    aiVar = (ai) ac.m40a(parcel, b, ai.CREATOR);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0112a(i4, i3, z2, i2, z, str2, i, str, aiVar);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m169o(x0);
    }

    public C0112a[] m169o(int i) {
        return new C0112a[i];
    }
}
