package substitutesingleton;

import java.lang.reflect.Constructor;

public class SingletonFactory {
    private static FruitCookiesSingletonFactory fruitCookiesSingletonFactory;
    private static MilkCookiesSingletonFactory milkCookiesSingletonFactory;

    static {
        try {
            // 創建水果餅乾工廠的實例
            Class fruitC = Class.forName(FruitCookiesSingletonFactory.class.getName());
            Constructor<FruitCookiesSingletonFactory> fruitConstractor = fruitC.getDeclaredConstructor();
            fruitConstractor.setAccessible(true);
            fruitCookiesSingletonFactory = fruitConstractor.newInstance();

            // 創建牛奶餅乾工廠的實例
            Class milkC = Class.forName(MilkCookiesSingletonFactory.class.getName());
            Constructor<MilkCookiesSingletonFactory> milkConstractor = milkC.getDeclaredConstructor();
            milkConstractor.setAccessible(true);
            milkCookiesSingletonFactory = milkConstractor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FruitCookiesSingletonFactory getFruitCookiesSingletonFactory() {
        return fruitCookiesSingletonFactory;
    }

    public MilkCookiesSingletonFactory getMilkCookiesSingletonFactory() {
        return milkCookiesSingletonFactory;
    }
}
