package com.malik.mysubmission4.db.dbmovie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieFavorite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QueryDAO queryDAO();
}
