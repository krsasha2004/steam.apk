package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.Tile;

public class dg {
    public static void m239a(Tile tile, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, tile.m407u());
        ad.m94c(parcel, 2, tile.width);
        ad.m94c(parcel, 3, tile.height);
        ad.m88a(parcel, 4, tile.data, false);
        ad.m75C(parcel, d);
    }
}
