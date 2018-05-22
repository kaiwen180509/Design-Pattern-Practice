package multiplefactory;

import common.ICookies;
import common.MilkCookies;

public class MilkCookiesFactory extends AbstractFactory {
    @Override
    public ICookies make() {
        return new MilkCookies();
    }
}
