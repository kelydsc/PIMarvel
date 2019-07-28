package br.com.digitalhouse.digital.pimarvel.serie.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.serie.model.Result;
import io.reactivex.Flowable;

@Dao
public interface SerieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Result result);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Result> results);

    @Update
    void update(Result result);

    @Delete
    void delete(Result result);

    @Query("DELETE FROM series")
    void deleteAll();

    @Query("SELECT * FROM series")
    List<Result> getAll();

    @Query("SELECT * FROM series")
    Flowable<List<Result>> getAllRxJava();

    @Query("SELECT * FROM series WHERE id = :id ORDER BY id")
    Result getById(String id);

    @Query("SELECT * FROM series WHERE title = :title")
    Result getByTitle(String title);
}