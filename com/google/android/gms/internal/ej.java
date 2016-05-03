package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0123b;
import com.google.android.gms.internal.eq.C0123b.C0121a;
import com.google.android.gms.internal.eq.C0123b.C0122b;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class ej implements Creator<C0123b> {
    static void m268a(C0123b c0123b, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0123b.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0123b.m312u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m83a(parcel, 2, c0123b.cf(), i, true);
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m83a(parcel, 3, c0123b.cg(), i, true);
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m94c(parcel, 4, c0123b.getLayout());
        }
        ad.m75C(parcel, d);
    }

    public C0123b[] m269T(int i) {
        return new C0123b[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m270y(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m269T(x0);
    }

    public C0123b m270y(Parcel parcel) {
        C0122b c0122b = null;
        int i = 0;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        C0121a c0121a = null;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i2 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    C0121a c0121a2 = (C0121a) ac.m40a(parcel, b, C0121a.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    c0121a = c0121a2;
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    C0122b c0122b2 = (C0122b) ac.m40a(parcel, b, C0122b.CREATOR);
                    hashSet.add(Integer.valueOf(3));
                    c0122b = c0122b2;
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0123b(hashSet, i2, c0121a, c0122b, i);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }
}
