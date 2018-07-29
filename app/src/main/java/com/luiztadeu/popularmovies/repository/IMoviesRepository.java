package com.luiztadeu.popularmovies.repository;

public interface IMoviesRepository {

    void serviceMoviesPopular();

    void serviceMoviesTopRated();

    interface CallbackService {
        void onSuccess();
        void onError();
    }

    interface Consumer {
        void hasInternet();
        void dontHasInternet();
    }
}
