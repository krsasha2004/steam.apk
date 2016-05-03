package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.CircleOptions;

public class cz {
    public static void m232a(CircleOptions circleOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, circleOptions.m393u());
        ad.m83a(parcel, 2, circleOptions.getCenter(), i, false);
        ad.m77a(parcel, 3, circleOptions.getRadius());
        ad.m78a(parcel, 4, circleOptions.getStrokeWidth());
        ad.m94c(parcel, 5, circleOptions.getStrokeColor());
        ad.m94c(parcel, 6, circleOptions.getFillColor());
        ad.m78a(parcel, 7, circleOptions.getZIndex());
        ad.m87a(parcel, 8, circleOptions.isVisible());
        ad.m75C(parcel, d);
    }
}
