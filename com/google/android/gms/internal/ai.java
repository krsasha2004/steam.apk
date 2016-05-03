package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.internal.an.C0111b;

public class ai implements ae {
    public static final aj CREATOR;
    private final int f13T;
    private final ak br;

    static {
        CREATOR = new aj();
    }

    ai(int i, ak akVar) {
        this.f13T = i;
        this.br = akVar;
    }

    private ai(ak akVar) {
        this.f13T = 1;
        this.br = akVar;
    }

    public static ai m108a(C0111b<?, ?> c0111b) {
        if (c0111b instanceof ak) {
            return new ai((ak) c0111b);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    ak m109B() {
        return this.br;
    }

    public C0111b<?, ?> m110C() {
        if (this.br != null) {
            return this.br;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }

    public int describeContents() {
        return 0;
    }

    int m111u() {
        return this.f13T;
    }

    public void writeToParcel(Parcel out, int flags) {
        aj.m112a(this, out, flags);
    }
}
