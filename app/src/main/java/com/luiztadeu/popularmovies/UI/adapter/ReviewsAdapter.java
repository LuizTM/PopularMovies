package com.luiztadeu.popularmovies.UI.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luiztadeu.popularmovies.model.MovieDetail.Reviews;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private Reviews movieReviews;

    public ReviewsAdapter(Reviews reviews) {
        this.movieReviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return movieReviews.getResults().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView author;
        private TextView review;

         ViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(android.R.id.text1);
            review = itemView.findViewById(android.R.id.text2);
        }

        void bind(int position){
            String nameAuthor = movieReviews.getResults().get(position).getAuthor();
            String reviewAuthor = movieReviews.getResults().get(position).getContent();
            author.setText(nameAuthor);
            review.setText(reviewAuthor);
        }
    }
}
