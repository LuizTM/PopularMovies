package com.luiztadeu.popularmovies.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.luiztadeu.popularmovies.BuildConfig;
import com.luiztadeu.popularmovies.NetworkUtils.ApiMovies;
import com.luiztadeu.popularmovies.NetworkUtils.RetrofitProvider;
import com.luiztadeu.popularmovies.R;
import com.luiztadeu.popularmovies.UI.adapter.ReviewsAdapter;
import com.luiztadeu.popularmovies.UI.adapter.TrailerAdapter;
import com.luiztadeu.popularmovies.model.Result;
import com.luiztadeu.popularmovies.model.MovieDetail;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity
implements TrailerAdapter.TrailerActionListener {

    public static final String EXTRA_DETAIL_MOVIE = "movies_detail";
    private Result movie;

    private ImageView imgPoster;
    private TextView txtTitle;
    private TextView txtRating;
    private TextView txtReleaseDate;
    private TextView txtDescription;

    private RecyclerView lstTrailer;
    private RecyclerView lstReviews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        if (savedInstanceState != null) {
            movie = (Result) savedInstanceState.getSerializable(EXTRA_DETAIL_MOVIE);
        }
        getBundleExtras();
        init();
    }

    private void init() {
        imgPoster = findViewById(R.id.detail_img_poster_movie);
        txtTitle = findViewById(R.id.detail_txt_title);
        txtRating = findViewById(R.id.detail_txt_rating);
        txtReleaseDate = findViewById(R.id.detail_txt_release_date);
        txtDescription = findViewById(R.id.detail_txt_description);

        lstTrailer = findViewById(R.id.list_trailers);
        lstReviews = findViewById(R.id.list_reviews);

        callServiceTrailer();

        populateViews();
    }

    private void populateViews() {
        Picasso.with(this)
                .load(BuildConfig.enpointImgMoviesW500.concat(movie.getPosterPath()))
                .error(android.R.drawable.star_off)
                .placeholder(android.R.drawable.star_on)
                .resize(500, 300)
                .into(imgPoster);

        txtTitle.setText(String.format("%s", movie.getOriginalTitle()));
        txtRating.setText(String.format("Rating: %s", movie.getVoteAverage()));
        txtReleaseDate.setText(String.format("Date: %s", movie.getReleaseDate()));
        txtDescription.setText(String.format("Description: %s", movie.getOverview()));
    }

    private void getBundleExtras() {
        if (getIntent() != null
                && getIntent().getExtras() != null) {
            movie = (Result) getIntent().getExtras().getSerializable(EXTRA_DETAIL_MOVIE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_DETAIL_MOVIE, movie);
    }


    private void callServiceTrailer() {
        ApiMovies apiMovies = RetrofitProvider.getRetrofitBuilder(ApiMovies.class);
        Call<MovieDetail> apiMoviesCall = apiMovies.getMoviesTrailersAndReviews(movie.getId(), BuildConfig.appKey, "pt-BR", "videos,reviews");
        apiMoviesCall.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetail> call, @NonNull Response<MovieDetail> response) {
                MovieDetail movieDetail = response.body();
                configAdapterTrailer(movieDetail);
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetail> call, @NonNull Throwable t) {

            }
        });
    }

    private void configAdapterTrailer(MovieDetail movieDetail) {
        lstTrailer.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lstTrailer.setLayoutManager(layoutManager);
        lstTrailer.setAdapter(new TrailerAdapter(this, movieDetail));
        configAdapterReview(movieDetail);
    }

    private void configAdapterReview(MovieDetail movieDetail) {
        lstTrailer.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lstTrailer.setLayoutManager(layoutManager);
        lstTrailer.setAdapter(new ReviewsAdapter(movieDetail.getReviews()));
    }

    @Override
    public void onActionClick(Intent intent) {
        startActivity(intent);
    }
}
