# 工廠模式 - Factory Pattern
## 定義
> - “Define an interface for creating an object, but let subclasses decide which class to instantiate. The Factory method lets a class defer instantiation it uses to subclasses.”
> - 定義一個為了創建物件的介面，讓子類別決定哪一個類別被實例化。
> - 工廠方法讓一個類別的實例化延緩到子類別。

## 優點
> 1. 良好的封裝性
> 2. 架構清晰
> 3. 降低模組間的耦合
> 4. 良好的擴展性
> 5. 符合[迪米特法則（LoD）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LeastKnowledgePrinciple/Notes.md "迪米特法則（LoD）")
> 6. 符合[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")
> 7. 符合[里氏替換原則（LSP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "里氏替換原則（LSP）")
> 
> 使用工廠來產生物件的實例，讓模組間不需要知道如何去建立，降低了耦合，並且具有良好的擴展性，只要修改具體的工廠類別或是使用[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")來擴展工廠，都可以完成需求變更，是一個典型的解耦合框架。

## 缺點
> 1. 提升程式的複雜度
> 
> 由於需要建立工廠來產生物件的實例，因此導致程式的複雜度上升。

## 使用時機
> 1. 代替 new 來建立實例
> 2. 需要可擴展的靈活框架時

## 實踐
> 1. 工廠方法模式
> 2. 簡單工廠模式（擴展）
> 3. 多個工廠類別（擴展）
> 4. 替代[單例模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/SingletonPattern/Notes.md "單例模式")（擴展）
> 5. 延遲初始化（擴展）
> 
> 工廠模式是一個很常見的模式，其中分為一般的工廠方法模式，以及擴展的四個模式，分別是簡單工廠模式、多個工廠類別、替代單例模式還有延遲初始化等諸多基本的工廠方法模式擴展。

## 範例
以一個生產餅乾的工廠來做例子，先建立各個模式會用到的共用類別，餅乾的介面`ICookies`定義餅乾口味`flavor()`與價格`price()`的方法，而具體實作交給巧克力餅乾`ChocolateCookies`、牛奶餅乾`MilkCookies`還有水果餅乾`FruitCookies`，下面是實際的程式碼：
#### ICookies
```java
public interface ICookies {
    void flavor();
    void price();
}
```
#### ChocolateCookies
```java
public class ChocolateCookies implements ICookies {
    @Override
    public void flavor() {
        System.out.println("巧克力口味");
    }

    @Override
    public void price() {
        System.out.println("價格：15 元");
    }
}
```
#### MilkCookies
```java
public class MilkCookies implements ICookies {
    @Override
    public void flavor() {
        System.out.println("牛奶口味");
    }

    @Override
    public void price() {
        System.out.println("價格：12 元");
    }
}
```
#### FruitCookies
```java
public class FruitCookies implements ICookies {
    @Override
    public void flavor() {
        System.out.println("水果口味");
    }

    @Override
    public void price() {
        System.out.println("價格：10 元");
    }
}
```
### 工廠方法模式：
首先建立一個抽象的工廠類別`Factory`，裡面定義一個[泛型](https://en.wikipedia.org/wiki/Generic_programming "泛型")方法來限制製作方法`make()`的輸入參數，並且讓餅乾工廠`CookiesFactory`的類別來實作，實際的製作是由`CookiesFactory`的類別完成的。

以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/FactoryPattern/Picture/FactoryMethodPicture.png)

#### Factory
```java
public abstract class Factory {
    public abstract <T extends ICookies> T make(Class<T> c);
}
```
#### CookiesFactory
```java
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
```
### 簡單工廠模式：
又稱：靜態工廠模式，簡化工廠類別的創建，但是擴展較為困難，不符合[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")。只要建立一個簡單的餅乾工廠類別`SimpleCookiesFactory`，裡面提供一個製作`make()`的方法製作餅乾。

以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/FactoryPattern/Picture/SimpleFactoryPicture.png)

#### SimpleCookiesFactory
```java
public class SimpleCookiesFactory {
    public static <T extends ICookies> T make(Class<T> c) {
        ICookies cookies = null;
        try {
            cookies = (ICookies) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) cookies;
    }
}
```
### 多個工廠模式：
讓每一種餅乾都由一種工廠來生產，符合[單一職責原則（SRP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "單一職責原則（SRP）")，並且讓工廠變得較簡單，但是增加了擴展與維護的麻煩，每多一種新的餅乾就要新建一個新的工廠，在複雜的情況下可以新增一個協調類別來封裝各個工廠來改善。

建立一個抽象的工廠類別`AbstractFactory`，裡面定義一個製作`make()`的方法，讓子工廠`ChocolateCookiesFactory`、`FruitCookiesFactory`以及`MilkCookiesFactory`類別繼承，再具體的去產生餅乾。

以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/FactoryPattern/Picture/MultipleFactoryPicture.png)

#### AbstractFactory
```java
public abstract class AbstractFactory {
    public abstract ICookies make();
}
```
#### ChocolateCookiesFactory
```java
public class ChocolateCookiesFactory extends AbstractFactory {
    @Override
    public ICookies make() {
        return new ChocolateCookies();
    }
}
```
#### FruitCookiesFactory
```java
public class FruitCookiesFactory extends AbstractFactory {
    @Override
    public ICookies make() {
        return new FruitCookies();
    }
}
```
#### MilkCookiesFactory
```java
public class MilkCookiesFactory extends AbstractFactory {
    @Override
    public ICookies make() {
        return new MilkCookies();
    }
}
```
### 替代單例模式：
透過工廠模式來建立[單例模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/SingletonPattern/Notes.md "單例模式")的唯一實例，可以作為專案中的單例構造器。

建立兩個單例的工廠來生產餅乾，分別是水果餅乾工廠`FruitCookiesSingletonFactory`以及牛奶餅乾工廠`MilkCookiesSingletonFactory`，讓單例構造器`SingletonFactory`來產生這兩個類別的唯一實例，並且提供`get()`方法讓應用程式可以取得使用。

以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/FactoryPattern/Picture/SubstituteSingletonPicture.png)

