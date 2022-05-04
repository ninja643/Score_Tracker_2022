package com.example.scoretracker2022.model;

import androidx.room.ColumnInfo;

public class GameLite {
    @ColumnInfo(name = "team_a")
    public String teamA;
    @ColumnInfo(name = "team_b")
    public String teamB;
}
