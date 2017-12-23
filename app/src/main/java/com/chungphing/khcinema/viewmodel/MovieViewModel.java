package com.chungphing.khcinema.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.chungphing.khcinema.DataRepository;
import com.chungphing.khcinema.Khcinema;
import com.chungphing.khcinema.db.entities.MovieEntity;

/**
 * Created by chungphing
 */

public class MovieViewModel extends AndroidViewModel{
    private final LiveData<MovieEntity> mObservableMovie;

    public ObservableField<MovieEntity> movie = new ObservableField<>();

    private final int mId;

    public MovieViewModel(@NonNull Application application, DataRepository repository,
                          final int id){
        super(application);

        mId = id;
        mObservableMovie = repository.loadMovie(mId);
    }

    public LiveData<MovieEntity> getObservableMovie(){
        return mObservableMovie;
    }

    public void setMovie(MovieEntity movie){
        this.movie.set(movie);
    }

    /**
     * A creator is used to inject the movie ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */

    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        private final Application mApplication;

        private final int mId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int id){
            mApplication = application;
            mId = id;
            mRepository = ((Khcinema) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new MovieViewModel(mApplication, mRepository, mId);
        }
    }
}
