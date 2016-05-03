package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.C0137w;
import com.google.android.gms.internal.C0138x;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.cy;

public final class CameraPosition implements ae {
    public static final CameraPositionCreator CREATOR;
    private final int f52T;
    public final float bearing;
    public final LatLng target;
    public final float tilt;
    public final float zoom;

    static {
        CREATOR = new CameraPositionCreator();
    }

    CameraPosition(int versionCode, LatLng target, float zoom, float tilt, float bearing) {
        C0138x.m381b((Object) target, (Object) "null camera target");
        boolean z = 0.0f <= tilt && tilt <= 90.0f;
        C0138x.m382b(z, (Object) "Tilt needs to be between 0 and 90 inclusive");
        this.f52T = versionCode;
        this.target = target;
        this.zoom = zoom;
        this.tilt = tilt + 0.0f;
        if (((double) bearing) <= 0.0d) {
            bearing = (bearing % 360.0f) + 360.0f;
        }
        this.bearing = bearing % 360.0f;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) o;
        return this.target.equals(cameraPosition.target) && Float.floatToIntBits(this.zoom) == Float.floatToIntBits(cameraPosition.zoom) && Float.floatToIntBits(this.tilt) == Float.floatToIntBits(cameraPosition.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(cameraPosition.bearing);
    }

    public int hashCode() {
        return C0137w.hashCode(this.target, Float.valueOf(this.zoom), Float.valueOf(this.tilt), Float.valueOf(this.bearing));
    }

    public String toString() {
        return C0137w.m377c(this).m375a("target", this.target).m375a("zoom", Float.valueOf(this.zoom)).m375a("tilt", Float.valueOf(this.tilt)).m375a("bearing", Float.valueOf(this.bearing)).toString();
    }

    public int m391u() {
        return this.f52T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            cy.m231a(this, out, flags);
        } else {
            CameraPositionCreator.m392a(this, out, flags);
        }
    }
}
