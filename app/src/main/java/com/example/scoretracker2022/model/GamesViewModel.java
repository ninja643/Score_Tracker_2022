package com.example.scoretracker2022.model;

import android.widget.AdapterView;

import com.example.scoretracker2022.repository.GamesRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class GamesViewModel extends ViewModel {
    private MutableLiveData<List<Game>> games = new MutableLiveData<>();
    private MutableLiveData<Game> selectedGame = new MutableLiveData<>();
    private Integer selectedPosition = RecyclerView.NO_POSITION;

    private GamesRepository repository = GamesRepository.INSTANCE;

    public Integer getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Integer selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public LiveData<List<Game>> getGames() {
        games.setValue(repository.getGames());
        return games;
    }

    public LiveData<Game> getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(Game game) {
        selectedGame.setValue(game);
    }
}
