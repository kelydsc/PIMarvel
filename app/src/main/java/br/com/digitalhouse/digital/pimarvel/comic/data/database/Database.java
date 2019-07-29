package br.com.digitalhouse.digital.pimarvel.comic.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.digitalhouse.digital.pimarvel.comic.data.database.dao.ComicDAO;
import br.com.digitalhouse.digital.pimarvel.comic.model.Result;

@androidx.room.Database(entities = {Result.class}, version = 06, exportSchema = false)
@TypeConverters(Converters.class)

public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract ComicDAO comicDAO();

    public static Database getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "my_db_comic")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
