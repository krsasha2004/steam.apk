package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class eg implements Creator<ef> {
    static void m262a(ef efVar, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = efVar.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, efVar.m261u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m84a(parcel, 2, efVar.getId(), true);
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m83a(parcel, 4, efVar.bQ(), i, true);
        }
        if (bz.contains(Integer.valueOf(5))) {
            ad.m84a(parcel, 5, efVar.getStartDate(), true);
        }
        if (bz.contains(Integer.valueOf(6))) {
            ad.m83a(parcel, 6, efVar.bR(), i, true);
        }
        if (bz.contains(Integer.valueOf(7))) {
            ad.m84a(parcel, 7, efVar.getType(), true);
        }
        ad.m75C(parcel, d);
    }

    public ef[] m263R(int i) {
        return new ef[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m264w(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m263R(x0);
    }

    public ef m264w(Parcel parcel) {
        String str = null;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        ed edVar = null;
        String str2 = null;
        ed edVar2 = null;
        String str3 = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            ed edVar3;
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    str3 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    edVar3 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(4));
                    edVar2 = edVar3;
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    str2 = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    edVar3 = (ed) ac.m40a(parcel, b, ed.CREATOR);
                    hashSet.add(Integer.valueOf(6));
                    edVar = edVar3;
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(7));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new ef(hashSet, i, str3, edVar2, str2, edVar, str);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }
}
