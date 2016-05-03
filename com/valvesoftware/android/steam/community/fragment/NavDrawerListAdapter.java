package com.valvesoftware.android.steam.community.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.valvesoftware.android.steam.community.C0151R;
import java.util.List;

public class NavDrawerListAdapter extends BaseExpandableListAdapter {
    private final Context context;
    private final LayoutInflater layoutInflater;
    private final List<NavDrawerItem> navigationItems;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.NavDrawerListAdapter.1 */
    class C02431 implements OnClickListener {
        final /* synthetic */ NavDrawerItem val$current;
        final /* synthetic */ int val$groupPosition;

        C02431(int i, NavDrawerItem navDrawerItem) {
            this.val$groupPosition = i;
            this.val$current = navDrawerItem;
        }

        public void onClick(View view) {
            String.format("position: %d ", new Object[]{Integer.valueOf(this.val$groupPosition)}) + NavDrawerListAdapter.this.context.getResources().getString(this.val$current.getNameId());
            this.val$current.onClick();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.NavDrawerListAdapter.2 */
    class C02442 implements OnClickListener {
        final /* synthetic */ NavDrawerItem val$current;

        C02442(NavDrawerItem navDrawerItem) {
            this.val$current = navDrawerItem;
        }

        public void onClick(View view) {
            this.val$current.onClick();
        }
    }

    public NavDrawerListAdapter(Context context, List<NavDrawerItem> navigationItems) {
        this.context = context;
        this.navigationItems = navigationItems;
        this.layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getGroupCount() {
        return this.navigationItems.size();
    }

    public int getChildrenCount(int groupPosition) {
        if (groupPosition >= this.navigationItems.size()) {
            return 0;
        }
        return ((NavDrawerItem) this.navigationItems.get(groupPosition)).getChildrenCount();
    }

    public NavDrawerItem getGroup(int groupPosition) {
        if (groupPosition >= this.navigationItems.size()) {
            return null;
        }
        return (NavDrawerItem) this.navigationItems.get(groupPosition);
    }

    public NavDrawerItem getChild(int groupPosition, int childPosition) {
        if (groupPosition >= this.navigationItems.size()) {
            return null;
        }
        NavDrawerItem group = (NavDrawerItem) this.navigationItems.get(groupPosition);
        if (group == null || childPosition >= group.getChildrenCount()) {
            return null;
        }
        return group.getChild(childPosition);
    }

    public long getGroupId(int groupPosition) {
        return (long) groupPosition;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return (long) childPosition;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        NavDrawerItem current = getGroup(groupPosition);
        if (v == null) {
            v = this.layoutInflater.inflate(C0151R.layout.nav_group_list_item, null);
        } else {
            NavDrawerItem old = (NavDrawerItem) v.getTag();
            if (old != null) {
                old.setView(null);
            }
            View navNotificationCtn = v.findViewById(C0151R.id.nav_item_notification_count_ctn);
            if (navNotificationCtn != null) {
                navNotificationCtn.setVisibility(8);
            }
        }
        v.setTag(current);
        current.setView(v);
        TextView navItemName = (TextView) v.findViewById(C0151R.id.nav_item_name);
        ImageView expandCollapseIcon = (ImageView) v.findViewById(C0151R.id.group_expand_collapse_icon);
        if (current.hasChildren()) {
            v.setClickable(false);
        } else {
            v.setOnClickListener(new C02431(groupPosition, current));
        }
        if (current.hasChildren()) {
            if (isExpanded) {
                expandCollapseIcon.setImageResource(C0151R.drawable.ic_action_expand_less);
            } else {
                expandCollapseIcon.setImageResource(C0151R.drawable.ic_action_expand_more);
            }
            expandCollapseIcon.setVisibility(0);
        } else {
            expandCollapseIcon.setVisibility(8);
        }
        navItemName.setText(current.getNameId());
        return v;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        NavDrawerItem current = getChild(groupPosition, childPosition);
        if (v == null) {
            v = this.layoutInflater.inflate(C0151R.layout.nav_list_item, null);
        } else {
            NavDrawerItem old = (NavDrawerItem) v.getTag();
            if (old != null) {
                old.setView(null);
            }
        }
        v.setTag(current);
        current.setView(v);
        TextView navItemName = (TextView) v.findViewById(C0151R.id.nav_item_name);
        v.setOnClickListener(new C02442(current));
        if (current.isHidden()) {
            v.setVisibility(8);
        }
        navItemName.setText(current.getNameId());
        if (current instanceof NavDrawerNotificationItem) {
            navItemName.append(" ");
            navItemName.append(Integer.valueOf(((NavDrawerNotificationItem) current).getNotificationCount()).toString());
        }
        return v;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
