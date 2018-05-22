package factory;

import common.ICookies;

public class CookiesFactory extends Factory {
    @Override
    public <T extends ICookies> T make(Class<T> c) {
        ICookies cookies = null;
        try {
            cookies = (ICookies) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) cookies;
    }
}
