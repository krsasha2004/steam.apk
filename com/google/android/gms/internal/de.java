package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.PolygonOptions;

public class de {
    public static void m237a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, polygonOptions.m403u());
        ad.m93b(parcel, 2, polygonOptions.getPoints(), false);
        ad.m95c(parcel, 3, polygonOptions.ba(), false);
        ad.m78a(parcel, 4, polygonOptions.getStrokeWidth());
        ad.m94c(parcel, 5, polygonOptions.getStrokeColor());
        ad.m94c(parcel, 6, polygonOptions.getFillColor());
        ad.m78a(parcel, 7, polygonOptions.getZIndex());
        ad.m87a(parcel, 8, polygonOptions.isVisible());
        ad.m87a(parcel, 9, polygonOptions.isGeodesic());
        ad.m75C(parcel, d);
    }
}
