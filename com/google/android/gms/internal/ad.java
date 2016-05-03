package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class ad {
    private static int m73A(Parcel parcel, int i) {
        parcel.writeInt(-65536 | i);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    private static void m74B(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition - i;
        parcel.setDataPosition(i - 4);
        parcel.writeInt(i2);
        parcel.setDataPosition(dataPosition);
    }

    public static void m75C(Parcel parcel, int i) {
        m74B(parcel, i);
    }

    public static void m76a(Parcel parcel, int i, byte b) {
        m92b(parcel, i, 4);
        parcel.writeInt(b);
    }

    public static void m77a(Parcel parcel, int i, double d) {
        m92b(parcel, i, 8);
        parcel.writeDouble(d);
    }

    public static void m78a(Parcel parcel, int i, float f) {
        m92b(parcel, i, 4);
        parcel.writeFloat(f);
    }

    public static void m79a(Parcel parcel, int i, long j) {
        m92b(parcel, i, 8);
        parcel.writeLong(j);
    }

    public static void m80a(Parcel parcel, int i, Bundle bundle, boolean z) {
        if (bundle != null) {
            int A = m73A(parcel, i);
            parcel.writeBundle(bundle);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m81a(Parcel parcel, int i, IBinder iBinder, boolean z) {
        if (iBinder != null) {
            int A = m73A(parcel, i);
            parcel.writeStrongBinder(iBinder);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m82a(Parcel parcel, int i, Parcel parcel2, boolean z) {
        if (parcel2 != null) {
            int A = m73A(parcel, i);
            parcel.appendFrom(parcel2, 0, parcel2.dataSize());
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m83a(Parcel parcel, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable != null) {
            int A = m73A(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m84a(Parcel parcel, int i, String str, boolean z) {
        if (str != null) {
            int A = m73A(parcel, i);
            parcel.writeString(str);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m85a(Parcel parcel, int i, List<String> list, boolean z) {
        if (list != null) {
            int A = m73A(parcel, i);
            parcel.writeStringList(list);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m86a(Parcel parcel, int i, short s) {
        m92b(parcel, i, 4);
        parcel.writeInt(s);
    }

    public static void m87a(Parcel parcel, int i, boolean z) {
        m92b(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    public static void m88a(Parcel parcel, int i, byte[] bArr, boolean z) {
        if (bArr != null) {
            int A = m73A(parcel, i);
            parcel.writeByteArray(bArr);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static <T extends Parcelable> void m89a(Parcel parcel, int i, T[] tArr, int i2, boolean z) {
        if (tArr != null) {
            int A = m73A(parcel, i);
            parcel.writeInt(r3);
            for (Parcelable parcelable : tArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m91a(parcel, parcelable, i2);
                }
            }
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m90a(Parcel parcel, int i, String[] strArr, boolean z) {
        if (strArr != null) {
            int A = m73A(parcel, i);
            parcel.writeStringArray(strArr);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    private static <T extends Parcelable> void m91a(Parcel parcel, T t, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        t.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    private static void m92b(Parcel parcel, int i, int i2) {
        if (i2 >= 65535) {
            parcel.writeInt(-65536 | i);
            parcel.writeInt(i2);
            return;
        }
        parcel.writeInt((i2 << 16) | i);
    }

    public static <T extends Parcelable> void m93b(Parcel parcel, int i, List<T> list, boolean z) {
        if (list != null) {
            int A = m73A(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m91a(parcel, parcelable, 0);
                }
            }
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static void m94c(Parcel parcel, int i, int i2) {
        m92b(parcel, i, 4);
        parcel.writeInt(i2);
    }

    public static void m95c(Parcel parcel, int i, List list, boolean z) {
        if (list != null) {
            int A = m73A(parcel, i);
            parcel.writeList(list);
            m74B(parcel, A);
        } else if (z) {
            m92b(parcel, i, 0);
        }
    }

    public static int m96d(Parcel parcel) {
        return m73A(parcel, 20293);
    }
}
