# 依賴反轉原則 - Dependency Inversion Principle, DIP
## 定義
> - “High-level modules should not depend on low-level modules. Both should depend on abstractions.Abstractions should not depend on details. Details should depend on abstractions.”
> - “program to an interface,  not an implementation”
> - 高層次的模組不應該依賴於低層次的模組，兩者都應該依賴於抽象介面。
> - 抽象的類別或介面（不能被實例化）不應該依賴於實作的介面或類別。
> - 細節則應該依賴於抽象。
> - 物件導向設計(Object-Oriented Design, OOD)的精隨。
> - 模組間的依賴透過抽象發生，實作類別之間不會直接有依賴關係。
> - 介面或抽象類別不依賴實作類別，而實作類別依賴介面或抽象類別。

## 優點
> 1. 減少類別間的耦合性
> 2. 提升系統的穩定性
> 3. 降低併行開發的風險
> 4. 可讀性提高
> 5. 可維護性提高
> 
> 使用抽象類別或介面來約束依賴，減少實作類別間的耦合，除了提升程式的可讀性與穩定之外，由於實作類別間的低耦合，因此也減少了併行開發中的衝突，讓風險降低，除此之外，對於日後維護的工作也很有幫助。

## 實踐
> 1. 每個類別都有介面或抽象類別，或兩者兼備
> 2. 變數的表面型別盡量是抽象類別或介面
> 3. 任何類別都不應該從具體類別派生
> 4. 盡量不要複寫抽象類別的方法
> 5. 結合[里氏替換原則](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "里氏替換原則")來運用
> 
> 對於每個類別都使用介面或抽象類別來約束，結合[里氏替換原則](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "里氏替換原則")使用後，可以很好的提升程式的兼容性，並且子類別要盡量避免從具體的實作類別來繼承，以及複寫抽象類別的方法，以免造成依賴的穩定性出現問題。

## 範例
以司機開車為例子，建立一個車子介面`ICar`與司機介面`IDriver`，`ICar`有開車`start()`與停車`stop()`的方法，具體的細節交給實作介面的類別公車`Bus`與卡車`Truck`來實現，司機介面`IDriver`的則有行駛`drive`與停下`park`方法，具體的細節也交給類別`Driver`實作，接著結合[里氏替換原則](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "里氏替換原則")，傳遞依賴`ICar`給司機類別`Driver`，司機類別`Driver`只要依賴`ICar`的`start()`與`stop()`的方法來開車與停車就好，依賴總共有三種不同的寫法，分為建構子傳遞依賴、介面傳遞依賴以及Setter方法傳遞依賴。

### 建構子傳遞依賴的類別圖與實際範例如下：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/SOLID/Picture/DIPClassPicture.png)

#### ICar
```java
public interface ICar {
    void start();
    void stop();
}
```
#### Bus
```java
public class Bus implements ICar {
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
public class Truck implements ICar {
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
#### IDriver
```java
public interface IDriver {
    void drive();
    void park();
}
```
#### Driver
```java
public class Driver implements IDriver {
    private ICar car;

    public Driver(ICar car) {
        this.car = car;
    }

    @Override
    public void drive() {
        car.start();
    }

    @Override
    public void park() {
        car.stop();
    }
}
```

### 介面傳遞依賴的類別圖與實際範例如下：
其中的`ICar`介面、`Bus`與`Truck`類別都與建構子依賴相同，只需要修改`IDriver`介面與`Driver`類別。

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/SOLID/Picture/DIPByInterfaceClassPicture.png)
#### IDriverByInterface
```java
public interface IDriverByInterface {
    void drive(ICar car);
    void park(ICar car);
}
```
#### DriverByInterface
```java
public class DriverByInterface implements IDriverByInterface {
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
### Setter方法傳遞依賴的類別圖與實際範例如下：
其中的`ICar`介面、`Bus`與`Truck`類別都與建構子依賴相同，只需要修改`IDriver`介面與`Driver`類別。

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/SOLID/Picture/DIPBySetterClassPicture.png)

#### IDriverBySetter
```java
public interface IDriverBySetter {
    void drive();
    void park();
    void setCar(ICar car);
}
```
#### DriverBySetter
```java
public class DriverBySetter implements IDriverBySetter {
    private ICar car;

    @Override
    public void drive() {
        car.start();
    }

    @Override
    public void park() {
        car.stop();
    }

    @Override
    public void setCar(ICar car) {
        this.car = car;
    }
}
```

## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        System.out.println("----------建構子傳遞依賴----------");
        driving();
        System.out.println("----------介面傳遞依賴----------");
        drivingByInterface();
        System.out.println("----------Setter方法傳遞依賴----------");
        drivingBySetter();
    }

    private static void driving() {
        // 讓司機開卡車
        ICar car = new Truck();
        IDriver driver = new Driver(car);
        driver.drive();
        driver.park();
    }

    private static void drivingByInterface() {
        // 讓司機開公車
        IDriverByInterface driver = new DriverByInterface();
        ICar car = new Bus();
        driver.drive(car);
        driver.park(car);
    }

    private static void drivingBySetter() {
        // 讓司機開卡車
        IDriverBySetter driver = new DriverBySetter();
        ICar car = new Truck();
        driver.setCar(car);
        driver.drive();
        driver.park();
    }
}
```
#### 執行結果
>     ----------建構子傳遞依賴----------
>     卡車上路...
>     卡車停車...
>     ----------介面傳遞依賴----------
>     公車上路...
>     公車到站，停車...
>     ----------Setter方法傳遞依賴----------
>     卡車上路...
>     卡車停車...

## 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Liskov_substitution_principle
 - https://en.wikipedia.org/wiki/Dependency_inversion_principle
 - http://teddy-chen-tw.blogspot.tw/2012/01/5dependency-inversion-principle.html
