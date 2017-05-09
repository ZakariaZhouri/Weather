package organize.weather.Network;

import io.reactivex.Observable;
import organize.weather.Models.ModelWeatherReport;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author ZHOURI Zakaria
 */

public interface WeatherApiClient {

    @GET("daily?q=Paris&units=metric&cnt=5")
    Observable<ModelWeatherReport> getWeatherReportDefault(@Query("appid") String apiId);
}
