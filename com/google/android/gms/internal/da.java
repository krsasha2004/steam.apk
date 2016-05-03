package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.maps.model.GroundOverlayOptions;

public class da {
    public static void m233a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, groundOverlayOptions.m395u());
        ad.m81a(parcel, 2, groundOverlayOptions.aY(), false);
        ad.m83a(parcel, 3, groundOverlayOptions.getLocation(), i, false);
        ad.m78a(parcel, 4, groundOverlayOptions.getWidth());
        ad.m78a(parcel, 5, groundOverlayOptions.getHeight());
        ad.m83a(parcel, 6, groundOverlayOptions.getBounds(), i, false);
        ad.m78a(parcel, 7, groundOverlayOptions.getBearing());
        ad.m78a(parcel, 8, groundOverlayOptions.getZIndex());
        ad.m87a(parcel, 9, groundOverlayOptions.isVisible());
        ad.m78a(parcel, 10, groundOverlayOptions.getTransparency());
        ad.m78a(parcel, 11, groundOverlayOptions.getAnchorU());
        ad.m78a(parcel, 12, groundOverlayOptions.getAnchorV());
        ad.m75C(parcel, d);
    }
}
