package com.luiztadeu.popularmovies.UI;
import com.luiztadeu.popularmovies.model.Result;
import java.util.List;

public interface HomeView {

    void showLoading();
    void hideLoading();
    void populateView(List<Result> movies);

    void callMoviesPopular();
    void callMoviesTopRated();

    void showFailureConnection();
    void showSuccessConnection();

    interface ActionClickListListener{
        void onClickListenerList(Result result);
        void onAddFavorites(Result result);
    }
}
