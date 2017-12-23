package com.chungphing.khcinema.ui;

import android.arch.lifecycle.Lifecycle;
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
import com.chungphing.khcinema.databinding.ListFragmentBinding;
import com.chungphing.khcinema.db.entities.MovieEntity;
import com.chungphing.khcinema.model.Movie;
import com.chungphing.khcinema.viewmodel.MovieListViewModel;

import java.util.List;

/**
 * Created by chungphing
 */

public class MovieListFragment extends Fragment {

    public static final String TAG = "MovieListViewModel";

    private MovieAdapter mMovieAdapter;

    private ListFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        mMovieAdapter = new MovieAdapter(mMovieClickCallback);
        mBinding.moviesList.setAdapter(mMovieAdapter);


        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final MovieListViewModel viewModel =
                ViewModelProviders.of(this).get(MovieListViewModel.class);

        subscribeUi(viewModel);
    }

    private void subscribeUi(MovieListViewModel viewModel) {
        //Update the list when the data changes
        viewModel.getMovies().observe(this, new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> myMovies) {
                if (myMovies !=null){
                    mBinding.setIsLoading(false);
                    mMovieAdapter.setMovieList(myMovies);
                } else {
                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final MovieClickCallback mMovieClickCallback = new MovieClickCallback() {
        @Override
        public void onClick(Movie movie) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(movie);
            }
        }
    };
}
