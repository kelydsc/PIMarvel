package br.com.digitalhouse.digital.pimarvel.event.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.digitalhouse.digital.pimarvel.event.model.Event;
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
