package com.valvesoftware.android.steam.community.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.valvesoftware.android.steam.community.AndroidUtils;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.SteamAppUri;
import com.valvesoftware.android.steam.community.SteamCommunityApplication;
import com.valvesoftware.android.steam.community.activity.MainActivity;
import com.valvesoftware.android.steam.community.model.Group;
import com.valvesoftware.android.steam.community.model.GroupCategoryInList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GroupsListAdapter extends BaseAdapter implements FilterableAdapter<Group> {
    private final OnClickListener dummyClickListener;
    private final boolean groupAndLabelByStatus;
    private GenericFilter groupInfoFilter;
    private OnClickListener groupListClickListener;
    private List<Group> groupsList;
    private String groupsSearchString;
    private Map<String, Group> idsToGroupsMap;
    private final ImageLoader imageLoader;
    private final Group searchItemInfo;
    private String searchString;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.GroupsListAdapter.1 */
    class C02381 implements OnClickListener {
        C02381() {
        }

        public void onClick(View v) {
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.GroupsListAdapter.2 */
    class C02392 implements OnClickListener {
        final /* synthetic */ FragmentActivity val$activity;

        C02392(FragmentActivity fragmentActivity) {
            this.val$activity = fragmentActivity;
        }

        public void onClick(View v) {
            String clickedSteamId = "0";
            GroupViewHolder viewHolder = (GroupViewHolder) v.getTag();
            if (viewHolder.group != null) {
                clickedSteamId = viewHolder.group.steamId;
            }
            if (clickedSteamId.equals("0")) {
                this.val$activity.startActivity(new Intent().setClass(this.val$activity, MainActivity.class).addFlags(536870912).addFlags(268435456).setData(SteamAppUri.createGroupsSearchUri(GroupsListAdapter.this.getSearchString())));
                return;
            }
            Group entry = viewHolder.group;
            if (entry != null) {
                this.val$activity.startActivity(new Intent().addFlags(402653184).setClass(this.val$activity, MainActivity.class).setData(SteamAppUri.groupWebPage(entry.profileUrl)).setAction("android.intent.action.VIEW"));
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.GroupsListAdapter.3 */
    class C02403 implements Comparator<Group> {
        C02403() {
        }

        public int compare(Group o1, Group o2) {
            if (o1.categoryInList != o2.categoryInList) {
                return o1.categoryInList.ordinal() < o2.categoryInList.ordinal() ? -1 : 1;
            } else {
                return o1.name.compareToIgnoreCase(o2.name);
            }
        }
    }

    private class GroupViewHolder {
        NetworkImageViewWithBackup avatarView;
        ImageView avatarViewFrame;
        Group group;
        TextView labelView;
        TextView lblMembersOnline;
        TextView lblMembersTotal;
        TextView nameView;

        private GroupViewHolder() {
        }
    }

    public GroupsListAdapter(List<Group> groups, FragmentActivity activity) {
        this(groups, activity, true);
    }

    public GroupsListAdapter(List<Group> groups, FragmentActivity activity, boolean groupAndLabelByStatus) {
        this.groupsSearchString = "";
        this.idsToGroupsMap = new HashMap();
        this.groupsList = new ArrayList(groups);
        this.imageLoader = SteamCommunityApplication.GetInstance().imageLoader;
        this.groupsSearchString = activity.getResources().getString(C0151R.string.Group_Search_All);
        this.groupAndLabelByStatus = groupAndLabelByStatus;
        for (Group g : groups) {
            this.idsToGroupsMap.put(g.steamId, g);
        }
        this.dummyClickListener = new C02381();
        this.groupListClickListener = new C02392(activity);
        this.searchItemInfo = createSearchItem();
    }

    public String getSearchString() {
        return this.searchString;
    }

    public void setSearchString(String newSearchString) {
        if (newSearchString != null && newSearchString.length() > 0 && this.groupInfoFilter == null) {
            this.groupInfoFilter = new GenericFilter(this.groupsList, this, this.searchItemInfo);
        }
        if (this.groupInfoFilter != null) {
            if (newSearchString == null || newSearchString.length() <= 0) {
                this.groupInfoFilter.removeFromOriginal(this.searchItemInfo);
            } else {
                this.groupInfoFilter.removeFromOriginal(this.searchItemInfo);
                this.groupInfoFilter.addToOriginal(this.searchItemInfo);
            }
            this.searchString = newSearchString;
            this.groupInfoFilter.filter(this.searchString);
        }
    }

    public void notifyDataSetChanged() {
        Collections.sort(this.groupsList, new C02403());
        super.notifyDataSetChanged();
    }

    public Filter getFilter() {
        return this.groupInfoFilter;
    }

    public int getCount() {
        if (this.groupsList == null) {
            return 0;
        }
        return this.groupsList.size();
    }

    public Group getItem(int position) {
        if (position >= getCount()) {
            return null;
        }
        return (Group) this.groupsList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View v, ViewGroup parent) {
        Group group = getItem(position);
        if (group == null) {
            return v;
        }
        GroupViewHolder viewHolder;
        Context appContext = SteamCommunityApplication.GetInstance();
        if (v == null) {
            v = ((LayoutInflater) appContext.getSystemService("layout_inflater")).inflate(C0151R.layout.group_list_item, null);
            viewHolder = new GroupViewHolder();
            viewHolder.labelView = (TextView) v.findViewById(C0151R.id.label);
            viewHolder.nameView = (TextView) v.findViewById(C0151R.id.name);
            viewHolder.lblMembersTotal = (TextView) v.findViewById(C0151R.id.groupMembersTotal);
            viewHolder.lblMembersOnline = (TextView) v.findViewById(C0151R.id.groupMembersOnline);
            viewHolder.avatarView = (NetworkImageViewWithBackup) v.findViewById(C0151R.id.avatar);
            viewHolder.avatarViewFrame = (ImageView) v.findViewById(C0151R.id.avatar_frame);
            v.setClickable(true);
            v.setOnClickListener(this.groupListClickListener);
            v.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) v.getTag();
        }
        viewHolder.group = group;
        if (!this.groupAndLabelByStatus || (position != 0 && group.categoryInList == getItem(position - 1).categoryInList)) {
            viewHolder.labelView.setVisibility(8);
        } else {
            viewHolder.labelView.setText(appContext.getResources().getString(group.categoryInList.getDisplayNumber()).toUpperCase(Locale.getDefault()));
            viewHolder.labelView.setVisibility(0);
        }
        viewHolder.labelView.setOnClickListener(this.dummyClickListener);
        AndroidUtils.setTextViewText(viewHolder.nameView, group.name);
        if (group != this.searchItemInfo) {
            viewHolder.avatarView.setVisibility(0);
            viewHolder.avatarView.setImageUrl(group.mediumAvatarUrl, group.smallAvatarUrl, this.imageLoader);
            viewHolder.avatarView.forceLayout();
            viewHolder.avatarViewFrame.setImageResource(C0151R.drawable.avatar_frame_offline);
            viewHolder.avatarViewFrame.setVisibility(0);
            viewHolder.lblMembersTotal.setText(Integer.toString(group.numUsersTotal) + " " + appContext.getResources().getString(C0151R.string.Group_Num_Members_Total));
            viewHolder.lblMembersOnline.setText(Integer.toString(group.numUsersOnline) + " " + appContext.getResources().getString(C0151R.string.Group_Num_Members_Online));
        } else {
            viewHolder.avatarViewFrame.setImageResource(C0151R.drawable.icon_search);
            viewHolder.avatarView.setVisibility(4);
            AndroidUtils.setTextViewText(viewHolder.lblMembersTotal, getSearchString());
            viewHolder.lblMembersOnline.setText("");
        }
        return v;
    }

    public void clear() {
        if (this.groupsList != null) {
            this.groupsList.clear();
        }
    }

    public void add(Group group) {
        if (group != null) {
            if (this.groupsList == null) {
                this.groupsList = new ArrayList();
            }
            this.groupsList.add(group);
        }
    }

    public void add(List<Group> groups) {
        this.groupsList = new ArrayList(groups);
    }

    private Group createSearchItem() {
        Group group = new Group();
        group.steamId = "0";
        group.name = this.groupsSearchString;
        group.categoryInList = GroupCategoryInList.SEARCH_ALL;
        return group;
    }
}
