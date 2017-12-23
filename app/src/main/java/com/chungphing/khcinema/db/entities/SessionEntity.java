package com.chungphing.khcinema.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.chungphing.khcinema.model.Session;

/**
 * Created by chungphing
 */
@Entity(tableName = "session")
public class SessionEntity implements Session{
    @NonNull
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "sid")
    private String sid;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "screen")
    private int screen;

    @Override
    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @Override
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public SessionEntity(@NonNull int id, String sid, String time, int screen) {
        this.id = id;
        this.sid = sid;
        this.time = time;
        this.screen = screen;
    }

    public SessionEntity() {
    }

    public SessionEntity(Session session) {
        this.id = session.getId();
        this.sid = session.getSid();
        this.time = session.getTime();
        this.screen = session.getScreen();
    }
}
