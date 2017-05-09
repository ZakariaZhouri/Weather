package organize.weather.Presenter;

import javax.inject.Inject;

import organize.weather.Network.WeatherApiClient;
import organize.weather.ViewSubscritpion.MainBaseView;
import organize.weather.ViewSubscritpion.PresenterMainInterface;
import organize.weather.WeatherApp;

/**
 * @author ZHOURI Zakaria
 */

public abstract class MainPresenter implements PresenterMainInterface<Object> {

    @Inject
    WeatherApiClient mWeatherApiClient;


    @Override
    public void attachView(Object view) {

    }


    @Override
    public void detachView() {

    }
}
