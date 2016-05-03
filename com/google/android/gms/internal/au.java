package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;

public class au implements Creator<at> {
    static void m202a(at atVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, atVar.m201u());
        ad.m82a(parcel, 2, atVar.m197Y(), false);
        ad.m83a(parcel, 3, atVar.m198Z(), i, false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m203m(x0);
    }

    public at m203m(Parcel parcel) {
        aq aqVar = null;
        int c = ac.m46c(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    parcel2 = ac.m71y(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    aqVar = (aq) ac.m40a(parcel, b, aq.CREATOR);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new at(i, parcel2, aqVar);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m204s(x0);
    }

    public at[] m204s(int i) {
        return new at[i];
    }
}
