package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.internal.ae;
import java.util.List;

public class ActivityRecognitionResult implements ae {
    public static final ActivityRecognitionResultCreator CREATOR;
    int f48T;
    List<DetectedActivity> er;
    long es;
    long et;

    static {
        CREATOR = new ActivityRecognitionResultCreator();
    }

    public ActivityRecognitionResult() {
        this.f48T = 1;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "ActivityRecognitionResult [probableActivities=" + this.er + ", timeMillis=" + this.es + ", elapsedRealtimeMillis=" + this.et + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        ActivityRecognitionResultCreator.m384a(this, out, flags);
    }
}
