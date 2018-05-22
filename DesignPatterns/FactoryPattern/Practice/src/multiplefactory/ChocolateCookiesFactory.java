package multiplefactory;

import common.ChocolateCookies;
import common.ICookies;

public class ChocolateCookiesFactory extends AbstractFactory {
    @Override
    public ICookies make() {
        return new ChocolateCookies();
    }
}
