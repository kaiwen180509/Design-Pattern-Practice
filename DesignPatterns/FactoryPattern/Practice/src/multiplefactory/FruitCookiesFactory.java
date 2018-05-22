package multiplefactory;

import common.ICookies;
import common.FruitCookies;

public class FruitCookiesFactory extends AbstractFactory {
    @Override
    public ICookies make() {
        return new FruitCookies();
    }
}
