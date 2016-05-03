package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0127g;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class ep implements Creator<C0127g> {
    static void m286a(C0127g c0127g, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0127g.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0127g.m336u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m84a(parcel, 2, c0127g.getDepartment(), true);
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m84a(parcel, 3, c0127g.getDescription(), true);
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m84a(parcel, 4, c0127g.getEndDate(), true);
        }
        if (bz.contains(Integer.valueOf(5))) {
            ad.m84a(parcel, 5, c0127g.getLocation(), true);
        }
        if (bz.contains(Integer.valueOf(6))) {
            ad.m84a(parcel, 6, c0127g.getName(), true);
        }
        if (bz.contains(Integer.valueOf(7))) {
            ad.m87a(parcel, 7, c0127g.isPrimary());
        }
        if (bz.contains(Integer.valueOf(8))) {
            ad.m84a(parcel, 8, c0127g.getStartDate(), true);
        }
        if (bz.contains(Integer.valueOf(9))) {
            ad.m84a(parcel, 9, c0127g.getTitle(), true);
        }
        if (bz.contains(Integer.valueOf(10))) {
            ad.m94c(parcel, 10, c0127g.getType());
        }
        ad.m75C(parcel, d);
    }

    public C0127g m287E(Parcel parcel) {
        int i = 0;
        String str = null;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        String str2 = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i2 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    str7 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    str6 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    str5 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    str4 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    str3 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    z = ac.m48c(parcel, b);
                    hashSet.add(Integer.valueOf(7));
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    str2 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(10));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0127g(hashSet, i2, str7, str6, str5, str4, str3, z, str2, str, i);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public C0127g[] m288Z(int i) {
        return new C0127g[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m287E(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m288Z(x0);
    }
}
