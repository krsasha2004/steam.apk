package com.google.android.gms.internal;

import android.os.Parcel;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class an {

    /* renamed from: com.google.android.gms.internal.an.b */
    public interface C0111b<I, O> {
        int m115E();

        int m116F();

        I m117e(O o);
    }

    /* renamed from: com.google.android.gms.internal.an.a */
    public static class C0112a<I, O> implements ae {
        public static final ao CREATOR;
        private final int f15T;
        protected final boolean bA;
        protected final String bB;
        protected final int bC;
        protected final Class<? extends an> bD;
        protected final String bE;
        private aq bF;
        private C0111b<I, O> bG;
        protected final int bx;
        protected final boolean by;
        protected final int bz;

        static {
            CREATOR = new ao();
        }

        C0112a(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, ai aiVar) {
            this.f15T = i;
            this.bx = i2;
            this.by = z;
            this.bz = i3;
            this.bA = z2;
            this.bB = str;
            this.bC = i4;
            if (str2 == null) {
                this.bD = null;
                this.bE = null;
            } else {
                this.bD = at.class;
                this.bE = str2;
            }
            if (aiVar == null) {
                this.bG = null;
            } else {
                this.bG = aiVar.m110C();
            }
        }

        protected C0112a(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends an> cls, C0111b<I, O> c0111b) {
            this.f15T = 1;
            this.bx = i;
            this.by = z;
            this.bz = i2;
            this.bA = z2;
            this.bB = str;
            this.bC = i3;
            this.bD = cls;
            if (cls == null) {
                this.bE = null;
            } else {
                this.bE = cls.getCanonicalName();
            }
            this.bG = c0111b;
        }

        public static C0112a m132a(String str, int i, C0111b<?, ?> c0111b, boolean z) {
            return new C0112a(c0111b.m115E(), z, c0111b.m116F(), false, str, i, null, c0111b);
        }

        public static <T extends an> C0112a<T, T> m133a(String str, int i, Class<T> cls) {
            return new C0112a(11, false, 11, false, str, i, cls, null);
        }

        public static <T extends an> C0112a<ArrayList<T>, ArrayList<T>> m134b(String str, int i, Class<T> cls) {
            return new C0112a(11, true, 11, true, str, i, cls, null);
        }

        public static C0112a<Integer, Integer> m135c(String str, int i) {
            return new C0112a(0, false, 0, false, str, i, null, null);
        }

        public static C0112a<Double, Double> m137d(String str, int i) {
            return new C0112a(4, false, 4, false, str, i, null, null);
        }

        public static C0112a<Boolean, Boolean> m138e(String str, int i) {
            return new C0112a(6, false, 6, false, str, i, null, null);
        }

        public static C0112a<String, String> m139f(String str, int i) {
            return new C0112a(7, false, 7, false, str, i, null, null);
        }

        public static C0112a<ArrayList<String>, ArrayList<String>> m140g(String str, int i) {
            return new C0112a(7, true, 7, true, str, i, null, null);
        }

        public int m141E() {
            return this.bx;
        }

        public int m142F() {
            return this.bz;
        }

        public boolean m143K() {
            return this.by;
        }

        public boolean m144L() {
            return this.bA;
        }

        public String m145M() {
            return this.bB;
        }

        public int m146N() {
            return this.bC;
        }

        public Class<? extends an> m147O() {
            return this.bD;
        }

        String m148P() {
            return this.bE == null ? null : this.bE;
        }

        public boolean m149Q() {
            return this.bG != null;
        }

        ai m150R() {
            return this.bG == null ? null : ai.m108a(this.bG);
        }

        public HashMap<String, C0112a<?, ?>> m151S() {
            C0138x.m383d(this.bE);
            C0138x.m383d(this.bF);
            return this.bF.m179n(this.bE);
        }

        public void m152a(aq aqVar) {
            this.bF = aqVar;
        }

        public int describeContents() {
            return 0;
        }

        public I m153e(O o) {
            return this.bG.m117e(o);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Field\n");
            stringBuilder.append("            versionCode=").append(this.f15T).append('\n');
            stringBuilder.append("                 typeIn=").append(this.bx).append('\n');
            stringBuilder.append("            typeInArray=").append(this.by).append('\n');
            stringBuilder.append("                typeOut=").append(this.bz).append('\n');
            stringBuilder.append("           typeOutArray=").append(this.bA).append('\n');
            stringBuilder.append("        outputFieldName=").append(this.bB).append('\n');
            stringBuilder.append("      safeParcelFieldId=").append(this.bC).append('\n');
            stringBuilder.append("       concreteTypeName=").append(m148P()).append('\n');
            if (m147O() != null) {
                stringBuilder.append("     concreteType.class=").append(m147O().getCanonicalName()).append('\n');
            }
            stringBuilder.append("          converterName=").append(this.bG == null ? "null" : this.bG.getClass().getCanonicalName()).append('\n');
            return stringBuilder.toString();
        }

        public int m154u() {
            return this.f15T;
        }

        public void writeToParcel(Parcel out, int flags) {
            ao.m167a(this, out, flags);
        }
    }

    private void m155a(StringBuilder stringBuilder, C0112a c0112a, Object obj) {
        if (c0112a.m141E() == 11) {
            stringBuilder.append(((an) c0112a.m147O().cast(obj)).toString());
        } else if (c0112a.m141E() == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(ay.m214o((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    private void m156a(StringBuilder stringBuilder, C0112a c0112a, ArrayList<Object> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                m155a(stringBuilder, c0112a, obj);
            }
        }
        stringBuilder.append("]");
    }

    public abstract HashMap<String, C0112a<?, ?>> m157G();

    public HashMap<String, Object> m158H() {
        return null;
    }

    public HashMap<String, Object> m159I() {
        return null;
    }

    protected <O, I> I m160a(C0112a<I, O> c0112a, Object obj) {
        return c0112a.bG != null ? c0112a.m153e(obj) : obj;
    }

    protected boolean m161a(C0112a c0112a) {
        return c0112a.m142F() == 11 ? c0112a.m144L() ? m166m(c0112a.m145M()) : m165l(c0112a.m145M()) : m164k(c0112a.m145M());
    }

    protected Object m162b(C0112a c0112a) {
        boolean z = true;
        String M = c0112a.m145M();
        if (c0112a.m147O() == null) {
            return m163j(c0112a.m145M());
        }
        if (m163j(c0112a.m145M()) != null) {
            z = false;
        }
        C0138x.m379a(z, "Concrete field shouldn't be value object: " + c0112a.m145M());
        Map I = c0112a.m144L() ? m159I() : m158H();
        if (I != null) {
            return I.get(M);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(M.charAt(0)) + M.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object m163j(String str);

    protected abstract boolean m164k(String str);

    protected boolean m165l(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean m166m(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public String toString() {
        HashMap G = m157G();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : G.keySet()) {
            C0112a c0112a = (C0112a) G.get(str);
            if (m161a(c0112a)) {
                Object a = m160a(c0112a, m162b(c0112a));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (a != null) {
                    switch (c0112a.m142F()) {
                        case C0151R.styleable.Toolbar_popupTheme /*8*/:
                            stringBuilder.append("\"").append(aw.m212a((byte[]) a)).append("\"");
                            break;
                        case C0151R.styleable.Toolbar_titleTextAppearance /*9*/:
                            stringBuilder.append("\"").append(aw.m213b((byte[]) a)).append("\"");
                            break;
                        case C0151R.styleable.Toolbar_subtitleTextAppearance /*10*/:
                            az.m215a(stringBuilder, (HashMap) a);
                            break;
                        default:
                            if (!c0112a.m143K()) {
                                m155a(stringBuilder, c0112a, a);
                                break;
                            }
                            m156a(stringBuilder, c0112a, (ArrayList) a);
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }
}
