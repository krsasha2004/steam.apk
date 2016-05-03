package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0125d;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class en implements Creator<C0125d> {
    static void m280a(C0125d c0125d, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0125d.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0125d.m324u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m84a(parcel, 2, c0125d.getUrl(), true);
        }
        ad.m75C(parcel, d);
    }

    public C0125d m281C(Parcel parcel) {
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str = null;
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
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0125d(hashSet, i, str);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public C0125d[] m282X(int i) {
        return new C0125d[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m281C(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m282X(x0);
    }
}
