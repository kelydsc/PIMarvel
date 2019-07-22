package br.com.digitalhouse.digital.pimarvel.event.data;

import br.com.digitalhouse.digital.pimarvel.event.model.EventsResponse;
import io.reactivex.Single;

import static br.com.digitalhouse.digital.pimarvel.event.data.network.ApiService.PRIVATE_KEY;
import static br.com.digitalhouse.digital.pimarvel.event.data.network.ApiService.PUBLIC_KEY;
import static br.com.digitalhouse.digital.pimarvel.event.data.network.ApiService.getApiService;
import static br.com.digitalhouse.digital.pimarvel.event.utils.AppUtils.md5;


public class EventsRepository {

        public Single<EventsResponse> getEvents() {
            String ts = Long.toString(System.currentTimeMillis() / 1000);
            String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
            return getApiService().getEvents("magazine", "comic", true, "focDate", "50", ts, hash, PUBLIC_KEY);
        }
    }

