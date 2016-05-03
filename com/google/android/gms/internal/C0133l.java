package com.google.android.gms.internal;

import android.database.CursorWindow;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac.C0109a;
import com.valvesoftware.android.steam.community.C0151R;

/* renamed from: com.google.android.gms.internal.l */
public class C0133l implements Creator<C0132k> {
    static void m370a(C0132k c0132k, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m90a(parcel, 1, c0132k.f41U, false);
        ad.m94c(parcel, 1000, c0132k.f40T);
        ad.m89a(parcel, 2, c0132k.f43W, i, false);
        ad.m94c(parcel, 3, c0132k.f47p);
        ad.m80a(parcel, 4, c0132k.f44X, false);
        ad.m75C(parcel, d);
    }

    public C0132k m371a(Parcel parcel) {
        C0132k c0132k = new C0132k();
        int c = ac.m46c(parcel);
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    c0132k.f41U = ac.m69w(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    c0132k.f43W = (CursorWindow[]) ac.m45b(parcel, b, CursorWindow.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    c0132k.f47p = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    c0132k.f44X = ac.m60n(parcel, b);
                    break;
                case 1000:
                    c0132k.f40T = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() != c) {
            throw new C0109a("Overread allowed size end=" + c, parcel);
        }
        c0132k.m368g();
        return c0132k;
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return m371a(x0);
    }

    public C0132k[] m372f(int i) {
        return new C0132k[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return m372f(x0);
    }
}
