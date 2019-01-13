package com.example.wimblebuddy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.wimblebuddy.database.model.Gamecard;

import java.util.List;

public class GamecardAdapter extends RecyclerView.Adapter<GamecardAdapter.GamecardViewHolder> {

    private List<Gamecard> mGamecards;

    @NonNull
    @Override
    public GamecardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GamecardViewHolder gamecardViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GamecardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public GamecardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
