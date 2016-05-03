package com.valvesoftware.android.steam.community.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.LoggedInUserAccountInfo;
import com.valvesoftware.android.steam.community.SteamCommunityApplication;
import com.valvesoftware.android.steam.community.activity.ActivityHelper;
import com.valvesoftware.android.steam.community.activity.MainActivity;
import com.valvesoftware.android.steam.community.jsontranslators.GroupTranslator;
import com.valvesoftware.android.steam.community.model.Group;
import com.valvesoftware.android.steam.community.model.GroupRelationship;
import com.valvesoftware.android.steam.community.webrequests.Endpoints;
import com.valvesoftware.android.steam.community.webrequests.RequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.RequestErrorInfo;
import com.valvesoftware.android.steam.community.webrequests.ResponseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class GroupListFragment extends ListFragment {
    private GroupsListAdapter adapter;
    private final HashSet<Group> groupsSet;
    private MainActivity mainActivity;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.GroupListFragment.1 */
    class C02321 implements OnClickListener {
        C02321() {
        }

        public void onClick(View view) {
            GroupListFragment.this.getGroupsList();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.GroupListFragment.2 */
    class C02332 implements TextWatcher {
        C02332() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            GroupListFragment.this.setSearchText(charSequence.toString());
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.GroupListFragment.3 */
    class C02343 extends ResponseListener {
        C02343() {
        }

        public void onSuccess(JSONObject response) {
            List<Group> groups = GroupTranslator.translateList(response);
            Iterator<Group> groupsIterator = groups.iterator();
            while (groupsIterator.hasNext()) {
                Group group = (Group) groupsIterator.next();
                if (!(group.relationship == GroupRelationship.Member || group.relationship == GroupRelationship.Invited)) {
                    groupsIterator.remove();
                }
            }
            HashSet<Group> mostRecentSet = new HashSet(groups);
            GroupListFragment.this.groupsSet.retainAll(mostRecentSet);
            mostRecentSet.removeAll(GroupListFragment.this.groupsSet);
            GroupListFragment.this.groupsSet.addAll(mostRecentSet);
            GroupListFragment.this.display(new ArrayList(GroupListFragment.this.groupsSet));
            GroupListFragment.this.hideProgressIndicator();
            GroupListFragment.this.getDetailedGroupInfo(GroupListFragment.this.groupsSet);
        }

        public void onError(RequestErrorInfo errorInfo) {
            GroupListFragment.this.hideProgressIndicator();
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.GroupListFragment.4 */
    class C02354 extends ResponseListener {
        final /* synthetic */ AtomicInteger val$callsRemaining;
        final /* synthetic */ Map val$steamIdToInfoMap;

        C02354(Map map, AtomicInteger atomicInteger) {
            this.val$steamIdToInfoMap = map;
            this.val$callsRemaining = atomicInteger;
        }

        public void onSuccess(JSONObject response) {
            for (Group detail : GroupTranslator.translateList(response)) {
                Group group = (Group) this.val$steamIdToInfoMap.get(detail.steamId);
                if (group != null) {
                    group.merge(detail);
                }
            }
            if (this.val$callsRemaining.decrementAndGet() == 0) {
                GroupListFragment.this.hideProgressIndicator();
                GroupListFragment.this.notifyDataSetChanged();
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
            if (this.val$callsRemaining.decrementAndGet() == 0) {
                GroupListFragment.this.hideProgressIndicator();
                GroupListFragment.this.notifyDataSetChanged();
            }
        }
    }

    public GroupListFragment() {
        this.groupsSet = new HashSet();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(C0151R.layout.friends_groups_list_fragment, container, false);
    }

    public void onResume() {
        super.onResume();
        ActivityHelper.hideKeyboard(getActivity());
        setupEventListeners();
        setTitleText();
        this.mainActivity = getBaseActivity();
        getGroupsList();
    }

    private void setTitleText() {
        MainActivity mainActivity = getBaseActivity();
        if (mainActivity != null && ActivityHelper.fragmentIsActive(this)) {
            mainActivity.setTitle(C0151R.string.Groups);
        }
    }

    protected MainActivity getBaseActivity() {
        Activity parentActivity = getActivity();
        if (parentActivity instanceof MainActivity) {
            return (MainActivity) parentActivity;
        }
        return null;
    }

    private void showProgressIndicator() {
        if (this.mainActivity != null) {
            this.mainActivity.showProgressIndicator();
        }
    }

    private void hideProgressIndicator() {
        if (this.mainActivity != null) {
            this.mainActivity.hideProgressIndicator();
        }
    }

    private void setupEventListeners() {
        Activity parentActivity = getActivity();
        if (parentActivity instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) parentActivity;
            mainActivity.setRefreshButtonClickListener(new C02321());
            mainActivity.setSearchTextListener(new C02332());
        }
    }

    private void setSearchText(String searchString) {
        if (this.adapter != null) {
            this.adapter.setSearchString(searchString);
        }
    }

    private void display(List<Group> groups) {
        if (groups == null || !ActivityHelper.fragmentIsActive(this)) {
            return;
        }
        if (this.adapter == null) {
            this.adapter = new GroupsListAdapter(groups, getActivity());
            setListAdapter(this.adapter);
            return;
        }
        this.adapter.clear();
        this.adapter.add((List) groups);
    }

    private void notifyDataSetChanged() {
        if (ActivityHelper.fragmentIsActive(this) && this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
    }

    private void getGroupsList() {
        showProgressIndicator();
        RequestBuilder requestBuilder = Endpoints.getGroupListRequestBuilder(LoggedInUserAccountInfo.getLoginSteamID());
        requestBuilder.setResponseListener(new C02343());
        sendRequest(requestBuilder);
    }

    private void getDetailedGroupInfo(Collection<Group> groups) {
        if (groups.size() != 0) {
            Map<String, Group> steamIdToInfoMap = new HashMap();
            for (Group g : groups) {
                steamIdToInfoMap.put(g.steamId, g);
            }
            List<RequestBuilder> requestBuilders = Endpoints.getGroupSummariesRequestBuilder(steamIdToInfoMap.keySet());
            showProgressIndicator();
            AtomicInteger callsRemaining = new AtomicInteger(requestBuilders.size());
            for (RequestBuilder requestBuilder : requestBuilders) {
                requestBuilder.setResponseListener(new C02354(steamIdToInfoMap, callsRemaining));
                sendRequest(requestBuilder);
            }
        }
    }

    private void sendRequest(RequestBuilder requestBuilder) {
        SteamCommunityApplication.GetInstance().sendRequest(requestBuilder);
    }
}
