package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class MarkerOptionsCreator implements Creator<MarkerOptions> {
    static void m402a(MarkerOptions markerOptions, Parcel parcel, int i) {
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

    public MarkerOptions createFromParcel(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        IBinder iBinder = null;
        int c = ac.m46c(parcel);
        boolean z2 = false;
        float f2 = 0.0f;
        String str = null;
        String str2 = null;
        LatLng latLng = null;
        int i = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    latLng = (LatLng) ac.m40a(parcel, b, LatLng.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    str2 = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    str = ac.m58l(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    iBinder = ac.m59m(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    f2 = ac.m54i(parcel, b);
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
            return new MarkerOptions(i, latLng, str2, str, iBinder, f2, f, z2, z);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public MarkerOptions[] newArray(int size) {
        return new MarkerOptions[size];
    }
}
