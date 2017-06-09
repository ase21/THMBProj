package ase.thmbproj.presentationLayer.mainViev.presenter;

import android.util.Log;

import java.util.List;

import ase.thmbproj.base.BaseMvpPresenter;
import ase.thmbproj.businessLayer.mainView.interactor.MainInteractor;
import ase.thmbproj.dataLayer.mainConnect.FilmModel;
import ase.thmbproj.dataLayer.mainConnect.PageModel;
import ase.thmbproj.presentationLayer.mainViev.viev.MainView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ase911 on 23.05.2017.
 */

public class MainPresenter extends BaseMvpPresenter<MainView> {
    final String LOG_TAG = "myLogs";
    private final MainInteractor mainInteractor;

    public MainPresenter(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor; //иниц.интерактор
    }

    public void requestTopMovies() {
        Log.d(LOG_TAG, "получаем topRated при помощи RxJava2");
        compositeDisposable
                .add(mainInteractor.getFilmList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> {
                            if(view!=null)
                                view.setProgressBar();
                        })
                        .doFinally(() -> {
                            if(view!=null)
                                view.hideProgressBar();
                        })
                        .subscribe(consumer,consumerError));
    }

    public void requestPopular() {
        Log.d(LOG_TAG, "получаем Popular при помощи RxJava2");
        compositeDisposable
                .add(mainInteractor.getPopularFilmList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> {
                            view.setProgressBar();
                        })
                        .doFinally(() -> {
                            view.hideProgressBar();
                        })
                        .subscribe(consumer,consumerError));
    }

    public void requestFavorite() {
        Log.d(LOG_TAG, "получаем Favorite из БД");
        compositeDisposable
                .add(mainInteractor.getFavoriteFilmList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(listConsumer,consumerError));

    }
    //Consumer для вывода данных из интернетика
    private final Consumer<PageModel> consumer = new Consumer<PageModel>() {
        @Override
        public void accept(@NonNull PageModel pageModel) throws Exception {
            Log.d(LOG_TAG,"Успешно получили результат");
            view.setSuccess(pageModel.getResults());
        }
    };

    //Consumer для вывода FavoriteList
    private final Consumer<List<FilmModel>> listConsumer = new Consumer<List<FilmModel>>() {
        @Override
        public void accept(@NonNull List<FilmModel> filmModelList) throws Exception {
            Log.d(LOG_TAG,"Успешно получили List");
            view.setSuccess(filmModelList);
        }
    };

    //Consumer для ошибки
    private final Consumer<Throwable> consumerError = new Consumer<Throwable>() {
        @Override
        public void accept(@NonNull Throwable throwable) throws Exception {
            Log.d(LOG_TAG,"ERRROOOOOR");
        }
    };
}
