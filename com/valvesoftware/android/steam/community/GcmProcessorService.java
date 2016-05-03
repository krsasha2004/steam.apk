package com.valvesoftware.android.steam.community;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmProcessorService extends IntentService {
    private SettingInfoDB settingInfoDB;

    /* renamed from: com.valvesoftware.android.steam.community.GcmProcessorService.1 */
    class C01411 implements Runnable {
        final /* synthetic */ String val$strAlert;

        C01411(String str) {
            this.val$strAlert = str;
        }

        public void run() {
            if (!SteamCommunityApplication.mMainActivity.refreshConfirmationsPageIfActive()) {
                NotificationSender.getInstance().sendConfirmationNotification(this.val$strAlert);
            }
        }
    }

    public GcmProcessorService() {
        super(GcmProcessorService.class.getName());
        this.settingInfoDB = SteamCommunityApplication.GetInstance().GetSettingInfoDB();
    }

    protected void onHandleIntent(Intent intent) {
        NotificationSender notificationSender = NotificationSender.getInstance();
        if (!(intent == null || intent.getExtras() == null)) {
            if (intent.getExtras().containsKey("registration_id")) {
                new GcmRegistrar().storeRegistrationIdAndSendToServer(intent.getExtras().getString("registration_id"));
            } else {
                String messageType = intent.getStringExtra("type");
                if (messageType != null && messageType.equals("tfpr")) {
                    long timestampSent = 0;
                    try {
                        timestampSent = Long.parseLong(intent.getStringExtra("timestamp"));
                    } catch (Exception e) {
                    }
                    if (System.currentTimeMillis() / 1000 <= 30 + timestampSent) {
                        notificationSender.sendTwoFactorPromptNotification(intent.getStringExtra("gid"));
                    }
                } else if (LoggedInUserAccountInfo.isLoggedIn() && messageType != null) {
                    if (messageType.equals("chat")) {
                        if (this.settingInfoDB.usePushInBackground() && !UmqCommunicator.getInstance().isRunning()) {
                            String stringExtra = intent.getStringExtra("message");
                            String stringExtra2 = intent.getStringExtra("messageFrom");
                            boolean z = intent.getStringExtra("messageIsTruncated") != null && intent.getStringExtra("messageIsTruncated").equals("true");
                            notificationSender.sendChatNotification(stringExtra, stringExtra2, z);
                        }
                    } else if (messageType.equals("wishlist")) {
                        notificationSender.sendWishlistNotification(intent.getStringExtra("title"), intent.getStringExtra("message"));
                    } else if (messageType.equals("promotion")) {
                        notificationSender.sendPromotionNotification(intent.getStringExtra("title"), intent.getStringExtra("message"));
                    } else if (messageType.equals("conf")) {
                        String strAlert = intent.getStringExtra("alert");
                        if (!SteamCommunityApplication.isInForeground || SteamCommunityApplication.mMainActivity == null) {
                            notificationSender.sendConfirmationNotification(strAlert);
                        } else {
                            SteamCommunityApplication.confirmationRefreshHandler.post(new C01411(strAlert));
                        }
                    } else if (messageType.equals("rmtf")) {
                        SteamguardState.handleTwoFactorRemovalNotification(intent.getStringExtra("gid"), intent.getStringExtra("scheme"));
                    }
                }
            }
        }
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
