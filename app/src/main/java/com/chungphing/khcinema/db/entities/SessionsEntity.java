package com.chungphing.khcinema.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.chungphing.khcinema.model.Sessions;

/**
 * Created by chungphing
 */
@Entity(tableName = "sessions")
public class SessionsEntity implements Sessions {
    @NonNull
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "cid")
    private String cid;
    @ColumnInfo(name = "mid")
    private String mid;

    @Override
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @Override
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public SessionsEntity(@NonNull int id, String cid, String mid) {
        this.id = id;
        this.cid = cid;
        this.mid = mid;
    }

    public SessionsEntity() {
    }
    public SessionsEntity(Sessions sessions) {
        this.id = sessions.getId();
        this.cid = sessions.getCid();
        this.mid = sessions.getMid();
    }
}
