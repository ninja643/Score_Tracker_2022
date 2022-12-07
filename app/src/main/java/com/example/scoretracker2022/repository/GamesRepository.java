package com.example.scoretracker2022.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.scoretracker2022.model.Game;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class GamesRepository {

    private final GamesDao gamesDao;

    public GamesRepository(Context context) {
        final GamesDatabase gamesDatabase = GamesDatabase.getInstance(context);
        this.gamesDao = gamesDatabase.gamesDao();
    }

    public LiveData<List<Game>> getGames() {
        return gamesDao.getAllGames();
    }

    public void addGame(Game game) {
        ListenableFuture<Void> future = GamesDatabase.listeningExecutorService.submit(() -> {
            gamesDao.insertGames(game);
            return null;
        });

        Futures.addCallback(future, new FutureCallback<>() {
            @Override
            public void onSuccess(Void result) {
                Log.i("LOGTAG", "Game added");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGTAG", "Failed to add game: " + t.getLocalizedMessage());
            }
        }, GamesDatabase.databaseExecutor);
    }

    public void delete(Game game) {
        GamesDatabase.databaseExecutor.execute(() -> gamesDao.deleteGame(game));
    }

    public void update(Game game) {

        ListenableFuture<Void> future = GamesDatabase.listeningExecutorService.submit(() -> {
            Random r = new Random();
            if (r.nextBoolean())
            {
                throw new RuntimeException("Error " + game.toString());
            }
            gamesDao.updateGames(game);
            return null;
        });

        Futures.addCallback(future, new FutureCallback<>() {
            @Override
            public void onSuccess(Void result) {
                Log.i("LOGTAG", "Game updated");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("LOGTAG", "Failed to update game: " + t.getLocalizedMessage());
            }
        }, GamesDatabase.databaseExecutor);
    }
}
