package organize.weather.Presenter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import organize.weather.Models.ModelWeatherReport;
import organize.weather.Network.WeatherApiClient;
import organize.weather.Utils.WeatherConstants;
import organize.weather.ViewSubscritpion.MainBaseView;
import organize.weather.ViewSubscritpion.PresenterMainInterface;
import organize.weather.WeatherApp;


/**
 * @author ZHOURI Zakaria
 */

public class FiveDaysWeatherPresenter extends MainPresenter {

    private MainBaseView mMainBaseView;

    @Inject
    public FiveDaysWeatherPresenter() {
        WeatherApp.getmAppComponent().inject(this);
    }

    public void getWeather() {
        Observable<ModelWeatherReport> observable = mWeatherApiClient.getWeatherReportDefault(WeatherConstants.API_KEY);
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ModelWeatherReport>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ModelWeatherReport value) {
                mMainBaseView.showWeather(value);
                Log.e("Tag", "Le temps a paris est " + value.toString());

                //   BusProvider.getInstance().post(new TEST(value));
            }

            @Override
            public void onError(Throwable e) {
                mMainBaseView.showError();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void attachView(Object view) {
        mMainBaseView = (MainBaseView) view;

    }

    @Override
    public void detachView() {
        mMainBaseView = null;
    }


}
