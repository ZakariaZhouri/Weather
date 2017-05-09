package organize.weather.UI;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import organize.weather.Models.ListWeather;
import organize.weather.R;
import organize.weather.Utils.DateUtils;
import organize.weather.Utils.FileUtils;
import organize.weather.Utils.WeatherConstants;
import organize.weather.Utils.WeatherUtils;

/**
 * @author ZHOURI Zakaria
 */

public class DetailActivity extends AppCompatActivity {


    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.country)
    TextView mCountry;
    @BindView(R.id.temperature)
    TextView mTemperature;
    @BindView(R.id.main)
    TextView mMain;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.average)
    TextView mAverage;
    @BindView(R.id.night)
    TextView mNight;
    @BindView(R.id.minmum)
    TextView mMin;
    @BindView(R.id.maximum)
    TextView mMax;
    @BindView(R.id.evening)
    TextView mEvening;
    @BindView(R.id.morning)
    TextView mMorning;
    @BindView(R.id.rain)
    TextView mRain;
    @BindView(R.id.speed)
    TextView mSpeed;
    @BindView(R.id.clouds)
    TextView mClouds;
    @BindView(R.id.direction)
    TextView mWindDirection;
    @BindView(R.id.pressure)
    TextView mPressure;
    @BindView(R.id.humidity)
    TextView mHumidity;
    @BindView(R.id.weatherIcon)
    ImageView mWeatherIcon;


    private ListWeather mListWeather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        ButterKnife.bind(this);
        mListWeather = getIntent().getParcelableExtra("test");
        fillViewData();
    }

    private void fillViewData() {
        //Description
        long date = mListWeather.getDate();
        String formattedDate = DateUtils.convertDate(date);
        mDate.setText(formattedDate);
        String mainWeatherState = mListWeather.getWeather().get(0).getMain();
        mMain.setText(mainWeatherState);
        String description = mListWeather.getWeather().get(0).getDescription();
        mDescription.setText(description);
        //Temperature
        float min = mListWeather.getTemperature().getMin();
        float max = mListWeather.getTemperature().getMax();
        float day = mListWeather.getTemperature().getDay();
        float morning = mListWeather.getTemperature().getMorn();
        float evening = mListWeather.getTemperature().getEve();
        float night = mListWeather.getTemperature().getNight();

        mTemperature.setText(String.valueOf((int) day) + WeatherConstants.UNIT_TEMP_CELSIUS);
        mNight.setText(String.valueOf((int) night) + WeatherConstants.UNIT_TEMP_CELSIUS);
        mAverage.setText(String.valueOf((int) day) + WeatherConstants.UNIT_TEMP_CELSIUS);
        mMin.setText(String.valueOf((int) min) + WeatherConstants.UNIT_TEMP_CELSIUS);
        mMax.setText(String.valueOf((int) max) + WeatherConstants.UNIT_TEMP_CELSIUS);
        mEvening.setText(String.valueOf((int) evening) + WeatherConstants.UNIT_TEMP_CELSIUS);
        mMorning.setText(String.valueOf((int) morning) + WeatherConstants.UNIT_TEMP_CELSIUS);

        //Wind
        float speed = mListWeather.getSpeed();
        int clouds = mListWeather.getClouds();
        int spKmHours = WeatherUtils.convertSpeedWind(speed);

        mSpeed.setText(String.valueOf(spKmHours) + WeatherConstants.UNIT_WIND_SPEED);
        mClouds.setText((clouds + WeatherConstants.PERCENT));
        mWindDirection.setText(getDirectionWinds());

        //Pressure
        float pressure = mListWeather.getPressure();
        float humidity = mListWeather.getHumidity();
        mPressure.setText(String.valueOf(pressure) + WeatherConstants.PRESSURE_UNITY);
        mHumidity.setText(String.valueOf(humidity) + WeatherConstants.PERCENT);
        //Rain
        float rain = mListWeather.getRain();
        mRain.setText(String.valueOf(rain) + WeatherConstants.UNIT_RAIN);
        //Icon
        String icon = mListWeather.getWeather().get(0).getIcon();
        mWeatherIcon.setImageURI(accessToIconURI(icon));

    }

    private Uri accessToIconURI(String icon) {
        String uri = FileUtils.getCacheDirectoryApplication(this) + File.separator + icon + WeatherConstants.ICON_EXTENSION;
        return Uri.parse(uri);
    }

    private String getDirectionWinds() {

        String west = getString(R.string.west);
        String south = getString(R.string.south);
        String east = getString(R.string.east);
        String north = getString(R.string.north);

        int deg = WeatherUtils.getDegreeWinds(mListWeather.getDeg());

        String degDirect = null;

        switch (deg) {
            case 1:
                degDirect = east;
                break;
            case 2:
                degDirect = south;
                break;
            case 3:
                degDirect = west;
                break;
            case 4:
                degDirect = north;
                break;

        }
        return degDirect;
    }
}
