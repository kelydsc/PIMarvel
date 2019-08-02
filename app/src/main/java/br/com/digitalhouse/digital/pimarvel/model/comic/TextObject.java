
package br.com.digitalhouse.digital.pimarvel.model.comic;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class TextObject implements Parcelable {

    @Expose
    private String language;

    @Expose
    private String text;

    @Expose
    private String type;

    public TextObject() {
    }

    protected TextObject(Parcel in) {
        language = in.readString();
        text = in.readString();
        type = in.readString();
    }

    public static final Creator<TextObject> CREATOR = new Creator<TextObject>() {
        @Override
        public TextObject createFromParcel(Parcel in) {
            return new TextObject(in);
        }

        @Override
        public TextObject[] newArray(int size) {
            return new TextObject[size];
        }
    };

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(language);
        parcel.writeString(text);
        parcel.writeString(type);
    }
}
