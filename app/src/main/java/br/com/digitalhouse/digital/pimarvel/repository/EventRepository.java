package br.com.digitalhouse.digital.pimarvel.repository;

import android.content.Context;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.EventDAO;
import br.com.digitalhouse.digital.pimarvel.model.event.Event;
import br.com.digitalhouse.digital.pimarvel.model.event.EventResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PRIVATE_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PUBLIC_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.getApiService;
import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.md5;

public class EventRepository {

    //Pega os dados da base de dados
    public Flowable<List<Event>> getEventLocal(Context context) {

        Database database = Database.getDatabase(context);
        EventDAO eventDAO = database.eventDAO();

        return eventDAO.getAllRxJava();
    }

    //Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Event> events) {

        Database database = Database.getDatabase(context);
        EventDAO eventDAO = database.eventDAO();

        eventDAO.deleteAll();
        eventDAO.insertAll(events);
    }

    //Pega os items que virão da API de eventos
    public Single<EventResponse> getEvents() {
        String ts = Long.toString(System.currentTimeMillis() / 1000);
        String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
        return getApiService().getEvents("startDate", "30", ts, hash, PUBLIC_KEY);
    }
}
