
package br.com.digitalhouse.digital.pimarvel.event.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("limit")
    private Long mLimit;
    @SerializedName("offset")
    private Long mOffset;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("total")
    private Long mTotal;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Long getLimit() {
        return mLimit;
    }

    public void setLimit(Long limit) {
        mLimit = limit;
    }

    public Long getOffset() {
        return mOffset;
    }

    public void setOffset(Long offset) {
        mOffset = offset;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
