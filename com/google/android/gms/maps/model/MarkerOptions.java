package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.bc.C0116a;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.dd;

public final class MarkerOptions implements ae {
    public static final MarkerOptionsCreator CREATOR;
    private final int f57T;
    private boolean fZ;
    private float gh;
    private float gi;
    private LatLng go;
    private String gp;
    private String gq;
    private BitmapDescriptor gr;
    private boolean gs;

    static {
        CREATOR = new MarkerOptionsCreator();
    }

    public MarkerOptions() {
        this.gh = 0.5f;
        this.gi = 1.0f;
        this.fZ = true;
        this.f57T = 1;
    }

    MarkerOptions(int versionCode, LatLng position, String title, String snippet, IBinder wrappedIcon, float anchorU, float anchorV, boolean draggable, boolean visible) {
        this.gh = 0.5f;
        this.gi = 1.0f;
        this.fZ = true;
        this.f57T = versionCode;
        this.go = position;
        this.gp = title;
        this.gq = snippet;
        this.gr = wrappedIcon == null ? null : new BitmapDescriptor(C0116a.m216j(wrappedIcon));
        this.gh = anchorU;
        this.gi = anchorV;
        this.gs = draggable;
        this.fZ = visible;
    }

    public IBinder aZ() {
        return this.gr == null ? null : this.gr.aE().asBinder();
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

    public LatLng getPosition() {
        return this.go;
    }

    public String getSnippet() {
        return this.gq;
    }

    public String getTitle() {
        return this.gp;
    }

    public boolean isDraggable() {
        return this.gs;
    }

    public boolean isVisible() {
        return this.fZ;
    }

    public int m401u() {
        return this.f57T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            dd.m236a(this, out, flags);
        } else {
            MarkerOptionsCreator.m402a(this, out, flags);
        }
    }
}
