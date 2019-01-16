package com.example.wimblebuddy.database.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.wimblebuddy.database.AppDatabase;
import com.example.wimblebuddy.database.dao.GamecardDao;
import com.example.wimblebuddy.database.model.Gamecard;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GamecardRepository {

    private AppDatabase mAppDatabase;
    private GamecardDao mGamecardDao;
    private LiveData<List<Gamecard>> mCards;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public GamecardRepository(Context context){
        mAppDatabase = AppDatabase.getInstance(context);
        mGamecardDao = mAppDatabase.gamecardDao();
        mCards = mGamecardDao.getAllGamecards();
    }

    public LiveData<List<Gamecard>> getAllGamecards() {
        return mCards;
    }

    public void insert(final Gamecard card){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mGamecardDao.insertGamecards(card);
            }
        });
    }

    public void update(final Gamecard card){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mGamecardDao.updateGamecards(card);
            }
        });
    }

    public void delete(final Gamecard card){
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mGamecardDao.deleteGamecards(card);
            }
        });
    }
}
