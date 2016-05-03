package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ak.C0110a;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;

public class al implements Creator<ak> {
    static void m126a(ak akVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, akVar.m125u());
        ad.m93b(parcel, 2, akVar.m119D(), false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m127g(x0);
    }

    public ak m127g(Parcel parcel) {
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
                    arrayList = ac.m47c(parcel, b, C0110a.CREATOR);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new ak(i, arrayList);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public ak[] m128m(int i) {
        return new ak[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m128m(x0);
    }
}
