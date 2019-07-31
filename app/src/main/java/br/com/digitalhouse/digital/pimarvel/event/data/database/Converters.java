package br.com.digitalhouse.digital.pimarvel.event.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.event.model.Characters;
import br.com.digitalhouse.digital.pimarvel.event.model.Comics;
import br.com.digitalhouse.digital.pimarvel.event.model.Creators;
import br.com.digitalhouse.digital.pimarvel.event.model.Next;
import br.com.digitalhouse.digital.pimarvel.event.model.Previous;
import br.com.digitalhouse.digital.pimarvel.event.model.Series;
import br.com.digitalhouse.digital.pimarvel.event.model.Stories;
import br.com.digitalhouse.digital.pimarvel.event.model.Thumbnail;
import br.com.digitalhouse.digital.pimarvel.event.model.Url;

public class Converters {
    @TypeConverter
    public Date toDate(Long timestamp) {
        if (timestamp == null) {
            return null;
        } else {
            return new Date(timestamp);
        }
    }

    @TypeConverter
    public Long toTimestamp(Date date) {
        return date.getTime();
    }

    // Type converter para uam lista de String
    @TypeConverter
    public List<String> fromString(String value) {
        Type listType = (Type) new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromList(List<String> list) {
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
    public Series fromSeries(String value) {
        Type listType = (Type) new TypeToken<Series>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromSeries(Series list) {
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
}

