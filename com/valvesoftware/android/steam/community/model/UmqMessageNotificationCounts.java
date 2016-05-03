package com.valvesoftware.android.steam.community.model;

public class UmqMessageNotificationCounts extends UmqMessageBase {
    public final UserNotificationCounts notificationCounts;

    public UmqMessageNotificationCounts() {
        this.notificationCounts = new UserNotificationCounts();
    }
}
