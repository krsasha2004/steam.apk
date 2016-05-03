package com.google.android.gms.internal;

import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.google.android.gms.internal.k */
public final class C0132k implements ae {
    public static final C0133l CREATOR;
    private static final C0130a aa;
    int f40T;
    String[] f41U;
    Bundle f42V;
    CursorWindow[] f43W;
    Bundle f44X;
    int[] f45Y;
    int f46Z;
    boolean mClosed;
    int f47p;

    /* renamed from: com.google.android.gms.internal.k.a */
    public static class C0130a {
        private final String[] f39U;
        private final ArrayList<HashMap<String, Object>> ab;
        private final String ac;
        private final HashMap<Object, Integer> ad;
        private boolean ae;
        private String af;

        private C0130a(String[] strArr, String str) {
            this.f39U = (String[]) C0138x.m383d(strArr);
            this.ab = new ArrayList();
            this.ac = str;
            this.ad = new HashMap();
            this.ae = false;
            this.af = null;
        }
    }

    /* renamed from: com.google.android.gms.internal.k.1 */
    static class C01311 extends C0130a {
        C01311(String[] strArr, String str) {
            super(str, null);
        }
    }

    static {
        CREATOR = new C0133l();
        aa = new C01311(new String[0], null);
    }

    C0132k() {
        this.mClosed = false;
    }

    private void m361a(String str, int i) {
        if (this.f42V == null || !this.f42V.containsKey(str)) {
            throw new IllegalArgumentException("No such column: " + str);
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.f46Z) {
            throw new CursorIndexOutOfBoundsException(i, this.f46Z);
        }
    }

    public long m362a(String str, int i, int i2) {
        m361a(str, i);
        return this.f43W[i2].getLong(i - this.f45Y[i2], this.f42V.getInt(str));
    }

    public int m363b(String str, int i, int i2) {
        m361a(str, i);
        return this.f43W[i2].getInt(i - this.f45Y[i2], this.f42V.getInt(str));
    }

    public String m364c(String str, int i, int i2) {
        m361a(str, i);
        return this.f43W[i2].getString(i - this.f45Y[i2], this.f42V.getInt(str));
    }

    public int m365d(int i) {
        int i2 = 0;
        boolean z = i >= 0 && i < this.f46Z;
        C0138x.m378a(z);
        while (i2 < this.f45Y.length) {
            if (i < this.f45Y[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.f45Y.length ? i2 - 1 : i2;
    }

    public boolean m366d(String str, int i, int i2) {
        m361a(str, i);
        return Long.valueOf(this.f43W[i2].getLong(i - this.f45Y[i2], this.f42V.getInt(str))).longValue() == 1;
    }

    public int describeContents() {
        return 0;
    }

    public Uri m367f(String str, int i, int i2) {
        String c = m364c(str, i, i2);
        return c == null ? null : Uri.parse(c);
    }

    public void m368g() {
        int i;
        int i2 = 0;
        this.f42V = new Bundle();
        for (i = 0; i < this.f41U.length; i++) {
            this.f42V.putInt(this.f41U[i], i);
        }
        this.f45Y = new int[this.f43W.length];
        i = 0;
        while (i2 < this.f43W.length) {
            this.f45Y[i2] = i;
            i += this.f43W[i2].getNumRows();
            i2++;
        }
        this.f46Z = i;
    }

    public boolean m369g(String str, int i, int i2) {
        m361a(str, i);
        return this.f43W[i2].isNull(i - this.f45Y[i2], this.f42V.getInt(str));
    }

    public int getCount() {
        return this.f46Z;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel dest, int flags) {
        C0133l.m370a(this, dest, flags);
    }
}
