package ase.thmbproj.presentationLayer.mainViev.provider;

import android.content.Context;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import ase.thmbproj.businessLayer.filmView.interactor.FilmInteractor;
import ase.thmbproj.businessLayer.filmView.interactor.FilmInteractorImpl;
import ase.thmbproj.businessLayer.mainView.interactor.MainInteractor;
import ase.thmbproj.businessLayer.mainView.interactor.MainInteractorImpl;
import ase.thmbproj.dataLayer.mainConnect.THMDClient;
import ase.thmbproj.dataLayer.mainConnect.database.FilmDao;
import ase.thmbproj.dataLayer.mainConnect.database.FilmDatabase;
import ase.thmbproj.presentationLayer.filmViev.presenter.FilmPresenter;
import ase.thmbproj.presentationLayer.mainViev.presenter.MainPresenter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ase911 on 23.05.2017.
 */

public class DependencyProvider {


    final String LOG_TAG = "myLogs";
    public static final String token = "6f7b98c3237f23ffb35be741dadfd3db";
    public static final String lang = "ru-RU";

    private MainPresenter mainPresenter;
    private MainInteractor mainInteractor;
    private THMDClient thmdClient;
    private static DependencyProvider instance;
    private Retrofit retrofit;
    private FilmPresenter filmPresenter;
    private FilmInteractor filmInteractor;
    private FilmDao filmDao;
    private Context context;


    private DependencyProvider(Context context) {
        this.context = context;
        Log.d(LOG_TAG,"Добрались до конструктора Пресентера");
        init();
    }

    private void init(){
        filmDao = FilmDatabase.getInstance(context).filmDao();
        thmdClient = initTMDBApi();
        mainInteractor = new MainInteractorImpl(thmdClient, filmDao);
        mainPresenter = new MainPresenter(mainInteractor);
        filmInteractor = new FilmInteractorImpl(filmDao);
        filmPresenter = new FilmPresenter(filmInteractor);

    }

    public static DependencyProvider getInstance(Context context) {
        if (instance == null){
            instance = new DependencyProvider(context);
        }
        return instance;
    }

    public MainPresenter getMainPresenter(){
        return mainPresenter;
    }


    private THMDClient initTMDBApi(){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create())//Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//подключаем адаптер RxJava2
                .client(client)
                .build();

        return retrofit
                .create(THMDClient.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public FilmPresenter getFilmPresenter() {
        return filmPresenter;
    }
}
