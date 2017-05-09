package organize.weather.RecycleAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import organize.weather.Models.ListWeather;
import organize.weather.Models.Temperature;
import organize.weather.R;
import organize.weather.UI.DetailActivity;
import organize.weather.Utils.DateUtils;
import organize.weather.Utils.FileUtils;
import organize.weather.Utils.WeatherConstants;
import organize.weather.Utils.WeatherUtils;

/**
 * @author ZHOURI Zakaria
 */

public class WeatherRecyclerViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private ListWeather mListWeather;

    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.weather)
    TextView mWeather;
    @BindView(R.id.weather_description)
    TextView mWeatherDescription;
    @BindView(R.id.temperature)
    TextView mTemperature;
    @BindView(R.id.humidity)
    TextView mHumidity;
    @BindView(R.id.rain)
    TextView mRain;
    @BindView(R.id.speed)
    TextView mSpeed;
    @BindView(R.id.weatherIcon)
    ImageView mWeatherIcon;
    @BindView(R.id.more_details_image)
    ImageView mImageView;

    public WeatherRecyclerViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = context;
    }


    public void bindInformation(ListWeather listWeather) {
        mListWeather = listWeather;
        // Get current date of weather
        long date = listWeather.getDate();
        String formattedDate = DateUtils.convertDate(date);

        // Get current state of humidity
        int humidity = listWeather.getHumidity();

        // Get current state of rain
        float rain = listWeather.getRain();


        // Get current temperature
        Temperature temperature = listWeather.getTemperature();
        float dayTemp = temperature.getDay();

        // Get speed wind weather
        float sp = listWeather.getSpeed();
        int spKmHours = WeatherUtils.convertSpeedWind(sp);

        mDate.setText(formattedDate);
        mHumidity.setText(humidity + WeatherConstants.PERCENT);
        mRain.setText(String.valueOf(rain) + WeatherConstants.UNIT_RAIN);
        mTemperature.setText(String.valueOf((int) dayTemp) + WeatherConstants.UNIT_TEMP_CELSIUS);
        mWeather.setText(listWeather.getWeather().get(0).getMain());
        mWeatherDescription.setText("(" + listWeather.getWeather().get(0).getDescription() + ")");
        mSpeed.setText(String.valueOf(spKmHours) + WeatherConstants.UNIT_WIND_SPEED);
        loadWeatherIcon(listWeather.getWeather().get(0).getIcon());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetailActivity();
            }
        });

    }

    private void loadWeatherIcon(final String icon) {
        SimpleTarget simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                mWeatherIcon.setImageBitmap(resource);
                FileUtils.saveImage(mContext, icon, resource);
            }
        };
        Glide.with(mContext).load(getUrlIcon(icon)).asBitmap().into(simpleTarget);
    }

    @OnClick(R.id.more_details_image)
    public void onMoreDetailsClicked() {
        gotoDetailActivity();
    }

    /**
     * Concat name icon to url to get icon from server.
     *
     * @param iconName icon name.
     * @return url of icon.
     */
    private String getUrlIcon(String iconName) {
        return WeatherConstants.ICON_URL + iconName + WeatherConstants.ICON_EXTENSION;
    }

    public void gotoDetailActivity() {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("test", mListWeather);
        mContext.startActivity(intent);
    }
}
