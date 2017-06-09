package ase.thmbproj.presentationLayer.mainViev.viev;

import java.util.List;

import ase.thmbproj.base.MvpView;
import ase.thmbproj.dataLayer.mainConnect.FilmModel;

/**
 * Created by ase911 on 23.05.2017.
 */

public interface MainView extends MvpView{
    void setSuccess(List<FilmModel> pageModel);
    void setProgressBar();
    void hideProgressBar();
}
