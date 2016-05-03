package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.ac;
import com.google.android.gms.internal.ac.C0109a;
import com.google.android.gms.internal.ad;
import com.valvesoftware.android.steam.community.C0151R;

public class ActivityRecognitionResultCreator implements Creator<ActivityRecognitionResult> {
    static void m384a(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int d = ad.m96d(parcel);
        ad.m93b(parcel, 1, activityRecognitionResult.er, false);
        ad.m94c(parcel, 1000, activityRecognitionResult.f48T);
        ad.m79a(parcel, 2, activityRecognitionResult.es);
        ad.m79a(parcel, 3, activityRecognitionResult.et);
        ad.m75C(parcel, d);
    }

    public ActivityRecognitionResult createFromParcel(Parcel parcel) {
        ActivityRecognitionResult activityRecognitionResult = new ActivityRecognitionResult();
        int c = ac.m46c(parcel);
        while (parcel.dataPosition() < c) {
            int b = ac.m43b(parcel);
            switch (ac.m56j(b)) {
                case C0151R.styleable.View_paddingStart /*1*/:
                    activityRecognitionResult.er = ac.m47c(parcel, b, DetectedActivity.CREATOR);
                    break;
                case C0151R.styleable.View_paddingEnd /*2*/:
                    activityRecognitionResult.es = ac.m52g(parcel, b);
                    break;
                case C0151R.styleable.Toolbar_subtitle /*3*/:
                    activityRecognitionResult.et = ac.m52g(parcel, b);
                    break;
                case 1000:
                    activityRecognitionResult.f48T = ac.m51f(parcel, b);
                    break;
                default:
                    ac.m44b(parcel, b);
                    break;
            }
        }
        if (parcel.dataPosition() == c) {
            return activityRecognitionResult;
        }
        throw new C0109a("Overread allowed size end=" + c, parcel);
    }

    public ActivityRecognitionResult[] newArray(int size) {
        return new ActivityRecognitionResult[size];
    }
}
