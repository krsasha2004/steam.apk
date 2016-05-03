package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Player extends Parcelable, Freezable<Player> {
    String getDisplayName();

    Uri getHiResImageUri();

    Uri getIconImageUri();

    String getPlayerId();

    long getRetrievedTimestamp();
}
