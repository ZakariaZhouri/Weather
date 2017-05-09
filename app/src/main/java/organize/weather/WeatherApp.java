package organize.weather;

import android.app.Application;

import organize.weather.DaggerProvider.AppComponent;
import organize.weather.DaggerProvider.DaggerAppComponent;
import organize.weather.DaggerProvider.Modules.ContextModule;

/**
 * @author ZHOURI Zakaria
 */

public class WeatherApp extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();
    }

    public static AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
