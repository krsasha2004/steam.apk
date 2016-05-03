package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.eq.C0123b.C0122b;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashSet;
import java.util.Set;

public class el implements Creator<C0122b> {
    static void m274a(C0122b c0122b, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        Set bz = c0122b.bz();
        if (bz.contains(Integer.valueOf(1))) {
            ad.m94c(parcel, 1, c0122b.m306u());
        }
        if (bz.contains(Integer.valueOf(2))) {
            ad.m94c(parcel, 2, c0122b.getHeight());
        }
        if (bz.contains(Integer.valueOf(3))) {
            ad.m84a(parcel, 3, c0122b.getUrl(), true);
        }
        if (bz.contains(Integer.valueOf(4))) {
            ad.m94c(parcel, 4, c0122b.getWidth());
        }
        ad.m75C(parcel, d);
    }

    public C0122b m275A(Parcel parcel) {
        int i = 0;
        int c = ac.m46c(parcel);
        Set hashSet = new HashSet();
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i3 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    i2 = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    str = ac.m58l(parcel, b);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    i = ac.m51f(parcel, b);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new C0122b(hashSet, i3, i2, str, i);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public C0122b[] m276V(int i) {
        return new C0122b[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m275A(x0);
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m276V(x0);
    }
}
