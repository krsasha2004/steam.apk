package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0137w;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.di;

public final class VisibleRegion implements ae {
    public static final VisibleRegionCreator CREATOR;
    private final int f63T;
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;

    static {
        CREATOR = new VisibleRegionCreator();
    }

    VisibleRegion(int versionCode, LatLng nearLeft, LatLng nearRight, LatLng farLeft, LatLng farRight, LatLngBounds latLngBounds) {
        this.f63T = versionCode;
        this.nearLeft = nearLeft;
        this.nearRight = nearRight;
        this.farLeft = farLeft;
        this.farRight = farRight;
        this.latLngBounds = latLngBounds;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) o;
        return this.nearLeft.equals(visibleRegion.nearLeft) && this.nearRight.equals(visibleRegion.nearRight) && this.farLeft.equals(visibleRegion.farLeft) && this.farRight.equals(visibleRegion.farRight) && this.latLngBounds.equals(visibleRegion.latLngBounds);
    }

    public int hashCode() {
        return C0137w.hashCode(this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds);
    }

    public String toString() {
        return C0137w.m377c(this).m375a("nearLeft", this.nearLeft).m375a("nearRight", this.nearRight).m375a("farLeft", this.farLeft).m375a("farRight", this.farRight).m375a("latLngBounds", this.latLngBounds).toString();
    }

    public int m412u() {
        return this.f63T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            di.m241a(this, out, flags);
        } else {
            VisibleRegionCreator.m413a(this, out, flags);
        }
    }
}
