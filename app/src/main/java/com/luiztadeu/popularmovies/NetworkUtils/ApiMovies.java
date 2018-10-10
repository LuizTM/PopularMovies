package com.luiztadeu.popularmovies.NetworkUtils;

import com.luiztadeu.popularmovies.model.Movie;
import com.luiztadeu.popularmovies.model.MovieDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMovies {

    @GET("/3/movie/popular")
    Call<Movie> getMoviesPopular(@Query("api_key") String appkey,
                                 @Query("language") String language);

    @GET("/3/movie/top_rated")
    Call<Movie> getMoviesTopRated(@Query("api_key") String appkey,
                                  @Query("language") String language);

    @GET("/3/movie/{id_movie}")
    Call<MovieDetail> getMoviesTrailersAndReviews(@Path("id_movie") int id_movie,
                                                  @Query("api_key") String appkey,
                                                  @Query("language") String language,
                                                  @Query("append_to_response") String append);

}
