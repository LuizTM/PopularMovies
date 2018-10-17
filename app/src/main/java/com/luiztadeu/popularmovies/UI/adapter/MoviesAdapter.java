package com.luiztadeu.popularmovies.UI.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luiztadeu.popularmovies.BuildConfig;
import com.luiztadeu.popularmovies.R;
import com.luiztadeu.popularmovies.UI.HomeView;
import com.luiztadeu.popularmovies.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter
        extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Result> movies;
    private HomeView.ActionClickListListener actionClickListListener;
    private Context context;

    public MoviesAdapter(List<Result> movies,
                         HomeView.ActionClickListListener actionClickListListener,
                         Context context) {
        this.movies = movies;
        this.actionClickListListener = actionClickListListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_list_grid_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Result movie = movies.get(position);
        Picasso.with(context)
                .load(BuildConfig.enpointImgMoviesW185
                        .concat(movie.getPosterPath()))
                .resize(500, 750)
                .error(android.R.drawable.star_on)
                .placeholder(android.R.drawable.star_off)
                .into(holder.imagePoster);

        holder.imgAddFavorite.setImageResource(movie.isSavedDb() ?
                android.R.drawable.star_big_on : android.R.drawable.star_big_off);

        holder.imagePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClickListListener.onClickListenerList(movie);
            }
        });

        holder.imgAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movie.isSavedDb()) {
                    movie.setSavedDb(false);
                    actionClickListListener.onDeleteFavorite(movie);
                    holder.imgAddFavorite.setImageResource(android.R.drawable.star_big_off);
                } else {
                    movie.setSavedDb(true);
                    actionClickListListener.onAddFavorites(movie);
                    holder.imgAddFavorite.setImageResource(android.R.drawable.star_big_on);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void onChangeFavorites(List<Result> favorites) {
        movies = favorites;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagePoster;
        ImageView imgAddFavorite;

        ViewHolder(View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.img_movies_poster);
            imgAddFavorite = itemView.findViewById(R.id.img_add_favorite);
        }
    }
}
