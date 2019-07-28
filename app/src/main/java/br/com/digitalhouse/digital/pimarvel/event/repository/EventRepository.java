package br.com.digitalhouse.digital.pimarvel.event.repository;


import android.content.Context;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.event.dao.EventDAO;
import br.com.digitalhouse.digital.pimarvel.event.model.EventsResponse;
import br.com.digitalhouse.digital.pimarvel.event.model.Result;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PRIVATE_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PUBLIC_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.getApiService;
import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.md5;

public class EventRepository {

    //Pega os dados da base de dados
    public Flowable<List<Result>> getEventLocal(Context context) {

        Database database = Database.getDatabase(context);
        EventDAO eventDAO = database.eventDAO();

        return eventDAO.getAllRxJava();
    }

    //Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Result> results) {

        Database database = Database.getDatabase(context);
        EventDAO eventDAO = database.eventDAO();

        eventDAO.deleteAll();
        eventDAO.insertAll(results);
    }

    //Pega os items que virão da API de eventos
    public Single<EventsResponse> getEvents() {
        String ts = Long.toString(System.currentTimeMillis() / 1000);
        String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
        return getApiService().getEvents("startDate", "10", ts, hash, PUBLIC_KEY);
    }
}

