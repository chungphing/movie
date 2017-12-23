package com.chungphing.khcinema.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chungphing.khcinema.R;
import com.chungphing.khcinema.databinding.MovieFragmentBinding;
import com.chungphing.khcinema.db.entities.MovieEntity;
import com.chungphing.khcinema.viewmodel.MovieViewModel;

/**
 * Created by chungphing
 */

public class MovieFragment extends Fragment {
    private static final String KEY_MOVIE_ID = "movie_id";

    private MovieFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MovieViewModel.Factory factory = new MovieViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_MOVIE_ID)
        );

        final MovieViewModel model = ViewModelProviders.of(this, factory).get(MovieViewModel.class);

        mBinding.setMovieViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final MovieViewModel model) {
        //Observe movie data

        model.getObservableMovie().observe(this, new Observer<MovieEntity>() {
            @Override
            public void onChanged(@Nullable MovieEntity movieEntity) {
                model.setMovie(movieEntity);
            }
        });
    }


    public static MovieFragment forMovie(int id){
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_MOVIE_ID, id);
        fragment.setArguments(args);
        return fragment;
    }
}
