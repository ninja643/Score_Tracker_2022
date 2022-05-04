package com.example.scoretracker2022.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class Game extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private final long id;

    @ColumnInfo(name = "team_a")
    private final String teamA;

    @ColumnInfo(name = "team_b")
    private final String teamB;

    @ColumnInfo(name = "score_a")
    private int scoreA;

    @ColumnInfo(name = "score_b")
    private int scoreB;

    public enum Team {
        TEAM_A,
        TEAM_B
    }

    public Game(long id, String teamA, String teamB, int scoreA, int scoreB) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public long getId() {
        return id;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    @Bindable
    public int getScoreA() {
        return scoreA;
    }

    @Bindable
    public int getScoreB() {
        return scoreB;
    }

    public void addScore(int score, Team team) {
        if (team == Team.TEAM_A) {
            scoreA += score;
            notifyPropertyChanged(BR.scoreA);
        } else {
            scoreB += score;
            notifyPropertyChanged(BR.scoreB);
        }
    }

    public void reset() {
        scoreA = 0;
        scoreB = 0;
        notifyPropertyChanged(BR.scoreA);
        notifyPropertyChanged(BR.scoreB);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                '}';
    }
}
