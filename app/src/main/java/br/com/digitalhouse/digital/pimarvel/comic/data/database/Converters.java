package br.com.digitalhouse.digital.pimarvel.comic.data.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
//import java.util.Date;
import java.util.List;

import br.com.digitalhouse.digital.pimarvel.comic.model.Characters;
import br.com.digitalhouse.digital.pimarvel.comic.model.CollectedIssue;
import br.com.digitalhouse.digital.pimarvel.comic.model.Collection;
import br.com.digitalhouse.digital.pimarvel.comic.model.Creators;
import br.com.digitalhouse.digital.pimarvel.comic.model.Date;
import br.com.digitalhouse.digital.pimarvel.comic.model.Events;
import br.com.digitalhouse.digital.pimarvel.comic.model.Image;
import br.com.digitalhouse.digital.pimarvel.comic.model.Price;
import br.com.digitalhouse.digital.pimarvel.comic.model.Series;
import br.com.digitalhouse.digital.pimarvel.comic.model.Stories;
import br.com.digitalhouse.digital.pimarvel.comic.model.TextObject;
import br.com.digitalhouse.digital.pimarvel.comic.model.Thumbnail;
import br.com.digitalhouse.digital.pimarvel.comic.model.Url;
import br.com.digitalhouse.digital.pimarvel.comic.model.Variant;

public class Converters {

    //import java.util.Date;
    /*
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
    */

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
    public List<CollectedIssue> fromListCollectedIssue(String value) {
        Type listType = (Type) new TypeToken<List<CollectedIssue>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListCollectedIssue(List<CollectedIssue> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public List<Collection> fromListCollection(String value) {
        Type listType = (Type) new TypeToken<List<Collection>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListCollection(List<Collection> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public List<Date> fromListDate(String value) {
        Type listType = (Type) new TypeToken<List<Date>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListDate(List<Date> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public List<Image> fromListImage(String value) {
        Type listType = (Type) new TypeToken<List<Image>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListImage(List<Image> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public List<Variant> fromListVariant(String value) {
        Type listType = (Type) new TypeToken<List<Variant>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListVariant(List<Variant> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }


    // Type converter
    @TypeConverter
    public List<TextObject> fromListTextObject(String value) {
        Type listType = (Type) new TypeToken<List<TextObject>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListTextObject(List<TextObject> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    // Type converter
    @TypeConverter
    public List<Price> fromListPrice(String value) {
        Type listType = (Type) new TypeToken<List<Price>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromListPrice(List<Price> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
