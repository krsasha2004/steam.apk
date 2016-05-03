package com.google.android.gms.internal;

/* renamed from: com.google.android.gms.internal.x */
public final class C0138x {
    public static void m378a(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void m379a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void m380a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T> T m381b(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void m382b(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static <T> T m383d(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("null reference");
    }
}
