package com.chungphing.khcinema.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.chungphing.khcinema.db.entities.MovieEntity;
import com.chungphing.khcinema.db.entities.SessionEntity;

import java.util.List;

/**
 * Created by chungphing
 */

@Dao
public interface SessionDao {
    @Query("SELECT * FROM session")
    LiveData<List<SessionEntity>> loadAllSession();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SessionEntity> sessions);

    @Query("SELECT * FROM session WHERE id = :id")
    LiveData<SessionEntity> loadSession(int id);

    @Query("SELECT * FROM session WHERE id = :id")
    SessionEntity loadSessionSync(int id);
}
