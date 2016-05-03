package com.valvesoftware.android.steam.community.fragment;

import android.os.Bundle;
import android.util.Log;
import com.valvesoftware.android.steam.community.C0151R;
import com.valvesoftware.android.steam.community.activity.ActivityHelper;
import com.valvesoftware.android.steam.community.activity.MainActivity;
import com.valvesoftware.android.steam.community.jsontranslators.PersonaTranslator;
import com.valvesoftware.android.steam.community.jsontranslators.SearchResultsTranslator;
import com.valvesoftware.android.steam.community.model.Persona;
import com.valvesoftware.android.steam.community.model.SearchResults;
import com.valvesoftware.android.steam.community.webrequests.Endpoints;
import com.valvesoftware.android.steam.community.webrequests.RequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.RequestErrorInfo;
import com.valvesoftware.android.steam.community.webrequests.ResponseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONObject;

public class FriendSearchFragment extends BaseSearchListFragment {
    private List<Persona> searchResults;

    /* renamed from: com.valvesoftware.android.steam.community.fragment.FriendSearchFragment.1 */
    class C02151 implements Comparator<Persona> {
        C02151() {
        }

        public int compare(Persona persona, Persona persona2) {
            if (persona.personaName == null || persona.personaName.equals(persona2.personaName)) {
                return persona.steamId.compareTo(persona2.steamId);
            }
            return persona.personaName.compareTo(persona2.personaName);
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.FriendSearchFragment.2 */
    class C02162 extends ResponseListener {
        C02162() {
        }

        public void onSuccess(JSONObject response) {
            SearchResults searchResults = SearchResultsTranslator.translate(response);
            FriendSearchFragment.this.setNumTotalResults(searchResults.total);
            FriendSearchFragment.this.setNumCurrentResults(searchResults.getCurrentCount());
            FriendSearchFragment.this.getDetailedPersonaInfo(searchResults.getResultIds());
        }

        public void onError(RequestErrorInfo errorInfo) {
            Log.e("error", "error processing data");
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.fragment.FriendSearchFragment.3 */
    class C02173 extends ResponseListener {
        C02173() {
        }

        public void onSuccess(JSONObject response) {
            FriendSearchFragment.this.searchResults.addAll(PersonaTranslator.translateList(response));
            FriendSearchFragment.this.display();
            FriendSearchFragment.this.searchComplete();
        }

        public void onError(RequestErrorInfo errorInfo) {
            errorInfo.toString();
            FriendSearchFragment.this.searchComplete();
        }
    }

    public FriendSearchFragment() {
        this.searchResults = new ArrayList();
    }

    private void display() {
        if (this.searchResults != null && ActivityHelper.fragmentOrActivityIsActive(getActivity())) {
            Collections.sort(this.searchResults, new C02151());
            this.adapter = new FriendsListAdapter(this.searchResults, getActivity(), false);
            setListAdapter(this.adapter);
        }
    }

    protected void setTitleBarText(String query) {
        MainActivity mainActivity = getBaseActivity();
        if (mainActivity != null && ActivityHelper.fragmentIsActive(this)) {
            mainActivity.setTitle(getResources().getString(C0151R.string.Search_Players_Results).replace("#", query));
        }
    }

    protected void startSearch() {
        displayInProgress();
        Bundle args = getArguments();
        if (args != null) {
            String friendsSearchString = args.getString("friends");
            if (friendsSearchString != null) {
                this.queryString = friendsSearchString;
                setTitleBarText(this.queryString);
                query(this.queryString);
                return;
            }
        }
        getActivity().onBackPressed();
    }

    protected void query(String query) {
        RequestBuilder requestBuilder = Endpoints.getFriendsSearchRequestBuilder(query, this.queryOffset, 50);
        requestBuilder.setResponseListener(new C02162());
        sendRequest(requestBuilder);
    }

    private void getDetailedPersonaInfo(Collection<String> ids) {
        List<RequestBuilder> requestBuilders = Endpoints.getUserSummariesRequestBuilder(ids);
        this.searchResults.clear();
        for (RequestBuilder requestBuilder : requestBuilders) {
            requestBuilder.setResponseListener(new C02173());
            sendRequest(requestBuilder);
        }
    }
}
