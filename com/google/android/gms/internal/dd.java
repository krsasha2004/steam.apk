package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.MarkerOptions;

public class dd {
    public static void m236a(MarkerOptions markerOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, markerOptions.m401u());
        ad.m83a(parcel, 2, markerOptions.getPosition(), i, false);
        ad.m84a(parcel, 3, markerOptions.getTitle(), false);
        ad.m84a(parcel, 4, markerOptions.getSnippet(), false);
        ad.m81a(parcel, 5, markerOptions.aZ(), false);
        ad.m78a(parcel, 6, markerOptions.getAnchorU());
        ad.m78a(parcel, 7, markerOptions.getAnchorV());
        ad.m87a(parcel, 8, markerOptions.isDraggable());
        ad.m87a(parcel, 9, markerOptions.isVisible());
        ad.m75C(parcel, d);
    }
}
