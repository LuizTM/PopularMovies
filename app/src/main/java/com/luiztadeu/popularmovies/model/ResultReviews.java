package com.luiztadeu.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultReviews implements Serializable {

    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
