package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.VisibleRegion;

public class di {
    public static void m241a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, visibleRegion.m412u());
        ad.m83a(parcel, 2, visibleRegion.nearLeft, i, false);
        ad.m83a(parcel, 3, visibleRegion.nearRight, i, false);
        ad.m83a(parcel, 4, visibleRegion.farLeft, i, false);
        ad.m83a(parcel, 5, visibleRegion.farRight, i, false);
        ad.m83a(parcel, 6, visibleRegion.latLngBounds, i, false);
        ad.m75C(parcel, d);
    }
}
