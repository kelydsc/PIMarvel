
package br.com.digitalhouse.digital.pimarvel.model.serie;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.List;

@Entity(tableName = "series")
public class Serie implements Parcelable {

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

    //atributo para controlar os favoritos
    @Expose
    private boolean favorite;

    //atributo para controlar os favoritos
    @Expose
    private String loginUserSerie;

    //atributo para controlar os favoritos de Serie
    @Expose
    private String serieFavorito;

    public Serie() {
    }

    protected Serie(Parcel in) {
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
        favorite = in.readByte() != 0;
        loginUserSerie = in.readString();
        serieFavorito = in.readString();
    }

    public static final Creator<Serie> CREATOR = new Creator<Serie>() {
        @Override
        public Serie createFromParcel(Parcel in) {
            return new Serie(in);
        }

        @Override
        public Serie[] newArray(int size) {
            return new Serie[size];
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

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getLoginUserSerie() {
        return loginUserSerie;
    }

    public void setLoginUserSerie(String loginUserSerie) {
        this.loginUserSerie = loginUserSerie;
    }

    public String getSerieFavorito() {
        return serieFavorito;
    }

    public void setSerieFavorito(String serieFavorito) {
        this.serieFavorito = serieFavorito;
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
        parcel.writeString(endYear);
        parcel.writeParcelable(events, i);
        parcel.writeString(id);
        parcel.writeString(modified);
        parcel.writeParcelable(next, i);
        parcel.writeParcelable(previous, i);
        parcel.writeString(rating);
        parcel.writeString(resourceURI);
        parcel.writeString(startYear);
        parcel.writeParcelable(stories, i);
        parcel.writeParcelable(thumbnail, i);
        parcel.writeString(title);
        parcel.writeTypedList(urls);
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeString(loginUserSerie);
        parcel.writeString(serieFavorito);
    }
}
