package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0126e;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class eo implements Creator<C0126e> {
    static void m283a(C0126e c0126e, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0126e.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0126e.m330u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m84a(parcel, 2, c0126e.getFamilyName(), true);
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m84a(parcel, 3, c0126e.getFormatted(), true);
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m84a(parcel, 4, c0126e.getGivenName(), true);
        }
        if (bz.contains(Integer.valueOf(5))) {
            ad.m84a(parcel, 5, c0126e.getHonorificPrefix(), true);
        }
        if (bz.contains(Integer.valueOf(6))) {
            ad.m84a(parcel, 6, c0126e.getHonorificSuffix(), true);
        }
        if (bz.contains(Integer.valueOf(7))) {
            ad.m84a(parcel, 7, c0126e.getMiddleName(), true);
        }
        ad.m75C(parcel, d);
    }

    public C0126e m284D(Parcel parcel) {
        String str = null;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    str6 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    str5 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    str4 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    str3 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    str2 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(7));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0126e(hashSet, i, str6, str5, str4, str3, str2, str);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public C0126e[] m285Y(int i) {
        return new C0126e[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m284D(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m285Y(x0);
    }
}
