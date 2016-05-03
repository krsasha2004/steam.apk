package com.google.android.gms.internal;

import android.os.Parcel;

public final class ag implements ae {
    public static final ah CREATOR;
    private final int f12T;
    private final int bl;
    private final int bm;
    private final String bn;
    private final String bo;
    private final String bp;
    private final String bq;

    static {
        CREATOR = new ah();
    }

    public ag(int i, int i2, int i3, String str, String str2, String str3, String str4) {
        this.f12T = i;
        this.bl = i2;
        this.bm = i3;
        this.bn = str;
        this.bo = str2;
        this.bp = str3;
        this.bq = str4;
    }

    public boolean m98A() {
        return this.bl == 2;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ag)) {
            return false;
        }
        ag agVar = (ag) obj;
        return this.f12T == agVar.f12T && this.bl == agVar.bl && this.bm == agVar.bm && C0137w.m376a(this.bn, agVar.bn) && C0137w.m376a(this.bo, agVar.bo);
    }

    public String getDisplayName() {
        return this.bp;
    }

    public int getType() {
        return this.bl;
    }

    public int hashCode() {
        return C0137w.hashCode(Integer.valueOf(this.f12T), Integer.valueOf(this.bl), Integer.valueOf(this.bm), this.bn, this.bo);
    }

    public String toString() {
        if (m98A()) {
            return String.format("Person [%s] %s", new Object[]{m102x(), getDisplayName()});
        } else if (m104z()) {
            return String.format("Circle [%s] %s", new Object[]{m101w(), getDisplayName()});
        } else {
            return String.format("Group [%s] %s", new Object[]{m101w(), getDisplayName()});
        }
    }

    public int m99u() {
        return this.f12T;
    }

    public int m100v() {
        return this.bm;
    }

    public String m101w() {
        return this.bn;
    }

    public void writeToParcel(Parcel out, int flags) {
        ah.m105a(this, out, flags);
    }

    public String m102x() {
        return this.bo;
    }

    public String m103y() {
        return this.bq;
    }

    public boolean m104z() {
        return this.bl == 1 && this.bm == -1;
    }
}
