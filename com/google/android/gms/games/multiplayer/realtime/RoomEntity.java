package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.C0137w;
import java.util.ArrayList;

public final class RoomEntity implements Room {
    public static final Creator<RoomEntity> CREATOR;
    private final String cY;
    private final String ck;
    private final long dK;
    private final ArrayList<Participant> dN;
    private final int eb;
    private final Bundle ed;
    private final String eh;
    private final int ei;

    /* renamed from: com.google.android.gms.games.multiplayer.realtime.RoomEntity.1 */
    static class C01071 implements Creator<RoomEntity> {
        C01071() {
        }

        public RoomEntity[] m31G(int i) {
            return new RoomEntity[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m32s(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m31G(x0);
        }

        public RoomEntity m32s(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            String readString3 = parcel.readString();
            int readInt2 = parcel.readInt();
            Bundle readBundle = parcel.readBundle();
            int readInt3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt3);
            for (int i = 0; i < readInt3; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new RoomEntity(readString2, readLong, readInt, readString3, readInt2, readBundle, arrayList, null);
        }
    }

    static {
        CREATOR = new C01071();
    }

    public RoomEntity(Room room) {
        this.cY = room.getRoomId();
        this.eh = room.getCreatorId();
        this.dK = room.getCreationTimestamp();
        this.ei = room.getStatus();
        this.ck = room.getDescription();
        this.eb = room.getVariant();
        this.ed = room.getAutoMatchCriteria();
        ArrayList participants = room.getParticipants();
        int size = participants.size();
        this.dN = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.dN.add(((Participant) participants.get(i)).freeze());
        }
    }

    private RoomEntity(String roomId, String creatorId, long creationTimestamp, int roomStatus, String description, int variant, Bundle autoMatchCriteria, ArrayList<Participant> participants) {
        this.cY = roomId;
        this.eh = creatorId;
        this.dK = creationTimestamp;
        this.ei = roomStatus;
        this.ck = description;
        this.eb = variant;
        this.ed = autoMatchCriteria;
        this.dN = participants;
    }

    public static int m33a(Room room) {
        return C0137w.hashCode(room.getRoomId(), room.getCreatorId(), Long.valueOf(room.getCreationTimestamp()), Integer.valueOf(room.getStatus()), room.getDescription(), Integer.valueOf(room.getVariant()), room.getAutoMatchCriteria(), room.getParticipants());
    }

    public static boolean m34a(Room room, Object obj) {
        if (!(obj instanceof Room)) {
            return false;
        }
        if (room == obj) {
            return true;
        }
        Room room2 = (Room) obj;
        return C0137w.m376a(room2.getRoomId(), room.getRoomId()) && C0137w.m376a(room2.getCreatorId(), room.getCreatorId()) && C0137w.m376a(Long.valueOf(room2.getCreationTimestamp()), Long.valueOf(room.getCreationTimestamp())) && C0137w.m376a(Integer.valueOf(room2.getStatus()), Integer.valueOf(room.getStatus())) && C0137w.m376a(room2.getDescription(), room.getDescription()) && C0137w.m376a(Integer.valueOf(room2.getVariant()), Integer.valueOf(room.getVariant())) && C0137w.m376a(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && C0137w.m376a(room2.getParticipants(), room.getParticipants());
    }

    public static String m35b(Room room) {
        return C0137w.m377c(room).m375a("RoomId", room.getRoomId()).m375a("CreatorId", room.getCreatorId()).m375a("CreationTimestamp", Long.valueOf(room.getCreationTimestamp())).m375a("RoomStatus", Integer.valueOf(room.getStatus())).m375a("Description", room.getDescription()).m375a("Variant", Integer.valueOf(room.getVariant())).m375a("AutoMatchCriteria", room.getAutoMatchCriteria()).m375a("Participants", room.getParticipants()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return m34a(this, obj);
    }

    public Room freeze() {
        return this;
    }

    public Bundle getAutoMatchCriteria() {
        return this.ed;
    }

    public long getCreationTimestamp() {
        return this.dK;
    }

    public String getCreatorId() {
        return this.eh;
    }

    public String getDescription() {
        return this.ck;
    }

    public ArrayList<Participant> getParticipants() {
        return this.dN;
    }

    public String getRoomId() {
        return this.cY;
    }

    public int getStatus() {
        return this.ei;
    }

    public int getVariant() {
        return this.eb;
    }

    public int hashCode() {
        return m33a(this);
    }

    public String toString() {
        return m35b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cY);
        dest.writeString(this.eh);
        dest.writeLong(this.dK);
        dest.writeInt(this.ei);
        dest.writeString(this.ck);
        dest.writeInt(this.eb);
        dest.writeBundle(this.ed);
        int size = this.dN.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            ((Participant) this.dN.get(i)).writeToParcel(dest, flags);
        }
    }
}
