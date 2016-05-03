package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0137w;

public final class GameEntity implements Game {
    public static final Creator<GameEntity> CREATOR;
    private final String bp;
    private final String ch;
    private final String ci;
    private final String cj;
    private final String ck;
    private final String cl;
    private final Uri cm;
    private final Uri cn;
    private final Uri co;
    private final boolean cp;
    private final boolean cq;
    private final String cr;
    private final int cs;
    private final int ct;
    private final int cu;

    /* renamed from: com.google.android.gms.games.GameEntity.1 */
    static class C01021 implements Creator<GameEntity> {
        C01021() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m9n(x0);
        }

        public GameEntity m9n(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            Uri parse = readString7 == null ? null : Uri.parse(readString7);
            readString7 = parcel.readString();
            Uri parse2 = readString7 == null ? null : Uri.parse(readString7);
            readString7 = parcel.readString();
            return new GameEntity(readString2, readString3, readString4, readString5, readString6, parse, parse2, readString7 == null ? null : Uri.parse(readString7), parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), null);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m10v(x0);
        }

        public GameEntity[] m10v(int i) {
            return new GameEntity[i];
        }
    }

    static {
        CREATOR = new C01021();
    }

    public GameEntity(Game game) {
        this.ch = game.getApplicationId();
        this.ci = game.getPrimaryCategory();
        this.cj = game.getSecondaryCategory();
        this.ck = game.getDescription();
        this.cl = game.getDeveloperName();
        this.bp = game.getDisplayName();
        this.cm = game.getIconImageUri();
        this.cn = game.getHiResImageUri();
        this.co = game.getFeaturedImageUri();
        this.cp = game.isPlayEnabledGame();
        this.cq = game.isInstanceInstalled();
        this.cr = game.getInstancePackageName();
        this.cs = game.getGameplayAclStatus();
        this.ct = game.getAchievementTotalCount();
        this.cu = game.getLeaderboardCount();
    }

    private GameEntity(String applicationId, String displayName, String primaryCategory, String secondaryCategory, String description, String developerName, Uri iconImageUri, Uri hiResImageUri, Uri featuredImageUri, boolean playEnabledGame, boolean instanceInstalled, String instancePackageName, int gameplayAclStatus, int achievementTotalCount, int leaderboardCount) {
        this.ch = applicationId;
        this.bp = displayName;
        this.ci = primaryCategory;
        this.cj = secondaryCategory;
        this.ck = description;
        this.cl = developerName;
        this.cm = iconImageUri;
        this.cn = hiResImageUri;
        this.co = featuredImageUri;
        this.cp = playEnabledGame;
        this.cq = instanceInstalled;
        this.cr = instancePackageName;
        this.cs = gameplayAclStatus;
        this.ct = achievementTotalCount;
        this.cu = leaderboardCount;
    }

    public static int m11a(Game game) {
        return C0137w.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), Boolean.valueOf(game.isPlayEnabledGame()), Boolean.valueOf(game.isInstanceInstalled()), game.getInstancePackageName(), Integer.valueOf(game.getGameplayAclStatus()), Integer.valueOf(game.getAchievementTotalCount()), Integer.valueOf(game.getLeaderboardCount()));
    }

    public static boolean m12a(Game game, Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }
        if (game == obj) {
            return true;
        }
        Game game2 = (Game) obj;
        return C0137w.m376a(game2.getApplicationId(), game.getApplicationId()) && C0137w.m376a(game2.getDisplayName(), game.getDisplayName()) && C0137w.m376a(game2.getPrimaryCategory(), game.getPrimaryCategory()) && C0137w.m376a(game2.getSecondaryCategory(), game.getSecondaryCategory()) && C0137w.m376a(game2.getDescription(), game.getDescription()) && C0137w.m376a(game2.getDeveloperName(), game.getDeveloperName()) && C0137w.m376a(game2.getIconImageUri(), game.getIconImageUri()) && C0137w.m376a(game2.getHiResImageUri(), game.getHiResImageUri()) && C0137w.m376a(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && C0137w.m376a(Boolean.valueOf(game2.isPlayEnabledGame()), Boolean.valueOf(game.isPlayEnabledGame())) && C0137w.m376a(Boolean.valueOf(game2.isInstanceInstalled()), Boolean.valueOf(game.isInstanceInstalled())) && C0137w.m376a(game2.getInstancePackageName(), game.getInstancePackageName()) && C0137w.m376a(Integer.valueOf(game2.getGameplayAclStatus()), Integer.valueOf(game.getGameplayAclStatus())) && C0137w.m376a(Integer.valueOf(game2.getAchievementTotalCount()), Integer.valueOf(game.getAchievementTotalCount())) && C0137w.m376a(Integer.valueOf(game2.getLeaderboardCount()), Integer.valueOf(game.getLeaderboardCount()));
    }

    public static String m13b(Game game) {
        return C0137w.m377c(game).m375a("ApplicationId", game.getApplicationId()).m375a("DisplayName", game.getDisplayName()).m375a("PrimaryCategory", game.getPrimaryCategory()).m375a("SecondaryCategory", game.getSecondaryCategory()).m375a("Description", game.getDescription()).m375a("DeveloperName", game.getDeveloperName()).m375a("IconImageUri", game.getIconImageUri()).m375a("HiResImageUri", game.getHiResImageUri()).m375a("FeaturedImageUri", game.getFeaturedImageUri()).m375a("PlayEnabledGame", Boolean.valueOf(game.isPlayEnabledGame())).m375a("InstanceInstalled", Boolean.valueOf(game.isInstanceInstalled())).m375a("InstancePackageName", game.getInstancePackageName()).m375a("GameplayAclStatus", Integer.valueOf(game.getGameplayAclStatus())).m375a("AchievementTotalCount", Integer.valueOf(game.getAchievementTotalCount())).m375a("LeaderboardCount", Integer.valueOf(game.getLeaderboardCount())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return m12a(this, obj);
    }

    public Game freeze() {
        return this;
    }

    public int getAchievementTotalCount() {
        return this.ct;
    }

    public String getApplicationId() {
        return this.ch;
    }

    public String getDescription() {
        return this.ck;
    }

    public String getDeveloperName() {
        return this.cl;
    }

    public String getDisplayName() {
        return this.bp;
    }

    public Uri getFeaturedImageUri() {
        return this.co;
    }

    public int getGameplayAclStatus() {
        return this.cs;
    }

    public Uri getHiResImageUri() {
        return this.cn;
    }

    public Uri getIconImageUri() {
        return this.cm;
    }

    public String getInstancePackageName() {
        return this.cr;
    }

    public int getLeaderboardCount() {
        return this.cu;
    }

    public String getPrimaryCategory() {
        return this.ci;
    }

    public String getSecondaryCategory() {
        return this.cj;
    }

    public int hashCode() {
        return m11a(this);
    }

    public boolean isInstanceInstalled() {
        return this.cq;
    }

    public boolean isPlayEnabledGame() {
        return this.cp;
    }

    public String toString() {
        return m13b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        String str = null;
        dest.writeString(this.ch);
        dest.writeString(this.bp);
        dest.writeString(this.ci);
        dest.writeString(this.cj);
        dest.writeString(this.ck);
        dest.writeString(this.cl);
        dest.writeString(this.cm == null ? null : this.cm.toString());
        dest.writeString(this.cn == null ? null : this.cn.toString());
        if (this.co != null) {
            str = this.co.toString();
        }
        dest.writeString(str);
        dest.writeInt(this.cp ? 1 : 0);
        if (!this.cq) {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeString(this.cr);
        dest.writeInt(this.cs);
        dest.writeInt(this.ct);
        dest.writeInt(this.cu);
    }
}
