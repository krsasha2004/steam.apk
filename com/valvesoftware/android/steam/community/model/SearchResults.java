package com.valvesoftware.android.steam.community.model;

import java.util.ArrayList;
import java.util.Collection;

public class SearchResults {
    private final ArrayList<String> ids;
    public int total;

    public SearchResults() {
        this.ids = new ArrayList();
    }

    public void addSteamId(String steamId) {
        this.ids.add(steamId);
    }

    public Collection<String> getResultIds() {
        return (ArrayList) this.ids.clone();
    }

    public int getCurrentCount() {
        return this.ids.size();
    }
}
