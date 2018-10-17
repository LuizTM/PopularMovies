package com.luiztadeu.popularmovies.UI.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luiztadeu.popularmovies.R;
import com.luiztadeu.popularmovies.model.MovieDetail;
import com.squareup.picasso.Picasso;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private MovieDetail movieDetail;
    private TrailerActionListener trailerActionListener;

    public TrailerAdapter(TrailerActionListener trailerActionListener, MovieDetail movieDetail) {
        this.trailerActionListener = trailerActionListener;
        this.movieDetail = movieDetail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view_trailer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return movieDetail.getVideos().getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.img_thumbnail_trailer);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = movieDetail.getVideos().getResults().get(getAdapterPosition()).getKey();
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" +
                            id));

                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v=" + id));

                    trailerActionListener.onActionClick(webIntent);
                }
            });
        }

        void bind(int position) {
            Picasso.with(thumbnail.getContext())
                    .load("https://img.youtube.com/vi/" + movieDetail.getVideos().getResults().get(position).getKey()+"/0.jpg")
                    .error(android.R.drawable.star_off)
                    .placeholder(android.R.drawable.star_on)
                    .into(thumbnail);
        }
    }

    public interface TrailerActionListener {
        void onActionClick(Intent intent);
    }

}
