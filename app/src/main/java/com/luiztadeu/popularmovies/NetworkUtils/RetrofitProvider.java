package com.luiztadeu.popularmovies.NetworkUtils;

import android.net.Uri;

import com.luiztadeu.popularmovies.BuildConfig;

import java.net.URI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitProvider {

    public static <T> T getRetrofitBuilder(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.enpointMovies)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(clazz);
    }
}
