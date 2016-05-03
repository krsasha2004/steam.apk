package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0137w;
import com.google.android.gms.internal.C0138x;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.db;

public final class LatLngBounds implements ae {
    public static final LatLngBoundsCreator CREATOR;
    private final int f56T;
    public final LatLng northeast;
    public final LatLng southwest;

    static {
        CREATOR = new LatLngBoundsCreator();
    }

    LatLngBounds(int versionCode, LatLng southwest, LatLng northeast) {
        C0138x.m381b((Object) southwest, (Object) "null southwest");
        C0138x.m381b((Object) northeast, (Object) "null northeast");
        C0138x.m380a(northeast.latitude >= southwest.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(southwest.latitude), Double.valueOf(northeast.latitude));
        this.f56T = versionCode;
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) o;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public int hashCode() {
        return C0137w.hashCode(this.southwest, this.northeast);
    }

    public String toString() {
        return C0137w.m377c(this).m375a("southwest", this.southwest).m375a("northeast", this.northeast).toString();
    }

    public int m398u() {
        return this.f56T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            db.m234a(this, out, flags);
        } else {
            LatLngBoundsCreator.m399a(this, out, flags);
        }
    }
}
