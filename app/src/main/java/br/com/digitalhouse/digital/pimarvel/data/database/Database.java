package br.com.digitalhouse.digital.pimarvel.data.database;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.digitalhouse.digital.pimarvel.comic.model.Result;
import br.com.digitalhouse.digital.pimarvel.comic.dao.ComicDAO;
import br.com.digitalhouse.digital.pimarvel.event.dao.EventDAO;
import br.com.digitalhouse.digital.pimarvel.serie.dao.SerieDAO;

@androidx.room.Database(
        entities = {Result.class, br.com.digitalhouse.digital.pimarvel.event.model.Result.class,
                br.com.digitalhouse.digital.pimarvel.serie.model.Result.class},
        version = 01, exportSchema = false)

@TypeConverters(Converters.class)

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