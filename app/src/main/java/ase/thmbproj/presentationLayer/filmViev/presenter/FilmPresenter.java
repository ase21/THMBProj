package ase.thmbproj.presentationLayer.filmViev.presenter;


import android.util.Log;
import android.view.View;

import ase.thmbproj.base.BaseMvpPresenter;
import ase.thmbproj.businessLayer.filmView.interactor.FilmInteractor;
import ase.thmbproj.dataLayer.mainConnect.database.FilmFavoriteModel;
import ase.thmbproj.presentationLayer.filmViev.view.FilmView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ase911 on 07.06.2017.
 */

public class FilmPresenter extends BaseMvpPresenter<FilmView> {

    private final FilmInteractor filmInteractor;

    public FilmPresenter(FilmInteractor filmInteractor) {
        this.filmInteractor = filmInteractor;
    }

    public void checkFilmInFavorite(int id) {
        filmInteractor.checkFilmInFavorite(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.setDeleteState(), throwable -> view.setInsertState());
    }

    public void insertIntoFavorite(FilmFavoriteModel filmFavoriteModel) {
        filmInteractor.insertIntoFavorite(filmFavoriteModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.setDeleteState(), throwable -> Log.d("myLogs", "Some Error On Insert"));
    }

    public void deleteFromFavorite(FilmFavoriteModel filmFavoriteModel) {
        filmInteractor.deleteFromFavorite(filmFavoriteModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.setInsertState(), throwable -> Log.d("myLogs", "Some Error on delete"));
    }

}
