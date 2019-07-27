package br.com.digitalhouse.digital.pimarvel.event.database;


import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;

import br.com.digitalhouse.digital.pimarvel.event.database.dao.EventDao;
import br.com.digitalhouse.digital.pimarvel.event.view.Event;

@androidx.room.Database(entities = {Event.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract EventDao eventDao();

    public static Database getDatabase (Context context){
        if(INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, Database.class, "my_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
