package ase.thmbproj.businessLayer.mainView.interactor;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ase.thmbproj.dataLayer.mainConnect.FilmModel;
import ase.thmbproj.dataLayer.mainConnect.PageModel;
import ase.thmbproj.dataLayer.mainConnect.THMDClient;
import ase.thmbproj.presentationLayer.mainViev.provider.DependencyProvider;
import io.reactivex.Observable;
import io.reactivex.Single;
import ase.thmbproj.dataLayer.mainConnect.database.*;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by ase911 on 23.05.2017.
 */

public class MainInteractorImpl implements MainInteractor {
    final String LOG_TAG = "myLogs";
    private final THMDClient thmdClient;
    private final FilmDao filmDao;

    public MainInteractorImpl(THMDClient api, FilmDao filmDao) {
        this.filmDao = filmDao;
        this.thmdClient = api;
    }

    @Override
    public Single<PageModel> getFilmList() {
        Log.d(LOG_TAG, "Добрались до выполнения GET topRated");
        return thmdClient.topRated(DependencyProvider.token, DependencyProvider.lang)
                .doOnSuccess(pageModel -> filmDao.setAll(pageModel.getResults()))
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends PageModel>>() {
                    @Override
                    public SingleSource<PageModel> apply(@NonNull Throwable throwable) throws Exception {
                        return Single.create(e -> e.onSuccess(filmDao.getSavedAll()
                                .map(filmModelList -> {
                                    PageModel pageModel = new PageModel();
                                    pageModel.setResults(filmModelList);
                                    return pageModel;
                                }).blockingFirst()));
                    }
                });
    }

    @Override
    public Single<PageModel> getPopularFilmList() {
        Log.d(LOG_TAG, "Добрались до выполнения GET Popular");
        return thmdClient
                .popular(DependencyProvider.token, DependencyProvider.lang)
                .doOnSuccess((pageModel -> filmDao.setAllPopular(getFilmPopularList(pageModel.getResults()))))
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends PageModel>>() {
                    @Override
                    public SingleSource<PageModel> apply(@NonNull Throwable throwable) throws Exception {
                        return Single.create(e -> e.onSuccess(filmDao.getSavedAllPopular()
                                .map(filmModelList -> {
                                    PageModel pageModel = new PageModel();
                                    pageModel.setResults(filmModelList);
                                    return pageModel;
                                }).blockingFirst()));
                    }
                });
    }

    @Override
    public Observable<List<FilmModel>> getFavoriteFilmList() {
        Log.d(LOG_TAG, "Добрались до выполнения GET Popular");
        return  filmDao.getAll()
                .map(filmFavoriteModels -> {
                    List<FilmModel> filmModels = new ArrayList<>();
                    for (FilmFavoriteModel item : filmFavoriteModels){
                        filmModels.add(item.getFilmModel());
                    }
                    return filmModels;
                })
                .toObservable();
    }

    private List<FilmPopularModel> getFilmPopularList(List<FilmModel> filmModelList) {
        List<FilmPopularModel> filmPopularModels = new ArrayList<>();
        for (FilmModel item : filmModelList){
            filmPopularModels.add(item.getFilmPopularModel());
        }
        return filmPopularModels;
    }
}
