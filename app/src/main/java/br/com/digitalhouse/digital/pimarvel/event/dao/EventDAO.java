package br.com.digitalhouse.digital.pimarvel.event.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.event.model.Result;
import io.reactivex.Flowable;

@Dao
public interface EventDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Result result);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Result> results);

    @Update
    void update(Result result);

    @Delete
    void delete(Result result);

    @Query("DELETE FROM events")
    void deleteAll();

    @Query("SELECT * FROM events")
    List<Result> getAll();

    @Query("SELECT * FROM events")
    Flowable<List<Result>> getAllRxJava();

    @Query("SELECT * FROM events WHERE id = :id ORDER BY id")
    Result getById(String id);

    @Query("SELECT * FROM events WHERE title = :title")
    Result getByTitle(String title);
}