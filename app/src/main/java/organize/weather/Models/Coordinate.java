package organize.weather.Models;

/**
 * Coordinate  model.
 *
 * @author ZHOURI Zakaria
 */

public class Coordinate {

    private float lon;
    private float lat;

    public Coordinate(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Coordinate() {
    }


    /**
     * @return The lon
     */
    public float getLon() {
        return lon;
    }

    /**
     * @param lon The lon
     */
    public void setLon(float lon) {
        this.lon = lon;
    }

    /**
     * @return The lat
     */
    public float getLat() {
        return lat;
    }

    /**
     * @param lat The lat
     */
    public void setLat(float lat) {
        this.lat = lat;
    }
}


