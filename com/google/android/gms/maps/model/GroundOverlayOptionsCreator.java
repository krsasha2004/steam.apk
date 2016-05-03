package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class GroundOverlayOptionsCreator implements Creator<GroundOverlayOptions> {
    static void m396a(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
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

    public GroundOverlayOptions createFromParcel(Parcel parcel) {
        int c = ac.m46c(parcel);
        int i = 0;
        IBinder iBinder = null;
        LatLng latLng = null;
        float f = 0.0f;
        float f2 = 0.0f;
        LatLngBounds latLngBounds = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        boolean z = false;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    iBinder = ac.m59m(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    latLng = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    f = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    f2 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    latLngBounds = (LatLngBounds) ac.m40a(parcel, b, LatLngBounds.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    f3 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    f4 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    z = ac.m48c(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    f5 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_titleMargins /*11*/:
                    f6 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_titleMarginStart /*12*/:
                    f7 = ac.m54i(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new GroundOverlayOptions(i, iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public GroundOverlayOptions[] newArray(int size) {
        return new GroundOverlayOptions[size];
    }
}
