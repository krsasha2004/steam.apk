package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.internal.an.C0112a;
import java.util.ArrayList;
import java.util.HashMap;

public class aq implements ae {
    public static final ar CREATOR;
    private final int f16T;
    private final HashMap<String, HashMap<String, C0112a<?, ?>>> bH;
    private final ArrayList<C0113a> bI;
    private final String bJ;

    /* renamed from: com.google.android.gms.internal.aq.a */
    public static class C0113a implements ae {
        public static final as CREATOR;
        final ArrayList<C0114b> bK;
        final String className;
        final int versionCode;

        static {
            CREATOR = new as();
        }

        C0113a(int i, String str, ArrayList<C0114b> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.bK = arrayList;
        }

        C0113a(String str, HashMap<String, C0112a<?, ?>> hashMap) {
            this.versionCode = 1;
            this.className = str;
            this.bK = C0113a.m173a(hashMap);
        }

        private static ArrayList<C0114b> m173a(HashMap<String, C0112a<?, ?>> hashMap) {
            if (hashMap == null) {
                return null;
            }
            ArrayList<C0114b> arrayList = new ArrayList();
            for (String str : hashMap.keySet()) {
                arrayList.add(new C0114b(str, (C0112a) hashMap.get(str)));
            }
            return arrayList;
        }

        HashMap<String, C0112a<?, ?>> m174X() {
            HashMap<String, C0112a<?, ?>> hashMap = new HashMap();
            int size = this.bK.size();
            for (int i = 0; i < size; i++) {
                C0114b c0114b = (C0114b) this.bK.get(i);
                hashMap.put(c0114b.bL, c0114b.bM);
            }
            return hashMap;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            as.m184a(this, out, flags);
        }
    }

    /* renamed from: com.google.android.gms.internal.aq.b */
    public static class C0114b implements ae {
        public static final ap CREATOR;
        final String bL;
        final C0112a<?, ?> bM;
        final int versionCode;

        static {
            CREATOR = new ap();
        }

        C0114b(int i, String str, C0112a<?, ?> c0112a) {
            this.versionCode = i;
            this.bL = str;
            this.bM = c0112a;
        }

        C0114b(String str, C0112a<?, ?> c0112a) {
            this.versionCode = 1;
            this.bL = str;
            this.bM = c0112a;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            ap.m170a(this, out, flags);
        }
    }

    static {
        CREATOR = new ar();
    }

    aq(int i, ArrayList<C0113a> arrayList, String str) {
        this.f16T = i;
        this.bI = null;
        this.bH = m175b(arrayList);
        this.bJ = (String) C0138x.m383d(str);
        m176T();
    }

    private static HashMap<String, HashMap<String, C0112a<?, ?>>> m175b(ArrayList<C0113a> arrayList) {
        HashMap<String, HashMap<String, C0112a<?, ?>>> hashMap = new HashMap();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            C0113a c0113a = (C0113a) arrayList.get(i);
            hashMap.put(c0113a.className, c0113a.m174X());
        }
        return hashMap;
    }

    public void m176T() {
        for (String str : this.bH.keySet()) {
            HashMap hashMap = (HashMap) this.bH.get(str);
            for (String str2 : hashMap.keySet()) {
                ((C0112a) hashMap.get(str2)).m152a(this);
            }
        }
    }

    ArrayList<C0113a> m177V() {
        ArrayList<C0113a> arrayList = new ArrayList();
        for (String str : this.bH.keySet()) {
            arrayList.add(new C0113a(str, (HashMap) this.bH.get(str)));
        }
        return arrayList;
    }

    public String m178W() {
        return this.bJ;
    }

    public int describeContents() {
        return 0;
    }

    public HashMap<String, C0112a<?, ?>> m179n(String str) {
        return (HashMap) this.bH.get(str);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.bH.keySet()) {
            stringBuilder.append(str).append(":\n");
            HashMap hashMap = (HashMap) this.bH.get(str);
            for (String str2 : hashMap.keySet()) {
                stringBuilder.append("  ").append(str2).append(": ");
                stringBuilder.append(hashMap.get(str2));
            }
        }
        return stringBuilder.toString();
    }

    int m180u() {
        return this.f16T;
    }

    public void writeToParcel(Parcel out, int flags) {
        ar.m181a(this, out, flags);
    }
}
