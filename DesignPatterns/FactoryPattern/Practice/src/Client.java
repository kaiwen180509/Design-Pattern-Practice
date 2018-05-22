import common.ChocolateCookies;
import common.FruitCookies;
import common.ICookies;
import common.MilkCookies;
import factory.CookiesFactory;
import factory.Factory;
import lazyinitialization.LazyInitializationFactory;
import multiplefactory.ChocolateCookiesFactory;
import multiplefactory.MilkCookiesFactory;
import simplefactory.SimpleCookiesFactory;
import substitutesingleton.FruitCookiesSingletonFactory;
import substitutesingleton.MilkCookiesSingletonFactory;
import substitutesingleton.SingletonFactory;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------工廠方法模式----------");
        doFactory();
        System.out.println("----------簡單工廠模式----------");
        doSimpleFactroy();
        System.out.println("----------多個工廠模式----------");
        doMultipleFactory();
        System.out.println("----------替代單例模式----------");
        doSubstituteSingleton();
        System.out.println("----------延遲初始化模式----------");
        doLazyedInitialization();
    }

    private static void doFactory() {
        // 建立一個餅乾工廠
        Factory factory = new CookiesFactory();

        // 讓餅乾工廠製作巧克力餅乾
        ICookies chocolateICookies = factory.make(ChocolateCookies.class);
        chocolateICookies.flavor();
        chocolateICookies.price();

        // 讓餅乾工廠製作水果餅乾
        ICookies fruitICookies = factory.make(FruitCookies.class);
        fruitICookies.flavor();
        fruitICookies.price();

        // 讓餅乾工廠製作牛奶餅乾
        ICookies milkICookies = factory.make(MilkCookies.class);
        milkICookies.flavor();
        milkICookies.price();
    }

    private static void doSimpleFactroy() {
        // 建立一個餅乾簡單工廠
        SimpleCookiesFactory factory = new SimpleCookiesFactory();

        // 讓餅乾簡單工廠製作水果餅乾
        ICookies fruitICookies = factory.make(FruitCookies.class);
        fruitICookies.flavor();
        fruitICookies.price();
    }

    private static void doMultipleFactory() {
        // 讓巧克力餅乾專屬的餅乾工廠製作餅乾
        ICookies chocolateICookies = new ChocolateCookiesFactory().make();
        chocolateICookies.flavor();
        chocolateICookies.price();

        // 讓牛奶餅乾專屬的餅乾工廠製作餅乾
        ICookies milkICookies = new MilkCookiesFactory().make();
        milkICookies.flavor();
        milkICookies.price();
    }

    private static void doSubstituteSingleton() {
        // 建立一個單例工廠
        SingletonFactory singletonFactory = new SingletonFactory();

        // 找到牛奶餅乾工廠開始製作餅乾
        MilkCookiesSingletonFactory milkFactory = singletonFactory.getMilkCookiesSingletonFactory();
        ICookies milkICookies = milkFactory.make();
        milkICookies.flavor();
        milkICookies.price();

        // 找到水果餅乾工廠開始製作餅乾
        FruitCookiesSingletonFactory fruitFactory = singletonFactory.getFruitCookiesSingletonFactory();
        ICookies fruitICookies = fruitFactory.make();
        fruitICookies.flavor();
        fruitICookies.price();
    }

    private static void doLazyedInitialization() {
        // 建立一個延遲初始化工廠
        LazyInitializationFactory factory = new LazyInitializationFactory();

        // 讓工廠製造一個水果餅乾
        ICookies fruitICookies = factory.make("fruit");
        fruitICookies.flavor();
        fruitICookies.price();

        // 讓工廠製造一個巧克力餅乾
        ICookies chocolateICookies = factory.make("chocolate");
        chocolateICookies.flavor();
        chocolateICookies.price();

        // 讓工廠再次製造一個水果餅乾
        ICookies fruitICookies2 = factory.make("fruit");
        fruitICookies2.flavor();
        fruitICookies2.price();
    }
}
