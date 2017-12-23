package com.chungphing.khcinema.model;

import java.util.Date;

/**
 * Created by chungphing
 */

public interface Movie {
    String getUid();
    String getMid();
    String getFid();
    String getCid();
    String getName();
    String getGenre();
    Date getDateAdded();
    Date getDateShowed();
    String getRuntime();
    int getRating();
    String getDescription();
    String getTrailer();
    String getPoster();
    String getLink();
    int getId();
}
