package com.example.wimblebuddy.database.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

@Entity(tableName = "Gamecard")
public class Gamecard implements Parcelable, Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time_from")
    private String timeFrom;

    @ColumnInfo(name = "time_till")
    private String timeTill;

    @ColumnInfo(name = "full")
    private boolean full;

    public Gamecard(String location, String date, String timeFrom, String timeTill, boolean full) {
        this.location = location;
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTill = timeTill;
        this.full = full;
    }

    protected Gamecard(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        location = in.readString();
        date = in.readString();
        timeFrom = in.readString();
        timeTill = in.readString();
        full = in.readByte() != 0;
    }

    public static final Creator<Gamecard> CREATOR = new Creator<Gamecard>() {
        @Override
        public Gamecard createFromParcel(Parcel in) {
            return new Gamecard(in);
        }

        @Override
        public Gamecard[] newArray(int size) {
            return new Gamecard[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTill() {
        return timeTill;
    }

    public void setTimeTill(String timeTill) {
        this.timeTill = timeTill;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(location);
        parcel.writeString(date);
        parcel.writeString(timeFrom);
        parcel.writeString(timeTill);
        parcel.writeByte((byte) (full ? 1 : 0));
    }
}
