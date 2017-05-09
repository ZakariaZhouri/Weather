package organize.weather.UI;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import organize.weather.Models.ListWeather;
import organize.weather.Models.ModelWeatherReport;
import organize.weather.Presenter.FiveDaysWeatherPresenter;
import organize.weather.Presenter.PresentersTable;
import organize.weather.R;
import organize.weather.RecycleAdapters.WeatherRecyclerAdapter;
import organize.weather.ViewSubscritpion.MainBaseView;
import organize.weather.WeatherApp;

/**
 * @author ZHOURI Zakaria
 */

public class WeatherActivity extends AppCompatActivity implements MainBaseView, PresentersTable.PresenterId {

    private final static String LIST_WEATHER = "LIST_WEATHER";

    private WeatherRecyclerAdapter mWeatherRecyclerAdapter;
    List<ListWeather> list;

    @BindView(R.id.weather_recycler)
    RecyclerView mWeatherRecycler;

    @BindView(R.id.reload)
    Button mReloadButton;
    @BindView(R.id.network_error)
    TextView mNetworkError;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    FiveDaysWeatherPresenter mFiveDaysWeatherPresenter;

    @Inject
    public WeatherActivity() {
        WeatherApp.getmAppComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        getWeather();
        if (savedInstanceState != null) {
            mFiveDaysWeatherPresenter = PresentersTable.getPresenter(getId());
            list = savedInstanceState.getParcelableArrayList(LIST_WEATHER);
            updateRecycler();

        } else {
            PresentersTable.savePresenter(getId(), mFiveDaysWeatherPresenter);
        }
        mFiveDaysWeatherPresenter.attachView(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mReloadButton.setOnClickListener(new OnReloadClicked());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mReloadButton.setOnClickListener(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_WEATHER, (ArrayList<? extends Parcelable>) list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFiveDaysWeatherPresenter.detachView();
    }

    @Override
    public void showWeather(ModelWeatherReport weathers) {
        mProgressBar.setVisibility(View.GONE);
        list = weathers.getListWeather();
        updateRecycler();
    }

    @Override
    public void showError() {
        mNetworkError.setVisibility(View.VISIBLE);
        mReloadButton.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }


    @Override
    public int getId() {
        return 1;
    }

    /**
     * Functions utils
     */

    private void updateRecycler() {
        mWeatherRecyclerAdapter = new WeatherRecyclerAdapter(this);
        mWeatherRecyclerAdapter.setList(list);
        mWeatherRecycler.setLayoutManager(new LinearLayoutManager(this));
        mWeatherRecycler.setAdapter(mWeatherRecyclerAdapter);
        mWeatherRecyclerAdapter.notifyDataSetChanged();
    }

    private void getWeather() {
        mFiveDaysWeatherPresenter.getWeather();
    }

    /**
     * Listeners
     */

    private class OnReloadClicked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            getWeather();
        }
    }
}
