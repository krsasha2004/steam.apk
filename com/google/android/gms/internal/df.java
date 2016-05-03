package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.PolylineOptions;

public class df {
    public static void m238a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, polylineOptions.m405u());
        ad.m93b(parcel, 2, polylineOptions.getPoints(), false);
        ad.m78a(parcel, 3, polylineOptions.getWidth());
        ad.m94c(parcel, 4, polylineOptions.getColor());
        ad.m78a(parcel, 5, polylineOptions.getZIndex());
        ad.m87a(parcel, 6, polylineOptions.isVisible());
        ad.m87a(parcel, 7, polylineOptions.isGeodesic());
        ad.m75C(parcel, d);
    }
}
