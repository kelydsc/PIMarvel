package br.com.digitalhouse.digital.pimarvel.model.favorite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import br.com.digitalhouse.digital.pimarvel.model.comic.Comic;
import br.com.digitalhouse.digital.pimarvel.model.event.Event;
import br.com.digitalhouse.digital.pimarvel.model.serie.Serie;

@Entity(tableName = "favorites")
public class Favorite implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long tableId;

    @Expose
    private String loginUser;

    @Expose
    private String idComic;

    @Expose
    private String idEvent;

    @Expose
    private String idSerie;

    @Expose
    private Comic comicFavorite;

    @Expose
    private Event eventFavorite;

    @Expose
    private Serie serieFavorite;

    public Favorite() {
    }

    protected Favorite(Parcel in) {
        tableId = in.readLong();
        loginUser = in.readString();
        idComic = in.readString();
        idEvent = in.readString();
        idSerie = in.readString();
        comicFavorite = in.readParcelable(Comic.class.getClassLoader());
        eventFavorite = in.readParcelable(Event.class.getClassLoader());
        serieFavorite = in.readParcelable(Serie.class.getClassLoader());
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getIdComic() {
        return idComic;
    }

    public void setIdComic(String idComic) {
        this.idComic = idComic;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    public Comic getComicFavorite() {
        return comicFavorite;
    }

    public void setComicFavorite(Comic comicFavorite) {
        this.comicFavorite = comicFavorite;
    }

    public Event getEventFavorite() {
        return eventFavorite;
    }

    public void setEventFavorite(Event eventFavorite) {
        this.eventFavorite = eventFavorite;
    }

    public Serie getSerieFavorite() {
        return serieFavorite;
    }

    public void setSerieFavorite(Serie serieFavorite) {
        this.serieFavorite = serieFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(tableId);
        parcel.writeString(loginUser);
        parcel.writeString(idComic);
        parcel.writeString(idEvent);
        parcel.writeString(idSerie);
        parcel.writeParcelable(comicFavorite, i);
        parcel.writeParcelable(eventFavorite, i);
        parcel.writeParcelable(serieFavorite, i);
    }
}
