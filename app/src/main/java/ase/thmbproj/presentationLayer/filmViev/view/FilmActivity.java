package ase.thmbproj.presentationLayer.filmViev.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ase.thmbproj.R;
import ase.thmbproj.base.BaseMvpActivity;
import ase.thmbproj.dataLayer.mainConnect.FilmModel;
import ase.thmbproj.dataLayer.mainConnect.database.FilmFavoriteModel;
import ase.thmbproj.presentationLayer.filmViev.presenter.FilmPresenter;
import ase.thmbproj.presentationLayer.mainViev.provider.DependencyProvider;

import static ase.thmbproj.dataLayer.mainConnect.MainAdapter.POSITION;

/**
 * Created by ase911 on 26.05.2017.
 */

public class FilmActivity extends BaseMvpActivity<FilmView, FilmPresenter> implements FilmView{
    //TODO NAMING !!!!!!!!!! FilmActivity = FilmActivity
    private boolean addToFavoriteFlag = true;
    private FilmFavoriteModel filmFavoriteModel;
    private Button button; //// TODO: 08.06.2017  private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        Toolbar toolbar = (Toolbar) findViewById(R.id.backToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FilmModel filmModel = (FilmModel) getIntent().getExtras().get(POSITION);

        //todo move in initUi() method
        ImageView poster = (ImageView) findViewById(R.id.imageViewFilmActivity);
        TextView title = (TextView) findViewById(R.id.title);
        TextView release_date = (TextView) findViewById(R.id.release_date);
        TextView vote_average = (TextView) findViewById(R.id.vote_average);
        TextView overview = (TextView) findViewById(R.id.overview);
        button = (Button) findViewById((R.id.marktoDB));

        title.setText(filmModel.getTitle());
        release_date.setText(filmModel.getReleaseDate());
        vote_average.setText(String.valueOf(filmModel.getVoteAverage()));
        overview.setText(filmModel.getOverview());

        Picasso.with(this)
                .load("https://image.tmdb.org/t/p/w640" + filmModel.getPosterPath())
                .into(poster);

        filmFavoriteModel  = new FilmFavoriteModel(filmModel.getId(),
                filmModel.getTitle(),
                filmModel.getPosterPath(),
                filmModel.getReleaseDate(),
                filmModel.getVoteAverage(),
                filmModel.getOverview());
        presenter = DependencyProvider.getInstance(this).getFilmPresenter();
        presenter.checkFilmInFavorite(filmFavoriteModel.getId());
    }


    public void onClick(View v) {
        if (addToFavoriteFlag){
            presenter.insertIntoFavorite(filmFavoriteModel);
        } else {
            presenter.deleteFromFavorite(filmFavoriteModel);
        }
    }

    @Override
    public void setDeleteState() {
        button.setText(R.string.deleteFromFavorite);
        addToFavoriteFlag = false;
    }

    @Override
    public void setInsertState() {
        button.setText(R.string.addToFavorite);
        addToFavoriteFlag = true;
    }

    private void initUI(){
    }
}
