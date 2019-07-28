
package br.com.digitalhouse.digital.pimarvel.comic.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.List;

@Entity(tableName = "comics")
public class Result implements Parcelable {

    @Expose
    private Characters characters;

    @Expose
    private List<CollectedIssue> collectedIssues;

    @Expose
    private List<Collection> collections;

    @Expose
    private Creators creators;

    @Expose
    private List<Date> dates;

    @Expose
    private String description;

    @Expose
    private String diamondCode;

    @Expose
    private String digitalId;

    @Expose
    private String ean;

    @Expose
    private Events events;

    @Expose
    private String format;

    @Expose
    private String id;

    @Expose
    private List<Image> images;

    @Expose
    private String isbn;

    @Expose
    private String issn;

    @Expose
    private String issueNumber;

    @Expose
    private String modified;

    @Expose
    private String pageCount;

    @Expose
    private List<Price> prices;

    @Expose
    private String resourceURI;

    @Expose
    private Series series;

    @Expose
    private Stories stories;

    @Expose
    private List<TextObject> textObjects;

    @Expose
    private Thumbnail thumbnail;

    @Expose
    private String title;

    @Expose
    private String upc;

    @Expose
    private List<Url> urls;

    @Expose
    private String variantDescription;

    @Expose
    private List<Variant> variants;

    public Result() {
    }

    protected Result(Parcel in) {
        characters = in.readParcelable(Characters.class.getClassLoader());
        collectedIssues = in.createTypedArrayList(CollectedIssue.CREATOR);
        collections = in.createTypedArrayList(Collection.CREATOR);
        creators = in.readParcelable(Creators.class.getClassLoader());
        dates = in.createTypedArrayList(Date.CREATOR);
        description = in.readString();
        diamondCode = in.readString();
        digitalId = in.readString();
        ean = in.readString();
        events = in.readParcelable(Events.class.getClassLoader());
        format = in.readString();
        id = in.readString();
        images = in.createTypedArrayList(Image.CREATOR);
        isbn = in.readString();
        issn = in.readString();
        issueNumber = in.readString();
        modified = in.readString();
        pageCount = in.readString();
        prices = in.createTypedArrayList(Price.CREATOR);
        resourceURI = in.readString();
        title = in.readString();
        upc = in.readString();
        variantDescription = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public List<CollectedIssue> getCollectedIssues() {
        return collectedIssues;
    }

    public void setCollectedIssues(List<CollectedIssue> collectedIssues) {
        this.collectedIssues = collectedIssues;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public Creators getCreators() {
        return creators;
    }

    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public String getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(String digitalId) {
        this.digitalId = digitalId;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Stories getStories() {
        return stories;
    }

    public void setStories(Stories stories) {
        this.stories = stories;
    }

    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(List<TextObject> textObjects) {
        this.textObjects = textObjects;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(characters, flags);
        dest.writeTypedList(collectedIssues);
        dest.writeTypedList(collections);
        dest.writeParcelable(creators, flags);
        dest.writeTypedList(dates);
        dest.writeString(description);
        dest.writeString(diamondCode);
        dest.writeString(digitalId);
        dest.writeString(ean);
        dest.writeParcelable(events, flags);
        dest.writeString(format);
        dest.writeString(id);
        dest.writeTypedList(images);
        dest.writeString(isbn);
        dest.writeString(issn);
        dest.writeString(issueNumber);
        dest.writeString(modified);
        dest.writeString(pageCount);
        dest.writeTypedList(prices);
        dest.writeString(resourceURI);
        dest.writeString(title);
        dest.writeString(upc);
        dest.writeString(variantDescription);
    }
}
