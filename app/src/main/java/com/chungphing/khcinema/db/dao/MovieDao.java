package com.chungphing.khcinema.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.chungphing.khcinema.db.entities.MovieEntity;

import java.util.List;

/**
 * Created by chungphing
 */

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    LiveData<List<MovieEntity>> loadAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<MovieEntity> movies);

    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<MovieEntity> loadMovie(int id);

    @Query("SELECT * FROM movie WHERE id = :id")
    MovieEntity loadMovieSync(int id);
}
