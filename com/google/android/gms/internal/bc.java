package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface bc extends IInterface {

    /* renamed from: com.google.android.gms.internal.bc.a */
    public static abstract class C0116a extends Binder implements bc {

        /* renamed from: com.google.android.gms.internal.bc.a.a */
        static class C0115a implements bc {
            private IBinder f18a;

            C0115a(IBinder iBinder) {
                this.f18a = iBinder;
            }

            public IBinder asBinder() {
                return this.f18a;
            }
        }

        public static bc m216j(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof bc)) ? new C0115a(iBinder) : (bc) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString("com.google.android.gms.dynamic.IObjectWrapper");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
