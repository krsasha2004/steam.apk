package com.valvesoftware.android.steam.community;

import android.net.Uri;

public class SteamAppUri {
    public static final String CART;
    public static final String CONFIRMATION_WEB;
    public static final String STEAMGUARD_CHANGE;
    public static final String STEAMGUARD_HELP;
    public static final String STEAMGUARD_PRECHANGE;
    public static final String STEAMGUARD_RCODE;
    public static final String STEAM_NEWS;
    public static final String STEAM_NOTIFICATIONS_SETTINGS;
    public static final String URL_CURRENT_USER_PROFILE_BASE_CUSTOMURL;
    public static final String URL_CURRENT_USER_PROFILE_BASE_GENERIC;
    public static final String URL_CURRENT_USER_PROFILE_BASE_STEAMID;

    static {
        URL_CURRENT_USER_PROFILE_BASE_GENERIC = Config.URL_COMMUNITY_BASE + "/my";
        URL_CURRENT_USER_PROFILE_BASE_STEAMID = Config.URL_COMMUNITY_BASE_INSECURE + "/profiles";
        URL_CURRENT_USER_PROFILE_BASE_CUSTOMURL = Config.URL_COMMUNITY_BASE_INSECURE + "/id";
        CART = Config.URL_STORE_BASE_INSECURE + "/cart/";
        STEAM_NEWS = Config.URL_STORE_BASE_INSECURE + "/news/";
        STEAMGUARD_HELP = Config.URL_COMMUNITY_BASE + "/steamguard/help";
        STEAMGUARD_CHANGE = Config.URL_COMMUNITY_BASE + "/steamguard/change";
        STEAMGUARD_RCODE = Config.URL_COMMUNITY_BASE + "/steamguard/twofactor_recoverycode?countdown=0";
        STEAMGUARD_PRECHANGE = Config.URL_COMMUNITY_BASE + "/steamguard/prechange";
        CONFIRMATION_WEB = Config.URL_COMMUNITY_BASE + "/mobileconf/conf";
        STEAM_NOTIFICATIONS_SETTINGS = Config.URL_COMMUNITY_BASE_INSECURE + "/mobilesettings/GetManifest/v0001";
    }

    public static Uri createChatUri(String steamId) {
        return Uri.parse("steammobile://chat?steamid=" + steamId);
    }

    public static Uri createCurrentUserProfileUri(String path) {
        if (LoggedInUserAccountInfo.isLoggedIn()) {
            return createSteamAppWebUri(URL_CURRENT_USER_PROFILE_BASE_STEAMID + "/" + LoggedInUserAccountInfo.getLoginSteamID() + path);
        }
        return createSteamAppWebUri(URL_CURRENT_USER_PROFILE_BASE_GENERIC + path);
    }

    public static Uri createFriendsSearchUri(String query) {
        return createSearchUri(query, "friends");
    }

    public static Uri createGroupsSearchUri(String query) {
        return createSearchUri(query, "groups");
    }

    public static Uri library() {
        return createCurrentUserProfileUri("/games/?tab=all");
    }

    public static Uri settings() {
        return createUri("steammobile://", "appsettings");
    }

    private static Uri createSearchUri(String query, String resourceString) {
        return Uri.parse("steammobile://" + resourceString + "?" + "search" + "=" + query);
    }

    public static Uri groupWebPage(String groupProfileUrl) {
        return createSteamAppWebUri(Config.URL_COMMUNITY_BASE_INSECURE + groupProfileUrl);
    }

    public static Uri wishlist() {
        return createCurrentUserProfileUri("/wishlist/");
    }

    public static Uri shoppingCart() {
        return createSteamAppWebUri(CART);
    }

    public static Uri steamNews() {
        return createSteamAppWebUri(STEAM_NEWS);
    }

    public static Uri friendActivity() {
        return createCurrentUserProfileUri("/home/");
    }

    public static Uri createVisitProfileUri(String steamId) {
        return createSteamAppWebUri(Config.URL_COMMUNITY_BASE_INSECURE + "/profiles/" + steamId);
    }

    public static Uri createSteamAppWebUri(String url) {
        return Uri.parse("steammobile://openurl?url=" + url);
    }

    public static Uri searchSteam() {
        return createSteamAppWebUri(Config.URL_STORE_BASE_INSECURE + "/search/");
    }

    public static Uri catalog() {
        return createSteamAppWebUri(Config.URL_STORE_BASE_INSECURE);
    }

    public static Uri accountDetails() {
        return createSteamAppWebUri(Config.URL_STORE_BASE + "/account/");
    }

    public static Uri steamGuard() {
        return createUri("steammobile://", "steamguard");
    }

    public static Uri friendsList() {
        return createUri("steammobile://", "friends");
    }

    public static Uri groupsList() {
        return createUri("steammobile://", "groups");
    }

    public static Uri confirmationResource() {
        return createUri("steammobile://", "confirmation");
    }

    private static Uri createUri(String scheme, String resource) {
        return Uri.parse(scheme + resource);
    }

    public static Uri login() {
        return createUri("steammobile://", "login");
    }

    public static Uri deleteNotification() {
        return createUri("steammobile://", "deletenotification");
    }

    public static Uri notificationComments() {
        return createCurrentUserProfileUri("/commentnotifications");
    }

    public static Uri notificationItems() {
        return createCurrentUserProfileUri("/inventory");
    }

    public static Uri notificationInvites() {
        return createCurrentUserProfileUri("/home/invites");
    }

    public static Uri notificationGifts() {
        return createCurrentUserProfileUri("/inventory#pending_gifts");
    }

    public static Uri notificationTradeOffers() {
        return createCurrentUserProfileUri("/tradeoffers");
    }

    public static Uri notificationAsyncGame() {
        return createCurrentUserProfileUri("/gamenotifications");
    }

    public static Uri notificationModeratorMessage() {
        return createCurrentUserProfileUri("/moderatormessages");
    }

    public static String steamHelpUriPrefix() {
        return Config.URL_COMMUNITY_BASE + "/mobilelogin/help";
    }

    public static String steamSubscriberAgreementUriPrefix() {
        return Config.URL_STORE_BASE + "/mobilecheckout/ssapopup";
    }

    public static String steamPrivacyPolicyUriPrefix() {
        return Config.URL_STORE_BASE + "/mobilelogin/privacy_agreement";
    }
}
