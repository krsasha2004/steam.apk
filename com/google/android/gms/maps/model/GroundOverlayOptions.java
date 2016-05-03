package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.bc.C0116a;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.da;

public final class GroundOverlayOptions implements ae {
    public static final GroundOverlayOptionsCreator CREATOR;
    private final int f54T;
    private float fR;
    private float fY;
    private boolean fZ;
    private BitmapDescriptor gb;
    private LatLng gc;
    private float gd;
    private float ge;
    private LatLngBounds gf;
    private float gg;
    private float gh;
    private float gi;

    static {
        CREATOR = new GroundOverlayOptionsCreator();
    }

    public GroundOverlayOptions() {
        this.fZ = true;
        this.gg = 0.0f;
        this.gh = 0.5f;
        this.gi = 0.5f;
        this.f54T = 1;
    }

    GroundOverlayOptions(int versionCode, IBinder wrappedImage, LatLng location, float width, float height, LatLngBounds bounds, float bearing, float zIndex, boolean visible, float transparency, float anchorU, float anchorV) {
        this.fZ = true;
        this.gg = 0.0f;
        this.gh = 0.5f;
        this.gi = 0.5f;
        this.f54T = versionCode;
        this.gb = new BitmapDescriptor(C0116a.m216j(wrappedImage));
        this.gc = location;
        this.gd = width;
        this.ge = height;
        this.gf = bounds;
        this.fR = bearing;
        this.fY = zIndex;
        this.fZ = visible;
        this.gg = transparency;
        this.gh = anchorU;
        this.gi = anchorV;
    }

    public IBinder aY() {
        return this.gb.aE().asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.gh;
    }

    public float getAnchorV() {
        return this.gi;
    }

    public float getBearing() {
        return this.fR;
    }

    public LatLngBounds getBounds() {
        return this.gf;
    }

    public float getHeight() {
        return this.ge;
    }

    public LatLng getLocation() {
        return this.gc;
    }

    public float getTransparency() {
        return this.gg;
    }

    public float getWidth() {
        return this.gd;
    }

    public float getZIndex() {
        return this.fY;
    }

    public boolean isVisible() {
        return this.fZ;
    }

    public int m395u() {
        return this.f54T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            da.m233a(this, out, flags);
        } else {
            GroundOverlayOptionsCreator.m396a(this, out, flags);
        }
    }
}
