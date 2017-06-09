package ase.thmbproj.businessLayer.filmView.interactor;

import ase.thmbproj.dataLayer.mainConnect.database.FilmFavoriteModel;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by ase911 on 07.06.2017.
 */

public interface FilmInteractor {

    Completable insertIntoFavorite(FilmFavoriteModel filmFavoriteModel);

    Completable deleteFromFavorite(FilmFavoriteModel filmFavoriteModel);

    Completable checkFilmInFavorite(int id);
}
