package ase.thmbproj.dataLayer.mainConnect;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ase.thmbproj.dataLayer.mainConnect.database.FilmPopularModel;

/**
 * Created by ase911 on 23.05.2017.
 *
 */
@Entity
public class FilmModel implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private Boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;//постер
    @SerializedName("popularity")
    private Float popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private float voteAverage;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public FilmPopularModel getFilmPopularModel(){ //todo mapping is not allowed here. Should be moved in mapper
        FilmPopularModel filmPopularModel = new FilmPopularModel();
        filmPopularModel.setId(id);
        filmPopularModel.setOverview(overview);
        filmPopularModel.setPosterPath(posterPath);
        filmPopularModel.setAdult(adult);
        filmPopularModel.setReleaseDate(releaseDate);
        filmPopularModel.setOriginalTitle(originalTitle);
        filmPopularModel.setOriginalLanguage(originalLanguage);
        filmPopularModel.setTitle(title);
        filmPopularModel.setBackdropPath(backdropPath);//постер
        filmPopularModel.setPopularity(popularity);
        filmPopularModel.setVoteCount(voteCount);
        filmPopularModel.setVideo(video);
        filmPopularModel.setVoteAverage(voteAverage);
        return filmPopularModel;
    }

}
