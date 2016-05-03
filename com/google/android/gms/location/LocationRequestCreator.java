package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class LocationRequestCreator implements Creator<LocationRequest> {
    static void m388a(LocationRequest locationRequest, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, locationRequest.mPriority);
        ad.m94c(parcel, 1000, locationRequest.f50T);
        ad.m79a(parcel, 2, locationRequest.eD);
        ad.m79a(parcel, 3, locationRequest.eE);
        ad.m87a(parcel, 4, locationRequest.eF);
        ad.m79a(parcel, 5, locationRequest.ey);
        ad.m94c(parcel, 6, locationRequest.eG);
        ad.m78a(parcel, 7, locationRequest.eH);
        ad.m75C(parcel, d);
    }

    public LocationRequest createFromParcel(Parcel parcel) {
        LocationRequest locationRequest = new LocationRequest();
        int c = ac.m46c(parcel);
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    locationRequest.mPriority = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    locationRequest.eD = ac.m52g(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    locationRequest.eE = ac.m52g(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    locationRequest.eF = ac.m48c(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    locationRequest.ey = ac.m52g(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    locationRequest.eG = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    locationRequest.eH = ac.m54i(parcel, b);
                    break;
                case 1000:
                    locationRequest.f50T = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return locationRequest;
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public LocationRequest[] newArray(int size) {
        return new LocationRequest[size];
    }
}
