# 里氏替換原則 - Liskov Substitution Principle, LSP
## 定義
> - “If for each object o1 of type S there is an object o2 of type T such that for all programs P defined in terms of T, the behavior of P is unchanged when o1 is substituted for o2 then S is a subtype of T.”
> - 如果對每一個型別為S的物件o1，都有型別為T的物件o2，使得以T定義的所有程式P在所有的物件o1都替換成o2時，程式P的行為沒有發生變化，那麼型別S就是型別T的子型別。
> - “Functions that use pointers or references to base classes must be able to use objects of derived classes without knowing it.”
> - 所有參照基礎類別的地方必須能透明地使用其衍生類別的物件。
> - 子類別必須完全實作父類別的方法與屬性
> - 子類別可以有自己的方法與屬性
> - 多載父類別的方法時輸入參數可以被放大
> - 多載父類別的方法時輸出結果可以被縮小

## 優點
> 1. 增強程式的強健性
> 2. 保持好的兼容性
> 
> 使用父類別當作參數，傳遞子類別來完成各種業務邏輯，當產生新的子類別時便不需要再修改到其他類別，對於維護會較為容易。

## 缺點
> 子類別當作業務邏輯會使耦合關係複雜，導致缺乏類別替換的標準。

## 實踐
> 1. 盡量避免子類別有自己的方法與屬性
> 2. 子類別盡量複寫父類別的方法而不是多載
> 
> 在多載父類別方法時，如果輸入參數範圍變小，會導致執行的方法是子類別的方法，歪曲了父類別的意圖，這就可能會導致業務邏輯混亂，而輸入參數範圍變大則導致子類別的方法不被呼叫，做無用功，因此能避免多載則盡量避免。

## 範例
以司機開車為例子，建立一個抽象的車子`Car`類別，這個抽象類別有開車`start()`與停車`stop()`的抽象方法，具體細節的實作交給繼承此類別的子類別公車`Bus`與卡車`Truck`來實現，公車這個子類別有自己獨有的響鈴方法`ring()`，司機類別`Driver`的車子則以抽象類別`Car`當作參數傳入，不需要在意是哪個子類別，因為抽象類別的約束，使得每個繼承`Car`的子類別都有`start()`與`stop()`方法，司機只要開車與停車就好，類別圖與實際範例如下：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/SOLID/Picture/LSPClassPicture.png)

#### Car
```java
public abstract class Car {
    public abstract void start();
    public abstract void stop();
}
```
#### Bus
```java
public class Bus extends Car {
    @Override
    public void start() {
        System.out.println("公車上路...");
    }

    @Override
    public void stop() {
        System.out.println("公車到站，停車...");
    }

    public void ring() {
        System.out.println("下車鈴響起，下一站停車...");
    }
}
```
#### Truck
```java
public class Truck extends Car {
    @Override
    public void start() {
        System.out.println("卡車上路...");
    }

    @Override
    public void stop() {
        System.out.println("卡車停車...");
    }
}
```
#### Driver
```java
public class Driver {
    private Car car;

    public Driver(Car car) {
        this.car = car;
    }

    public void drive() {
        car.start();
    }

    public void park() {
        car.stop();
    }
}
```

#### 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 產生一台公車
        Car bus = new Bus();
        // 讓司機開公車
        Driver driver = new Driver(bus);
        driver.drive();

        // 公車的下車鈴響起
        ((Bus) bus).ring();

        // 司機停車
        driver.park();

        // 產生一台卡車讓司機2號開
        Car truck = new Truck();
        Driver driver2 = new Driver(truck);
        driver2.drive();
        driver2.park();
    }
}
```
#### 執行結果
>     公車上路...
>     下車鈴響起，下一站停車...
>     公車到站，停車...
>     卡車上路...
>     卡車停車...

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Liskov_substitution_principle
