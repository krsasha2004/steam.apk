package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.internal.an.C0111b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class ak implements ae, C0111b<String, Integer> {
    public static final al CREATOR;
    private final int f14T;
    private final HashMap<String, Integer> bs;
    private final HashMap<Integer, String> bt;
    private final ArrayList<C0110a> bu;

    /* renamed from: com.google.android.gms.internal.ak.a */
    public static final class C0110a implements ae {
        public static final am CREATOR;
        final String bv;
        final int bw;
        final int versionCode;

        static {
            CREATOR = new am();
        }

        C0110a(int i, String str, int i2) {
            this.versionCode = i;
            this.bv = str;
            this.bw = i2;
        }

        C0110a(String str, int i) {
            this.versionCode = 1;
            this.bv = str;
            this.bw = i;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            am.m129a(this, out, flags);
        }
    }

    static {
        CREATOR = new al();
    }

    public ak() {
        this.f14T = 1;
        this.bs = new HashMap();
        this.bt = new HashMap();
        this.bu = null;
    }

    ak(int i, ArrayList<C0110a> arrayList) {
        this.f14T = i;
        this.bs = new HashMap();
        this.bt = new HashMap();
        this.bu = null;
        m118a((ArrayList) arrayList);
    }

    private void m118a(ArrayList<C0110a> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0110a c0110a = (C0110a) it.next();
            m123b(c0110a.bv, c0110a.bw);
        }
    }

    ArrayList<C0110a> m119D() {
        ArrayList<C0110a> arrayList = new ArrayList();
        for (String str : this.bs.keySet()) {
            arrayList.add(new C0110a(str, ((Integer) this.bs.get(str)).intValue()));
        }
        return arrayList;
    }

    public int m120E() {
        return 7;
    }

    public int m121F() {
        return 0;
    }

    public String m122a(Integer num) {
        String str = (String) this.bt.get(num);
        return (str == null && this.bs.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    public ak m123b(String str, int i) {
        this.bs.put(str, Integer.valueOf(i));
        this.bt.put(Integer.valueOf(i), str);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public /* synthetic */ Object m124e(Object obj) {
        return m122a((Integer) obj);
    }

    int m125u() {
        return this.f14T;
    }

    public void writeToParcel(Parcel out, int flags) {
        al.m126a(this, out, flags);
    }
}
