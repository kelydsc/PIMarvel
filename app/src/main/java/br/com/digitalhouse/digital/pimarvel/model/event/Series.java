
package br.com.digitalhouse.digital.pimarvel.model.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.List;


public class Series implements Parcelable {

    @Expose
    private String available;

    @Expose
    private String collectionURI;

    @Expose
    private List<Item> items;

    @Expose
    private String returned;

    public Series() {
    }

    protected Series(Parcel in) {
        available = in.readString();
        collectionURI = in.readString();
        items = in.createTypedArrayList(Item.CREATOR);
        returned = in.readString();
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(available);
        parcel.writeString(collectionURI);
        parcel.writeTypedList(items);
        parcel.writeString(returned);
    }
}
