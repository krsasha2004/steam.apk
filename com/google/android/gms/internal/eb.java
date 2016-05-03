package com.google.android.gms.internal;

import android.os.Parcel;
import java.util.ArrayList;

public class eb implements ae {
    public static final ec CREATOR;
    private final int f24T;
    private final String ck;
    private final ArrayList<ag> hV;
    private final ArrayList<ag> hW;
    private final boolean hX;

    static {
        CREATOR = new ec();
    }

    public eb(int i, String str, ArrayList<ag> arrayList, ArrayList<ag> arrayList2, boolean z) {
        this.f24T = i;
        this.ck = str;
        this.hV = arrayList;
        this.hW = arrayList2;
        this.hX = z;
    }

    public ArrayList<ag> bw() {
        return this.hV;
    }

    public ArrayList<ag> bx() {
        return this.hW;
    }

    public boolean by() {
        return this.hX;
    }

    public int describeContents() {
        return 0;
    }

    public String getDescription() {
        return this.ck;
    }

    public int m243u() {
        return this.f24T;
    }

    public void writeToParcel(Parcel out, int flags) {
        ec.m244a(this, out, flags);
    }
}
