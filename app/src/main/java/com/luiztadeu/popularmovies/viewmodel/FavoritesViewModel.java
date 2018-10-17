package com.luiztadeu.popularmovies.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.luiztadeu.popularmovies.NetworkUtils.AppExecutors;
import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesModel;
import com.luiztadeu.popularmovies.NetworkUtils.MoviesDatabase;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    private LiveData<List<FavoritesModel>> favoritesModel;
    private MoviesDatabase database;

    public FavoritesViewModel(MoviesDatabase database) {
        this.database = database;
        favoritesModel = database.itemFavoriteModel().getAllFavorites();
    }

    public LiveData<List<FavoritesModel>> getAllMoviesFavorites() {
        return favoritesModel;
    }

    public void deleteItem(final FavoritesModel favoritesModel) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.itemFavoriteModel().deleteFavoriteMovie(favoritesModel);
            }
        });
    }
}
