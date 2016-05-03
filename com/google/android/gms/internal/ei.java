package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0120a;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class ei implements Creator<C0120a> {
    static void m265a(C0120a c0120a, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0120a.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0120a.m294u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m94c(parcel, 2, c0120a.getMax());
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m94c(parcel, 3, c0120a.getMin());
        }
        ad.m75C(parcel, d);
    }

    public C0120a[] m266S(int i) {
        return new C0120a[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m267x(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m266S(x0);
    }

    public C0120a m267x(Parcel parcel) {
        int i = 0;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i3 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    i2 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(3));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0120a(hashSet, i3, i2, i);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }
}
