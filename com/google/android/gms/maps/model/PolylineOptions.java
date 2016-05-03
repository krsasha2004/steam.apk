package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.df;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions implements ae {
    public static final PolylineOptionsCreator CREATOR;
    private int f59L;
    private final int f60T;
    private float fY;
    private boolean fZ;
    private float gd;
    private final List<LatLng> gu;
    private boolean gw;

    static {
        CREATOR = new PolylineOptionsCreator();
    }

    public PolylineOptions() {
        this.gd = 10.0f;
        this.f59L = -16777216;
        this.fY = 0.0f;
        this.fZ = true;
        this.gw = false;
        this.f60T = 1;
        this.gu = new ArrayList();
    }

    PolylineOptions(int versionCode, List points, float width, int color, float zIndex, boolean visible, boolean geodesic) {
        this.gd = 10.0f;
        this.f59L = -16777216;
        this.fY = 0.0f;
        this.fZ = true;
        this.gw = false;
        this.f60T = versionCode;
        this.gu = points;
        this.gd = width;
        this.f59L = color;
        this.fY = zIndex;
        this.fZ = visible;
        this.gw = geodesic;
    }

    public int describeContents() {
        return 0;
    }

    public int getColor() {
        return this.f59L;
    }

    public List<LatLng> getPoints() {
        return this.gu;
    }

    public float getWidth() {
        return this.gd;
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

    public int m405u() {
        return this.f60T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            df.m238a(this, out, flags);
        } else {
            PolylineOptionsCreator.m406a(this, out, flags);
        }
    }
}
