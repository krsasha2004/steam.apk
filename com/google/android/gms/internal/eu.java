package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0129i;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class eu implements Creator<C0129i> {
    static void m359a(C0129i c0129i, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0129i.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0129i.m348u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m87a(parcel, 2, c0129i.isPrimary());
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m94c(parcel, 3, c0129i.getType());
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m84a(parcel, 4, c0129i.getValue(), true);
        }
        ad.m75C(parcel, d);
    }

    public C0129i m360H(Parcel parcel) {
        int i = 0;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        String str = null;
        boolean z = false;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i2 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    z = ac.m48c(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0129i(hashSet, i2, z, i, str);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public C0129i[] ac(int i) {
        return new C0129i[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m360H(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return ac(x0);
    }
}
