package com.example.wimblebuddy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wimblebuddy.R;
import com.example.wimblebuddy.database.model.Gamecard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamecardAdapter extends RecyclerView.Adapter<GamecardAdapter.GamecardViewHolder> {

    private List<Gamecard> mGamecards;
    private static final String TAG = "GamecardAdapter";
    private GamecardClickListener mGamecardClickListener;

    public GamecardAdapter(List<Gamecard> mGamecards, GamecardClickListener mGamecardClickListener) {
        this.mGamecards = mGamecards;
        this.mGamecardClickListener = mGamecardClickListener;
    }

    @NonNull
    @Override
    public GamecardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_gamecard, null);

        return new GamecardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamecardViewHolder holder, int i) {
        final Gamecard card = mGamecards.get(i);
        if(card != null) {
            holder.vDate.setText(card.getDate());
            holder.vTime.setText(card.getTimeFrom() + " - " + card.getTimeTill());
            holder.vLocation.setText(card.getLocation());
            if (card.isFull()) {
                holder.vPlayers.setText("2/2");
            } else {
                holder.vPlayers.setText("1/2");
            }
        } else {
            Log.e(TAG, "onBindViewHolder: OBJECT IS LEEG" );
        }
    }

    @Override
    public int getItemCount() {
        return mGamecards.size();
    }

    public class GamecardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.dateTextView)
        TextView vDate;
        @BindView(R.id.timeTextView)
        TextView vTime;
        @BindView(R.id.locationTextView)
        TextView vLocation;
        @BindView(R.id.playersTextView)
        TextView vPlayers;

        public GamecardViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface GamecardClickListener{
        void GamecardOnClick (int i);
    }

    public void swapList (List<Gamecard> newList) {
        mGamecards = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }
}
