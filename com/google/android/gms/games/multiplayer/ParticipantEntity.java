package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.C0137w;

public final class ParticipantEntity implements Parcelable, Participant {
    public static final Creator<ParticipantEntity> CREATOR;
    private final String bp;
    private final Uri cm;
    private final Uri cn;
    private final PlayerEntity dQ;
    private final int dR;
    private final String dS;
    private final boolean dT;
    private final String dq;

    /* renamed from: com.google.android.gms.games.multiplayer.ParticipantEntity.1 */
    static class C01051 implements Creator<ParticipantEntity> {
        C01051() {
        }

        public ParticipantEntity[] m24E(int i) {
            return new ParticipantEntity[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m25q(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m24E(x0);
        }

        public ParticipantEntity m25q(Parcel parcel) {
            Object obj = 1;
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            Uri parse = readString3 == null ? null : Uri.parse(readString3);
            String readString4 = parcel.readString();
            Uri parse2 = readString4 == null ? null : Uri.parse(readString4);
            int readInt = parcel.readInt();
            String readString5 = parcel.readString();
            boolean z = parcel.readInt() > 0;
            if (parcel.readInt() <= 0) {
                obj = null;
            }
            return new ParticipantEntity(readString2, parse, parse2, readInt, readString5, z, obj != null ? (PlayerEntity) PlayerEntity.CREATOR.createFromParcel(parcel) : null, null);
        }
    }

    static {
        CREATOR = new C01051();
    }

    public ParticipantEntity(Participant participant) {
        Player player = participant.getPlayer();
        this.dQ = player == null ? null : new PlayerEntity(player);
        this.dq = participant.getParticipantId();
        this.bp = participant.getDisplayName();
        this.cm = participant.getIconImageUri();
        this.cn = participant.getHiResImageUri();
        this.dR = participant.getStatus();
        this.dS = participant.getClientAddress();
        this.dT = participant.isConnectedToRoom();
    }

    private ParticipantEntity(String participantId, String displayName, Uri iconImageUri, Uri hiResImageUri, int status, String clientAddress, boolean connectedToRoom, PlayerEntity player) {
        this.dq = participantId;
        this.bp = displayName;
        this.cm = iconImageUri;
        this.cn = hiResImageUri;
        this.dR = status;
        this.dS = clientAddress;
        this.dT = connectedToRoom;
        this.dQ = player;
    }

    public static int m26a(Participant participant) {
        return C0137w.hashCode(participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.getClientAddress(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri());
    }

    public static boolean m27a(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return C0137w.m376a(participant2.getPlayer(), participant.getPlayer()) && C0137w.m376a(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && C0137w.m376a(participant2.getClientAddress(), participant.getClientAddress()) && C0137w.m376a(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && C0137w.m376a(participant2.getDisplayName(), participant.getDisplayName()) && C0137w.m376a(participant2.getIconImageUri(), participant.getIconImageUri()) && C0137w.m376a(participant2.getHiResImageUri(), participant.getHiResImageUri());
    }

    public static String m28b(Participant participant) {
        return C0137w.m377c(participant).m375a("Player", participant.getPlayer()).m375a("Status", Integer.valueOf(participant.getStatus())).m375a("ClientAddress", participant.getClientAddress()).m375a("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).m375a("DisplayName", participant.getDisplayName()).m375a("IconImage", participant.getIconImageUri()).m375a("HiResImage", participant.getHiResImageUri()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return m27a(this, obj);
    }

    public Participant freeze() {
        return this;
    }

    public String getClientAddress() {
        return this.dS;
    }

    public String getDisplayName() {
        return this.dQ == null ? this.bp : this.dQ.getDisplayName();
    }

    public Uri getHiResImageUri() {
        return this.dQ == null ? this.cn : this.dQ.getHiResImageUri();
    }

    public Uri getIconImageUri() {
        return this.dQ == null ? this.cm : this.dQ.getIconImageUri();
    }

    public String getParticipantId() {
        return this.dq;
    }

    public Player getPlayer() {
        return this.dQ;
    }

    public int getStatus() {
        return this.dR;
    }

    public int hashCode() {
        return m26a(this);
    }

    public boolean isConnectedToRoom() {
        return this.dT;
    }

    public String toString() {
        return m28b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        String str = null;
        int i = 0;
        dest.writeString(this.dq);
        dest.writeString(this.bp);
        dest.writeString(this.cm == null ? null : this.cm.toString());
        if (this.cn != null) {
            str = this.cn.toString();
        }
        dest.writeString(str);
        dest.writeInt(this.dR);
        dest.writeString(this.dS);
        dest.writeInt(this.dT ? 1 : 0);
        if (this.dQ != null) {
            i = 1;
        }
        dest.writeInt(i);
        if (this.dQ != null) {
            this.dQ.writeToParcel(dest, flags);
        }
    }
}
