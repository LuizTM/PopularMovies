package com.luiztadeu.popularmovies.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.luiztadeu.popularmovies.NetworkUtils.MoviesDatabase;

public class FavoritesViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesDatabase mDb;

    public FavoritesViewModelFactory(MoviesDatabase database) {
        mDb = database;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new FavoritesViewModel(mDb);
    }
}
