package com.chungphing.khcinema.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.chungphing.khcinema.db.entities.MovieEntity;
import com.chungphing.khcinema.db.entities.SessionsEntity;

import java.util.List;

/**
 * Created by chungphing
 */

@Dao
public interface SessionsDao {
    @Query("SELECT * FROM sessions")
    LiveData<List<SessionsEntity>> loadAllSessions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SessionsEntity> Sessions);

    @Query("SELECT * FROM sessions WHERE id = :id")
    LiveData<SessionsEntity> loadSession(int id);

    @Query("SELECT * FROM sessions WHERE id = :id")
    SessionsEntity loadSessionSync(int id);
}
