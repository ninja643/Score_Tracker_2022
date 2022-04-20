package com.example.scoretracker2022.repository;

import android.util.Log;

import com.example.scoretracker2022.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GamesRepository {

    public static GamesRepository INSTANCE = new GamesRepository();

    private List<Game> games = new ArrayList<>();

    private GamesRepository() {
        games.add(new Game(1, "Real", "Barcelona"));
        games.add(new Game(2, "Denver", "GSW"));
        games.add(new Game(3, "Real", "Barcelona"));
        games.add(new Game(4, "Real", "Barcelona"));
        games.add(new Game(5, "Real", "Barcelona"));
        games.add(new Game(6, "Real", "Barcelona"));
        games.add(new Game(7, "Real", "Barcelona"));
        games.add(new Game(8, "Real", "Barcelona"));

        games.get(0).addScore(1, Game.Team.TEAM_A);
    }

    public List<Game> getGames() {
        return games;
    }
}
