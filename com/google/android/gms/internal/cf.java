package com.google.android.gms.internal;

import android.os.Parcel;
import com.valvesoftware.android.steam.community.C0151R;

public class cf implements ae {
    public static final cg CREATOR;
    private final int f22T;
    private final double eA;
    private final double eB;
    private final float eC;
    private final long eU;
    private final String ew;
    private final int ex;
    private final short ez;

    static {
        CREATOR = new cg();
    }

    public cf(int i, String str, int i2, short s, double d, double d2, float f, long j) {
        m223w(str);
        m222b(f);
        m221a(d, d2);
        int L = m219L(i2);
        this.f22T = i;
        this.ez = s;
        this.ew = str;
        this.eA = d;
        this.eB = d2;
        this.eC = f;
        this.eU = j;
        this.ex = L;
    }

    private static int m219L(int i) {
        int i2 = i & 3;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    private static String m220M(int i) {
        switch (i) {
            case C0151R.styleable.View_paddingStart /*1*/:
                return "CIRCLE";
            default:
                return null;
        }
    }

    private static void m221a(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    private static void m222b(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static void m223w(String str) {
        if (str == null || str.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + str);
        }
    }

    public short aB() {
        return this.ez;
    }

    public float aC() {
        return this.eC;
    }

    public int aD() {
        return this.ex;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof cf)) {
            return false;
        }
        cf cfVar = (cf) obj;
        if (this.eC != cfVar.eC) {
            return false;
        }
        if (this.eA != cfVar.eA) {
            return false;
        }
        if (this.eB != cfVar.eB) {
            return false;
        }
        return this.ez == cfVar.ez;
    }

    public long getExpirationTime() {
        return this.eU;
    }

    public double getLatitude() {
        return this.eA;
    }

    public double getLongitude() {
        return this.eB;
    }

    public String getRequestId() {
        return this.ew;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.eA);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.eB);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.eC)) * 31) + this.ez) * 31) + this.ex;
    }

    public String toString() {
        return String.format("Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, @%d]", new Object[]{m220M(this.ez), this.ew, Integer.valueOf(this.ex), Double.valueOf(this.eA), Double.valueOf(this.eB), Float.valueOf(this.eC), Long.valueOf(this.eU)});
    }

    public int m224u() {
        return this.f22T;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        cg.m225a(this, parcel, flags);
    }
}
