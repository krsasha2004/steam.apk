package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.LatLngBounds;

public class db {
    public static void m234a(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, latLngBounds.m398u());
        ad.m83a(parcel, 2, latLngBounds.southwest, i, false);
        ad.m83a(parcel, 3, latLngBounds.northeast, i, false);
        ad.m75C(parcel, d);
    }
}
