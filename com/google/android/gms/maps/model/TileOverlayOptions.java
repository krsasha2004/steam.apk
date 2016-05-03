package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.ae;
import com.google.android.gms.internal.cx;
import com.google.android.gms.internal.dh;
import com.google.android.gms.internal.dp;
import com.google.android.gms.internal.dp.C0119a;

public final class TileOverlayOptions implements ae {
    public static final TileOverlayOptionsCreator CREATOR;
    private final int f62T;
    private float fY;
    private boolean fZ;
    private TileProvider gA;
    private dp gz;

    /* renamed from: com.google.android.gms.maps.model.TileOverlayOptions.1 */
    class C01391 implements TileProvider {
        private final dp gB;
        final /* synthetic */ TileOverlayOptions gC;

        C01391(TileOverlayOptions tileOverlayOptions) {
            this.gC = tileOverlayOptions;
            this.gB = this.gC.gz;
        }
    }

    static {
        CREATOR = new TileOverlayOptionsCreator();
    }

    public TileOverlayOptions() {
        this.fZ = true;
        this.f62T = 1;
    }

    TileOverlayOptions(int versionCode, IBinder delegate, boolean visible, float zIndex) {
        this.fZ = true;
        this.f62T = versionCode;
        this.gz = C0119a.m242Q(delegate);
        this.gA = this.gz == null ? null : new C01391(this);
        this.fZ = visible;
        this.fY = zIndex;
    }

    public IBinder bb() {
        return this.gz.asBinder();
    }

    public int describeContents() {
        return 0;
    }

    public float getZIndex() {
        return this.fY;
    }

    public boolean isVisible() {
        return this.fZ;
    }

    public int m410u() {
        return this.f62T;
    }

    public void writeToParcel(Parcel out, int flags) {
        if (cx.aW()) {
            dh.m240a(this, out, flags);
        } else {
            TileOverlayOptionsCreator.m411a(this, out, flags);
        }
    }
}
