package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class LatLngCreator implements Creator<LatLng> {
    static void m400a(LatLng latLng, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, latLng.m397u());
        ad.m77a(parcel, 2, latLng.latitude);
        ad.m77a(parcel, 3, latLng.longitude);
        ad.m75C(parcel, d);
    }

    public LatLng createFromParcel(Parcel parcel) {
        double d = 0.0d;
        int c = ac.m46c(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    d2 = ac.m55j(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    d = ac.m55j(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new LatLng(i, d2, d);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public LatLng[] newArray(int size) {
        return new LatLng[size];
    }
}
