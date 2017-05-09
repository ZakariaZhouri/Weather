package organize.weather.Utils;

import org.greenrobot.eventbus.EventBus;

/**
 * @author ZHOURI Zakaria
 */

public class BusProvider {
    private static final EventBus BUS = new EventBus();

    public static EventBus getInstance() {
        return BUS;
    }

    private BusProvider() {

    }
}
