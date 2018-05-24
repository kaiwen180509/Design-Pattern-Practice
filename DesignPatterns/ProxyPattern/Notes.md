# 代理模式 - Proxy Pattern
## 定義
> - “Provide a surrogate or placeholder for another object to control access to it.”
> - 為了對另一個物件的存取與控制而提供的一種代理。

## 優點
> 1. 職責清晰
> 2. 高擴展性
> 3. 智能化
>
> 由物件去實作實際的操作，不用關心其他的職責，後期使用代理來完成事務，讓程式碼變簡潔，並且由於有介面所定義的約束，因此實作的類別不管如何擴展或變更，代理不需修改即可使用。

## 使用時機
> 1. 複雜的操作，為了減輕負擔時

## 實踐
> 1. 普通代理模式（靜態）
> 2. 代理模式擴展（靜態）
> 3. 強制代理模式（靜態）
> 4. 動態代理模式

## 參與者
> + 抽象主題角色 - Subject
>     * 基本方法的定義
> + 具體主題角色 - RealSubject
>     * 稱作被委託、被代理角色，具體實作 Subject 方法的類別
> + 代理主題角色 - Proxy
>     * 稱作委託、代理類別，負責 RealSubject 的應用，並做準備工作、預處理與後處理

## 範例
### 普通代理模式：
現在政府正在大力推行酒後代駕服務，讓喝酒的人可以找代理駕駛送自己回家，用這個代駕服務來當代理模式的例子，先定義個駕駛`IDriver`的介面，這個介面就是抽象主題角色，裡面定義了駕駛的各個方法，接著建立具體主題角色駕駛`Driver`的類別，這個類別實作了駕駛的介面`IDriver`，最後是代理主題角色的實現，建立一個代理駕駛`ProxyDriver`的類別，裡面除了實作駕駛的介面`IDriver`外，還加上了準備工作`prepare()`、預處理`before()`以及後處理`after()`，並且將被代理的實例`Driver`透過建構子傳入，以便控制具體主題角色駕駛`Driver`，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/ProxyPattern/Picture/ProxyPatternPicture.png)

#### IDriver
```java
public interface IDriver {
    void board();
    void leave();
    void drive();
    void park();
    void horn();
}
```
#### Driver
```java
public class Driver implements IDriver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    @Override
    public void board() {
        System.out.println(this.name + "上車");
    }

    @Override
    public void leave() {
        System.out.println(this.name + "下車");
    }

    @Override
    public void drive() {
        System.out.println(this.name + "在開車");
    }

    @Override
    public void park() {
        System.out.println(this.name + "在停車");
    }

    @Override
    public void horn() {
        System.out.println(this.name + "按喇叭");
    }
}
```
#### ProxyDriver
```java
public class ProxyDriver implements IDriver {
    private Driver driver;

    public ProxyDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void board() {
        prepare();
        this.driver.board();
    }

    @Override
    public void leave() {
        this.driver.leave();
        after();
    }

    @Override
    public void drive() {
        before();
        this.driver.drive();
    }

    @Override
    public void park() {
        before();
        this.driver.park();
    }

    @Override
    public void horn() {
        before();
        this.driver.horn();
    }

    // 準備工作
    private void prepare() {
        System.out.println("代理司機準備代替駕駛");
    }

    // 預處理
    private void before() {
        System.out.print("代理司機代替");
    }

    // 後處理
    private void after() {
        System.out.println("代理司機離開");
    }
}
```
### 代理模式擴展：
代理主題角色不僅可以實作抽象主題角色的介面，也可以實作其他介面，例如現在讓代理主題角色的多實作一個介面`IProxy`，並且實作介面定義的方法收費`fee()`，以下是實際程式碼與類別：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/ProxyPattern/Picture/ProxyPatternExtendPicture.png)

#### IProxy
```java
public interface IProxy {
    void fee();
}
```
#### ProxyDriverExtend
```java
public class ProxyDriverExtend implements IDriver, IProxy {
    private Driver driver;

    @Override
    public void fee() {
        System.out.println("代理司機費用為：1200 元");
    }

    public ProxyDriverExtend(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void board() {
        prepare();
        this.driver.board();
    }

    @Override
    public void leave() {
        this.driver.leave();
        after();
    }

    @Override
    public void drive() {
        before();
        this.driver.drive();
    }

    @Override
    public void park() {
        before();
        this.driver.park();
    }

    @Override
    public void horn() {
        before();
        this.driver.horn();
    }

    // 準備工作
    private void prepare() {
        System.out.println("代理司機準備代替駕駛");
    }

    // 預處理
    private void before() {
        System.out.print("代理司機代替");
    }

    // 後處理
    private void after() {
        System.out.println("代理司機離開");
    }
}
```
### 強制代理模式：
強制代理模式必須透過具體主題角色來取得代理主題角色，否則不能夠存取，也就是說只有透過代理者才能存取被代理者，而代理者本身也只有跟被代理者取得才行，下面舉一個例子來說明。