#### FruitCookiesSingletonFactory
```java
public class FruitCookiesSingletonFactory {
    private FruitCookiesSingletonFactory() {
    }

    public static ICookies make() {
        return new FruitCookies();
    }
}
```
#### MilkCookiesSingletonFactory
```java
public class MilkCookiesSingletonFactory {
    private MilkCookiesSingletonFactory() {
    }

    public static MilkCookies make() {
        return new MilkCookies();
    }
}
```
#### SingletonFactory
```java
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
```
### 延遲初始化模式：
當物件初始化後，由工廠保存下來，等待再次被使用，減少初始化複雜物件產生的資源耗費，根據需求，也可以設置物件的最大數量。

建立一個延遲初始化的工廠`LazyInitializationFactory`，定義一個`Map`的容器，保存物件的初始化，當製作`make()`方法被呼叫時，檢查`Map`容器裡面有沒有，有就從中取出回傳，減少資源耗費，沒有就產生再加入到`Map`的容器。

以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/FactoryPattern/Picture/LazyInitializationPicture.png)

#### LazyInitializationFactory
```java
public class LazyInitializationFactory {
    private static Map<String, ICookies> map = new HashMap<>();

    public static synchronized ICookies make(String type) {
        ICookies cookies = null;
        // 先檢查是否存在實例
        if (map.containsKey(type)) {
            System.out.println(type + " 已存在，從庫存中取出");
            cookies = map.get(type);
        } else {
            // 不存在就建立實例並保存
            if (type.equals("milk")) {
                cookies = new MilkCookies();
            } else if (type.equals("fruit")) {
                cookies = new FruitCookies();
            } else {
                cookies = new ChocolateCookies();
            }
            map.put(type, cookies);
        }
        return cookies;
    }
}
```
## 實際應用
```java
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
```
#### 執行結果
>     ----------工廠方法模式----------
>     巧克力口味
>     價格：15 元
>     水果口味
>     價格：10 元
>     牛奶口味
>     價格：12 元
>     ----------簡單工廠模式----------
>     水果口味
>     價格：10 元
>     ----------多個工廠模式----------
>     巧克力口味
>     價格：15 元
>     牛奶口味
>     價格：12 元
>     ----------替代單例模式----------
>     牛奶口味
>     價格：12 元
>     水果口味
>     價格：10 元
>     ----------延遲初始化模式----------
>     水果口味
>     價格：10 元
>     巧克力口味
>     價格：15 元
>     fruit 已存在，從庫存中取出
>     水果口味
>     價格：10 元

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Factory_method_pattern
 - https://en.wikipedia.org/wiki/Generic_programming
