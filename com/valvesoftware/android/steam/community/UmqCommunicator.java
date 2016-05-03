package com.valvesoftware.android.steam.community;

import android.os.Handler;
import com.valvesoftware.android.steam.community.jsontranslators.ActiveMessageSessionsTranslator;
import com.valvesoftware.android.steam.community.jsontranslators.UmqMessageHistoryTranslator;
import com.valvesoftware.android.steam.community.jsontranslators.UmqPollResultTranslator;
import com.valvesoftware.android.steam.community.model.MessageSession;
import com.valvesoftware.android.steam.community.model.PollStatus;
import com.valvesoftware.android.steam.community.model.UmqMessage;
import com.valvesoftware.android.steam.community.model.UmqPollResult;
import com.valvesoftware.android.steam.community.model.UserNotificationCounts;
import com.valvesoftware.android.steam.community.webrequests.Endpoints;
import com.valvesoftware.android.steam.community.webrequests.RequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.RequestErrorInfo;
import com.valvesoftware.android.steam.community.webrequests.ResponseListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

public class UmqCommunicator {
    private static UmqCommunicator instance;
    private boolean canSendTypingNotification;
    private ChatStateListener chatStateListener;
    private int consecutiveLoginAttemptsFailed;
    private final Handler enqueueStopHandler;
    private long lastMessageNumber;
    private final AtomicLong lastPollTime;
    private LoggedInStatusChangedListener loggedInStatusChangedListener;
    private NotificationCountUpdateListener notificationCountUpdateListener;
    final AtomicInteger numSwitchToPushRetries;
    private final AtomicBoolean pollInFlight;
    private final SteamCommunityApplication steamCommunityApplication;
    private boolean stopPolling;
    private final Handler uiThreadHandler;
    private String umqId;
    private final LocalDb umqdb;

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.10 */
    class AnonymousClass10 extends ResponseListener {
        final /* synthetic */ ResponseListener val$listener;

        AnonymousClass10(ResponseListener responseListener) {
            this.val$listener = responseListener;
        }

