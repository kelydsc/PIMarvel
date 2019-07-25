package br.com.digitalhouse.digital.pimarvel.event.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.event.view.Event;
import io.reactivex.Flowable;

@Dao
public interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Event> events);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM events")
    List<Event> getAll();

    @Query("SELECT * FROM events")
    Flowable<List<Event>> getAllRxJava ();

    @Query("SELECT * FROM events WHERE id = :id")
    Event getById (long id);

    @Query("SELECT * FROM events WHERE title = :title")
    Event getByTitle (String title);
}
