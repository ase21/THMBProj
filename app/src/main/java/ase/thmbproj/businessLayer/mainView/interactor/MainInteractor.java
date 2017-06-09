package ase.thmbproj.businessLayer.mainView.interactor;

import java.util.List;

import ase.thmbproj.dataLayer.mainConnect.FilmModel;
import ase.thmbproj.dataLayer.mainConnect.PageModel;
import ase.thmbproj.dataLayer.mainConnect.database.FilmFavoriteModel;
import ase.thmbproj.dataLayer.mainConnect.database.FilmPopularModel;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by ase911 on 23.05.2017.
 */

public interface MainInteractor {

    Single<PageModel> getFilmList();

    Single<PageModel> getPopularFilmList();

    Observable<List<FilmModel>> getFavoriteFilmList(); //todo return single

}
