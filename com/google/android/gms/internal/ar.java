package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.aq.C0113a;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;

public class ar implements Creator<aq> {
    static void m181a(aq aqVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, aqVar.m180u());
        ad.m93b(parcel, 2, aqVar.m177V(), false);
        ad.m84a(parcel, 3, aqVar.m178W(), false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m182k(x0);
    }

    public aq m182k(Parcel parcel) {
        String str = null;
        int c = ac.m46c(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    arrayList = ac.m47c(parcel, b, C0113a.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    str = ac.m58l(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new aq(i, arrayList, str);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m183q(x0);
    }

    public aq[] m183q(int i) {
        return new aq[i];
    }
}
