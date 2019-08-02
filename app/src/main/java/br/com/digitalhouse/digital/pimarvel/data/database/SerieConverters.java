package br.com.digitalhouse.digital.pimarvel.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.model.serie.Characters;
import br.com.digitalhouse.digital.pimarvel.model.serie.Comics;
import br.com.digitalhouse.digital.pimarvel.model.serie.Creators;
import br.com.digitalhouse.digital.pimarvel.model.serie.Events;
import br.com.digitalhouse.digital.pimarvel.model.serie.Next;
import br.com.digitalhouse.digital.pimarvel.model.serie.Previous;
import br.com.digitalhouse.digital.pimarvel.model.serie.Stories;
import br.com.digitalhouse.digital.pimarvel.model.serie.Thumbnail;
import br.com.digitalhouse.digital.pimarvel.model.serie.Url;

public class SerieConverters {

    // Type converter
    @TypeConverter
    public Comics fromComics(String value) {
        Type listType = (Type) new TypeToken<Comics>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromComics(Comics list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Events fromEvents(String value) {
        Type listType = (Type) new TypeToken<Events>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromEvents(Events list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Previous fromPrevious(String value) {
        Type listType = (Type) new TypeToken<Previous>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromPrevious(Previous list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Thumbnail fromThumbnail(String value) {
        Type listType = (Type) new TypeToken<Thumbnail>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromThumbnail(Thumbnail list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Characters fromCharacters(String value) {
        Type listType = (Type) new TypeToken<Characters>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromCharacters(Characters list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Next fromNext(String value) {
        Type listType = (Type) new TypeToken<Next>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromNext(Next list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public List<Url> fromListUrl(String value) {
        Type listType = (Type) new TypeToken<List<Url>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListUrl(List<Url> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Creators fromCreators(String value) {
        Type listType = (Type) new TypeToken<Creators>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromCreators(Creators list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public Stories fromStories(String value) {
        Type listType = (Type) new TypeToken<Stories>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromStories(Stories list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}

