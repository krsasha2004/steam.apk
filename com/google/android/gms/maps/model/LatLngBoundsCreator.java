package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class LatLngBoundsCreator implements Creator<LatLngBounds> {
    static void m399a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, latLngBounds.m398u());
        ad.m83a(parcel, 2, latLngBounds.southwest, i, false);
        ad.m83a(parcel, 3, latLngBounds.northeast, i, false);
        ad.m75C(parcel, d);
    }

    public LatLngBounds createFromParcel(Parcel parcel) {
        LatLng latLng = null;
        int c = ac.m46c(parcel);
        int i = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < c) {
            int f;
            LatLng latLng3;
            int b = ac.m43b(parcel);
            LatLng latLng4;
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    latLng4 = latLng;
                    latLng = latLng2;
                    f = ac.m51f(parcel, b);
                    latLng3 = latLng4;
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    f = i;
                    latLng4 = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    latLng3 = latLng;
                    latLng = latLng4;
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    latLng3 = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    latLng = latLng2;
                    f = i;
                    break;
                default:
                    ac.m44b(parcel, b);
                    latLng3 = latLng;
                    latLng = latLng2;
                    f = i;
                    break;
            }
            i = f;
            latLng2 = latLng;
            latLng = latLng3;
        }
        if (parcel.dataPosition() == c) {
            return new LatLngBounds(i, latLng2, latLng);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public LatLngBounds[] newArray(int size) {
        return new LatLngBounds[size];
    }
}
