package com.example.scoretracker2022.model;

import com.example.scoretracker2022.repository.GamesRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GamesViewModel extends ViewModel {
    private MutableLiveData<List<Game>> games = new MutableLiveData<>();
    private MutableLiveData<Game> selectedGame = new MutableLiveData<>();

    private GamesRepository repository = GamesRepository.INSTANCE;


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
