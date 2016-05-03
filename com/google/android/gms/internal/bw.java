package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.Participant;
import java.util.ArrayList;

public final class bw extends C0117j implements Invitation {
    private final ArrayList<Participant> dN;
    private final Game dO;
    private final bx dP;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return InvitationEntity.m22a(this, obj);
    }

    public Invitation freeze() {
        return new InvitationEntity(this);
    }

    public long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    public Game getGame() {
        return this.dO;
    }

    public String getInvitationId() {
        return getString("external_invitation_id");
    }

    public int getInvitationType() {
        return getInteger("type");
    }

    public Participant getInviter() {
        return this.dP;
    }

    public ArrayList<Participant> getParticipants() {
        return this.dN;
    }

    public int hashCode() {
        return InvitationEntity.m21a(this);
    }

    public String toString() {
        return InvitationEntity.m23b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((InvitationEntity) freeze()).writeToParcel(dest, flags);
    }
}
