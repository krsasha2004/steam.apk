package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class CameraPositionCreator implements Creator<CameraPosition> {
    static void m392a(CameraPosition cameraPosition, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, cameraPosition.m391u());
        ad.m83a(parcel, 2, cameraPosition.target, i, false);
        ad.m78a(parcel, 3, cameraPosition.zoom);
        ad.m78a(parcel, 4, cameraPosition.tilt);
        ad.m78a(parcel, 5, cameraPosition.bearing);
        ad.m75C(parcel, d);
    }

    public CameraPosition createFromParcel(Parcel parcel) {
        float f = 0.0f;
        int c = ac.m46c(parcel);
        int i = 0;
        LatLng latLng = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
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
                    f3 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    f2 = ac.m54i(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    f = ac.m54i(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new CameraPosition(i, latLng, f3, f2, f);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public CameraPosition[] newArray(int size) {
        return new CameraPosition[size];
    }
}
