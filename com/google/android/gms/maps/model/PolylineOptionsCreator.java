package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.List;

public class PolylineOptionsCreator implements Creator<PolylineOptions> {
    static void m406a(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, polylineOptions.m405u());
        ad.m93b(parcel, 2, polylineOptions.getPoints(), false);
        ad.m78a(parcel, 3, polylineOptions.getWidth());
        ad.m94c(parcel, 4, polylineOptions.getColor());
        ad.m78a(parcel, 5, polylineOptions.getZIndex());
        ad.m87a(parcel, 6, polylineOptions.isVisible());
        ad.m87a(parcel, 7, polylineOptions.isGeodesic());
        ad.m75C(parcel, d);
    }

    public PolylineOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int c = ac.m46c(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i2 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    list = ac.m47c(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    f2 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    f = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    z2 = ac.m48c(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    z = ac.m48c(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new PolylineOptions(i2, list, f2, i, f, z2, z);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public PolylineOptions[] newArray(int size) {
        return new PolylineOptions[size];
    }
}
