package com.luiztadeu.popularmovies.presenter;

import com.luiztadeu.popularmovies.model.Movie;

public interface IMoviesPresenter {

    void populateView(Movie movies);
    void notHasInternet();
}
