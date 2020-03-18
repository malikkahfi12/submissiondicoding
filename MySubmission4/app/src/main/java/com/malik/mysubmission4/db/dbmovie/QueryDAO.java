package com.malik.mysubmission4.db.dbmovie;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QueryDAO {

    @Query("SELECT * FROM moviefavorite WHERE id")
    List<MovieFavorite> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MovieFavorite... movieFavorites);

    @Delete
    void deleteMovie(MovieFavorite movieFavorites);

}
