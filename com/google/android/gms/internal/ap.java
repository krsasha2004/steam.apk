package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.an.C0112a;
import com.google.android.gms.internal.aq.C0114b;
import com.valvesoftware.android.steam.community.C0151R;

public class ap implements Creator<C0114b> {
    static void m170a(C0114b c0114b, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, c0114b.versionCode);
        ad.m84a(parcel, 2, c0114b.bL, false);
        ad.m83a(parcel, 3, c0114b.bM, i, false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m171j(x0);
    }

    public C0114b m171j(Parcel parcel) {
        C0112a c0112a = null;
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
                    c0112a = (C0112a) ac.m40a(parcel, b, C0112a.CREATOR);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0114b(i, str, c0112a);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m172p(x0);
    }

    public C0114b[] m172p(int i) {
        return new C0114b[i];
    }
}
