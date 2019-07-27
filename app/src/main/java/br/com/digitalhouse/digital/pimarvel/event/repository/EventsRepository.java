package br.com.digitalhouse.digital.pimarvel.event.repository;

import br.com.digitalhouse.digital.pimarvel.event.model.Events;
import io.reactivex.Single;

import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PRIVATE_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PUBLIC_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.getApiService;
import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.md5;


public class EventsRepository {

    /*
        public Single<Events> getEvents() {
            String ts = Long.toString(System.currentTimeMillis() / 1000);
            String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
            return getApiService().getEvents("events", "comic", true, "focDate", "50", ts, hash, PUBLIC_KEY);
        }
        */
    }

