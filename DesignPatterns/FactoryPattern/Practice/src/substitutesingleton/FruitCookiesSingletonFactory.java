package substitutesingleton;

import common.ICookies;
import common.FruitCookies;

public class FruitCookiesSingletonFactory {
    private FruitCookiesSingletonFactory() {
    }

    public static ICookies make() {
        return new FruitCookies();
    }
}
