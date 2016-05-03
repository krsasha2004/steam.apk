package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;

public class ah implements Creator<ag> {
    static void m105a(ag agVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, agVar.getType());
        ad.m94c(parcel, 1000, agVar.m99u());
        ad.m94c(parcel, 2, agVar.m100v());
        ad.m84a(parcel, 3, agVar.m101w(), false);
        ad.m84a(parcel, 4, agVar.m102x(), false);
        ad.m84a(parcel, 5, agVar.getDisplayName(), false);
        ad.m84a(parcel, 6, agVar.m103y(), false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m106e(x0);
    }

    public ag m106e(Parcel parcel) {
        int i = 0;
        String str = null;
        int c = ac.m46c(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i2 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    str4 = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    str3 = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    str2 = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    str = ac.m58l(parcel, b);
                    break;
                case 1000:
                    i3 = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new ag(i3, i2, i, str4, str3, str2, str);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public ag[] m107k(int i) {
        return new ag[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m107k(x0);
    }
}
