
package br.com.digitalhouse.digital.pimarvel.serie.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.List;

@Entity(tableName = "series")
public class Result implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long idBancoSeries;

    @Expose
    private Characters characters;

    @Expose
    private Comics comics;

    @Expose
    private Creators creators;

    @Expose
    private String description;

    @Expose
    private String endYear;

    @Expose
    private Events events;

    @Expose
    private String id;

    @Expose
    private String modified;

    @Expose
    private Next next;

    @Expose
    private Previous previous;

    @Expose
    private String rating;

    @Expose
    private String resourceURI;

    @Expose
    private String startYear;

    @Expose
    private Stories stories;

    @Expose
    private Thumbnail thumbnail;

    @Expose
    private String title;

    @Expose
    private List<Url> urls;

    public Result() {
    }

    protected Result(Parcel in) {
        idBancoSeries = in.readLong();
        characters = in.readParcelable(Characters.class.getClassLoader());
        comics = in.readParcelable(Comics.class.getClassLoader());
        creators = in.readParcelable(Creators.class.getClassLoader());
        description = in.readString();
        endYear = in.readString();
        events = in.readParcelable(Events.class.getClassLoader());
        id = in.readString();
        modified = in.readString();
        next = in.readParcelable(Next.class.getClassLoader());
        previous = in.readParcelable(Previous.class.getClassLoader());
        rating = in.readString();
        resourceURI = in.readString();
        startYear = in.readString();
        stories = in.readParcelable(Stories.class.getClassLoader());
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        title = in.readString();
        urls = in.createTypedArrayList(Url.CREATOR);
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public long getIdBancoSeries() {
        return idBancoSeries;
    }

    public void setIdBancoSeries(long idBancoSeries) {
        this.idBancoSeries = idBancoSeries;
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Next getNext() {
        return next;
    }

    public void setNext(Next next) {
        this.next = next;
    }

    public Previous getPrevious() {
        return previous;
    }

    public void setPrevious(Previous previous) {
        this.previous = previous;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public Stories getStories() {
        return stories;
    }

    public void setStories(Stories stories) {
        this.stories = stories;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idBancoSeries);
        dest.writeParcelable(characters, flags);
        dest.writeParcelable(comics, flags);
        dest.writeParcelable(creators, flags);
        dest.writeString(description);
        dest.writeString(endYear);
        dest.writeParcelable(events, flags);
        dest.writeString(id);
        dest.writeString(modified);
        dest.writeParcelable(next, flags);
        dest.writeParcelable(previous, flags);
        dest.writeString(rating);
        dest.writeString(resourceURI);
        dest.writeString(startYear);
        dest.writeParcelable(stories, flags);
        dest.writeParcelable(thumbnail, flags);
        dest.writeString(title);
        dest.writeTypedList(urls);
    }
}
