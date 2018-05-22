package factory;

import common.ICookies;

public abstract class Factory {
    public abstract <T extends ICookies> T make(Class<T> c);
}
