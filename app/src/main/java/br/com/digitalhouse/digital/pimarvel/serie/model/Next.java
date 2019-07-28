
package br.com.digitalhouse.digital.pimarvel.serie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Next implements Parcelable {

    @Expose
    private String name;
    @Expose
    private String resourceURI;

    protected Next(Parcel in) {
        name = in.readString();
        resourceURI = in.readString();
    }

    public static final Creator<Next> CREATOR = new Creator<Next>() {
        @Override
        public Next createFromParcel(Parcel in) {
            return new Next(in);
        }

        @Override
        public Next[] newArray(int size) {
            return new Next[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(resourceURI);
    }
}
