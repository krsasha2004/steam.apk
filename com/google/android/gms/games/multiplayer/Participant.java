package com.google.android.gms.games.multiplayer;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Player;

public interface Participant extends Parcelable, Freezable<Participant> {
    String getClientAddress();

    String getDisplayName();

    Uri getHiResImageUri();

    Uri getIconImageUri();

    String getParticipantId();

    Player getPlayer();

    int getStatus();

    boolean isConnectedToRoom();
}
