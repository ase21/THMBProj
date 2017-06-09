package ase.thmbproj;

import android.app.Application;
import android.content.Context;

import ase.thmbproj.dataLayer.mainConnect.database.FilmDatabase;
import ase.thmbproj.presentationLayer.mainViev.provider.DependencyProvider;

/**
 * Created by ase911 on 23.05.2017.
 */

public class App extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return App.context;
    }
}
