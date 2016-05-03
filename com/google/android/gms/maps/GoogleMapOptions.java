package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.ci;
import com.google.android.gms.internal.cj;
import com.google.android.gms.internal.cx;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements ae {
    public static final GoogleMapOptionsCreator CREATOR;
    private final int f51T;
    private Boolean fm;
    private Boolean fn;
    private int fo;
    private CameraPosition fp;
    private Boolean fq;
    private Boolean fr;
    private Boolean fs;
    private Boolean ft;
    private Boolean fu;
    private Boolean fv;

    static {
        CREATOR = new GoogleMapOptionsCreator();
    }

    public GoogleMapOptions() {
        this.fo = -1;
        this.f51T = 1;
    }

    GoogleMapOptions(int versionCode, byte zOrderOnTop, byte useViewLifecycleInFragment, int mapType, CameraPosition camera, byte zoomControlsEnabled, byte compassEnabled, byte scrollGesturesEnabled, byte zoomGesturesEnabled, byte tiltGesturesEnabled, byte rotateGesturesEnabled) {
        this.fo = -1;
        this.f51T = versionCode;
        this.fm = cj.m229a(zOrderOnTop);
        this.fn = cj.m229a(useViewLifecycleInFragment);
        this.fo = mapType;
        this.fp = camera;
        this.fq = cj.m229a(zoomControlsEnabled);
        this.fr = cj.m229a(compassEnabled);
        this.fs = cj.m229a(scrollGesturesEnabled);
        this.ft = cj.m229a(zoomGesturesEnabled);
        this.fu = cj.m229a(tiltGesturesEnabled);
        this.fv = cj.m229a(rotateGesturesEnabled);
    }

    public byte aH() {
        return cj.m230b(this.fm);
    }

    public byte aI() {
        return cj.m230b(this.fn);
    }

    public byte aJ() {
        return cj.m230b(this.fq);
    }

    public byte aK() {
        return cj.m230b(this.fr);
    }

    public byte aL() {
        return cj.m230b(this.fs);
    }

    public byte aM() {
        return cj.m230b(this.ft);
    }

    public byte aN() {
        return cj.m230b(this.fu);
    }

    public byte aO() {
        return cj.m230b(this.fv);
    }

    public int describeContents() {
        return 0;
    }

    public CameraPosition getCamera() {
        return this.fp;
    }

    public int getMapType() {
        return this.fo;
    }

    public int m389u() {
        return this.f51T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            ci.m228a(this, out, flags);
        } else {
            GoogleMapOptionsCreator.m390a(this, out, flags);
        }
    }
}
