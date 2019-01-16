package com.example.wimblebuddy.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.wimblebuddy.database.model.Gamecard;

import java.util.List;

/**
 * Deze interface handelt de queries af voor een gamecard item
 */
@Dao
public interface GamecardDao {

    @Query("SELECT * FROM gamecard")
    LiveData<List<Gamecard>> getAllGamecards();

    @Insert
    void insertGamecards(Gamecard card);

    @Delete
    void deleteGamecards(Gamecard card);

    @Update
    void updateGamecards(Gamecard card);
}
