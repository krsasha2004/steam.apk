package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;

public class aj implements Creator<ai> {
    static void m112a(ai aiVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, aiVar.m111u());
        ad.m83a(parcel, 2, aiVar.m109B(), i, false);
        ad.m75C(parcel, d);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m113f(x0);
    }

    public ai m113f(Parcel parcel) {
        int c = ac.m46c(parcel);
        int i = 0;
        ak akVar = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    akVar = (ak) ac.m40a(parcel, b, ak.CREATOR);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new ai(i, akVar);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public ai[] m114l(int i) {
        return new ai[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m114l(x0);
    }
}
