package com.luiztadeu.popularmovies.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.luiztadeu.popularmovies.BuildConfig;
import com.luiztadeu.popularmovies.R;
import com.luiztadeu.popularmovies.model.Result;
import com.squareup.picasso.Picasso;

public class DetailMovieAcivity extends AppCompatActivity {

    public static final String EXTRA_DETAIL_MOVIE = "movies_detail";
    private Result movie;

    private ImageView imgPoster;
    private TextView txtTitle;
    private TextView txtRating;
    private TextView txtReleaseDate;
    private TextView txtDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        if (savedInstanceState != null){
            movie = (Result) savedInstanceState.getSerializable(EXTRA_DETAIL_MOVIE);
        }
        getBundleExtras();
        initView();
    }

    private void initView() {
        imgPoster = findViewById(R.id.detail_img_poster_movie);
        txtTitle = findViewById(R.id.detail_txt_title);
        txtRating = findViewById(R.id.detail_txt_rating);
        txtReleaseDate = findViewById(R.id.detail_txt_release_date);
        txtDescription = findViewById(R.id.detail_txt_description);

        populateViews();
    }

    private void populateViews() {
        Picasso.with(this)
                .load(BuildConfig.enpointImgMoviesW500.concat(movie.getPosterPath()))
                .error(android.R.drawable.star_off)
                .resize(500, 300)
                .placeholder(android.R.drawable.star_on)
                .into(imgPoster);

        txtTitle.setText(String.format("%s", movie.getOriginalTitle()));
        txtRating.setText(String.format("Rating: %s", movie.getVoteAverage()));
        txtReleaseDate.setText(String.format("Date: %s", movie.getReleaseDate()));
        txtDescription.setText(String.format("Description: %s", movie.getOverview()));
    }

    private void getBundleExtras(){
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
}
