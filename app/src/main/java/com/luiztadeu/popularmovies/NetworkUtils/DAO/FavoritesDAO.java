package com.luiztadeu.popularmovies.NetworkUtils.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface FavoritesDAO {

    @Query("SELECT * FROM FavoritesModel")
    LiveData<List<FavoritesModel>> getAllFavorites();

    @Query("SELECT * FROM FavoritesModel WHERE id = :id")
    List<FavoritesModel> getItembyId(String id);

    @Insert(onConflict = REPLACE)
    void addFavoriteMovie(FavoritesModel borrowModel);

    @Delete
    void deleteFavoriteMovie(FavoritesModel borrowModel);
}
