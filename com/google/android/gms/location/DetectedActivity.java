package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.internal.ae;

public class DetectedActivity implements ae {
    public static final DetectedActivityCreator CREATOR;
    int f49T;
    int eu;
    int ev;

    static {
        CREATOR = new DetectedActivityCreator();
    }

    public DetectedActivity() {
        this.f49T = 1;
    }

    private int m385H(int i) {
        return i > 5 ? 4 : i;
    }

    public int describeContents() {
        return 0;
    }

    public int getType() {
        return m385H(this.eu);
    }

    public String toString() {
        return "DetectedActivity [type=" + getType() + ", confidence=" + this.ev + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        DetectedActivityCreator.m386a(this, out, flags);
    }
}
