
package br.com.digitalhouse.digital.pimarvel.event.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Previous implements Parcelable {

    @Expose
    private String name;
    @Expose
    private String resourceURI;

    protected Previous(Parcel in) {
        name = in.readString();
        resourceURI = in.readString();
    }

    public static final Creator<Previous> CREATOR = new Creator<Previous>() {
        @Override
        public Previous createFromParcel(Parcel in) {
            return new Previous(in);
        }

        @Override
        public Previous[] newArray(int size) {
            return new Previous[size];
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
