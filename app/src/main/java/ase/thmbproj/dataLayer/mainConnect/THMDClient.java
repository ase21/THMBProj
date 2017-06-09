package ase.thmbproj.dataLayer.mainConnect;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ase911 on 23.05.2017.
 */

public interface THMDClient {
    //топ рейтинга
    @GET("top_rated")
    Single<PageModel> topRated(@Query("api_key") String token,
                               @Query("language") String lang);

    //popular
    @GET("popular")
    Single<PageModel> popular(@Query("api_key") String token,
                              @Query("language") String lang);

}
