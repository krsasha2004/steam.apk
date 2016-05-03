package com.valvesoftware.android.steam.community;

import java.util.Calendar;

public class DebugUtilRecord {
    public long id;
    public String key;
    public DebugUtilRecord parent;
    public long threadid;
    public Calendar timestamp;
    public String value;

    public DebugUtilRecord() {
        this.timestamp = Calendar.getInstance();
        this.threadid = Thread.currentThread().getId();
    }

    public long getId() {
        return this.id;
    }
}