由於酒駕的人還是太多，因此現在政府決定要強制推動這項代駕服務了，因此只要喝了酒的人，就不能開車，要找代理駕駛，但是當一個女司機要找代理駕駛怎麼辦？如果遇到壞的代理駕駛，不就糟糕了，既然隨便找的代理駕駛不放心，那就只能讓女司機自己找一個她指定的代理司機，此時正好可以使用強制代理模式。

首先建立一個抽象主題角色的介面`IWomanDriver`，裡面除了定義了駕駛的方法，還有一個取得指定代理駕駛的方法`getProxy()`，用來提供具體主題角色的代理者，接著在實作抽象主題角色介面的類別`WomanDriver`內，新增一個方法`isProxy()`，在其他的方法中呼叫檢查是不是指定的代理者，不是就拒絕執行，以此保護自己，而代理主題角色的代理者類別`ProxyWomanDriver`則用來控制具體主題角色`WomanDriver`，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/ProxyPattern/Picture/ProxyPatternForcePicture.png)

#### IWomanDriver
```java
public interface IWomanDriver {
    void board();
    void leave();
    void drive();
    void park();
    void horn();
    IWomanDriver getProxy();
}
```
#### WomanDriver
```java
public class WomanDriver implements IWomanDriver {
    private String name;
    private IWomanDriver proxy = null;

    public WomanDriver(String name) {
        this.name = name;
    }

    @Override
    public void board() {
        if (this.isProxy()) {
            System.out.println(this.name + "上車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void leave() {
        if (this.isProxy()) {
            System.out.println(this.name + "下車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void drive() {
        if (this.isProxy()) {
            System.out.println(this.name + "在開車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void park() {
        if (this.isProxy()) {
            System.out.println(this.name + "在停車");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public void horn() {
        if (this.isProxy()) {
            System.out.println(this.name + "按喇叭");
        } else {
            System.out.println("請讓指定的代理來");
        }
    }

    @Override
    public IWomanDriver getProxy() {
        this.proxy = new ProxyWomanDriver(this);
        return this.proxy;
    }

    // 檢查是不是代理
    private boolean isProxy() {
        if (this.proxy == null) {
            return false;
        }
        return true;
    }
}
```
#### ProxyWomanDriver
```java
public class ProxyWomanDriver implements IWomanDriver {
    private WomanDriver womanDriver = null;

    public ProxyWomanDriver(WomanDriver womanDriver) {
        this.womanDriver = womanDriver;
    }

    @Override
    public void board() {
        prepare();
        this.womanDriver.board();
    }

    @Override
    public void leave() {
        this.womanDriver.leave();
        after();
    }

    @Override
    public void drive() {
        before();
        this.womanDriver.drive();
    }

    @Override
    public void park() {
        before();
        this.womanDriver.park();
    }

    @Override
    public void horn() {
        before();
        this.womanDriver.horn();
    }

    @Override
    public IWomanDriver getProxy() {
        return this;
    }

    // 準備工作
    private void prepare() {
        System.out.println("女代理司機準備代替駕駛");
    }

    // 預處理
    private void before() {
        System.out.print("女代理司機代替");
    }

    // 後處理
    private void after() {
        System.out.println("女代理司機離開");
    }
}
```
### 動態代理模式：
[橫切面導向程式設計（Aspect-Oriented Programming, AOP）](https://en.wikipedia.org/wiki/Aspect-oriented_programming "橫切面導向程式設計（Aspect-Oriented Programming, AOP）")核心就是採用動態代理機制，而動態代理與靜態代理之間的差異，在於動態代理不會改變原有的代理類別，在不修改既有的架構下增加對被代理者物件的控制，而靜態代理一旦需要改變某種業務邏輯，就需要進入代理者的類別修改，有此可見動態代理的好處。

依舊以代理駕駛的例子為例，首先建立一個類別`DriverIH`，這個類別實作 [JDK](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html "JDK") 提供的介面`InvocationHandler`，`invoke()`方法主要是負責對被代理的物件進行處理，當動態代理呼叫被代理物件的方法時，會自動執行此`invoke()`方法。

接著建立一個動態代理的類別`DynamicProxy`，主要目的為創建代理對象，裡面呼叫了一個`newProxyInstance()`的方法，這個方法主要是讓 [JVM](https://en.wikipedia.org/wiki/Java_virtual_machine "JVM") 動態的創建一個動態代理實例，其中有三個參數，第一個參數是被代理物件的`ClassLoader`，第二個參數就是抽象主題角色所定義的介面，最後一個參數則是類別`DriverIH`的物件，最後擴展動態代理的類別`DynamicProxy`為`DynamicProxyDriver`，便於使用者的使用，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/ProxyPattern/Picture/ProxyPatternDynamicPicture.png)

#### DriverIH
```java
public class DriverIH implements InvocationHandler {
    // 被代理的物件
    private Object object = null;

    public DriverIH(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理的方法準備工作與預處理
        if (method.getName().equalsIgnoreCase("board")) {
            System.out.println("代理司機準備代替駕駛");
        } else if (!method.getName().equalsIgnoreCase("leave")) {
            System.out.print("代理司機代替");
        }

        Object result = method.invoke(this.object, args);

        // 代理的方法後處理
        if (method.getName().equalsIgnoreCase("leave")) {
            System.out.println("代理司機離開");
        }
        return result;
    }
}
```
####  DynamicProxy
```java
public class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) {
        return (T) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}
```
#### DynamicProxyDriver
```java
public class DynamicProxyDriver extends DynamicProxy {
    public static <T> T newProxyInstance(Driver driver) {
        // 取得 ClassLoader
        ClassLoader classLoader = driver.getClass().getClassLoader();
        // 取得介面
        Class<?>[] interfaces = driver.getClass().getInterfaces();
        // 取得 Handler
        InvocationHandler handler = new DriverIH(driver);

        return newProxyInstance(classLoader, interfaces, handler);
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        System.out.println("----------普通代理模式----------");
        doNormalProxy();
        System.out.println("----------代理模式擴展----------");
        doExtendProxy();
        System.out.println("----------強制代理模式----------");
        doForceProxy();
        System.out.println("----------動態代理模式----------");
        doDynamicProxy();
    }

    private static void doNormalProxy() {
        // 有一個司機三毛
        Driver sanMao = new Driver("三毛");

        // 三毛現在準備酒駕
        System.out.println("-----喝醉的三毛開車-----");
        sanMao.board();
        sanMao.horn();
        sanMao.drive();
        sanMao.horn();
        sanMao.park();
        sanMao.horn();
        System.out.println("警察臨檢，三毛好傷心");
        sanMao.leave();

        // 找一個代理司機幫三毛駕駛
        System.out.println("-----代理司機代替三毛開車-----");
        IDriver proxy = new ProxyDriver(sanMao);

        // 代理司機開始行動
        proxy.board();
        proxy.drive();
        proxy.park();
        proxy.leave();
    }

    private static void doExtendProxy() {
        // 有一個司機 Grylls，找了一個代理司機幫他駕駛
        Driver grylls = new Driver("Grylls");
        ProxyDriverExtend proxy = new ProxyDriverExtend(grylls);

        // 代理司機開始行動
        proxy.board();
        proxy.drive();
        proxy.park();
        proxy.leave();
        proxy.fee();
    }

    private static void doForceProxy() {
        // 有一個女司機小花
        WomanDriver xiaoHua = new WomanDriver("小花");

        // 小花開車
        System.out.println("-----喝醉的小花開車-----");
        xiaoHua.drive();

        // 隨便找一個女代理司機
        System.out.println("-----不熟的女代理司機開車-----");
        ProxyWomanDriver proxy = new ProxyWomanDriver(xiaoHua);
        proxy.board();

        // 找小花指定的女代理司機來
        System.out.println("-----指定的女代理司機開車-----");
        ProxyWomanDriver assignProxy = (ProxyWomanDriver) xiaoHua.getProxy();
        assignProxy.board();
        assignProxy.drive();
        assignProxy.park();
        assignProxy.leave();
    }

    private static void doDynamicProxy() {
        // 一個司機叫做 Jhon
        Driver driver = new Driver("Jhon");
        // 動態產生一個代理者
        IDriver proxy = DynamicProxyDriver.newProxyInstance(driver);

        // 代理司機開始行動
        proxy.board();
        proxy.drive();
        proxy.horn();
        proxy.park();
        proxy.leave();
    }
}
```
#### 執行結果
>     ----------普通代理模式----------
>     -----喝醉的三毛開車-----
>     三毛上車
>     三毛按喇叭
>     三毛在開車
>     三毛按喇叭
>     三毛在停車
>     三毛按喇叭
>     警察臨檢，三毛好傷心
>     三毛下車
>     -----代理司機代替三毛開車-----
>     代理司機準備代替駕駛
>     三毛上車
>     代理司機代替三毛在開車
>     代理司機代替三毛在停車
>     三毛下車
>     代理司機離開
>     ----------代理模式擴展----------
>     代理司機準備代替駕駛
>     Grylls上車
>     代理司機代替Grylls在開車
>     代理司機代替Grylls在停車
>     Grylls下車
>     代理司機離開
>     代理司機費用為：1200 元
>     ----------強制代理模式----------
>     -----喝醉的小花開車-----
>     請讓指定的代理來
>     -----不熟的女代理司機開車-----
>     女代理司機準備代替駕駛
>     請讓指定的代理來
>     -----指定的女代理司機開車-----
>     女代理司機準備代替駕駛
>     小花上車
>     女代理司機代替小花在開車
>     女代理司機代替小花在停車
>     小花下車
>     女代理司機離開
>     ----------動態代理模式----------
>     代理司機準備代替駕駛
>     Jhon上車
>     代理司機代替Jhon在開車
>     代理司機代替Jhon按喇叭
>     代理司機代替Jhon在停車
>     Jhon下車
>     代理司機離開

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Proxy_pattern
 - http://www.cnblogs.com/xiaoluo501395377/p/3383130.html
 - https://en.wikipedia.org/wiki/Java_virtual_machine
 - https://en.wikipedia.org/wiki/Aspect-oriented_programming
 - https://docs.oracle.com/javase/8/docs/api/index.html
 - https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html
