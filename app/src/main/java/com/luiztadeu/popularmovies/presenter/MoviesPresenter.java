package com.luiztadeu.popularmovies.presenter;

import com.luiztadeu.popularmovies.UI.HomeMoviesActivity;
import com.luiztadeu.popularmovies.UI.HomeView;
import com.luiztadeu.popularmovies.model.Movie;
import com.luiztadeu.popularmovies.model.Result;

import java.util.ArrayList;
import java.util.List;

public class MoviesPresenter implements IMoviesPresenter {

    private HomeView view;

    public MoviesPresenter(HomeView view) {
        this.view = view;
    }

    @Override
    public void populateView(List<Result> movies) {
        view.showSuccessConnection();
        view.populateView(movies);
    }

    @Override
    public void notHasInternet() {
        view.hideLoading();
        view.showFailureConnection();
    }
}
