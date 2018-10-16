package com.luiztadeu.popularmovies.NetworkUtils.DAO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FavoritesModel{

    @PrimaryKey(autoGenerate = true)
    public Integer id;
    private String title;
    private Double voteAverage;
    private String overview;
    private String posterPath;
    private boolean saveDb;

    public FavoritesModel(boolean sabeDb) {
        this.saveDb = sabeDb;
    }

    public FavoritesModel(Integer id,
                          String title,
                          Double voteAverage,
                          String overview,
                          String posterPath,
                          boolean saveDb) {
        this.id = id;
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterPath = posterPath;
        this.saveDb = saveDb;

    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public boolean isSaveDb() {
        return saveDb;
    }
}
