package substitutesingleton;

import common.MilkCookies;

public class MilkCookiesSingletonFactory {
    private MilkCookiesSingletonFactory() {
    }

    public static MilkCookies make() {
        return new MilkCookies();
    }
}
