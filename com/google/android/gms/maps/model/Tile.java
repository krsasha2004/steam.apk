package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.dg;

public final class Tile implements ae {
    public static final TileCreator CREATOR;
    private final int f61T;
    public final byte[] data;
    public final int height;
    public final int width;

    static {
        CREATOR = new TileCreator();
    }

    Tile(int versionCode, int width, int height, byte[] data) {
        this.f61T = versionCode;
        this.width = width;
        this.height = height;
        this.data = data;
    }

    public Tile(int width, int height, byte[] data) {
        this(1, width, height, data);
    }

    public int describeContents() {
        return 0;
    }

    public int m407u() {
        return this.f61T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            dg.m239a(this, out, flags);
        } else {
            TileCreator.m408a(this, out, flags);
        }
    }
}
