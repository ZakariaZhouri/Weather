package organize.weather.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * List Weather model.
 *
 * @author ZHOURI Zakaria
 */
public class ListWeather implements Parcelable {

    private long dt;
    private Temperature temp;
    private float pressure;
    private int humidity;
    private List<Weather> weather = new ArrayList<>();
    private float speed;
    private int deg;
    private int clouds;
    private float rain;

    public ListWeather() {
    }

    public ListWeather(long dt, Temperature temp, float pressure, int humidity, List<Weather> weather, float speed, int deg, int clouds, float rain) {
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.deg = deg;
        this.clouds = clouds;
        this.rain = rain;
    }

    public long getDate() {
        return dt;
    }

    public void setDate(long dt) {
        this.dt = dt;
    }

    public Temperature getTemperature() {
        return temp;
    }

    public void setTemperature(Temperature temperature) {
        this.temp = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.dt);
        dest.writeParcelable(this.temp, flags);
        dest.writeFloat(this.pressure);
        dest.writeInt(this.humidity);
        dest.writeList(this.weather);
        dest.writeFloat(this.speed);
        dest.writeInt(this.deg);
        dest.writeInt(this.clouds);
        dest.writeFloat(this.rain);
    }

    protected ListWeather(Parcel in) {
        this.dt = in.readLong();
        this.temp = in.readParcelable(Temperature.class.getClassLoader());
        this.pressure = in.readFloat();
        this.humidity = in.readInt();
        this.weather = new ArrayList<Weather>();
        in.readList(this.weather, Weather.class.getClassLoader());
        this.speed = in.readFloat();
        this.deg = in.readInt();
        this.clouds = in.readInt();
        this.rain = in.readFloat();
    }

    public static final Parcelable.Creator<ListWeather> CREATOR = new Parcelable.Creator<ListWeather>() {
        @Override
        public ListWeather createFromParcel(Parcel source) {
            return new ListWeather(source);
        }

        @Override
        public ListWeather[] newArray(int size) {
            return new ListWeather[size];
        }
    };
}
