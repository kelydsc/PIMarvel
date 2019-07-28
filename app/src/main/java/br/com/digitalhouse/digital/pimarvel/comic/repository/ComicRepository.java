package br.com.digitalhouse.digital.pimarvel.comic.repository;

import android.content.Context;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.comic.dao.ComicDAO;
import br.com.digitalhouse.digital.pimarvel.comic.model.ComicsResponse;
import br.com.digitalhouse.digital.pimarvel.comic.model.Result;
import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PRIVATE_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.PUBLIC_KEY;
import static br.com.digitalhouse.digital.pimarvel.data.network.ApiService.getApiService;
import static br.com.digitalhouse.digital.pimarvel.util.AppUtil.md5;

public class ComicRepository {

    //Pega os dados da base de dados
    public Flowable<List<Result>> getComicLocal(Context context) {

        Database database = Database.getDatabase(context);
        ComicDAO comicDAO = database.comicDAO();

        return comicDAO.getAllRxJava();
    }

    //Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Result> results) {

        Database database = Database.getDatabase(context);
        ComicDAO comicDAO = database.comicDAO();

        comicDAO.deleteAll();
        comicDAO.insertAll(results);
    }


    //Pega os items que virão da API de Comics
    public Single<ComicsResponse> getComics() {
        String ts = Long.toString(System.currentTimeMillis() / 1000);
        String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);
        return getApiService().getComics("magazine", "comic", true, "focDate", "50", ts, hash, PUBLIC_KEY);
    }
}

