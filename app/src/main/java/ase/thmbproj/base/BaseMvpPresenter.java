package ase.thmbproj.base;



import android.view.View;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseMvpPresenter <V extends MvpView> {

    protected V view;

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        compositeDisposable.clear();
        view = null;
    }

}
