package organize.weather.ViewSubscritpion;

/**
 * @author ZHOURI Zakaria
 */
public interface PresenterMainInterface<V> {
    void attachView(V view);

    void detachView();
}
