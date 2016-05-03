package com.google.android.gms.internal;

import android.net.Uri;

/* renamed from: com.google.android.gms.internal.j */
public abstract class C0117j {
    protected final C0132k f19O;
    protected final int f20R;
    private final int f21S;

    public C0117j(C0132k c0132k, int i) {
        this.f19O = (C0132k) C0138x.m383d(c0132k);
        boolean z = i >= 0 && i < c0132k.getCount();
        C0138x.m378a(z);
        this.f20R = i;
        this.f21S = c0132k.m365d(this.f20R);
    }

    protected Uri m217c(String str) {
        return this.f19O.m367f(str, this.f20R, this.f21S);
    }

    protected boolean m218d(String str) {
        return this.f19O.m369g(str, this.f20R, this.f21S);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C0117j)) {
            return false;
        }
        C0117j c0117j = (C0117j) obj;
        return C0137w.m376a(Integer.valueOf(c0117j.f20R), Integer.valueOf(this.f20R)) && C0137w.m376a(Integer.valueOf(c0117j.f21S), Integer.valueOf(this.f21S)) && c0117j.f19O == this.f19O;
    }

    protected boolean getBoolean(String column) {
        return this.f19O.m366d(column, this.f20R, this.f21S);
    }

    protected int getInteger(String column) {
        return this.f19O.m363b(column, this.f20R, this.f21S);
    }

    protected long getLong(String column) {
        return this.f19O.m362a(column, this.f20R, this.f21S);
    }

    protected String getString(String column) {
        return this.f19O.m364c(column, this.f20R, this.f21S);
    }

    public int hashCode() {
        return C0137w.hashCode(Integer.valueOf(this.f20R), Integer.valueOf(this.f21S), this.f19O);
    }
}
