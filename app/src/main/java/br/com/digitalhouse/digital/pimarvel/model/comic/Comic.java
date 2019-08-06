
package br.com.digitalhouse.digital.pimarvel.model.comic;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.util.List;

@Entity(tableName = "comics")
public class Comic implements Parcelable {

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
    @PrimaryKey
    @NonNull
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

    //atributo para controlar os favoritos
    @Expose
    private boolean favorite;

    //atributo para controlar os favoritos
    @Expose
    private String loginUserComic;

    public Comic() {
    }

    protected Comic(Parcel in) {
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
        series = in.readParcelable(Series.class.getClassLoader());
        stories = in.readParcelable(Stories.class.getClassLoader());
        textObjects = in.createTypedArrayList(TextObject.CREATOR);
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        title = in.readString();
        upc = in.readString();
        urls = in.createTypedArrayList(Url.CREATOR);
        variantDescription = in.readString();
        variants = in.createTypedArrayList(Variant.CREATOR);
        favorite = in.readByte() != 0;
        loginUserComic = in.readString();
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
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

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getLoginUserComic() {
        return loginUserComic;
    }

    public void setLoginUserComic(String loginUserComic) {
        this.loginUserComic = loginUserComic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(characters, i);
        parcel.writeTypedList(collectedIssues);
        parcel.writeTypedList(collections);
        parcel.writeParcelable(creators, i);
        parcel.writeTypedList(dates);
        parcel.writeString(description);
        parcel.writeString(diamondCode);
        parcel.writeString(digitalId);
        parcel.writeString(ean);
        parcel.writeParcelable(events, i);
        parcel.writeString(format);
        parcel.writeString(id);
        parcel.writeTypedList(images);
        parcel.writeString(isbn);
        parcel.writeString(issn);
        parcel.writeString(issueNumber);
        parcel.writeString(modified);
        parcel.writeString(pageCount);
        parcel.writeTypedList(prices);
        parcel.writeString(resourceURI);
        parcel.writeParcelable(series, i);
        parcel.writeParcelable(stories, i);
        parcel.writeTypedList(textObjects);
        parcel.writeParcelable(thumbnail, i);
        parcel.writeString(title);
        parcel.writeString(upc);
        parcel.writeTypedList(urls);
        parcel.writeString(variantDescription);
        parcel.writeTypedList(variants);
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeString(loginUserComic);
    }
}
