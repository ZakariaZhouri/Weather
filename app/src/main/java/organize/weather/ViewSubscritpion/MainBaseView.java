package organize.weather.ViewSubscritpion;

import organize.weather.Models.ModelWeatherReport;

/**
 * @author ZHOURI Zakaria
 */

public interface MainBaseView {

    void showWeather(ModelWeatherReport weathers);

    void showError();

}
