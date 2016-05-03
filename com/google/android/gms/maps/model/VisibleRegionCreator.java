package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class VisibleRegionCreator implements Creator<VisibleRegion> {
    static void m413a(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, visibleRegion.m412u());
        ad.m83a(parcel, 2, visibleRegion.nearLeft, i, false);
        ad.m83a(parcel, 3, visibleRegion.nearRight, i, false);
        ad.m83a(parcel, 4, visibleRegion.farLeft, i, false);
        ad.m83a(parcel, 5, visibleRegion.farRight, i, false);
        ad.m83a(parcel, 6, visibleRegion.latLngBounds, i, false);
        ad.m75C(parcel, d);
    }

    public VisibleRegion createFromParcel(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int c = ac.m46c(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    latLng4 = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    latLng3 = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    latLng2 = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    latLng = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    latLngBounds = (LatLngBounds) ac.m40a(parcel, b, LatLngBounds.CREATOR);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public VisibleRegion[] newArray(int size) {
        return new VisibleRegion[size];
    }
}
