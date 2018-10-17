package com.luiztadeu.popularmovies.repository;

import android.support.annotation.NonNull;

import com.luiztadeu.popularmovies.BuildConfig;
import com.luiztadeu.popularmovies.NetworkUtils.ApiMovies;
import com.luiztadeu.popularmovies.NetworkUtils.ConnectionsUtils;
import com.luiztadeu.popularmovies.NetworkUtils.RetrofitProvider;
import com.luiztadeu.popularmovies.UI.HomeView;
import com.luiztadeu.popularmovies.model.Movie;
import com.luiztadeu.popularmovies.presenter.IMoviesPresenter;
import com.luiztadeu.popularmovies.presenter.MoviesPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository implements IMoviesRepository {

    private ApiMovies apiMovies;
    private IMoviesPresenter mPresenter;

    private static final int FILMES_POPULAR = 0;
    private static final int FILMES_TOP_RATED = 1;

    public MoviesRepository(HomeView view) {
        mPresenter = new MoviesPresenter(view);
        apiMovies = RetrofitProvider.getRetrofitBuilder(ApiMovies.class);
    }

    @Override
    public void serviceMoviesPopular() {
        checkConnetion(FILMES_POPULAR);
    }

    @Override
    public void serviceMoviesTopRated() {
        checkConnetion(FILMES_TOP_RATED);
    }

    private void moviesPopular() {
        Call<Movie> apiMoviesCall = apiMovies.getMoviesPopular(BuildConfig.appKey, "pt-BR");
        apiMoviesCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                Movie popular = response.body();
                mPresenter.populateView(popular.getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {

            }
        });
    }

    private void moviesTopRated() {
        Call<Movie> apiMoviesCall = apiMovies.getMoviesTopRated(BuildConfig.appKey, "pt-BR");
        apiMoviesCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                Movie topRated = response.body();
                mPresenter.populateView(topRated.getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {

            }
        });
    }

    private void checkConnetion(final int typeMovie) {
        ConnectionsUtils.isConnected(new IMoviesRepository.Consumer() {
            @Override
            public void dontHasInternet() {
                mPresenter.notHasInternet();
            }

            @Override
            public void hasInternet() {
                switch (typeMovie) {
                    case FILMES_POPULAR:
                        moviesPopular();
                        break;

                    case FILMES_TOP_RATED:
                        moviesTopRated();
                        break;
                }
            }
        });
    }
}
