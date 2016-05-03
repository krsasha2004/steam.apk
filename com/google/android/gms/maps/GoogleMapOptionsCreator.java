package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.google.android.gms.maps.model.CameraPosition;
import com.valvesoftware.android.steam.community.C0151R;

public class GoogleMapOptionsCreator implements Creator<GoogleMapOptions> {
    static void m390a(GoogleMapOptions googleMapOptions, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, googleMapOptions.m389u());
        ad.m76a(parcel, 2, googleMapOptions.aH());
        ad.m76a(parcel, 3, googleMapOptions.aI());
        ad.m94c(parcel, 4, googleMapOptions.getMapType());
        ad.m83a(parcel, 5, googleMapOptions.getCamera(), i, false);
        ad.m76a(parcel, 6, googleMapOptions.aJ());
        ad.m76a(parcel, 7, googleMapOptions.aK());
        ad.m76a(parcel, 8, googleMapOptions.aL());
        ad.m76a(parcel, 9, googleMapOptions.aM());
        ad.m76a(parcel, 10, googleMapOptions.aN());
        ad.m76a(parcel, 11, googleMapOptions.aO());
        ad.m75C(parcel, d);
    }

    public GoogleMapOptions createFromParcel(Parcel parcel) {
        byte b = (byte) 0;
        int c = ac.m46c(parcel);
        CameraPosition cameraPosition = null;
        byte b2 = (byte) 0;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        byte b6 = (byte) 0;
        int i = 0;
        byte b7 = (byte) 0;
        byte b8 = (byte) 0;
        int i2 = 0;
        while (parcel.dataPosition() < c) {
            int b9 = ac.m43b(parcel);
            switch (ac.m56j(b9)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i2 = ac.m51f(parcel, b9);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    b8 = ac.m49d(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    b7 = ac.m49d(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    i = ac.m51f(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    cameraPosition = (CameraPosition) ac.m40a(parcel, b9, CameraPosition.CREATOR);
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    b6 = ac.m49d(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    b5 = ac.m49d(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                    b4 = ac.m49d(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                    b3 = ac.m49d(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    b2 = ac.m49d(parcel, b9);
                    break;
                case C0151R.styleable.Toolbar_titleMargins /*11*/:
                    b = ac.m49d(parcel, b9);
                    break;
                default:
                    ac.m44b(parcel, b9);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new GoogleMapOptions(i2, b8, b7, i, cameraPosition, b6, b5, b4, b3, b2, b);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public GoogleMapOptions[] newArray(int size) {
        return new GoogleMapOptions[size];
    }
}
