package br.com.digitalhouse.digital.pimarvel.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.digitalhouse.digital.pimarvel.data.database.dao.ComicDAO;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.EventDAO;
import br.com.digitalhouse.digital.pimarvel.data.database.dao.SerieDAO;
import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.model.event.Event;
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;

@androidx.room.Database(entities = {Comic.class, Event.class, Serie.class}, version = 8, exportSchema = false)
@TypeConverters({ComicConverters.class, EventConverters.class, SerieConverters.class})

public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract ComicDAO comicDAO();

    public abstract EventDAO eventDAO();

    public abstract SerieDAO serieDAO();

    public static Database getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "my_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
