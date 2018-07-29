package com.luiztadeu.popularmovies.NetworkUtils;

import com.luiztadeu.popularmovies.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMovies {

    @GET("/3/movie/popular")
    Call<Movie> getMoviesPopular(@Query("api_key") String appkey,
                                 @Query("language") String language);

    @GET("/3/movie/top_rated")
    Call<Movie> getMoviesTopRated(@Query("api_key") String appkey,
                                  @Query("language") String language);

}
