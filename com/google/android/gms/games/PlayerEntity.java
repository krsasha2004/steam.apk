package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.C0134n;
import com.google.android.gms.internal.C0137w;

public final class PlayerEntity implements Player {
    public static final Creator<PlayerEntity> CREATOR;
    private final String bp;
    private final long cA;
    private final Uri cm;
    private final Uri cn;
    private final String cz;

    /* renamed from: com.google.android.gms.games.PlayerEntity.1 */
    static class C01031 implements Creator<PlayerEntity> {
        C01031() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return m14o(x0);
        }

        public /* synthetic */ Object[] newArray(int x0) {
            return m15w(x0);
        }

        public PlayerEntity m14o(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            return new PlayerEntity(readString2, readString3 == null ? null : Uri.parse(readString3), readString4 == null ? null : Uri.parse(readString4), parcel.readLong(), null);
        }

        public PlayerEntity[] m15w(int i) {
            return new PlayerEntity[i];
        }
    }

    static {
        CREATOR = new C01031();
    }

    public PlayerEntity(Player player) {
        this.cz = player.getPlayerId();
        this.bp = player.getDisplayName();
        this.cm = player.getIconImageUri();
        this.cn = player.getHiResImageUri();
        this.cA = player.getRetrievedTimestamp();
        C0134n.m374b(this.cz);
        C0134n.m374b(this.bp);
        C0134n.m373a(this.cA > 0);
    }

    private PlayerEntity(String playerId, String displayName, Uri iconImageUri, Uri hiResImageUri, long retrievedTimestamp) {
        this.cz = playerId;
        this.bp = displayName;
        this.cm = iconImageUri;
        this.cn = hiResImageUri;
        this.cA = retrievedTimestamp;
    }

    public static int m16a(Player player) {
        return C0137w.hashCode(player.getPlayerId(), player.getDisplayName(), player.getIconImageUri(), player.getHiResImageUri(), Long.valueOf(player.getRetrievedTimestamp()));
    }

    public static boolean m17a(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return C0137w.m376a(player2.getPlayerId(), player.getPlayerId()) && C0137w.m376a(player2.getDisplayName(), player.getDisplayName()) && C0137w.m376a(player2.getIconImageUri(), player.getIconImageUri()) && C0137w.m376a(player2.getHiResImageUri(), player.getHiResImageUri()) && C0137w.m376a(Long.valueOf(player2.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp()));
    }

    public static String m18b(Player player) {
        return C0137w.m377c(player).m375a("PlayerId", player.getPlayerId()).m375a("DisplayName", player.getDisplayName()).m375a("IconImageUri", player.getIconImageUri()).m375a("HiResImageUri", player.getHiResImageUri()).m375a("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return m17a(this, obj);
    }

    public Player freeze() {
        return this;
    }

    public String getDisplayName() {
        return this.bp;
    }

    public Uri getHiResImageUri() {
        return this.cn;
    }

    public Uri getIconImageUri() {
        return this.cm;
    }

    public String getPlayerId() {
        return this.cz;
    }

    public long getRetrievedTimestamp() {
        return this.cA;
    }

    public int hashCode() {
        return m16a(this);
    }

    public String toString() {
        return m18b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        String str = null;
        dest.writeString(this.cz);
        dest.writeString(this.bp);
        dest.writeString(this.cm == null ? null : this.cm.toString());
        if (this.cn != null) {
            str = this.cn.toString();
        }
        dest.writeString(str);
        dest.writeLong(this.cA);
    }
}
