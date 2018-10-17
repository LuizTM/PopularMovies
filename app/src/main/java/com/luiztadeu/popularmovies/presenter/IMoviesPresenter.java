package com.luiztadeu.popularmovies.presenter;

import com.luiztadeu.popularmovies.model.Movie;
import com.luiztadeu.popularmovies.model.Result;

import java.util.ArrayList;
import java.util.List;

public interface IMoviesPresenter {

    void populateView(List<Result> movies);
    void notHasInternet();
}
