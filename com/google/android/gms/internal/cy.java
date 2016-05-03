package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.CameraPosition;

public class cy {
    public static void m231a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, cameraPosition.m391u());
        ad.m83a(parcel, 2, cameraPosition.target, i, false);
        ad.m78a(parcel, 3, cameraPosition.zoom);
        ad.m78a(parcel, 4, cameraPosition.tilt);
        ad.m78a(parcel, 5, cameraPosition.bearing);
        ad.m75C(parcel, d);
    }
}
