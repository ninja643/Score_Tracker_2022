package com.example.scoretracker2022.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.scoretracker2022.model.Game;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Database(entities = {Game.class}, version = 1, exportSchema = false)
public abstract class GamesDatabase extends RoomDatabase {

    private static GamesDatabase INSTANCE;
    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(2);
    public static final ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(databaseExecutor);

    public static GamesDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (GamesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context, GamesDatabase.class, "games_database")
                            .setQueryCallback(GamesDatabase::logQuery, databaseExecutor)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public static void logQuery(@NonNull String sqlQuery, @NonNull List<Object> bindArgs) {
//        Log.i("LOGTAG", "Query: " + sqlQuery);
//        Log.i("LOGTAG", "Arguments: " + bindArgs.stream().map(Object::toString)
//                .collect(Collectors.joining(", ")));
    }

    public abstract GamesDao gamesDao();
}
