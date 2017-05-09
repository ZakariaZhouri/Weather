package organize.weather.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ZHOURI Zakaria
 */

public class FileUtils {
    private static final String TAG = "FileUtils";

    /**
     * CACHE PARIS WEATHER APPLICATION
     */
    public static final String CACHE_PARIS_WEATHER_APPLICATION = "ParisWeather";

    /**
     * Save image icon in the sd card.
     *
     * @param iconName icon name.
     * @param bitmap   current bitmap.
     * @return state of saving process: <code>true</code> means the saving of image has done with
     * success.
     */
    public static boolean saveImage(Context context, String iconName, Bitmap bitmap) {

        // Create paris weather cache file.
        if (createDirectoryApplication(context)) {
            Log.e("", "Error occurred during creation application directory");
        }
        String path = getCacheDirectoryApplication(context).getPath() + File.separator + iconName + WeatherConstants.ICON_EXTENSION;
        // Init image stream file
        File file = new File(path);

        try {

            boolean resultCreation = file.createNewFile();



            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.close();

        } catch (IOException e) {
            Log.e(TAG, "Error occurred during creation application directory:" + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Check given file is already exist or otherwise.
     *
     * @param path path of given file.
     * @return <code>true</code> means is exist or otherwise.
     */
    public static boolean isExistFile(String path) {
        return new File(path).exists();
    }

    /**
     * Create Directory Application
     *
     * @return the created directory.
     */
    private static boolean createDirectoryApplication(Context context) {

        File directory = getCacheDirectoryApplication(context);

        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Log.e("test", "test");
                return false;
            }
        }
        return true;
    }

    /**
     * Getter cache directory application.
     *
     * @return instance of directory application.
     */
    public static File getCacheDirectoryApplication(Context context) {
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), CACHE_PARIS_WEATHER_APPLICATION);
    }

}
