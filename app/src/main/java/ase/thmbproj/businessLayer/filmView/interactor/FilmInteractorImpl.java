package ase.thmbproj.businessLayer.filmView.interactor;

import java.util.NoSuchElementException;

import ase.thmbproj.dataLayer.mainConnect.database.FilmDao;
import ase.thmbproj.dataLayer.mainConnect.database.FilmFavoriteModel;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by ase911 on 07.06.2017.
 */

public class FilmInteractorImpl implements FilmInteractor {

    private final FilmDao filmDao;

    public FilmInteractorImpl(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @Override
    public Completable insertIntoFavorite(final FilmFavoriteModel filmFavoriteModel) {
        return Completable.fromAction(() -> filmDao.insert(filmFavoriteModel));
    }

    @Override
    public Completable deleteFromFavorite(final FilmFavoriteModel filmFavoriteModel) {
        return Completable.fromAction(() -> filmDao.delete(filmFavoriteModel));
    }

    @Override
    public Completable checkFilmInFavorite(final int id) {
        return Completable.create(e -> {
            int count = filmDao.getSome(id).blockingFirst();
            if (count == 0) {
                e.onError(new NoSuchElementException());
            } else {
                e.onComplete();
            }
        });
    }
}
