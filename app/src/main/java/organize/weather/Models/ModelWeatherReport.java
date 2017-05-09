package organize.weather.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class of weather model, is contains setter and getter for each weather flags.
 *
 * @author ZHOURI Zakaria
 */
public class ModelWeatherReport {

    private City city;
    private List<ListWeather> list = new ArrayList<>();

    /**
     * Getter city.
     *
     * @return city instance.
     */
    public City getCity() {
        return city;
    }

    /**
     * Setter city.
     *
     * @param city instance of city.
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Getter list of weather flags.
     *
     * @return list of weather flags
     */
    public List<ListWeather> getListWeather() {
        return list;
    }

    /**
     * Setter list of weather flags.
     *
     * @param list list of weather flags.
     */
    public void setListWeather(List<ListWeather> list) {
        this.list = list;
    }
}
