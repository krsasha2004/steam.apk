package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.TileOverlayOptions;

public class dh {
    public static void m240a(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, tileOverlayOptions.m410u());
        ad.m81a(parcel, 2, tileOverlayOptions.bb(), false);
        ad.m87a(parcel, 3, tileOverlayOptions.isVisible());
        ad.m78a(parcel, 4, tileOverlayOptions.getZIndex());
        ad.m75C(parcel, d);
    }
}
