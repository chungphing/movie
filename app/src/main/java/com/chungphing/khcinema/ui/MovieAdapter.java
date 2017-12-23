package com.chungphing.khcinema.ui;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chungphing.khcinema.R;
import com.chungphing.khcinema.databinding.MovieItemBinding;
import com.chungphing.khcinema.model.Movie;
import android.databinding.DataBindingUtil;
import java.util.List;
import java.util.Objects;

/**
 * Created by chungphing
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    List<? extends Movie> mMovieList;

    @Nullable
    private final MovieClickCallback mMovieClickCallback;

    public MovieAdapter(@Nullable MovieClickCallback clickCallback){
        mMovieClickCallback = clickCallback;
    }

    public void setMovieList(final List<? extends Movie> movieList){
        if (mMovieList == null){
            mMovieList = movieList;
            notifyItemRangeInserted(0, movieList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mMovieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mMovieList.get(oldItemPosition).getUid() ==
                            movieList.get(newItemPosition).getUid();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Movie newMovie = movieList.get(newItemPosition);
                    Movie oldMovie = mMovieList.get(oldItemPosition);

                    return newMovie.getUid() == oldMovie.getUid()
                        && Objects.equals(newMovie.getDescription(), oldMovie.getDescription())
                        && Objects.equals(newMovie.getName(), oldMovie.getName());
                }
            });
            mMovieList = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item,
                        parent, false);
        binding.setCallback(mMovieClickCallback);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.binding.setMovie(mMovieList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }


    static class MovieViewHolder extends  RecyclerView.ViewHolder{
        final MovieItemBinding binding;
        public MovieViewHolder(MovieItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
