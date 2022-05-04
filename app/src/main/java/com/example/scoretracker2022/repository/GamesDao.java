package com.example.scoretracker2022.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.scoretracker2022.model.Game;
import com.example.scoretracker2022.model.GameLite;

import java.util.List;

@Dao
public interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGames(Game... games);

    @Update
    void updateGames(Game... games);

    @Delete
    void deleteGame(Game game);

    @Query("SELECT * FROM games")
    LiveData<List<Game>> getAllGames();

    @Query("SELECT * FROM games WHERE team_a = :teamA")
    List<Game> getByTeamA(String teamA);

    @Query("SELECT team_a, team_b FROM games")
    List<GameLite> getLiteInfo();

    @Query("SELECT * FROM games WHERE team_a IN (:teams) OR team_b IN (:teams)")
    List<Game> getGamesByTeams(List<String> teams);
}
