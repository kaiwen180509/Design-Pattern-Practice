# 開閉原則 - Open/Closed Principle, OCP
## 定義
> - “Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.”
> - 軟體中的實體（類別，模組，函式等等）應該對於擴展是開放的，但是對於修改是封閉的。
> - 透過擴展來實作變化，而非修改已有的程式來實作變化。

## 優點
> 1. 可維護性高
> 2. 重利用性高
> 3. 減少測試的需求
> 4. 靈活性高
> 5. 穩定性高
> 
> 使用開閉原則（OCP）在日後維護與需求改變時特別有利，因為沒有變動到舊有的類別，因此不需要重新測試原有的類別，使得穩定性提升，只需要透過繼承來擴展新類別，靈活的覆寫方法，即可完成維護或需求變更，不僅提升類別的利用，也減少許多風險。

## 實踐
> 1. 引用物件盡量使用介面或抽象類別
> 2. 保持抽象層的穩定，確定後不再修改
> 3. 透過介面或抽象類別約束
> 
> 設計類別時盡量避免一些在介面或抽象類別中不存在的public方法。

## 範例
以變更需求來舉例，變更[里氏替換原則（LSP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "里氏替換原則（LSP）")筆記裡舉的範例需求，現在由於日益重視公安問題，所以現在的公車都需要安裝警報器，當警報聲響起，公車必須立刻停下，以便乘客逃生，在這個變更的需求下，使用開閉原則（OCP）來處理，不去修改舊有的公車`Bus`類別，而是新增一個新式公車`NewBus`的類別繼承自公車`Bus`類別，並且增加一個警報聲`alarm()`的方法，並且覆寫停車`stop()`的方法，因為現在停車不只是公車到站了，還有警報響起也要停車。

剩下的部分只要修改業務場景的應用，如此便可在不改變舊有的類別下，達成新的需求，實際的類別圖與範例如下：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/SOLID/Picture/OCPClassPicture.png)

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
#### NewBus
```java
public class NewBus extends Bus {
    public void alarm() {
        System.out.println("警報聲響起...");
    }

    @Override
    public void stop() {
        System.out.println("公車停車...");
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

## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        System.out.println("----------開公車----------");
        // 產生一台公車
        Car bus = new Bus();
        // 讓司機開公車
        Driver driver = new Driver(bus);
        driver.drive();

        // 公車的下車鈴響起
        ((Bus) bus).ring();

        // 司機停車
        driver.park();

        System.out.println("----------開卡車----------");
        // 產生一台卡車讓司機2號開
        Car truck = new Truck();
        Driver driver2 = new Driver(truck);
        driver2.drive();
        driver2.park();

        System.out.println("----------開新式公車----------");
        // 產生一台新式公車讓司機3號開
        Car newBus = new NewBus();
        Driver driver3 = new Driver(newBus);
        driver3.drive();

        // 新式公車的下車鈴響起，司機3號停車
        ((NewBus) newBus).ring();
        driver3.park();

        // 司機3號繼續開新式公車
        driver3.drive();

        // 警鈴聲響起，司機3號停車
        ((NewBus) newBus).alarm();
        driver3.park();
    }
}
```
#### 執行結果
>     ----------開公車----------
>     公車上路...
>     下車鈴響起，下一站停車...
>     公車到站，停車...
>     ----------開卡車----------
>     卡車上路...
>     卡車停車...
>     ----------開新式公車----------
>     公車上路...
>     下車鈴響起，下一站停車...
>     公車停車...
>     公車上路...
>     警報聲響起...
>     公車停車...

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Open/closed_principle
