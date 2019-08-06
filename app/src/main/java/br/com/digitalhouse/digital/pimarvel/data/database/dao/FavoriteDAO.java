package br.com.digitalhouse.digital.pimarvel.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.model.favorite.Favorite;
import io.reactivex.Flowable;

@Dao
public interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favorite favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Favorite> favorites);

    @Update
    void update(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("DELETE FROM favorites WHERE loginUser = :loginUser and idComic = :idComic")
    void deleteByUserComicId(String loginUser, String idComic);

    @Query("DELETE FROM favorites WHERE loginUser = :loginUser and idEvent = :idEvent")
    void deleteByUserEventId(String loginUser, String idEvent);

    @Query("DELETE FROM favorites WHERE loginUser = :loginUser and idSerie = :idSerie")
    void deleteByUserSerieId(String loginUser, String idSerie);

    @Query("SELECT * FROM favorites")
    List<Favorite> getAll();

    @Query("SELECT * FROM favorites")
    Flowable<List<Favorite>> getAllRxJava();

    @Query("SELECT * FROM favorites WHERE loginUser = :loginUser and idComic = :idComic")
    Favorite getFavoritesByUserComicId(String loginUser, String idComic);

    @Query("SELECT * FROM favorites WHERE loginUser = :loginUser and idEvent = :idEvent")
    Favorite getFavoritesByUserEventId(String loginUser, String idEvent);

    @Query("SELECT * FROM favorites WHERE loginUser = :loginUser and idSerie = :idSerie")
    Favorite getFavoritesByUserSerieId(String loginUser, String idSerie);
}
