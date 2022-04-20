package com.example.scoretracker2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.scoretracker2022.fragment.GameDetailsFragment;
import com.example.scoretracker2022.fragment.GamesRecyclerViewAdapter;
import com.example.scoretracker2022.model.Game;
import com.example.scoretracker2022.model.GamesViewModel;

public class ScoreTrackerMainActivity extends AppCompatActivity implements GamesRecyclerViewAdapter.GameSelectedListener {

    private GamesViewModel gamesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamesViewModel = new ViewModelProvider(this).get(GamesViewModel.class);
    }

    @Override
    public void onGameSelected(Game game, int selectedPosition) {
        Log.i("LOGTAG", "onGameSelected(), selectedPosition: " + selectedPosition);
        gamesViewModel.setSelectedGame(game);
        gamesViewModel.setSelectedPosition(selectedPosition);

        if (findViewById(R.id.vertical_fragment_container) != null) {
            // Swap the fragments
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.vertical_fragment_container, GameDetailsFragment.class, null)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
