package com.chungphing.khcinema.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.chungphing.khcinema.AppExecutors;
import com.chungphing.khcinema.db.converter.DateConverter;
import com.chungphing.khcinema.db.dao.MovieDao;
import com.chungphing.khcinema.db.dao.SessionDao;
import com.chungphing.khcinema.db.dao.SessionsDao;
import com.chungphing.khcinema.db.entities.MovieEntity;
import com.chungphing.khcinema.db.entities.SessionEntity;
import com.chungphing.khcinema.db.entities.SessionsEntity;

import java.util.List;

/**
 * Created by chungphing
 */
@Database(entities = {MovieEntity.class, SessionEntity.class, SessionsEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "khmovie_db";

    public abstract MovieDao movieDao();
    public abstract SessionDao sessionDao();
    public abstract SessionsDao sessionsDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors){
        if (sInstance == null){
            synchronized (AppDatabase.class){
                if (sInstance == null){
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }



    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation

                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            List<MovieEntity> movies = DataGenerator.generateProducts();

                            insertData(database, movies);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final AppDatabase database, final List<MovieEntity> movies) {
        database.runInTransaction(() -> {
            database.movieDao().insertAll(movies);
        });
    }

    /**
     * Add Delay to simulate long-running operation
     */
    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
