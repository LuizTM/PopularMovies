package com.luiztadeu.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.luiztadeu.popularmovies.NetworkUtils.AppExecutors;
import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesModel;
import com.luiztadeu.popularmovies.NetworkUtils.MoviesDatabase;
import com.luiztadeu.popularmovies.model.Result;

import java.util.List;

public class MoviesViewModel extends AndroidViewModel {

    private final LiveData<List<FavoritesModel>> itemFavoritesMovies;
    private MoviesDatabase moviesDatabase;

    public MoviesViewModel(@NonNull Application application) {
        super(application);

        moviesDatabase = MoviesDatabase.getDatabase(this.getApplication());
        itemFavoritesMovies = moviesDatabase.itemFavoriteModel().getAllFavorites();
    }

    public LiveData<List<FavoritesModel>> getItemFavoritesMovies() {
        return itemFavoritesMovies;
    }

    public void addItem(final FavoritesModel favoritesModel) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                moviesDatabase.itemFavoriteModel().addFavoriteMovie(favoritesModel);
            }
        });
    }

    public void deleteItem(final FavoritesModel favoritesModel) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                moviesDatabase.itemFavoriteModel().deleteFavoriteMovie(favoritesModel);
            }
        });
    }
}
