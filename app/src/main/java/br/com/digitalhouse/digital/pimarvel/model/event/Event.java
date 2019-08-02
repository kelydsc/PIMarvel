
package br.com.digitalhouse.digital.pimarvel.model.event;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.List;

@Entity(tableName = "events")
public class Event implements Parcelable {

    @Expose
    private Characters characters;

    @Expose
    private Comics comics;

    @Expose
    private Creators creators;

    @Expose
    private String description;

    @Expose
    private String end;

    @Expose
    @PrimaryKey
    @NonNull
    private String id;

    @Expose
    private String modified;

    @Expose
    private Next next;

    @Expose
    private Previous previous;

    @Expose
    private String resourceURI;

    @Expose
    private Series series;

    @Expose
    private String start;

    @Expose
    private Stories stories;

    @Expose
    private Thumbnail thumbnail;

    @Expose
    private String title;

    @Expose
    private List<Url> urls;

    public Event() {
    }

    protected Event(Parcel in) {
        characters = in.readParcelable(Characters.class.getClassLoader());
        comics = in.readParcelable(Comics.class.getClassLoader());
        creators = in.readParcelable(Creators.class.getClassLoader());
        description = in.readString();
        end = in.readString();
        id = in.readString();
        modified = in.readString();
        next = in.readParcelable(Next.class.getClassLoader());
        previous = in.readParcelable(Previous.class.getClassLoader());
        resourceURI = in.readString();
        series = in.readParcelable(Series.class.getClassLoader());
        start = in.readString();
        stories = in.readParcelable(Stories.class.getClassLoader());
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        title = in.readString();
        urls = in.createTypedArrayList(Url.CREATOR);
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

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

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(characters, i);
        parcel.writeParcelable(comics, i);
        parcel.writeParcelable(creators, i);
        parcel.writeString(description);
        parcel.writeString(end);
        parcel.writeString(id);
        parcel.writeString(modified);
        parcel.writeParcelable(next, i);
        parcel.writeParcelable(previous, i);
        parcel.writeString(resourceURI);
        parcel.writeParcelable(series, i);
        parcel.writeString(start);
        parcel.writeParcelable(stories, i);
        parcel.writeParcelable(thumbnail, i);
        parcel.writeString(title);
        parcel.writeTypedList(urls);
    }
}
