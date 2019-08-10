
package br.com.digitalhouse.digital.pimarvel.model.comic;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data implements Parcelable {

    @Expose
    private String count;

    @Expose
    private String limit;

    @Expose
    private String offset;

    @Expose
    @SerializedName("results")
    private List<Comic> comics;

    @Expose
    private String total;

    public Data() {
    }

    protected Data(Parcel in) {
        count = in.readString();
        limit = in.readString();
        offset = in.readString();
        comics = in.createTypedArrayList(Comic.CREATOR);
        total = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void setComics(List<Comic> comics) {
        this.comics = comics;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(count);
        parcel.writeString(limit);
        parcel.writeString(offset);
        parcel.writeTypedList(comics);
        parcel.writeString(total);
    }
}
