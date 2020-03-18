package com.malik.mysubmission4.db.dbserial;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;



import java.util.List;

@Dao
public interface QuerySerialDAO {
    @Query("SELECT * FROM serialfavorite")
    List<SerialFavorite> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SerialFavorite... serialFavorites);

    @Delete
    void deleteMovie(SerialFavorite serialFavorite);
}
