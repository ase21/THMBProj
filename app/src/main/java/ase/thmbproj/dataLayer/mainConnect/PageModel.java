package ase.thmbproj.dataLayer.mainConnect;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Result;

/**
 * Created by ase911 on 23.05.2017.
 */

public class PageModel {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<FilmModel> results = null;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<FilmModel> getResults() {
        return results;
    }

    public void setResults(List<FilmModel> results) {
        this.results = results;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}

