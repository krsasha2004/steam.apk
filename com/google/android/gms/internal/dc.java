package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.LatLng;

public class dc {
    public static void m235a(LatLng latLng, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, latLng.m397u());
        ad.m77a(parcel, 2, latLng.latitude);
        ad.m77a(parcel, 3, latLng.longitude);
        ad.m75C(parcel, d);
    }
}
