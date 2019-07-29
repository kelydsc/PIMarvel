package br.com.digitalhouse.digital.pimarvel.serie.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.digitalhouse.digital.pimarvel.serie.data.database.dao.SerieDAO;
import br.com.digitalhouse.digital.pimarvel.serie.model.Result;

@androidx.room.Database(entities = {Result.class}, version = 05, exportSchema = false)
@TypeConverters(Converters.class)

public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract SerieDAO serieDAO();

    public static Database getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "my_db_serie")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
