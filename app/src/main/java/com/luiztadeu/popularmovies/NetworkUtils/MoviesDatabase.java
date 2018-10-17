package com.luiztadeu.popularmovies.NetworkUtils;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesDAO;
import com.luiztadeu.popularmovies.NetworkUtils.DAO.FavoritesModel;

@Database(entities = {FavoritesModel.class}, version = 1, exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {

    private static MoviesDatabase INSTANCE;

    public static MoviesDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MoviesDatabase.class, "movies_db")
//                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return INSTANCE;
    }

    public abstract FavoritesDAO itemFavoriteModel();

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE FavoritesModel ADD COLUMN releaseDate TEXT");

        }
    };
}
