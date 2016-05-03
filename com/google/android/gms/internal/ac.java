package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ac {

    /* renamed from: com.google.android.gms.internal.ac.a */
    public static class C0109a extends RuntimeException {
        public C0109a(String str, Parcel parcel) {
            super(str + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
        }
    }

    public static int m39a(Parcel parcel, int i) {
        return (i & -65536) != -65536 ? (i >> 16) & 65535 : parcel.readInt();
    }

    public static <T extends Parcelable> T m40a(Parcel parcel, int i, Creator<T> creator) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(a + dataPosition);
        return parcelable;
    }

    private static void m41a(Parcel parcel, int i, int i2) {
        int a = m39a(parcel, i);
        if (a != i2) {
            throw new C0109a("Expected size " + i2 + " got " + a + " (0x" + Integer.toHexString(a) + ")", parcel);
        }
    }

    public static void m42a(Parcel parcel, int i, List list, ClassLoader classLoader) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a != 0) {
            parcel.readList(list, classLoader);
            parcel.setDataPosition(a + dataPosition);
        }
    }

    public static int m43b(Parcel parcel) {
        return parcel.readInt();
    }

    public static void m44b(Parcel parcel, int i) {
        parcel.setDataPosition(m39a(parcel, i) + parcel.dataPosition());
    }

    public static <T> T[] m45b(Parcel parcel, int i, Creator<T> creator) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        T[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArray;
    }

    public static int m46c(Parcel parcel) {
        int b = m43b(parcel);
        int a = m39a(parcel, b);
        int dataPosition = parcel.dataPosition();
        if (m56j(b) != 20293) {
            throw new C0109a("Expected object header. Got 0x" + Integer.toHexString(b), parcel);
        }
        b = dataPosition + a;
        if (b >= dataPosition && b <= parcel.dataSize()) {
            return b;
        }
        throw new C0109a("Size read is invalid start=" + dataPosition + " end=" + b, parcel);
    }

    public static <T> ArrayList<T> m47c(Parcel parcel, int i, Creator<T> creator) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<T> createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(a + dataPosition);
        return createTypedArrayList;
    }

    public static boolean m48c(Parcel parcel, int i) {
        m41a(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    public static byte m49d(Parcel parcel, int i) {
        m41a(parcel, i, 4);
        return (byte) parcel.readInt();
    }

    public static short m50e(Parcel parcel, int i) {
        m41a(parcel, i, 4);
        return (short) parcel.readInt();
    }

    public static int m51f(Parcel parcel, int i) {
        m41a(parcel, i, 4);
        return parcel.readInt();
    }

    public static long m52g(Parcel parcel, int i) {
        m41a(parcel, i, 8);
        return parcel.readLong();
    }

    public static BigInteger m53h(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(a + dataPosition);
        return new BigInteger(createByteArray);
    }

    public static float m54i(Parcel parcel, int i) {
        m41a(parcel, i, 4);
        return parcel.readFloat();
    }

    public static double m55j(Parcel parcel, int i) {
        m41a(parcel, i, 8);
        return parcel.readDouble();
    }

    public static int m56j(int i) {
        return 65535 & i;
    }

    public static BigDecimal m57k(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        int readInt = parcel.readInt();
        parcel.setDataPosition(a + dataPosition);
        return new BigDecimal(new BigInteger(createByteArray), readInt);
    }

    public static String m58l(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(a + dataPosition);
        return readString;
    }

    public static IBinder m59m(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(a + dataPosition);
        return readStrongBinder;
    }

    public static Bundle m60n(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(a + dataPosition);
        return readBundle;
    }

    public static byte[] m61o(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(a + dataPosition);
        return createByteArray;
    }

    public static boolean[] m62p(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        boolean[] createBooleanArray = parcel.createBooleanArray();
        parcel.setDataPosition(a + dataPosition);
        return createBooleanArray;
    }

    public static int[] m63q(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(a + dataPosition);
        return createIntArray;
    }

    public static long[] m64r(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        long[] createLongArray = parcel.createLongArray();
        parcel.setDataPosition(a + dataPosition);
        return createLongArray;
    }

    public static BigInteger[] m65s(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigInteger[] bigIntegerArr = new BigInteger[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            bigIntegerArr[i2] = new BigInteger(parcel.createByteArray());
        }
        parcel.setDataPosition(dataPosition + a);
        return bigIntegerArr;
    }

    public static float[] m66t(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        float[] createFloatArray = parcel.createFloatArray();
        parcel.setDataPosition(a + dataPosition);
        return createFloatArray;
    }

    public static double[] m67u(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        double[] createDoubleArray = parcel.createDoubleArray();
        parcel.setDataPosition(a + dataPosition);
        return createDoubleArray;
    }

    public static BigDecimal[] m68v(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        BigDecimal[] bigDecimalArr = new BigDecimal[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            byte[] createByteArray = parcel.createByteArray();
            bigDecimalArr[i2] = new BigDecimal(new BigInteger(createByteArray), parcel.readInt());
        }
        parcel.setDataPosition(dataPosition + a);
        return bigDecimalArr;
    }

    public static String[] m69w(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(a + dataPosition);
        return createStringArray;
    }

    public static ArrayList<String> m70x(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(a + dataPosition);
        return createStringArrayList;
    }

    public static Parcel m71y(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.appendFrom(parcel, dataPosition, a);
        parcel.setDataPosition(a + dataPosition);
        return obtain;
    }

    public static Parcel[] m72z(Parcel parcel, int i) {
        int a = m39a(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (a == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        Parcel[] parcelArr = new Parcel[readInt];
        for (int i2 = 0; i2 < readInt; i2++) {
            int readInt2 = parcel.readInt();
            if (readInt2 != 0) {
                int dataPosition2 = parcel.dataPosition();
                Parcel obtain = Parcel.obtain();
                obtain.appendFrom(parcel, dataPosition2, readInt2);
                parcelArr[i2] = obtain;
                parcel.setDataPosition(readInt2 + dataPosition2);
            } else {
                parcelArr[i2] = null;
            }
        }
        parcel.setDataPosition(dataPosition + a);
        return parcelArr;
    }
}
