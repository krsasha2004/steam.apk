package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.cz;

public final class CircleOptions implements ae {
    public static final CircleOptionsCreator CREATOR;
    private final int f53T;
    private LatLng fT;
    private double fU;
    private float fV;
    private int fW;
    private int fX;
    private float fY;
    private boolean fZ;

    static {
        CREATOR = new CircleOptionsCreator();
    }

    public CircleOptions() {
        this.fT = null;
        this.fU = 0.0d;
        this.fV = 10.0f;
        this.fW = -16777216;
        this.fX = 0;
        this.fY = 0.0f;
        this.fZ = true;
        this.f53T = 1;
    }

    CircleOptions(int versionCode, LatLng center, double radius, float strokeWidth, int strokeColor, int fillColor, float zIndex, boolean visible) {
        this.fT = null;
        this.fU = 0.0d;
        this.fV = 10.0f;
        this.fW = -16777216;
        this.fX = 0;
        this.fY = 0.0f;
        this.fZ = true;
        this.f53T = versionCode;
        this.fT = center;
        this.fU = radius;
        this.fV = strokeWidth;
        this.fW = strokeColor;
        this.fX = fillColor;
        this.fY = zIndex;
        this.fZ = visible;
    }

    public int describeContents() {
        return 0;
    }

    public LatLng getCenter() {
        return this.fT;
    }

    public int getFillColor() {
        return this.fX;
    }

    public double getRadius() {
        return this.fU;
    }

    public int getStrokeColor() {
        return this.fW;
    }

    public float getStrokeWidth() {
        return this.fV;
    }

    public float getZIndex() {
        return this.fY;
    }

    public boolean isVisible() {
        return this.fZ;
    }

    public int m393u() {
        return this.f53T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            cz.m232a(this, out, flags);
        } else {
            CircleOptionsCreator.m394a(this, out, flags);
        }
    }
}
