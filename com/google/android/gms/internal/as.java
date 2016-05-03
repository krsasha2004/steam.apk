package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.aq.C0113a;
import com.google.android.gms.internal.aq.C0114b;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;

public class as implements Creator<C0113a> {
    static void m184a(C0113a c0113a, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, c0113a.versionCode);
        ad.m84a(parcel, 2, c0113a.className, false);
        ad.m93b(parcel, 3, c0113a.bK, false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m185l(x0);
    }

    public C0113a m185l(Parcel parcel) {
        ArrayList arrayList = null;
        int c = ac.m46c(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    str = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    arrayList = ac.m47c(parcel, b, C0114b.CREATOR);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0113a(i, str, arrayList);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m186r(x0);
    }

    public C0113a[] m186r(int i) {
        return new C0113a[i];
    }
}
