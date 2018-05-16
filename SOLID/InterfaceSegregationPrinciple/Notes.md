# 介面隔離原則 - Interface Segregation Principle, ISP
## 定義
> - “Client should not be forced to depend upon interfaces that they don't use.”
> - “The dependency of one class to another one should depend on the smallest possible interface.”
> - 類別不應該依賴它不需要的介面。
> - 類別間的依賴關係應該建立在最小的介面上。
> - 介面盡量細化，且方法盡量少。
> - 單獨定製服務，減少變更引起的風險。
> - 根據經驗與常識有限度的設計介面。

## 優點
> 1. 系統靈活性高
> 
> 介面的設計細化之後，類別可以靈活的實作，不需要實作不必要的介面，造成浪費與臃腫。

## 缺點
> 1. 過度拆分介面會導致複雜化
> 2. 開發難度增加
> 3. 維護性降低
> 
> 當介面細化之後，可能會使介面數量遽增，導致開發難度提高，也會提升維護的成本。

## 實踐
> 1. 介面與類別盡量使用原子（不能再分割）的介面與類別
> 2. 回顧介面的方法，避免過多不必要的public方法
> 3. 盡量隔離介面，風險較大則採用介面卡模式（Adapter Pattern）進行處理
> 4. 根據專案與開發環境來調整設計
> 5. 不能違反單一職責原則（SRP）
> 
> 在設計介面與類別時，盡量細化到不能再分割的情況，以及避免不必要的public方法，如果細分會導致風險，則採用介面卡模式處理，如果與單一職責原則產生衝突，則遵守單一職責原則。

## 範例
以乘客搭公車的例子來說明，建立幾個介面分別是車子`ICar`、下車鈴`IBusRing`、司機`IDriver`以及乘客`IPassenger`，在`ICar`中定義開車`start()`與停車`stop()`的方法，在`IBusRing`中定義響鈴`ring()`的方法，在`IDriver`中定義行駛`drive()`與停下`park()`的方法，在`IPassenger`中定義開車`pressingRing()`、`board()`以及`leave()`的方法，彼此之間藉由[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")來建立依賴，剩下的具體細節則交給實作類別去實現，類別圖與實際範例如下：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/SOLID/Picture/ISPClassPicture.png)

#### ICar
```java
public interface ICar {
    void start();
    void stop();
}
```
#### IBusRing
```java
public interface IBusRing {
    void ring();
}
```
#### IDriver
```java
public interface IDriver {
    void drive(ICar car);
    void park(ICar car);
}
```
#### IPassenger
```java
public interface IPassenger {
    void pressingRing(IBusRing busRing);
    void board(ICar car);
    void leave(ICar car);
}
```
#### Bus
```java
public class Bus implements ICar, IBusRing {
    @Override
    public void start() {
        System.out.println("公車上路...");
    }

    @Override
    public void stop() {
        System.out.println("公車到站，停車...");
    }

    @Override
    public void ring() {
        System.out.println("下車鈴響起，下一站停車...");
    }
}
```
#### ShuttleBus
```java
public class ShuttleBus implements ICar {
    @Override
    public void start() {
        System.out.println("接駁車上路...");
    }

    @Override
    public void stop() {
        System.out.println("接駁車到站，停車...");
    }
}
```
#### Driver
```java
public class Driver implements IDriver {
    @Override
    public void drive(ICar car) {
        car.start();
    }

    @Override
    public void park(ICar car) {
        car.stop();
    }
}
```
#### Passenger
```java
public class Passenger implements IPassenger {
    @Override
    public void pressingRing(IBusRing busRing) {
        busRing.ring();
    }

    @Override
    public void board(ICar car) {
        System.out.println("乘客上車...");
    }

    @Override
    public void leave(ICar car) {
        System.out.println("乘客下車...");
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 一個司機與乘客
        IDriver driver = new Driver();
        IPassenger passenger = new Passenger();

        System.out.println("----------場景一----------");
        // 乘客上車，司機開公車
        ICar bus = new Bus();
        passenger.board(bus);
        driver.drive(bus);

        // 乘客在下一站想要下車，按了下車鈴
        passenger.pressingRing((IBusRing) bus);

        // 司機停車，讓乘客下車
        driver.park(bus);
        passenger.leave(bus);

        System.out.println("----------場景二----------");
        // 司機開著定點接駁車，從起點駛向終點
        ICar shuttleBus = new ShuttleBus();
        // 乘客上車
        passenger.board(shuttleBus);
        // 司機開車
        driver.drive(shuttleBus);
        // 終點到了，司機停車
        driver.park(shuttleBus);
        // 乘客下車
        passenger.leave(shuttleBus);
    }
}
```
#### 執行結果
>     ----------場景一----------
>     乘客上車...
>     公車上路...
>     下車鈴響起，下一站停車...
>     公車到站，停車...
>     乘客下車...
>     ----------場景二----------
>     乘客上車...
>     接駁車上路...
>     接駁車到站，停車...
>     乘客下車...

## 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Interface_segregation_principle
 - https://danielkjchen.wordpress.com/2016/02/05/%E7%89%A9%E4%BB%B6%E5%B0%8E%E5%90%91%E4%BB%8B%E9%9D%A2%E9%9A%94%E9%9B%A2%E5%8E%9F%E5%89%87-interface-segregation-principle/