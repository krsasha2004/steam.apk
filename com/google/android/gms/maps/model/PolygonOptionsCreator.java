package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;
import java.util.List;

public class PolygonOptionsCreator implements Creator<PolygonOptions> {
    static void m404a(PolygonOptions polygonOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, polygonOptions.m403u());
        ad.m93b(parcel, 2, polygonOptions.getPoints(), false);
        ad.m95c(parcel, 3, polygonOptions.ba(), false);
        ad.m78a(parcel, 4, polygonOptions.getStrokeWidth());
        ad.m94c(parcel, 5, polygonOptions.getStrokeColor());
        ad.m94c(parcel, 6, polygonOptions.getFillColor());
        ad.m78a(parcel, 7, polygonOptions.getZIndex());
        ad.m87a(parcel, 8, polygonOptions.isVisible());
        ad.m87a(parcel, 9, polygonOptions.isGeodesic());
        ad.m75C(parcel, d);
    }

    public PolygonOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int c = ac.m46c(parcel);
        List list = null;
        List arrayList = new ArrayList();
        boolean z2 = false;
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
                    list = ac.m47c(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    ac.m42a(parcel, b, arrayList, getClass().getClassLoader());
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
                    z2 = ac.m48c(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    z = ac.m48c(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new PolygonOptions(i3, list, arrayList, f2, i2, i, f, z2, z);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public PolygonOptions[] newArray(int size) {
        return new PolygonOptions[size];
    }
}
