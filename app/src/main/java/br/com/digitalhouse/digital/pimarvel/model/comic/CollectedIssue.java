
package br.com.digitalhouse.digital.pimarvel.model.comic;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class CollectedIssue implements Parcelable {

    @Expose
    private String name;

    @Expose
    private String resourceURI;

    public CollectedIssue() {
    }

    protected CollectedIssue(Parcel in) {
        name = in.readString();
        resourceURI = in.readString();
    }

    public static final Creator<CollectedIssue> CREATOR = new Creator<CollectedIssue>() {
        @Override
        public CollectedIssue createFromParcel(Parcel in) {
            return new CollectedIssue(in);
        }

        @Override
        public CollectedIssue[] newArray(int size) {
            return new CollectedIssue[size];
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(resourceURI);
    }
}
