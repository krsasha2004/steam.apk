package com.valvesoftware.android.steam.community.activity;

import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;

/* compiled from: BaseActivity */
class CustomOnGroupExpandListener implements OnGroupExpandListener {
    private static int prevExpandedGroup;
    private final ExpandableListView expandableListView;

    public CustomOnGroupExpandListener(ExpandableListView expandableListView) {
        this.expandableListView = expandableListView;
    }

    static {
        prevExpandedGroup = -1;
    }

    public void onGroupExpand(int group) {
        if (!(prevExpandedGroup == -1 || prevExpandedGroup == group || this.expandableListView == null)) {
            this.expandableListView.collapseGroup(prevExpandedGroup);
        }
        prevExpandedGroup = group;
    }
}
