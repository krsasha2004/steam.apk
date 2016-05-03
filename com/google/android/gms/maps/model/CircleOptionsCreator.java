package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class CircleOptionsCreator implements Creator<CircleOptions> {
    static void m394a(CircleOptions circleOptions, Parcel parcel, int i) {
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

    public CircleOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int c = ac.m46c(parcel);
        LatLng latLng = null;
        double d = 0.0d;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i3 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    latLng = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    d = ac.m55j(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    f2 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    i2 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    f = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    z = ac.m48c(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new CircleOptions(i3, latLng, d, f2, i2, i, f, z);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public CircleOptions[] newArray(int size) {
        return new CircleOptions[size];
    }
}
