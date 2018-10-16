package com.luiztadeu.popularmovies.NetworkUtils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesDAO;
import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesModel;

@Database(entities = {FavoritesModel.class}, version = 1, exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {

    private static MoviesDatabase INSTANCE;

    public static MoviesDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, MoviesDatabase.class, "movies_db")
                    .build();
        }
        return INSTANCE;
    }

    public abstract FavoritesDAO itemFavoriteModel();
}
