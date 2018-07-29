package com.luiztadeu.popularmovies.presenter;

import com.luiztadeu.popularmovies.UI.HomeMoviesActivity;
import com.luiztadeu.popularmovies.UI.HomeView;
import com.luiztadeu.popularmovies.model.Movie;

public class MoviesPresenter implements IMoviesPresenter {

    private HomeView view;

    public MoviesPresenter(HomeView view) {
        this.view = view;
    }

    @Override
    public void populateView(Movie movies) {
        view.showSuccessConnection();
        view.populateView(movies);
    }

    @Override
    public void notHasInternet() {
        view.hideLoading();
        view.showFailureConnection();
    }
}
