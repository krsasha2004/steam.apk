package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;

public final class bg extends C0117j implements Player {
    public bg(C0132k c0132k, int i) {
        super(c0132k, i);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return PlayerEntity.m17a(this, obj);
    }

    public Player freeze() {
        return new PlayerEntity(this);
    }

    public String getDisplayName() {
        return getString("profile_name");
    }

    public Uri getHiResImageUri() {
        return m217c("profile_hi_res_image_uri");
    }

    public Uri getIconImageUri() {
        return m217c("profile_icon_image_uri");
    }

    public String getPlayerId() {
        return getString("external_player_id");
    }

    public long getRetrievedTimestamp() {
        return getLong("last_updated");
    }

    public int hashCode() {
        return PlayerEntity.m16a(this);
    }

    public String toString() {
        return PlayerEntity.m18b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((PlayerEntity) freeze()).writeToParcel(dest, flags);
    }
}
