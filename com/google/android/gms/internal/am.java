package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ak.C0110a;
import com.valvesoftware.android.steam.community.C0151R;

public class am implements Creator<C0110a> {
    static void m129a(C0110a c0110a, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, c0110a.versionCode);
        ad.m84a(parcel, 2, c0110a.bv, false);
        ad.m94c(parcel, 3, c0110a.bw);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m130h(x0);
    }

    public C0110a m130h(Parcel parcel) {
        int i = 0;
        int c = ac.m46c(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i2 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    str = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    i = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0110a(i2, str, i);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public C0110a[] m131n(int i) {
        return new C0110a[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m131n(x0);
    }
}
