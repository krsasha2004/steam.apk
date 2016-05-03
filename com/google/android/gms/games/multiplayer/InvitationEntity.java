package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.C0137w;
import com.google.android.gms.internal.C0138x;
import java.util.ArrayList;

public final class InvitationEntity implements Invitation {
    public static final Creator<InvitationEntity> CREATOR;
    private final GameEntity dI;
    private final String dJ;
    private final long dK;
    private final int dL;
    private final Participant dM;
    private final ArrayList<Participant> dN;

    /* renamed from: com.google.android.gms.games.multiplayer.InvitationEntity.1 */
    static class C01041 implements Creator<InvitationEntity> {
        C01041() {
        }

        public InvitationEntity[] m19D(int i) {
            return new InvitationEntity[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m20p(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m19D(x0);
        }

        public InvitationEntity m20p(Parcel parcel) {
            GameEntity gameEntity = (GameEntity) GameEntity.CREATOR.createFromParcel(parcel);
            String readString = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            Participant participant = (Participant) ParticipantEntity.CREATOR.createFromParcel(parcel);
            int readInt2 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i = 0; i < readInt2; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(readString, readLong, readInt, participant, arrayList, null);
        }
    }

    static {
        CREATOR = new C01041();
    }

    private InvitationEntity(GameEntity game, String invitationId, long creationTimestamp, int invitationType, Participant inviter, ArrayList<Participant> participants) {
        this.dI = game;
        this.dJ = invitationId;
        this.dK = creationTimestamp;
        this.dL = invitationType;
        this.dM = inviter;
        this.dN = participants;
    }

    public InvitationEntity(Invitation invitation) {
        this.dI = new GameEntity(invitation.getGame());
        this.dJ = invitation.getInvitationId();
        this.dK = invitation.getCreationTimestamp();
        this.dL = invitation.getInvitationType();
        String participantId = invitation.getInviter().getParticipantId();
        Object obj = null;
        ArrayList participants = invitation.getParticipants();
        int size = participants.size();
        this.dN = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Participant participant = (Participant) participants.get(i);
            if (participant.getParticipantId().equals(participantId)) {
                obj = participant;
            }
            this.dN.add(participant.freeze());
        }
        C0138x.m381b(obj, (Object) "Must have a valid inviter!");
        this.dM = (Participant) obj.freeze();
    }

    public static int m21a(Invitation invitation) {
        return C0137w.hashCode(invitation.getGame(), invitation.getInvitationId(), Long.valueOf(invitation.getCreationTimestamp()), Integer.valueOf(invitation.getInvitationType()), invitation.getInviter(), invitation.getParticipants());
    }

    public static boolean m22a(Invitation invitation, Object obj) {
        if (!(obj instanceof Invitation)) {
            return false;
        }
        if (invitation == obj) {
            return true;
        }
        Invitation invitation2 = (Invitation) obj;
        return C0137w.m376a(invitation2.getGame(), invitation.getGame()) && C0137w.m376a(invitation2.getInvitationId(), invitation.getInvitationId()) && C0137w.m376a(Long.valueOf(invitation2.getCreationTimestamp()), Long.valueOf(invitation.getCreationTimestamp())) && C0137w.m376a(Integer.valueOf(invitation2.getInvitationType()), Integer.valueOf(invitation.getInvitationType())) && C0137w.m376a(invitation2.getInviter(), invitation.getInviter()) && C0137w.m376a(invitation2.getParticipants(), invitation.getParticipants());
    }

    public static String m23b(Invitation invitation) {
        return C0137w.m377c(invitation).m375a("Game", invitation.getGame()).m375a("InvitationId", invitation.getInvitationId()).m375a("CreationTimestamp", Long.valueOf(invitation.getCreationTimestamp())).m375a("InvitationType", Integer.valueOf(invitation.getInvitationType())).m375a("Inviter", invitation.getInviter()).m375a("Participants", invitation.getParticipants()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return m22a(this, obj);
    }

    public Invitation freeze() {
        return this;
    }

    public long getCreationTimestamp() {
        return this.dK;
    }

    public Game getGame() {
        return this.dI;
    }

    public String getInvitationId() {
        return this.dJ;
    }

    public int getInvitationType() {
        return this.dL;
    }

    public Participant getInviter() {
        return this.dM;
    }

    public ArrayList<Participant> getParticipants() {
        return this.dN;
    }

    public int hashCode() {
        return m21a(this);
    }

    public String toString() {
        return m23b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        this.dI.writeToParcel(dest, flags);
        dest.writeString(this.dJ);
        dest.writeLong(this.dK);
        dest.writeInt(this.dL);
        this.dM.writeToParcel(dest, flags);
        int size = this.dN.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            ((Participant) this.dN.get(i)).writeToParcel(dest, flags);
        }
    }
}
