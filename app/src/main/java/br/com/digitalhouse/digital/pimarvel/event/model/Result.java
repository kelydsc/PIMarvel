
package br.com.digitalhouse.digital.pimarvel.event.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.List;

@Entity(tableName = "events")
public class Result implements Parcelable {

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

    public Result() {
    }

    protected Result(Parcel in) {
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
        start = in.readString();
        title = in.readString();
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(characters, flags);
        dest.writeParcelable(comics, flags);
        dest.writeParcelable(creators, flags);
        dest.writeString(description);
        dest.writeString(end);
        dest.writeString(id);
        dest.writeString(modified);
        dest.writeParcelable(next, flags);
        dest.writeParcelable(previous, flags);
        dest.writeString(resourceURI);
        dest.writeString(start);
        dest.writeString(title);
    }
}
