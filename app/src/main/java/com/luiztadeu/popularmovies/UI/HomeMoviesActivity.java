package com.luiztadeu.popularmovies.UI;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesModel;
import com.luiztadeu.popularmovies.R;
import com.luiztadeu.popularmovies.UI.adapter.MoviesAdapter;
import com.luiztadeu.popularmovies.model.Result;
import com.luiztadeu.popularmovies.repository.MoviesRepository;
import com.luiztadeu.popularmovies.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeMoviesActivity extends AppCompatActivity
        implements HomeView, HomeView.ActionClickListListener {

    private static final String BUNDLE_MOVIES = "movies";
    public static final int REQUEST_CODE_RESULT = 0;

    private RecyclerView mRecyclerView;
    private List<Result> movies;
    private MoviesRepository mRepository;
    private ContentLoadingProgressBar mLoadingProgressBar;
    private MoviesViewModel viewModel;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_movies);
        initViews();
        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList(BUNDLE_MOVIES);
        }
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        viewModel.getItemFavoritesMovies().observe(this, new Observer<List<FavoritesModel>>() {
            @Override
            public void onChanged(@Nullable List<FavoritesModel> favoritesModels) {
                Log.i("HomeMoviesActivity", "observeCalling");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(BUNDLE_MOVIES, (ArrayList<? extends Parcelable>) movies);
    }

    private void initViews() {
        mLoadingProgressBar = findViewById(R.id.address_looking_up);
        mRecyclerView = findViewById(R.id.movies_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRepository = new MoviesRepository(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        callMovies();
    }

    private void callMovies() {
        if (movies == null) {
            callMoviesPopular();
        } else {
            populateView(movies);
        }
    }

    @Override
    public void showSuccessConnection() {
        LinearLayout connectionFailure = findViewById(R.id.movies_linear_connection_off);
        connectionFailure.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFailureConnection() {
        final LinearLayout connectionFailure = findViewById(R.id.movies_linear_connection_off);
        Button btnRetry = findViewById(R.id.movies_btn_retry);
        connectionFailure.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectionFailure.setVisibility(View.GONE);
                callMovies();
            }
        });
    }

    @Override
    public void populateView(List<Result> movies) {
        this.movies = movies;
        adapter = new MoviesAdapter(
                movies,
                this,
                this);
        mRecyclerView.setAdapter(adapter);
        hideLoading();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_movies_popular:
                callMoviesPopular();
                return true;
            case R.id.menu_movies_top_rated:
                callMoviesTopRated();
                return true;
            case R.id.menu_movies_favority:
                callMoviesFavorites();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void callMoviesFavorites() {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClickListenerList(Result result) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_MOVIE, result);
        startActivity(intent);
    }

    @Override
    public void onAddFavorites(Result result) {
        final FavoritesModel model = new FavoritesModel(result.getId(),
                result.getTitle(),
                result.getVoteAverage(),
                result.getOverview(),
                result.getPosterPath(),
                result.getReleaseDate(),
                result.isSavedDb());
        viewModel.addItem(model);
    }

    @Override
    public void onDeleteFavorite(Result result) {
        final FavoritesModel model = new FavoritesModel(result.getId(),
                result.getTitle(),
                result.getVoteAverage(),
                result.getOverview(),
                result.getPosterPath(),
                result.getReleaseDate(),
                result.isSavedDb());
        viewModel.deleteItem(model);
    }

    @Override
    public void callMoviesPopular() {
        showLoading();
        mRepository.serviceMoviesPopular();
    }

    @Override
    public void callMoviesTopRated() {
        showLoading();
        mRepository.serviceMoviesTopRated();
    }

    @Override
    public void showLoading() {
        if (mLoadingProgressBar != null)
            mLoadingProgressBar.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingProgressBar != null)
            mLoadingProgressBar.hide();
    }
}
