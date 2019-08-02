
package br.com.digitalhouse.digital.pimarvel.model.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;


public class Url implements Parcelable {

    @Expose
    private String type;

    @Expose
    private String url;

    protected Url(Parcel in) {
        type = in.readString();
        url = in.readString();
    }

    public static final Creator<Url> CREATOR = new Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel in) {
            return new Url(in);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(url);
    }
}
