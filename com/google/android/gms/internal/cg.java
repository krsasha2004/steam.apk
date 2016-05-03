package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;

public class cg implements Creator<cf> {
    static void m225a(cf cfVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m84a(parcel, 1, cfVar.getRequestId(), false);
        ad.m94c(parcel, 1000, cfVar.m224u());
        ad.m79a(parcel, 2, cfVar.getExpirationTime());
        ad.m86a(parcel, 3, cfVar.aB());
        ad.m77a(parcel, 4, cfVar.getLatitude());
        ad.m77a(parcel, 5, cfVar.getLongitude());
        ad.m78a(parcel, 6, cfVar.aC());
        ad.m94c(parcel, 7, cfVar.aD());
        ad.m75C(parcel, d);
    }

    public cf[] m226N(int i) {
        return new cf[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m227t(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m226N(x0);
    }

    public cf m227t(Parcel parcel) {
        double d = 0.0d;
        short s = (short) 0;
        int c = ac.m46c(parcel);
        String str = null;
        float f = 0.0f;
        long j = 0;
        double d2 = 0.0d;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    str = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    j = ac.m52g(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    s = ac.m50e(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    d2 = ac.m55j(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    d = ac.m55j(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    f = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    i = ac.m51f(parcel, b);
                    break;
                case 1000:
                    i2 = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new cf(i2, str, i, s, d2, d, f, j);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }
}
