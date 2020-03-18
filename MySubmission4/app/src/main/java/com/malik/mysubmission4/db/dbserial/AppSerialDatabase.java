package com.malik.mysubmission4.db.dbserial;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SerialFavorite.class}, version = 1)
public abstract class AppSerialDatabase extends RoomDatabase {
    public abstract QuerySerialDAO querySerialDAO();
}