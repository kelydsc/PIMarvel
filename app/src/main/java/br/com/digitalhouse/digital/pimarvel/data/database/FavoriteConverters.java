package br.com.digitalhouse.digital.pimarvel.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.model.event.Event;
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;

public class FavoriteConverters {

    // Type converter
    @TypeConverter
    public Comic fromComic(String value) {
        Type listType = (Type) new TypeToken<Comic>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromComic(Comic list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Event fromEvent(String value) {
        Type listType = (Type) new TypeToken<Event>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromEvent(Event list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Serie fromSerie(String value) {
        Type listType = (Type) new TypeToken<Serie>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromSerie(Serie list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }


}
