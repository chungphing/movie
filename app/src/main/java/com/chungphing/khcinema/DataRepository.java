package com.chungphing.khcinema;

/**
 * Created by chungphing
 */


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.chungphing.khcinema.db.AppDatabase;
import com.chungphing.khcinema.db.entities.MovieEntity;
import com.chungphing.khcinema.db.entities.SessionEntity;
import com.chungphing.khcinema.db.entities.SessionsEntity;
import com.chungphing.khcinema.model.Movie;

import java.util.List;

/**
 * Repository handling the work with products and comments.
 */
public class DataRepository {
    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<MovieEntity>> mObservableMovies;
    private MediatorLiveData<List<SessionEntity>> mObservableSession;
    private MediatorLiveData<List<SessionsEntity>> mObservableSessions;

    private DataRepository(final AppDatabase database){
        mDatabase = database;
        mObservableMovies = new MediatorLiveData<>();
        mObservableSession = new MediatorLiveData<>();
        mObservableSessions = new MediatorLiveData<>();

        mObservableMovies.addSource(mDatabase.movieDao().loadAllMovies(),
                movieEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null){
                        mObservableMovies.postValue(movieEntities);
                    }
                });
        mObservableSession.addSource(mDatabase.sessionDao().loadAllSession(),
                sessionEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null){
                        mObservableSession.postValue(sessionEntities);
                    }
                });
        mObservableSessions.addSource(mDatabase.sessionsDao().loadAllSessions(),
                sessionsEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null){
                        mObservableSessions.postValue(sessionsEntities);
                    }
                });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }
    /**
     * Get the list of products from the database and get notified when the data changes.
     */

    public LiveData<List<MovieEntity>> getMovies(){
        return mObservableMovies;
    }
    public LiveData<MovieEntity> loadMovie(final int id){
        return mDatabase.movieDao().loadMovie(id);
    }

}
