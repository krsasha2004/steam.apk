package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;

public final class bx extends C0117j implements Participant {
    private final bg dU;

    public bx(C0132k c0132k, int i) {
        super(c0132k, i);
        this.dU = new bg(c0132k, i);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return ParticipantEntity.m27a(this, obj);
    }

    public Participant freeze() {
        return new ParticipantEntity(this);
    }

    public String getClientAddress() {
        return getString("client_address");
    }

    public String getDisplayName() {
        return m218d("external_player_id") ? getString("default_display_name") : this.dU.getDisplayName();
    }

    public Uri getHiResImageUri() {
        return m218d("external_player_id") ? null : this.dU.getHiResImageUri();
    }

    public Uri getIconImageUri() {
        return m218d("external_player_id") ? m217c("default_display_image_uri") : this.dU.getIconImageUri();
    }

    public String getParticipantId() {
        return getString("external_participant_id");
    }

    public Player getPlayer() {
        return m218d("external_player_id") ? null : this.dU;
    }

    public int getStatus() {
        return getInteger("player_status");
    }

    public int hashCode() {
        return ParticipantEntity.m26a(this);
    }

    public boolean isConnectedToRoom() {
        return getInteger("connected") > 0;
    }

    public String toString() {
        return ParticipantEntity.m28b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((ParticipantEntity) freeze()).writeToParcel(dest, flags);
    }
}
