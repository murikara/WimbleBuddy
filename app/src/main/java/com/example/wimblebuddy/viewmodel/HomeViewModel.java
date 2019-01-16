package com.example.wimblebuddy.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.wimblebuddy.database.model.Gamecard;
import com.example.wimblebuddy.database.repository.GamecardRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private GamecardRepository mGamecardRepository;
    private LiveData<List<Gamecard>> mCards;

    public HomeViewModel(Context context) {
        this.mGamecardRepository = new GamecardRepository(context);
        this.mCards = mGamecardRepository.getAllGamecards();
    }

    public LiveData<List<Gamecard>> getCards() {
        return mCards;
    }

    public void insert(Gamecard item){
        mGamecardRepository.insert(item);
    }

    public void delete(Gamecard item){
        mGamecardRepository.delete(item);
    }

    public void update(Gamecard item){
        mGamecardRepository.update(item);
    }
}
