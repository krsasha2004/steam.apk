package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Game extends Parcelable, Freezable<Game> {
    int getAchievementTotalCount();

    String getApplicationId();

    String getDescription();

    String getDeveloperName();

    String getDisplayName();

    Uri getFeaturedImageUri();

    int getGameplayAclStatus();

    Uri getHiResImageUri();

    Uri getIconImageUri();

    String getInstancePackageName();

    int getLeaderboardCount();

    String getPrimaryCategory();

    String getSecondaryCategory();

    boolean isInstanceInstalled();

    boolean isPlayEnabledGame();
}
