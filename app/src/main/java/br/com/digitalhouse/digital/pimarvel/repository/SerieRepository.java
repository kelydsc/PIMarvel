package br.com.digitalhouse.digital.pimarvel.repository;

import android.content.Context;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.SerieDAO;
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;
import br.com.digitalhouse.digital.pimarvel.model.serie.SerieResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PRIVATE_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PUBLIC_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.getApiService;
import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.md5;

public class SerieRepository {

    //Pega os dados da base de dados
    public Flowable<List<Serie>> getSerieLocal(Context context) {

        Database database = Database.getDatabase(context);
        SerieDAO serieDAO = database.serieDAO();

        return serieDAO.getAllRxJava();
    }

    //Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Serie> series) {

        Database database = Database.getDatabase(context);
        SerieDAO serieDAO = database.serieDAO();

        serieDAO.deleteAll();
        serieDAO.insertAll(series);
    }

    //Pega os items que virão da API de series
    public Single<SerieResponse> getSeries() {
        String ts = Long.toString(System.currentTimeMillis() / 1000);
        String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
        return getApiService().getSeries("collection", "digital comic", "startYear", "30", ts, hash, PUBLIC_KEY);
    }
}

