package com.chungphing.khcinema.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.chungphing.khcinema.Khcinema;
import com.chungphing.khcinema.db.entities.MovieEntity;

import java.util.List;

/**
 * Created by chungphing
 */

public class MovieListViewModel extends AndroidViewModel{

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<MovieEntity>> mObservableMovies;

    public MovieListViewModel(@NonNull Application application) {
        super(application);

        mObservableMovies = new MediatorLiveData<>();

        //set by default null, until we get data from the database.
        mObservableMovies.setValue(null);

        LiveData<List<MovieEntity>> movies = ((Khcinema) application)
                .getRepository()
                .getMovies();
        mObservableMovies.addSource(movies, mObservableMovies::setValue);
    }


    /**
     * Expose the LiveData Movies query so the UI can observe it.
     */

    public LiveData<List<MovieEntity>> getMovies() {
        return mObservableMovies;
    }
}
