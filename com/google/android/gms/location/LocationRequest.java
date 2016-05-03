package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.internal.C0137w;
import com.google.android.gms.internal.ae;

public final class LocationRequest implements ae {
    public static final LocationRequestCreator CREATOR;
    int f50T;
    long eD;
    long eE;
    boolean eF;
    int eG;
    float eH;
    long ey;
    int mPriority;

    static {
        CREATOR = new LocationRequestCreator();
    }

    public LocationRequest() {
        this.mPriority = 102;
        this.eD = 3600000;
        this.eE = (long) (((double) this.eD) / 6.0d);
        this.eF = false;
        this.ey = Long.MAX_VALUE;
        this.eG = Integer.MAX_VALUE;
        this.eH = 0.0f;
    }

    public static String m387J(int i) {
        switch (i) {
            case 100:
                return "PRIORITY_HIGH_ACCURACY";
            case 102:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case 104:
                return "PRIORITY_LOW_POWER";
            case 105:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) object;
        return this.mPriority == locationRequest.mPriority && this.eD == locationRequest.eD && this.eE == locationRequest.eE && this.eF == locationRequest.eF && this.ey == locationRequest.ey && this.eG == locationRequest.eG && this.eH == locationRequest.eH;
    }

    public int hashCode() {
        return C0137w.hashCode(Integer.valueOf(this.mPriority), Long.valueOf(this.eD), Long.valueOf(this.eE), Boolean.valueOf(this.eF), Long.valueOf(this.ey), Integer.valueOf(this.eG), Float.valueOf(this.eH));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Request[").append(m387J(this.mPriority));
        if (this.mPriority != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.eD + "ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.eE + "ms");
        if (this.ey != Long.MAX_VALUE) {
            long elapsedRealtime = this.ey - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime + "ms");
        }
        if (this.eG != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.eG);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        LocationRequestCreator.m388a(this, parcel, flags);
    }
}
