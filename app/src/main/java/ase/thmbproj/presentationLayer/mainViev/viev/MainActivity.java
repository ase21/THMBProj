package ase.thmbproj.presentationLayer.mainViev.viev;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ase.thmbproj.R;
import ase.thmbproj.base.BaseMvpActivity;
import ase.thmbproj.dataLayer.mainConnect.FilmModel;
import ase.thmbproj.dataLayer.mainConnect.MainAdapter;
import ase.thmbproj.presentationLayer.mainViev.presenter.MainPresenter;
import ase.thmbproj.presentationLayer.mainViev.provider.DependencyProvider;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MainView {

    final String LOG_TAG = "myLogs";
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "получаем пресентер");

        initUi();

        presenter = DependencyProvider.getInstance(this).getMainPresenter();
        presenter.requestTopMovies();
    }

    @Override
    public void setSuccess(List<FilmModel> filmModelList) {
        Log.d(LOG_TAG,"надо показать результат");
        adapter.setData(filmModelList);
    }

    @Override
    public void setProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.topRated:
                Log.d(LOG_TAG,"выбор пункта меню");
                presenter.requestTopMovies();
                return true;
            case R.id.popular:
                presenter.requestPopular();
                return true;
            case R.id.favorite:
                presenter.requestFavorite();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initUi() {
        Log.d(LOG_TAG,"Попадаем в initUI");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