        public void onSuccess(JSONObject response) {
            if (this.val$listener != null) {
                this.val$listener.onSuccess(response);
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
            if (this.val$listener != null) {
                this.val$listener.onError(errorInfo);
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.11 */
    class AnonymousClass11 extends ResponseListener {
        final /* synthetic */ ResponseListener val$responseListener;

        AnonymousClass11(ResponseListener responseListener) {
            this.val$responseListener = responseListener;
        }

        public void onSuccess(JSONObject response) {
            if (this.val$responseListener != null) {
                this.val$responseListener.onSuccess(response);
            }
            UmqCommunicator.this.umqId = "0";
            if (UmqCommunicator.this.loggedInStatusChangedListener != null) {
                UmqCommunicator.this.loggedInStatusChangedListener.loggedOff();
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
            if (this.val$responseListener != null) {
                this.val$responseListener.onError(errorInfo);
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.1 */
    class C01651 implements Runnable {
        C01651() {
        }

        public void run() {
            UmqCommunicator.this.stopPolling = true;
            UmqCommunicator.this.switchToPush();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.2 */
    class C01682 implements Runnable {

        /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.2.1 */
        class C01671 extends ResponseListener {

            /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.2.1.1 */
            class C01661 extends ResponseListener {
                C01661() {
                }

                public void onSuccess(JSONObject response) {
                    UmqCommunicator.this.numSwitchToPushRetries.set(0);
                }

                public void onError(RequestErrorInfo errorInfo) {
                    if (UmqCommunicator.this.numSwitchToPushRetries.getAndIncrement() < 3) {
                        UmqCommunicator.this.switchToPush();
                    }
                }
            }

            C01671() {
            }

            public void onSuccess(JSONObject response) {
                RequestBuilder rb = Endpoints.getSwitchToPushRequestBuilder(UmqCommunicator.this.umqId);
                UmqCommunicator.this.numSwitchToPushRetries.set(0);
                rb.setResponseListener(new C01661());
                UmqCommunicator.this.steamCommunityApplication.sendRequest(rb);
                UmqCommunicator.this.umqId = "0";
            }

            public void onError(RequestErrorInfo errorInfo) {
                if (UmqCommunicator.this.numSwitchToPushRetries.getAndIncrement() < 3) {
                    UmqCommunicator.this.switchToPush();
                }
            }
        }

        C01682() {
        }

        public void run() {
            if (SteamCommunityApplication.GetInstance().GetSettingInfoDB().usePushInBackground()) {
                String regId = new GcmRegistrar().getStoredRegistrationId();
                if (regId != null || regId.length() != 0) {
                    UmqCommunicator.this.setPushInfoOnServer(true, new C01671());
                }
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.3 */
    class C01713 extends ResponseListener {
        final /* synthetic */ String val$chatPartnerSteamId;
        final /* synthetic */ UmqMessage val$message;

        /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.3.1 */
        class C01701 implements Runnable {
            final /* synthetic */ JSONObject val$response;

            /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.3.1.1 */
            class C01691 implements Runnable {
                C01691() {
                }

                public void run() {
                    UmqCommunicator.this.chatStateListener.messageSent(C01713.this.val$message);
                }
            }

            C01701(JSONObject jSONObject) {
                this.val$response = jSONObject;
            }

            public void run() {
                long timestamp = this.val$response.optLong("utc_timestamp", 0);
                C01713.this.val$message.utcTimeStamp = timestamp;
                UmqCommunicator.this.umqdb.saveSentMessage(C01713.this.val$message.text, timestamp, LoggedInUserAccountInfo.getLoginSteamID(), C01713.this.val$chatPartnerSteamId);
                if (UmqCommunicator.this.chatStateListener != null) {
                    UmqCommunicator.this.uiThreadHandler.post(new C01691());
                }
            }
        }

        C01713(UmqMessage umqMessage, String str) {
            this.val$message = umqMessage;
            this.val$chatPartnerSteamId = str;
        }

        public void onSuccess(JSONObject response) {
            UmqCommunicator.this.steamCommunityApplication.runOnBackgroundThread(new C01701(response));
        }

        public void onError(RequestErrorInfo errorInfo) {
            if (UmqCommunicator.this.chatStateListener != null) {
                UmqCommunicator.this.chatStateListener.messageSendFailed(this.val$message);
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.4 */
    class C01724 extends ResponseListener {
        C01724() {
        }

        public void onSuccess(JSONObject response) {
            UmqCommunicator.this.canSendTypingNotification = true;
        }

        public void onError(RequestErrorInfo errorInfo) {
            UmqCommunicator.this.canSendTypingNotification = true;
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.5 */
    class C01735 extends ResponseListener {
        C01735() {
        }

        public void onSuccess(JSONObject response) {
            for (MessageSession session : ActiveMessageSessionsTranslator.translateList(response)) {
                UmqCommunicator.this.updateChatMessages(session.steamId);
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.6 */
    class C01766 extends ResponseListener {
        final /* synthetic */ String val$chatPartnerSteamId;
        final /* synthetic */ boolean val$enterNewMessagesAsUnread;
        final /* synthetic */ CompleteCallback val$onCompleteCallback;

        /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.6.1 */
        class C01751 implements Runnable {
            final /* synthetic */ List val$umqMessages;

            /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.6.1.1 */
            class C01741 implements Runnable {
                C01741() {
                }

                public void run() {
                    UmqCommunicator.this.sendMessagesSavedNotification(C01751.this.val$umqMessages);
                }
            }

            C01751(List list) {
                this.val$umqMessages = list;
            }

            public void run() {
                long mostRecentDeletedMessageTime = UmqCommunicator.this.umqdb.getMostRecentDeletionTime(LoggedInUserAccountInfo.getLoginSteamID(), C01766.this.val$chatPartnerSteamId);
                Iterator<UmqMessage> msgIterator = this.val$umqMessages.iterator();
                while (msgIterator.hasNext()) {
                    if (((UmqMessage) msgIterator.next()).utcTimeStamp <= mostRecentDeletedMessageTime) {
                        msgIterator.remove();
                    }
                }
                if (this.val$umqMessages.size() > 0 && UmqCommunicator.this.umqdb.saveMessages(this.val$umqMessages, LoggedInUserAccountInfo.getLoginSteamID(), C01766.this.val$enterNewMessagesAsUnread) > 0) {
                    UmqCommunicator.this.uiThreadHandler.post(new C01741());
                }
            }
        }

        C01766(String str, CompleteCallback completeCallback, boolean z) {
            this.val$chatPartnerSteamId = str;
            this.val$onCompleteCallback = completeCallback;
            this.val$enterNewMessagesAsUnread = z;
        }

        public void onSuccess(JSONObject response) {
            List<UmqMessage> umqMessages = UmqMessageHistoryTranslator.translateList(response, this.val$chatPartnerSteamId);
            if (umqMessages != null && umqMessages.size() != 0) {
                SteamCommunityApplication.GetInstance().runOnBackgroundThread(new C01751(umqMessages));
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.7 */
    class C01777 extends ResponseListener {
        C01777() {
        }

        public void onSuccess(JSONObject response) {
            UmqPollResult umqPollResult = UmqPollResultTranslator.translate(response);
            UmqCommunicator.this.pollInFlight.set(false);
            if (umqPollResult.statusCode == PollStatus.NOT_LOGGED_ON) {
                if (!UmqCommunicator.this.stopPolling) {
                    UmqCommunicator.this.loginToUmq();
                }
            } else if (umqPollResult.statusCode == PollStatus.TIMEOUT) {
                UmqCommunicator.this.pollUmqStatus();
            } else {
                UmqCommunicator.this.lastMessageNumber = umqPollResult.lastMessageNumber;
                if (umqPollResult.containsMessageText()) {
                    UmqCommunicator.this.pollUmqForMessageContents();
                    return;
                }
                UmqCommunicator.this.sendRefreshSteamIdsNotification(umqPollResult.steamIdsWithPersonaStateChange());
                UmqCommunicator.this.sendRelationshipChangeNotification(umqPollResult.steamIdsWithRelationshipChanges());
                if (umqPollResult.containsIsTypingNotification()) {
                    UmqCommunicator.this.sendIsTypingNotification(umqPollResult.getTypingNotificationMessages());
                }
                if (umqPollResult.containsNotificationCountUpdate()) {
                    UmqCommunicator.this.sendNotificationCountsUpdate(umqPollResult.getNotificationCountMessage().notificationCounts);
                }
                if (!UmqCommunicator.this.stopPolling) {
                    UmqCommunicator.this.pollUmqStatus();
                }
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
            UmqCommunicator.this.pollInFlight.set(false);
            UmqCommunicator.this.loginToUmq();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.8 */
    class C01808 extends ResponseListener {

        /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.8.1 */
        class C01791 implements Runnable {
            final /* synthetic */ List val$umqMessages;

            /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.8.1.1 */
            class C01781 implements Runnable {
                C01781() {
                }

                public void run() {
                    UmqCommunicator.this.sendMessagesSavedNotification(C01791.this.val$umqMessages);
                    for (UmqMessage message : C01791.this.val$umqMessages) {
                        UmqCommunicator.this.sendNoticesAsNeeded(message.chatPartnerSteamId, message.text);
                    }
                }
            }

            C01791(List list) {
                this.val$umqMessages = list;
            }

            public void run() {
                UmqCommunicator.this.umqdb.saveMessages(this.val$umqMessages, LoggedInUserAccountInfo.getLoginSteamID(), true);
                UmqCommunicator.this.uiThreadHandler.post(new C01781());
            }
        }

        C01808() {
        }

        public void onSuccess(JSONObject response) {
            UmqPollResult umqPollResult = UmqPollResultTranslator.translate(response);
            if (umqPollResult.statusCode != PollStatus.NOT_LOGGED_ON) {
                UmqCommunicator.this.lastMessageNumber = umqPollResult.lastMessageNumber;
                if (umqPollResult.containsMessageText()) {
                    UmqCommunicator.this.steamCommunityApplication.runOnBackgroundThread(new C01791(umqPollResult.getAllMessagesWithText()));
                }
                UmqCommunicator.this.pollUmqStatus();
            } else if (!UmqCommunicator.this.stopPolling) {
                UmqCommunicator.this.loginToUmq();
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
            UmqCommunicator.this.pollUmqStatus();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.9 */
    class C01829 extends ResponseListener {
        final /* synthetic */ ResponseListener val$listener;

        /* renamed from: com.valvesoftware.android.steam.community.UmqCommunicator.9.1 */
        class C01811 implements Runnable {
            C01811() {
            }

            public void run() {
                UmqCommunicator.this.loginToUmq();
            }
        }

        C01829(ResponseListener responseListener) {
            this.val$listener = responseListener;
        }

        public void onSuccess(JSONObject response) {
            UmqCommunicator.this.consecutiveLoginAttemptsFailed = 0;
            UmqCommunicator.this.umqId = response.optString("umqid");
            if (response.optInt("push", -1) == 0) {
                UmqCommunicator.this.setServerPushStateBasedOnUserPreference();
            }
            UmqCommunicator.this.lastMessageNumber = response.optLong("message", -1);
            UmqCommunicator.this.pollUmqStatus();
            if (this.val$listener != null) {
                this.val$listener.onSuccess(response);
            }
            if (UmqCommunicator.this.loggedInStatusChangedListener != null) {
                UmqCommunicator.this.loggedInStatusChangedListener.loggedIn();
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
            if (UmqCommunicator.this.loggedInStatusChangedListener != null && UmqCommunicator.this.consecutiveLoginAttemptsFailed > 3) {
                UmqCommunicator.this.loggedInStatusChangedListener.loggedOff();
            }
            UmqCommunicator.this.consecutiveLoginAttemptsFailed = UmqCommunicator.this.consecutiveLoginAttemptsFailed + 1;
            new Handler().postDelayed(new C01811(), 2000);
        }
    }

    private UmqCommunicator() {
        this.canSendTypingNotification = true;
        this.stopPolling = true;
        this.pollInFlight = new AtomicBoolean(false);
        this.lastPollTime = new AtomicLong(0);
        this.enqueueStopHandler = new Handler();
        this.uiThreadHandler = new Handler();
        this.numSwitchToPushRetries = new AtomicInteger(0);
        this.consecutiveLoginAttemptsFailed = 0;
        this.steamCommunityApplication = SteamCommunityApplication.GetInstance();
        this.umqdb = new LocalDb(this.steamCommunityApplication.getApplicationContext());
    }

    public static UmqCommunicator getInstance() {
        if (instance == null) {
            synchronized (UmqCommunicator.class) {
                instance = new UmqCommunicator();
            }
        }
        return instance;
    }

    public void stop() {
        stop(60000);
    }

    public void stopImmediate() {
        stop(0);
    }

    public void stop(int delayBeforeStop) {
        if (delayBeforeStop == 0) {
            this.stopPolling = true;
        }
        this.enqueueStopHandler.postDelayed(new C01651(), (long) delayBeforeStop);
    }

    private void switchToPush() {
        new Handler().postDelayed(new C01682(), (long) (this.numSwitchToPushRetries.get() * 2000));
    }

    public void start() {
        this.enqueueStopHandler.removeCallbacksAndMessages(null);
        if (this.stopPolling || !this.pollInFlight.get()) {
            this.stopPolling = false;
            pollUmqStatus();
        }
    }

    public boolean isRunning() {
        return !this.stopPolling;
    }

    public void sendMessage(UmqMessage message, String chatPartnerSteamId) {
        if (message != null && !message.isEmpty()) {
            RequestBuilder requestBuilder = Endpoints.getSendUMQMessageRequestBuilder(message.text, chatPartnerSteamId, this.umqId);
            requestBuilder.setResponseListener(new C01713(message, chatPartnerSteamId));
            this.steamCommunityApplication.sendRequest(requestBuilder);
        }
    }

    public void sendTypingNotification(String chatPartnerSteamId) {
        if (this.canSendTypingNotification) {
            this.canSendTypingNotification = false;
            RequestBuilder requestBuilder = Endpoints.getSendUMQTypingNotificationRequestBuilder(chatPartnerSteamId, this.umqId);
            requestBuilder.setResponseListener(new C01724());
            this.steamCommunityApplication.sendRequest(requestBuilder);
        }
    }

    public void updateOfflineChats() {
        RequestBuilder requestBuilder = Endpoints.getActiveMessageSessions();
        requestBuilder.setResponseListener(new C01735());
        this.steamCommunityApplication.sendRequest(requestBuilder);
    }

    public void setChatLoggedInStatusChangedListener(LoggedInStatusChangedListener loggedInStatusChangedListener) {
        this.loggedInStatusChangedListener = loggedInStatusChangedListener;
    }

    public void setChatStateListener(ChatStateListener chatStateListener) {
        this.chatStateListener = chatStateListener;
    }

    public void setNotificationCountUpdateListener(NotificationCountUpdateListener notificationCountUpdateListener) {
        this.notificationCountUpdateListener = notificationCountUpdateListener;
    }

    public void updateChatMessages(String chatPartnerSteamId) {
        updateChatMessages(chatPartnerSteamId, false, null);
    }

    public void updateChatMessages(String chatPartnerSteamId, boolean enterNewMessagesAsUnread, CompleteCallback onCompleteCallback) {
        RequestBuilder requestBuilder = Endpoints.getRecentMessages(LoggedInUserAccountInfo.getLoginSteamID(), chatPartnerSteamId);
        requestBuilder.setResponseListener(new C01766(chatPartnerSteamId, onCompleteCallback, enterNewMessagesAsUnread));
        this.steamCommunityApplication.sendRequest(requestBuilder);
    }

    private void pollUmqStatus() {
        if (!this.stopPolling) {
            if (this.umqId == null) {
                loginToUmq();
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!this.pollInFlight.get() || currentTimeMillis - this.lastPollTime.get() >= 30000) {
                this.lastPollTime.set(System.currentTimeMillis());
                this.pollInFlight.set(true);
                RequestBuilder requestBuilder = Endpoints.getUMQPollStatusRequestBuilder(LoggedInUserAccountInfo.getLoginSteamID(), this.umqId, this.lastMessageNumber);
                requestBuilder.setResponseListener(new C01777());
                this.steamCommunityApplication.sendRequest(requestBuilder);
            }
        }
    }

    private void sendRefreshSteamIdsNotification(List<String> steamIds) {
        if (steamIds != null && steamIds.size() != 0 && this.chatStateListener != null) {
            this.chatStateListener.personaStateChanged(steamIds);
        }
    }

    private void sendRelationshipChangeNotification(List<String> steamIds) {
        if (steamIds != null && steamIds.size() != 0 && this.chatStateListener != null) {
            this.chatStateListener.relationshipStateChanged(steamIds);
        }
    }

    private void sendNotificationCountsUpdate(UserNotificationCounts notificationCounts) {
        if (this.notificationCountUpdateListener != null) {
            this.notificationCountUpdateListener.notificationCountsChanged(notificationCounts);
        }
    }

    private void pollUmqForMessageContents() {
        if (this.umqId == null) {
            pollUmqStatus();
            return;
        }
        RequestBuilder requestBuilder = Endpoints.getUMQPollForMessageRequestBuilder(LoggedInUserAccountInfo.getLoginSteamID(), this.umqId, this.lastMessageNumber);
        requestBuilder.setResponseListener(new C01808());
        this.steamCommunityApplication.sendRequest(requestBuilder);
    }

    public void loginToUmq() {
        loginToUmq(null);
    }

    public void loginToUmq(ResponseListener listener) {
        RequestBuilder requestBuilder = Endpoints.getUMQLogonRequestBuilder();
        requestBuilder.setResponseListener(new C01829(listener));
        this.steamCommunityApplication.sendRequest(requestBuilder);
    }

    public void setServerPushStateBasedOnUserPreference() {
        setPushInfoOnServer(this.steamCommunityApplication.GetSettingInfoDB().usePushInBackground(), null);
    }

    private void setPushInfoOnServer(boolean pushOn, ResponseListener listener) {
        String regId = new GcmRegistrar().getStoredRegistrationId();
        if (regId != null && regId.length() != 0) {
            RequestBuilder requestBuilder = Endpoints.getSendServerPushInfoRequestBuilder(regId, pushOn, this.umqId);
            requestBuilder.setResponseListener(new AnonymousClass10(listener));
            this.steamCommunityApplication.sendRequest(requestBuilder);
        }
    }

    public void signOutOfAppCompletely() {
        this.stopPolling = true;
        setPushInfoOnServer(false, null);
    }

    public void logOffFromUmq(ResponseListener responseListener) {
        stopImmediate();
        RequestBuilder requestBuilder = Endpoints.getUMQLogoffRequestBuilder(this.umqId != null ? this.umqId : "0");
        requestBuilder.setResponseListener(new AnonymousClass11(responseListener));
        this.steamCommunityApplication.sendRequest(requestBuilder);
    }

    public boolean isLoggedInToChat() {
        return (this.umqId == null || this.umqId.equals("0")) ? false : true;
    }

    private void sendMessagesSavedNotification(List<UmqMessage> umqMessages) {
        if (this.chatStateListener != null) {
            this.chatStateListener.newMessagesSaved(umqMessages);
        }
    }

    private void sendIsTypingNotification(List<UmqMessage> isTypingMessages) {
        if (this.chatStateListener != null) {
            this.chatStateListener.isTypingMessageReceived(isTypingMessages);
        }
    }

    public void sendNoticesAsNeeded(String chatPartnerSteamId, String messageText) {
        boolean sendSystemTrayNotification = true;
        if (SteamCommunityApplication.isInForeground && this.chatStateListener != null && (this.chatStateListener.listenerWillHandleAllVisualChatNotifications() || this.chatStateListener.listenerWillHandleVisualChatNotificationForSteamId(chatPartnerSteamId))) {
            sendSystemTrayNotification = false;
        }
        String fromPersonaName = "";
        if (this.umqdb != null) {
            fromPersonaName = this.umqdb.getPersonaNameForSteamId(chatPartnerSteamId);
        }
        if (sendSystemTrayNotification) {
            NotificationSender.getInstance().sendChatNotification(messageText, fromPersonaName, false);
        } else {
            NotificationSender.getInstance().ringOrVibrateAsNeededForChat(fromPersonaName);
        }
    }
}
