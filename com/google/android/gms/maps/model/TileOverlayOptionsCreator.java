package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class TileOverlayOptionsCreator implements Creator<TileOverlayOptions> {
    static void m411a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, tileOverlayOptions.m410u());
        ad.m81a(parcel, 2, tileOverlayOptions.bb(), false);
        ad.m87a(parcel, 3, tileOverlayOptions.isVisible());
        ad.m78a(parcel, 4, tileOverlayOptions.getZIndex());
        ad.m75C(parcel, d);
    }

    public TileOverlayOptions createFromParcel(Parcel parcel) {
        boolean z = false;
        int c = ac.m46c(parcel);
        IBinder iBinder = null;
        float f = 0.0f;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    iBinder = ac.m59m(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    z = ac.m48c(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    f = ac.m54i(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new TileOverlayOptions(i, iBinder, z, f);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public TileOverlayOptions[] newArray(int size) {
        return new TileOverlayOptions[size];
    }
}
