package com.example.wimblebuddy.fragment;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wimblebuddy.R;
import com.example.wimblebuddy.adapter.GamecardAdapter;
import com.example.wimblebuddy.database.model.Gamecard;
import com.example.wimblebuddy.viewmodel.HomeViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements GamecardAdapter.GamecardClickListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private final String CELSIUS = "\u2109";
    private GamecardAdapter mAdapter;
    private HomeViewModel mHomeViewModel;
    private List<Gamecard> mCards;
    private static final String TAG = "HomeFragment";

    @BindView(R.id.gamecardRecyclerView)
    RecyclerView mRecyclerView;

    public HomeFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HomeFragment newInstance(int sectionNumber) {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);
        mHomeViewModel = new HomeViewModel(inflater.getContext());
        mHomeViewModel.getCards().observe(this, new Observer<List<Gamecard>>() {
            @Override
            public void onChanged(@Nullable List<Gamecard> cards) {
                System.out.println(cards.size());
                mCards = cards;
                updateUI();
            }
        });
        return rootView;
    }

    @OnClick(R.id.createGameButton_Home)
    public void createDialogFragment(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment createGameFragment = new CreateGameFragment();
        createGameFragment.show(ft, "createGame");

    }

    /**
     * Update de UI
     */
    private void updateUI() {
        Log.e(TAG, "updateUI: in the method now" );
        if (mAdapter == null) {
            mAdapter = new GamecardAdapter(mCards, this);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
            Log.e(TAG, "updateUI: created new Adapter" );
        } else {
            mAdapter.swapList(mCards);
            mAdapter.notifyDataSetChanged();
            Log.e(TAG, "updateUI: swapped the list" );
        }
        Log.e(TAG, "updateUI: leaving the method" );
    }

    @Override
    public void GamecardOnClick(int i) {

    }
}
