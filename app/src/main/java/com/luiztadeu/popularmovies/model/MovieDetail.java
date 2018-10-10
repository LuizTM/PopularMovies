package com.luiztadeu.popularmovies.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieDetail implements Serializable {

    @SerializedName("reviews")
    public Reviews reviews;

    @SerializedName("videos")
    public Videos videos;


    public Reviews getReviews() {
        return reviews;
    }

    public Videos getVideos() {
        return videos;
    }

    public class Videos implements Serializable {

        @SerializedName("results")
        public List<ReviewsTrailer> results = null;

        public List<ReviewsTrailer> getResults() {
            return results;
        }
    }

    public class Reviews implements Serializable {

        @SerializedName("results")
        public List<ResultReviews> results = null;

        public List<ResultReviews> getResults() {
            return results;
        }
    }
}
