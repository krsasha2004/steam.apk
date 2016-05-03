package com.valvesoftware.android.steam.community.model;

import android.graphics.Bitmap;

public class Group {
    public Bitmap avatar;
    public GroupCategoryInList categoryInList;
    public int favoriteAppId;
    public String fullAvatarUrl;
    public String mediumAvatarUrl;
    public String name;
    public int numUsersOnline;
    public int numUsersTotal;
    public String profileUrl;
    public GroupRelationship relationship;
    public String smallAvatarUrl;
    public String steamId;
    public GroupType type;

    public Group() {
        this.type = GroupType.PRIVATE;
        this.categoryInList = GroupCategoryInList.PUBLIC;
        this.relationship = GroupRelationship.None;
        this.favoriteAppId = 0;
    }

    public boolean hasProfileUrl() {
        return this.profileUrl != null && this.profileUrl.length() > 0;
    }

    public void merge(Group detail) {
        this.numUsersOnline = detail.numUsersOnline;
        this.numUsersTotal = detail.numUsersTotal;
        this.profileUrl = detail.profileUrl;
        this.avatar = detail.avatar;
        this.name = detail.name;
        this.categoryInList = detail.categoryInList;
        this.smallAvatarUrl = detail.smallAvatarUrl;
        this.mediumAvatarUrl = detail.mediumAvatarUrl;
        this.relationship = detail.relationship == GroupRelationship.None ? this.relationship : detail.relationship;
        this.favoriteAppId = detail.favoriteAppId;
        this.type = detail.type;
        determineDisplayCategory();
    }

    private void determineDisplayCategory() {
        if (this.relationship == GroupRelationship.Invited) {
            this.categoryInList = GroupCategoryInList.REQUEST_INVITE;
        } else if (this.type == GroupType.OFFICIAL) {
            this.categoryInList = GroupCategoryInList.OFFICIAL;
        } else if (this.type == GroupType.PRIVATE) {
            this.categoryInList = GroupCategoryInList.PRIVATE;
        } else {
            this.categoryInList = GroupCategoryInList.PUBLIC;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        if (this.steamId != null) {
            if (this.steamId.equals(group.steamId)) {
                return true;
            }
        } else if (group.steamId == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.steamId != null ? this.steamId.hashCode() : 0;
    }

    public String toString() {
        return this.name != null ? this.name : "";
    }
}
