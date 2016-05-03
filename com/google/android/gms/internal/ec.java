package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;

public class ec implements Creator<eb> {
    static void m244a(eb ebVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m84a(parcel, 1, ebVar.getDescription(), false);
        ad.m94c(parcel, 1000, ebVar.m243u());
        ad.m93b(parcel, 2, ebVar.bw(), false);
        ad.m93b(parcel, 3, ebVar.bx(), false);
        ad.m87a(parcel, 4, ebVar.by());
        ad.m75C(parcel, d);
    }

    public eb[] m245P(int i) {
        return new eb[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m246u(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m245P(x0);
    }

    public eb m246u(Parcel parcel) {
        boolean z = false;
        ArrayList arrayList = null;
        int c = ac.m46c(parcel);
        ArrayList arrayList2 = null;
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    str = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    arrayList2 = ac.m47c(parcel, b, ag.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    arrayList = ac.m47c(parcel, b, ag.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    z = ac.m48c(parcel, b);
                    break;
                case 1000:
                    i = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new eb(i, str, arrayList2, arrayList, z);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }
}
