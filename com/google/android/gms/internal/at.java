package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.an.C0112a;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class at extends an implements ae {
    public static final au CREATOR;
    private final int f17T;
    private final aq bF;
    private final Parcel bN;
    private final int bO;
    private int bP;
    private int bQ;
    private final String mClassName;

    static {
        CREATOR = new au();
    }

    at(int i, Parcel parcel, aq aqVar) {
        this.f17T = i;
        this.bN = (Parcel) C0138x.m383d(parcel);
        this.bO = 2;
        this.bF = aqVar;
        if (this.bF == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.bF.m178W();
        }
        this.bP = 2;
    }

    public static HashMap<String, String> m187a(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    private void m188a(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case C0151R.styleable.View_android_focusable /*0*/:
            case C0151R.styleable.View_paddingStart /*1*/:
            case C0151R.styleable.View_paddingEnd /*2*/:
            case C0151R.styleable.Toolbar_subtitle /*3*/:
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                stringBuilder.append(obj);
            case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                stringBuilder.append("\"").append(ay.m214o(obj.toString())).append("\"");
            case C0151R.styleable.Toolbar_popupTheme /*8*/:
                stringBuilder.append("\"").append(aw.m212a((byte[]) obj)).append("\"");
            case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                stringBuilder.append("\"").append(aw.m213b((byte[]) obj));
                stringBuilder.append("\"");
            case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                az.m215a(stringBuilder, (HashMap) obj);
            case C0151R.styleable.Toolbar_titleMargins /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void m189a(StringBuilder stringBuilder, C0112a<?, ?> c0112a, Parcel parcel, int i) {
        switch (c0112a.m142F()) {
            case C0151R.styleable.View_android_focusable /*0*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, Integer.valueOf(ac.m51f(parcel, i))));
            case C0151R.styleable.View_paddingStart /*1*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, ac.m53h(parcel, i)));
            case C0151R.styleable.View_paddingEnd /*2*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, Long.valueOf(ac.m52g(parcel, i))));
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, Float.valueOf(ac.m54i(parcel, i))));
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, Double.valueOf(ac.m55j(parcel, i))));
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, ac.m57k(parcel, i)));
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, Boolean.valueOf(ac.m48c(parcel, i))));
            case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, ac.m58l(parcel, i)));
            case C0151R.styleable.Toolbar_popupTheme /*8*/:
            case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, ac.m61o(parcel, i)));
            case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                m194b(stringBuilder, (C0112a) c0112a, m160a(c0112a, m187a(ac.m60n(parcel, i))));
            case C0151R.styleable.Toolbar_titleMargins /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + c0112a.m142F());
        }
    }

    private void m190a(StringBuilder stringBuilder, String str, C0112a<?, ?> c0112a, Parcel parcel, int i) {
        stringBuilder.append("\"").append(str).append("\":");
        if (c0112a.m149Q()) {
            m189a(stringBuilder, c0112a, parcel, i);
        } else {
            m193b(stringBuilder, c0112a, parcel, i);
        }
    }

    private void m191a(StringBuilder stringBuilder, HashMap<String, C0112a<?, ?>> hashMap, Parcel parcel) {
        HashMap b = m192b(hashMap);
        stringBuilder.append('{');
        int c = ac.m46c(parcel);
        Object obj = null;
        while (parcel.dataPosition() < c) {
            int b2 = ac.m43b(parcel);
            Entry entry = (Entry) b.get(Integer.valueOf(ac.m56j(b2)));
            if (entry != null) {
                if (obj != null) {
                    stringBuilder.append(",");
                }
                m190a(stringBuilder, (String) entry.getKey(), (C0112a) entry.getValue(), parcel, b2);
                obj = 1;
            }
        }
        if (parcel.dataPosition() != c) {
            throw new C0109a("Overread allowed size end=" + c, parcel);
        }
        stringBuilder.append('}');
    }

    private static HashMap<Integer, Entry<String, C0112a<?, ?>>> m192b(HashMap<String, C0112a<?, ?>> hashMap) {
        HashMap<Integer, Entry<String, C0112a<?, ?>>> hashMap2 = new HashMap();
        for (Entry entry : hashMap.entrySet()) {
            hashMap2.put(Integer.valueOf(((C0112a) entry.getValue()).m146N()), entry);
        }
        return hashMap2;
    }

    private void m193b(StringBuilder stringBuilder, C0112a<?, ?> c0112a, Parcel parcel, int i) {
        if (c0112a.m144L()) {
            stringBuilder.append("[");
            switch (c0112a.m142F()) {
                case C0151R.styleable.View_android_focusable /*0*/:
                    av.m207a(stringBuilder, ac.m63q(parcel, i));
                    break;
                case C0151R.styleable.View_paddingStart /*1*/:
                    av.m209a(stringBuilder, ac.m65s(parcel, i));
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    av.m208a(stringBuilder, ac.m64r(parcel, i));
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    av.m206a(stringBuilder, ac.m66t(parcel, i));
                    break;
                case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                    av.m205a(stringBuilder, ac.m67u(parcel, i));
                    break;
                case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                    av.m209a(stringBuilder, ac.m68v(parcel, i));
                    break;
                case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                    av.m211a(stringBuilder, ac.m62p(parcel, i));
                    break;
                case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                    av.m210a(stringBuilder, ac.m69w(parcel, i));
                    break;
                case C0151R.styleable.Toolbar_popupTheme /*8*/:
                case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case C0151R.styleable.Toolbar_titleMargins /*11*/:
                    Parcel[] z = ac.m72z(parcel, i);
                    int length = z.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            stringBuilder.append(",");
                        }
                        z[i2].setDataPosition(0);
                        m191a(stringBuilder, c0112a.m151S(), z[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            stringBuilder.append("]");
            return;
        }
        switch (c0112a.m142F()) {
            case C0151R.styleable.View_android_focusable /*0*/:
                stringBuilder.append(ac.m51f(parcel, i));
            case C0151R.styleable.View_paddingStart /*1*/:
                stringBuilder.append(ac.m53h(parcel, i));
            case C0151R.styleable.View_paddingEnd /*2*/:
                stringBuilder.append(ac.m52g(parcel, i));
            case C0151R.styleable.Toolbar_subtitle /*3*/:
                stringBuilder.append(ac.m54i(parcel, i));
            case C0151R.styleable.Toolbar_contentInsetStart /*4*/:
                stringBuilder.append(ac.m55j(parcel, i));
            case C0151R.styleable.Toolbar_contentInsetEnd /*5*/:
                stringBuilder.append(ac.m57k(parcel, i));
            case C0151R.styleable.Toolbar_contentInsetLeft /*6*/:
                stringBuilder.append(ac.m48c(parcel, i));
            case C0151R.styleable.Toolbar_contentInsetRight /*7*/:
                stringBuilder.append("\"").append(ay.m214o(ac.m58l(parcel, i))).append("\"");
            case C0151R.styleable.Toolbar_popupTheme /*8*/:
                stringBuilder.append("\"").append(aw.m212a(ac.m61o(parcel, i))).append("\"");
            case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                stringBuilder.append("\"").append(aw.m213b(ac.m61o(parcel, i)));
                stringBuilder.append("\"");
            case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                Bundle n = ac.m60n(parcel, i);
                Set<String> keySet = n.keySet();
                keySet.size();
                stringBuilder.append("{");
                int i3 = 1;
                for (String str : keySet) {
                    if (i3 == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("\"").append(str).append("\"");
                    stringBuilder.append(":");
                    stringBuilder.append("\"").append(ay.m214o(n.getString(str))).append("\"");
                    i3 = 0;
                }
                stringBuilder.append("}");
            case C0151R.styleable.Toolbar_titleMargins /*11*/:
                Parcel y = ac.m71y(parcel, i);
                y.setDataPosition(0);
                m191a(stringBuilder, c0112a.m151S(), y);
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void m194b(StringBuilder stringBuilder, C0112a<?, ?> c0112a, Object obj) {
        if (c0112a.m143K()) {
            m195b(stringBuilder, (C0112a) c0112a, (ArrayList) obj);
        } else {
            m188a(stringBuilder, c0112a.m141E(), obj);
        }
    }

    private void m195b(StringBuilder stringBuilder, C0112a<?, ?> c0112a, ArrayList<?> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            m188a(stringBuilder, c0112a.m141E(), arrayList.get(i));
        }
        stringBuilder.append("]");
    }

    public HashMap<String, C0112a<?, ?>> m196G() {
        return this.bF == null ? null : this.bF.m179n(this.mClassName);
    }

    public Parcel m197Y() {
        switch (this.bP) {
            case C0151R.styleable.View_android_focusable /*0*/:
                this.bQ = ad.m96d(this.bN);
                ad.m75C(this.bN, this.bQ);
                this.bP = 2;
                break;
            case C0151R.styleable.View_paddingStart /*1*/:
                ad.m75C(this.bN, this.bQ);
                this.bP = 2;
                break;
        }
        return this.bN;
    }

    aq m198Z() {
        switch (this.bO) {
            case C0151R.styleable.View_android_focusable /*0*/:
                return null;
            case C0151R.styleable.View_paddingStart /*1*/:
                return this.bF;
            case C0151R.styleable.View_paddingEnd /*2*/:
                return this.bF;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.bO);
        }
    }

    public int describeContents() {
        return 0;
    }

    protected Object m199j(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    protected boolean m200k(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public String toString() {
        C0138x.m381b(this.bF, (Object) "Cannot convert to JSON on client side.");
        Parcel Y = m197Y();
        Y.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        m191a(stringBuilder, this.bF.m179n(this.mClassName), Y);
        return stringBuilder.toString();
    }

    public int m201u() {
        return this.f17T;
    }

    public void writeToParcel(Parcel out, int flags) {
        au.m202a(this, out, flags);
    }
}
