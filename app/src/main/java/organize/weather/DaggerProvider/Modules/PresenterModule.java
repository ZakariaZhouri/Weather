package organize.weather.DaggerProvider.Modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import organize.weather.Presenter.FiveDaysWeatherPresenter;

/**
 * @author ZHOURI Zakaria
 */
@Module
public class PresenterModule {

    @Singleton
    @Provides
    public FiveDaysWeatherPresenter provideMainPresenter() {
        return new FiveDaysWeatherPresenter();
    }
}
