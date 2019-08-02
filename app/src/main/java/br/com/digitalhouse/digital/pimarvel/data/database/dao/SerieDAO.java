package br.com.digitalhouse.digital.pimarvel.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;
import io.reactivex.Flowable;

@Dao
public interface SerieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Serie serie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Serie> series);

    @Update
    void update(Serie serie);

    @Delete
    void delete(Serie serie);

    @Query("DELETE FROM series")
    void deleteAll();

    @Query("SELECT * FROM series")
    List<Serie> getAll();

    @Query("SELECT * FROM series")
    Flowable<List<Serie>> getAllRxJava();

    @Query("SELECT * FROM series WHERE id = :id ORDER BY id")
    Serie getById(String id);

    @Query("SELECT * FROM series WHERE title = :title")
    Serie getByTitle(String title);
}
