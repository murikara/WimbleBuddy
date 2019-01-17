package com.example.wimblebuddy.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wimblebuddy.R;
import com.example.wimblebuddy.database.model.Gamecard;
import com.example.wimblebuddy.viewmodel.HomeViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteGameFragment extends DialogFragment {
    
    private HomeViewModel mViewModel;
    private static final String TAG = "DeleteGameFragment";

    public DeleteGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param card the game to be deleted
     * @return A new instance of fragment DeleteGameFragment
     */
    public static DeleteGameFragment newInstance(Gamecard card){
        DeleteGameFragment fragment = new DeleteGameFragment();
        Bundle args = new Bundle();
        args.putParcelable("Gamecard", card);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_delete_game, container, false);
        ButterKnife.bind(this, v);
        mViewModel = new HomeViewModel(getActivity());
        // Do all the stuff to initialize your custom view
        return v;
    }
    
    @OnClick(R.id.deleteGameButton)
    public void deleteGame(){
        Gamecard cardToDelete = getArguments().getParcelable("Gamecard");
        if(cardToDelete != null){
            mViewModel.delete(cardToDelete);
            Toast.makeText(getActivity(), getString(R.string.fragment_delete_game_succes),
                    Toast.LENGTH_LONG).show();
            dismiss();
        } else {
            Log.e(TAG, "deleteGame: cardToDelete is NULL" );
            Toast.makeText(getActivity(), getString(R.string.fragment_delete_game_error),
                    Toast.LENGTH_LONG).show();
        }
    }
}
