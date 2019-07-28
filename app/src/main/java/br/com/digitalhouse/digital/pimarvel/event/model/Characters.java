
package br.com.digitalhouse.digital.pimarvel.event.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Characters implements Parcelable {

    @Expose
    private String available;

    @Expose
    private String collectionURI;

    @Expose
    private List<Item> items;

    @Expose
    private String returned;

    protected Characters(Parcel in) {
        available = in.readString();
        collectionURI = in.readString();
        returned = in.readString();
    }

    public static final Creator<Characters> CREATOR = new Creator<Characters>() {
        @Override
        public Characters createFromParcel(Parcel in) {
            return new Characters(in);
        }

        @Override
        public Characters[] newArray(int size) {
            return new Characters[size];
        }
    };

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(available);
        dest.writeString(collectionURI);
        dest.writeString(returned);
    }
}
