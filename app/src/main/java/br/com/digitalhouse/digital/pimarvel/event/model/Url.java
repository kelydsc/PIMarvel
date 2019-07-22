
package br.com.digitalhouse.digital.pimarvel.event.model;


import com.google.gson.annotations.SerializedName;


public class Url {

    @SerializedName("type")
    private String mType;
    @SerializedName("url")
    private String mUrl;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
