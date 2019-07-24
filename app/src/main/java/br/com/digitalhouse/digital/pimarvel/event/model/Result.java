
package br.com.digitalhouse.digital.pimarvel.event.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Result {

    @SerializedName("characters")
    private Characters mCharacters;
    @SerializedName("comics")
    private Comics mComics;
    @SerializedName("creators")
    private Creators mCreators;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("end")
    private String mEnd;
    @SerializedName("id")
    private Long mId;
    @SerializedName("modified")
    private String mModified;
    @SerializedName("next")
    private Next mNext;
    @SerializedName("previous")
    private Previous mPrevious;
    @SerializedName("resourceURI")
    private String mResourceURI;
    @SerializedName("series")
    private Series mSeries;
    @SerializedName("start")
    private String mStart;
    @SerializedName("stories")
    private Stories mStories;
    @SerializedName("thumbnail")
    private Thumbnail mThumbnail;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("urls")
    private List<Url> mUrls;

    public Characters getCharacters() {
        return mCharacters;
    }

    public void setCharacters(Characters characters) {
        mCharacters = characters;
    }

    public Comics getComics() {
        return mComics;
    }

    public void setComics(Comics comics) {
        mComics = comics;
    }

    public Creators getCreators() {
        return mCreators;
    }

    public void setCreators(Creators creators) {
        mCreators = creators;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getEnd() {
        return mEnd;
    }

    public void setEnd(String end) {
        mEnd = end;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }

    public Next getNext() {
        return mNext;
    }

    public void setNext(Next next) {
        mNext = next;
    }

    public Previous getPrevious() {
        return mPrevious;
    }

    public void setPrevious(Previous previous) {
        mPrevious = previous;
    }

    public String getResourceURI() {
        return mResourceURI;
    }

    public void setResourceURI(String resourceURI) {
        mResourceURI = resourceURI;
    }

    public Series getSeries() {
        return mSeries;
    }

    public void setSeries(Series series) {
        mSeries = series;
    }

    public String getStart() {
        return mStart;
    }

    public void setStart(String start) {
        mStart = start;
    }

    public Stories getStories() {
        return mStories;
    }

    public void setStories(Stories stories) {
        mStories = stories;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        mThumbnail = thumbnail;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<Url> getUrls() {
        return mUrls;
    }

    public void setUrls(List<Url> urls) {
        mUrls = urls;
    }

}
