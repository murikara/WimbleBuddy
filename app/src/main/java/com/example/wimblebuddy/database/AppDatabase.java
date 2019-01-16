package com.example.wimblebuddy.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.wimblebuddy.database.dao.GamecardDao;
import com.example.wimblebuddy.database.model.Gamecard;

@Database(entities = {Gamecard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GamecardDao gamecardDao();
    private final static String NAME_DATABASE = "wimblebuddy";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class,   NAME_DATABASE).allowMainThreadQueries().build();
        }
        return sInstance;
    }
}
