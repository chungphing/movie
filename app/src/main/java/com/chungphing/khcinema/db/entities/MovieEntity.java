package com.chungphing.khcinema.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.chungphing.khcinema.model.Movie;
import java.util.Date;

/**
 * Created by chungphing
 */

@Entity(tableName = "movie")
public class MovieEntity implements Movie {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uid")
    private String uid;
    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="mid")
    private String mid;


    @ColumnInfo(name="cid")
    private String cid;


    @ColumnInfo(name="fid")
    private String fid;

    @ColumnInfo(name = "trailer")
    private String trailer;

    @ColumnInfo(name = "poster")
    private String poster;

    @ColumnInfo(name = "link")
    private String link;

    @Override
    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="genre")
    private String genre;

    @ColumnInfo(name="date_added")
    private Date dateAdded;

    @ColumnInfo(name = "date_showed")
    private Date dateShowed;

    @ColumnInfo(name = "runtime")
    private String runtime;

    @ColumnInfo(name = "rating")
    private int rating;

    @Override
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Override
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;

    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateShowed() {
        return dateShowed;
    }

    public void setDateShowed(Date dateShowed) {
        this.dateShowed = dateShowed;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public MovieEntity() {
    }

    public MovieEntity(int id, String uid, String name, String mid, String cid, String description, String genre, Date dateAdded, Date dateShowed, String runtime, int rating, String trailer, String poster, String link) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.mid = mid;
        this.cid = cid;
        this.fid = fid;
        this.description = description;
        this.genre = genre;
        this.dateAdded = dateAdded;
        this.dateShowed = dateShowed;
        this.runtime = runtime;
        this.rating = rating;
        this.trailer = trailer;
        this.poster = poster;
        this.link = link;
    }

    public MovieEntity(Movie movie) {
        this.id = movie.getId();
        this.uid = movie.getUid();
        this.name = movie.getName();
        this.mid = movie.getMid();
        this.cid = movie.getCid();
        this.fid = movie.getFid();
        this.description = movie.getDescription();
        this.genre = movie.getGenre();
        this.dateAdded = movie.getDateAdded();
        this.dateShowed = movie.getDateShowed();
        this.runtime = movie.getRuntime();
        this.rating = movie.getRating();
        this.trailer = movie.getTrailer();
        this.poster = movie.getPoster();
        this.link = movie.getLink();
    }

}
