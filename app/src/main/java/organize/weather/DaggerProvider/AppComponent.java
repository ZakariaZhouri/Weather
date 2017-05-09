package organize.weather.DaggerProvider;

import javax.inject.Singleton;

import dagger.Component;
import organize.weather.DaggerProvider.Modules.ApiModule;
import organize.weather.DaggerProvider.Modules.ContextModule;
import organize.weather.Presenter.MainPresenter;
import organize.weather.UI.WeatherActivity;

/**
 * @author ZHOURI Zakaria
 */

@Singleton
@Component(modules = {ContextModule.class, ApiModule.class})
public interface AppComponent {

    void inject(WeatherActivity weatherActivity);

    void inject(MainPresenter fiveDaysWeatherPresenter);

}
