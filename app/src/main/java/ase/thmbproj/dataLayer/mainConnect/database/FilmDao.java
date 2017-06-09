package ase.thmbproj.dataLayer.mainConnect.database;

import android.arch.persistence.room.*;
import android.support.annotation.Nullable;

import java.util.List;

import ase.thmbproj.dataLayer.mainConnect.FilmModel;
import ase.thmbproj.dataLayer.mainConnect.PageModel;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by ase911 on 31.05.2017.
 */
@Dao
public interface FilmDao {
    @Query("SELECT * FROM filmfavoritemodel")
    Flowable<List<FilmFavoriteModel>> getAll();


    @Query("SELECT COUNT(*) FROM filmfavoritemodel WHERE id LIKE :filmId")
    Flowable<Integer> getSome(int filmId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(FilmFavoriteModel filmFavoriteModel);

    @Delete
    public void delete(FilmFavoriteModel filmFavoriteModel);

    @Query("DELETE FROM filmmodel")
    public void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void setAll(List<FilmModel> filmModels);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void setAllPopular(List<FilmPopularModel> results);

    @Query("SELECT COUNT(*) FROM filmmodel")
    public int getCount();

    @Query("SELECT * FROM filmmodel")
    Flowable<List<FilmModel>> getSavedAll();

    @Query("SELECT * FROM filmPopularModel")
    Flowable<List<FilmModel>> getSavedAllPopular();


}
