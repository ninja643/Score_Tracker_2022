package com.example.scoretracker2022.model;

import android.app.Application;

import com.example.scoretracker2022.repository.GamesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

public class GamesViewModel extends AndroidViewModel {
    private MutableLiveData<Game> selectedGame = new MutableLiveData<>();
    private Integer selectedPosition = RecyclerView.NO_POSITION;

    private GamesRepository gamesRepository;

    public Integer getSelectedPosition() {
        return selectedPosition;
    }

    public GamesViewModel(@NonNull Application application) {
        super(application);

        gamesRepository = new GamesRepository(application);
    }

    public void setSelectedPosition(Integer selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public LiveData<List<Game>> getGames() {
        return gamesRepository.getGames();
    }

    public LiveData<Game> getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(Game game) {

        game.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                gameUpdated(sender, propertyId);
            }
        });

        selectedGame.setValue(game);
    }

    public void gameUpdated(Observable sender, int propertyId) {
        final Game game = (Game) sender;
        gamesRepository.update(game);
    }
}
