package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class DetectedActivityCreator implements Creator<DetectedActivity> {
    static void m386a(DetectedActivity detectedActivity, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m94c(parcel, 1, detectedActivity.eu);
        ad.m94c(parcel, 1000, detectedActivity.f49T);
        ad.m94c(parcel, 2, detectedActivity.ev);
        ad.m75C(parcel, d);
    }

    public DetectedActivity createFromParcel(Parcel parcel) {
        DetectedActivity detectedActivity = new DetectedActivity();
        int c = ac.m46c(parcel);
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    detectedActivity.eu = ac.m51f(parcel, b);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    detectedActivity.ev = ac.m51f(parcel, b);
                    break;
                case 1000:
                    detectedActivity.f49T = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return detectedActivity;
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public DetectedActivity[] newArray(int size) {
        return new DetectedActivity[size];
    }
}
