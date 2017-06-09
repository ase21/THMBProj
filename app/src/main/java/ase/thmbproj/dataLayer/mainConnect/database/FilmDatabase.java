package ase.thmbproj.dataLayer.mainConnect.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ase.thmbproj.dataLayer.mainConnect.FilmModel;

/**
 * Created by ase911 on 31.05.2017.
 */
@Database(entities = {FilmFavoriteModel.class, FilmModel.class, FilmPopularModel.class}, version = 4)
public abstract class FilmDatabase extends RoomDatabase {
    private static FilmDatabase instance;
    public static final String DB_NAME = "themoviedb";

    public static synchronized FilmDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), FilmDatabase.class, DB_NAME)
                    .build();
        }
        return instance;
    }
    public abstract FilmDao filmDao();
}
