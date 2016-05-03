package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.GoogleMapOptions;

public class ci {
    public static void m228a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, googleMapOptions.m389u());
        ad.m76a(parcel, 2, googleMapOptions.aH());
        ad.m76a(parcel, 3, googleMapOptions.aI());
        ad.m94c(parcel, 4, googleMapOptions.getMapType());
        ad.m83a(parcel, 5, googleMapOptions.getCamera(), i, false);
        ad.m76a(parcel, 6, googleMapOptions.aJ());
        ad.m76a(parcel, 7, googleMapOptions.aK());
        ad.m76a(parcel, 8, googleMapOptions.aL());
        ad.m76a(parcel, 9, googleMapOptions.aM());
        ad.m76a(parcel, 10, googleMapOptions.aN());
        ad.m76a(parcel, 11, googleMapOptions.aO());
        ad.m75C(parcel, d);
    }
}
