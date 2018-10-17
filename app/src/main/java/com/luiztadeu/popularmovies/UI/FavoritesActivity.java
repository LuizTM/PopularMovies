package com.luiztadeu.popularmovies.UI;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesModel;
import com.luiztadeu.popularmovies.NetworkUtils.MoviesDatabase;
import com.luiztadeu.popularmovies.R;
import com.luiztadeu.popularmovies.UI.adapter.MoviesAdapter;
import com.luiztadeu.popularmovies.model.Result;
import com.luiztadeu.popularmovies.viewmodel.FavoritesViewModel;
import com.luiztadeu.popularmovies.viewmodel.FavoritesViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity
        implements HomeView.ActionClickListListener {

    private RecyclerView mRecyclerView;
    private FavoritesViewModel favoritesViewModel;
    private MoviesAdapter adapter;
    private MoviesDatabase moviesDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        initViews();

        moviesDatabase = MoviesDatabase.getDatabase(getApplicationContext());

        FavoritesViewModelFactory factory = new FavoritesViewModelFactory(moviesDatabase);

        favoritesViewModel = ViewModelProviders.of(this, factory).get(FavoritesViewModel.class);

        favoritesViewModel.getAllMoviesFavorites().observe(this, new Observer<List<FavoritesModel>>() {
            @Override
            public void onChanged(@Nullable List<FavoritesModel> favoritesModels) {
                callMoviesFavorites(favoritesModels);
            }
        });
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.movies_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void callMoviesFavorites(List<FavoritesModel> favorites) {
        List<Result> results = new ArrayList<>();
        assert favorites != null;
        for (FavoritesModel model : favorites) {
            results.add(new Result(model.getId(),
                    model.getVoteAverage(),
                    model.getTitle(),
                    model.getPosterPath(),
                    model.getReleaseDate(),
                    model.getOverview(),
                    model.isSaveDb()));
        }
        adapter = new MoviesAdapter(results, this, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickListenerList(Result result) {
        Intent intent = new Intent(this, DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.EXTRA_DETAIL_MOVIE, result);
        startActivityForResult(intent, HomeMoviesActivity.REQUEST_CODE_RESULT);
    }

    @Override
    public void onAddFavorites(Result result) {
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
        favoritesViewModel.deleteItem(model);
    }
}
