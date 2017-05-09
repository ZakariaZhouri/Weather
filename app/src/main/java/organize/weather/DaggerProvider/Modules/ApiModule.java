package organize.weather.DaggerProvider.Modules;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import organize.weather.Network.WeatherApiClient;
import organize.weather.Utils.WeatherConstants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ZHOURI Zakaria
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    public WeatherApiClient provideWeatherApiClient() {
        return new Retrofit.Builder()
                .baseUrl(WeatherConstants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(WeatherApiClient.class);
    }
}
