package com.valvesoftware.android.steam.community;

import com.valvesoftware.android.steam.community.jsontranslators.PersonaTranslator;
import com.valvesoftware.android.steam.community.model.Persona;
import com.valvesoftware.android.steam.community.webrequests.Endpoints;
import com.valvesoftware.android.steam.community.webrequests.RequestBuilder;
import com.valvesoftware.android.steam.community.webrequests.RequestErrorInfo;
import com.valvesoftware.android.steam.community.webrequests.ResponseListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class PersonaRepository {

    /* renamed from: com.valvesoftware.android.steam.community.PersonaRepository.1 */
    static class C01491 extends ResponseListener {
        final /* synthetic */ AtomicInteger val$callsRemaining;
        final /* synthetic */ RepositoryCallback val$multipleResponseCallback;

        C01491(AtomicInteger atomicInteger, RepositoryCallback repositoryCallback) {
            this.val$callsRemaining = atomicInteger;
            this.val$multipleResponseCallback = repositoryCallback;
        }

        public void onSuccess(JSONObject response) {
            List<Persona> friendDetails = PersonaTranslator.translateList(response);
            int remaining = this.val$callsRemaining.decrementAndGet();
            if (this.val$multipleResponseCallback != null) {
                this.val$multipleResponseCallback.dataAvailable(friendDetails);
                if (remaining == 0) {
                    this.val$multipleResponseCallback.end();
                }
            }
        }

        public void onError(RequestErrorInfo errorInfo) {
            if (this.val$callsRemaining.decrementAndGet() == 0 && this.val$multipleResponseCallback != null) {
                this.val$multipleResponseCallback.end();
            }
        }
    }

    /* renamed from: com.valvesoftware.android.steam.community.PersonaRepository.2 */
    static class C01502 implements RepositoryCallback<List<Persona>> {
        final /* synthetic */ RepositoryCallback val$callback;

        C01502(RepositoryCallback repositoryCallback) {
            this.val$callback = repositoryCallback;
        }

        public void dataAvailable(List<Persona> persona) {
            if (this.val$callback != null && persona != null && persona.size() > 0) {
                this.val$callback.dataAvailable(persona.get(0));
            }
        }

        public void end() {
            if (this.val$callback != null) {
                this.val$callback.end();
            }
        }
    }

    public static void getDetailedPersonaInfo(Collection<String> steamIds, RepositoryCallback<List<Persona>> multipleResponseCallback) {
        List<RequestBuilder> requestBuilders = Endpoints.getUserSummariesRequestBuilder(steamIds);
        AtomicInteger callsRemaining = new AtomicInteger(requestBuilders.size());
        for (RequestBuilder requestBuilder : requestBuilders) {
            requestBuilder.setResponseListener(new C01491(callsRemaining, multipleResponseCallback));
            sendRequest(requestBuilder);
        }
    }

    public static void getDetailedPersonaInfo(String steamId, RepositoryCallback<Persona> callback) {
        getDetailedPersonaInfo(Collections.singletonList(steamId), new C01502(callback));
    }

    private static void sendRequest(RequestBuilder requestBuilder) {
        SteamCommunityApplication.GetInstance().sendRequest(requestBuilder);
    }
}
