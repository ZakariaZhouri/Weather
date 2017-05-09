package organize.weather.Presenter;

import android.util.SparseArray;

import organize.weather.ViewSubscritpion.PresenterMainInterface;

/**
 * @author ZHOURI Zakaria
 */

public class PresentersTable {

    private static SparseArray<PresenterMainInterface> mPresenters = new SparseArray<>();

    public static <T extends PresenterMainInterface> T getPresenter(int viewId) {
        return (T) mPresenters.get(viewId);
    }

    public static void savePresenter(int viewId, PresenterMainInterface presenter) {
        mPresenters.put(viewId, presenter);
    }

    public static void removePresenter(int viewId) {
        mPresenters.remove(viewId);
    }

    public interface PresenterId {
        int getId();
    }
}
