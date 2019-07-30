package br.com.digitalhouse.digital.pimarvel.event.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.digitalhouse.digital.pimarvel.event.data.database.dao.EventDAO;
import br.com.digitalhouse.digital.pimarvel.event.model.Result;

//Foi criado um Database para cada API para evitar conflito devido a classe Result
@androidx.room.Database(entities = {Result.class}, version = 07, exportSchema = false)
@TypeConverters(Converters.class)

public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract EventDAO eventDAO();

    public static Database getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "my_db_event")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
