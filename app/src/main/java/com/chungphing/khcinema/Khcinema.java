package com.chungphing.khcinema;

import android.app.Application;

import com.chungphing.khcinema.db.AppDatabase;

/**
 * Created by chungphing
 */

public class Khcinema extends Application {

    private AppExecutors mAppExecutors;
    
    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase(){
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository(){
        return DataRepository.getInstance(getDatabase());
    }
}
