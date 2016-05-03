package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.de;
import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions implements ae {
    public static final PolygonOptionsCreator CREATOR;
    private final int f58T;
    private float fV;
    private int fW;
    private int fX;
    private float fY;
    private boolean fZ;
    private final List<LatLng> gu;
    private final List<List<LatLng>> gv;
    private boolean gw;

    static {
        CREATOR = new PolygonOptionsCreator();
    }

    public PolygonOptions() {
        this.fV = 10.0f;
        this.fW = -16777216;
        this.fX = 0;
        this.fY = 0.0f;
        this.fZ = true;
        this.gw = false;
        this.f58T = 1;
        this.gu = new ArrayList();
        this.gv = new ArrayList();
    }

    PolygonOptions(int versionCode, List<LatLng> points, List holes, float strokeWidth, int strokeColor, int fillColor, float zIndex, boolean visible, boolean geodesic) {
        this.fV = 10.0f;
        this.fW = -16777216;
        this.fX = 0;
        this.fY = 0.0f;
        this.fZ = true;
        this.gw = false;
        this.f58T = versionCode;
        this.gu = points;
        this.gv = holes;
        this.fV = strokeWidth;
        this.fW = strokeColor;
        this.fX = fillColor;
        this.fY = zIndex;
        this.fZ = visible;
        this.gw = geodesic;
    }

    public List ba() {
        return this.gv;
    }

    public int describeContents() {
        return 0;
    }

    public int getFillColor() {
        return this.fX;
    }

    public List<LatLng> getPoints() {
        return this.gu;
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

    public boolean isGeodesic() {
        return this.gw;
    }

    public boolean isVisible() {
        return this.fZ;
    }

    public int m403u() {
        return this.f58T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            de.m237a(this, out, flags);
        } else {
            PolygonOptionsCreator.m404a(this, out, flags);
        }
    }
}
