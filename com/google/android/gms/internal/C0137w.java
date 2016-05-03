package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.google.android.gms.internal.w */
public final class C0137w {

    /* renamed from: com.google.android.gms.internal.w.a */
    public static final class C0136a {
        private final List<String> aZ;
        private final Object ba;

        private C0136a(Object obj) {
            this.ba = C0138x.m383d(obj);
            this.aZ = new ArrayList();
        }

        public C0136a m375a(String str, Object obj) {
            this.aZ.add(((String) C0138x.m383d(str)) + "=" + String.valueOf(obj));
            return this;
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.ba.getClass().getSimpleName()).append('{');
            int size = this.aZ.size();
            for (int i = 0; i < size; i++) {
                append.append((String) this.aZ.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }

    public static boolean m376a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static C0136a m377c(Object obj) {
        return new C0136a(null);
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }
}
