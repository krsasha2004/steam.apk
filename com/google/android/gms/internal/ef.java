package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.internal.an.C0112a;
import com.google.android.gms.plus.model.moments.Moment;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public final class ef extends an implements ae, Moment {
    public static final eg CREATOR;
    private static final HashMap<String, C0112a<?, ?>> hY;
    private final int f27T;
    private final Set<Integer> hZ;
    private String iD;
    private String iO;
    private String iU;
    private ed iX;
    private ed iY;

    static {
        CREATOR = new eg();
        hY = new HashMap();
        hY.put("id", C0112a.m139f("id", 2));
        hY.put("result", C0112a.m133a("result", 4, ed.class));
        hY.put("startDate", C0112a.m139f("startDate", 5));
        hY.put("target", C0112a.m133a("target", 6, ed.class));
        hY.put("type", C0112a.m139f("type", 7));
    }

    public ef() {
        this.f27T = 1;
        this.hZ = new HashSet();
    }

    ef(Set<Integer> set, int i, String str, ed edVar, String str2, ed edVar2, String str3) {
        this.hZ = set;
        this.f27T = i;
        this.iD = str;
        this.iX = edVar;
        this.iO = str2;
        this.iY = edVar2;
        this.iU = str3;
    }

    public HashMap<String, C0112a<?, ?>> m256G() {
        return hY;
    }

    protected boolean m257a(C0112a c0112a) {
        return this.hZ.contains(Integer.valueOf(c0112a.m146N()));
    }

    protected Object m258b(C0112a c0112a) {
        switch (c0112a.m146N()) {
            case C0151R.styleable.View_paddingEnd /*2*/:
                return this.iD;
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                return this.iX;
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                return this.iO;
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                return this.iY;
            case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                return this.iU;
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + c0112a.m146N());
        }
    }

    ed bQ() {
        return this.iX;
    }

    ed bR() {
        return this.iY;
    }

    public ef bS() {
        return this;
    }

    Set<Integer> bz() {
        return this.hZ;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ef)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ef efVar = (ef) obj;
        for (C0112a c0112a : hY.values()) {
            if (m257a(c0112a)) {
                if (!efVar.m257a(c0112a)) {
                    return false;
                }
                if (!m258b(c0112a).equals(efVar.m258b(c0112a))) {
                    return false;
                }
            } else if (efVar.m257a(c0112a)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return bS();
    }

    public String getId() {
        return this.iD;
    }

    public String getStartDate() {
        return this.iO;
    }

    public String getType() {
        return this.iU;
    }

    public int hashCode() {
        int i = 0;
        for (C0112a c0112a : hY.values()) {
            int hashCode;
            if (m257a(c0112a)) {
                hashCode = m258b(c0112a).hashCode() + (i + c0112a.m146N());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    protected Object m259j(String str) {
        return null;
    }

    protected boolean m260k(String str) {
        return false;
    }

    int m261u() {
        return this.f27T;
    }

    public void writeToParcel(Parcel out, int flags) {
        eg.m262a(this, out, flags);
    }
}
