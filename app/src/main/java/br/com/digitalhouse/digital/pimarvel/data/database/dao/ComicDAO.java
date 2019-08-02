package br.com.digitalhouse.digital.pimarvel.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import io.reactivex.Flowable;

@Dao
public interface ComicDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Comic comic);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Comic> comics);

    @Update
    void update(Comic comic);

    @Delete
    void delete(Comic comic);

    @Query("DELETE FROM comics")
    void deleteAll();

    @Query("SELECT * FROM comics")
    List<Comic> getAll();

    @Query("SELECT * FROM comics")
    Flowable<List<Comic>> getAllRxJava();

    @Query("SELECT * FROM comics WHERE id = :id ORDER BY id")
    Comic getById(String id);

    @Query("SELECT * FROM comics WHERE title = :title")
    Comic getByTitle(String title);
}
