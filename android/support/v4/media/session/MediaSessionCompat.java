package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class MediaSessionCompat {

    public static final class Token implements Parcelable {
        public static final Creator<Token> CREATOR;
        private final Parcelable mInner;

        /* renamed from: android.support.v4.media.session.MediaSessionCompat.Token.1 */
        static class C00191 implements Creator<Token> {
            C00191() {
            }

            public Token createFromParcel(Parcel in) {
                return new Token(in.readParcelable(null));
            }

            public Token[] newArray(int size) {
                return new Token[size];
            }
        }

        Token(Parcelable inner) {
            this.mInner = inner;
        }

        public int describeContents() {
            return this.mInner.describeContents();
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.mInner, flags);
        }

        static {
            CREATOR = new C00191();
        }
    }
}
