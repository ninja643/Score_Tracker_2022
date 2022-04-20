package com.example.scoretracker2022.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class Game extends BaseObservable {
    private final long id;
    private final String teamA;
    private final String teamB;

    private int scoreA;
    private int scoreB;

    public enum Team {
        TEAM_A,
        TEAM_B
    }

    public Game(long id, String teamA, String teamB) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
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
