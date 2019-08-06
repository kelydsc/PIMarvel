package br.com.digitalhouse.digital.pimarvel.repository;

import android.content.Context;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.data.database.Database;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.FavoriteDAO;
import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;
import io.reactivex.Flowable;

public class FavoriteRepository {

    public Flowable<List<Favorite>> getFavoriteLocal(Context context) {

        Database database = Database.getDatabase(context);

        FavoriteDAO favoriteDAO = database.favoriteDAO();

        return favoriteDAO.getAllRxJava();
    }
}
