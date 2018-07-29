package com.luiztadeu.popularmovies.UI;

import com.luiztadeu.popularmovies.model.Movie;
import com.luiztadeu.popularmovies.model.Result;

public interface HomeView {

    void showLoading();
    void hideLoading();
    void populateView(Movie movies);

    void callMoviesPopular();
    void callMoviesTopRated();

    void showFailureConnection();
    void showSuccessConnection();

    interface ActionClickListListener{
        void onClickListenerList(Result result);
    }
}
