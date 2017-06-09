package ase.thmbproj.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseMvpActivity<V extends MvpView, P extends BaseMvpPresenter<V>>
        extends AppCompatActivity
        implements MvpView {

    protected P presenter;

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView((V)this);
    }

    @Override
    protected void onStop() {
        presenter.detachView();
        super.onStop();
    }
}
