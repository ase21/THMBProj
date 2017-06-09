package ase.thmbproj.dataLayer.mainConnect.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import ase.thmbproj.dataLayer.mainConnect.FilmModel;

/**
 * Created by ase911 on 29.05.2017.
 */
@Entity
public class FilmFavoriteModel {

    @PrimaryKey
    private Integer id; //id

    private String title; //имя

    private String posterPath;//постер

    private String releaseDate;// дата выхода на экран

    private float voteAverage; //рейтинг

    private String overview; //описание

    public FilmFavoriteModel(Integer id, String title, String posterPath, String releaseDate, float voteAverage, String overview) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public FilmModel getFilmModel(){
        FilmModel filmModel = new FilmModel();

        filmModel.setId(id);
        filmModel.setTitle(title);
        filmModel.setPosterPath(posterPath);
        filmModel.setReleaseDate(releaseDate);
        filmModel.setVoteAverage(voteAverage);
        filmModel.setOverview(overview);
        return filmModel;
    }
}
