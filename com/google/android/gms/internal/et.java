package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0128h;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class et implements Creator<C0128h> {
    static void m357a(C0128h c0128h, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0128h.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0128h.m342u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m87a(parcel, 2, c0128h.isPrimary());
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m84a(parcel, 3, c0128h.getValue(), true);
        }
        ad.m75C(parcel, d);
    }

    public C0128h m358G(Parcel parcel) {
        boolean z = false;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    z = ac.m48c(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(3));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0128h(hashSet, i, z, str);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public C0128h[] ab(int i) {
        return new C0128h[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m358G(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ab(x0);
    }
}
