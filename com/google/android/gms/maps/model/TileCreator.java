package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class TileCreator implements Creator<Tile> {
    static void m408a(Tile tile, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, tile.m407u());
        ad.m94c(parcel, 2, tile.width);
        ad.m94c(parcel, 3, tile.height);
        ad.m88a(parcel, 4, tile.data, false);
        ad.m75C(parcel, d);
    }

    public Tile createFromParcel(Parcel parcel) {
        int i = 0;
        int c = ac.m46c(parcel);
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    i3 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    i2 = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    i = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    bArr = ac.m61o(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return new Tile(i3, i2, i, bArr);
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public Tile[] newArray(int size) {
        return new Tile[size];
    }
}
