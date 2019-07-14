package br.com.digitalhouse.digital.pimarvel.event.data.database;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.com.digitalhouse.digital.pimarvel.event.data.database.dao.EventDao;
import br.com.digitalhouse.digital.pimarvel.event.model.Event;

@android.arch.persistence.room.Database(entities = {Event.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class DataBase extends RoomDatabase {

    private static volatile DataBase INSTANCE;

    public abstract EventDao eventDao();

    public static DataBase getDatabase (Context context){
        if(INSTANCE == null){
            synchronized (DataBase.class){
                if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, DataBase.class, "my_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
