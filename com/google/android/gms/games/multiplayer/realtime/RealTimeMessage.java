package com.google.android.gms.games.multiplayer.realtime;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0138x;

public final class RealTimeMessage implements Parcelable {
    public static final Creator<RealTimeMessage> CREATOR;
    private final String dV;
    private final byte[] dW;
    private final int dX;

    /* renamed from: com.google.android.gms.games.multiplayer.realtime.RealTimeMessage.1 */
    static class C01061 implements Creator<RealTimeMessage> {
        C01061() {
        }

        public RealTimeMessage[] m29F(int i) {
            return new RealTimeMessage[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m30r(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m29F(x0);
        }

        public RealTimeMessage m30r(Parcel parcel) {
            return new RealTimeMessage(null);
        }
    }

    static {
        CREATOR = new C01061();
    }

    private RealTimeMessage(Parcel parcel) {
        this(parcel.readString(), parcel.createByteArray(), parcel.readInt());
    }

    public RealTimeMessage(String senderParticipantId, byte[] messageData, int isReliable) {
        this.dV = (String) C0138x.m383d(senderParticipantId);
        this.dW = (byte[]) ((byte[]) C0138x.m383d(messageData)).clone();
        this.dX = isReliable;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flag) {
        parcel.writeString(this.dV);
        parcel.writeByteArray(this.dW);
        parcel.writeInt(this.dX);
    }
}
