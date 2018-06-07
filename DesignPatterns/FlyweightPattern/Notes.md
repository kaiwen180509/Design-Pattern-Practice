# 享元模式 - Flyweight Pattern
## 定義
> - “Use sharing to support large numbers of fine-grained objects efficiently.”
> - 使用共享物件有效地支持大量的細粒度物件。
> - 將物件分為內部狀態與外部狀態。
>     - 內部狀態：可以共享的資訊，存在享元物件內部，且不隨環境改變。
>     - 外部狀態：可以被依賴的一個標記，存在享元物件內部，會隨環境改變，不可被共享，且是唯一的一個索引值。

## 參與者
> + 抽象享元角色 - Flyweight
>     * 定義物件的內部與外部狀態
> + 具體享元角色 - ConcreteFlyweight
>     * 實作 Flyweight 的類別，修改內部的狀態不能影響到外部狀態
> + 不可共享的享元角色 - UnsharedConcreteFlyweight
>     * 不存在外部狀態也不使用共享技術的物件，不會在享元工廠 FlyweightFactory 中出現
> + 享元工廠 - FlyweightFactory
>     * 建造一個池的容器，並提供取得池中物件的方法

## 優點
> 1. 減少資源耗費
> 2. 增強效能
>
> 由於共享了部分的物件資訊，減少記憶體的損耗，也提升了程式效能。

## 缺點
> 1. 提升程式複雜度
>
> 在**享元模式**之中，由於需要分出內部與外部狀態，導致程式系統的邏輯複雜，增加程式的複雜度。

## 使用時機
> 1. 需要緩衝池的情況
> 2. 系統中有大量的相似物件時
> 3. 物件本身具備內部狀態且與環境無關，又有相似的外部狀態時

## 實踐
> **享元模式**的目的在於建立多個可以共享的物件。

## 範例
在台灣的路上，常常能夠見到手搖飲料店，可以根據顧客的需求來訂製飲料，例如：飲料的大、小杯，飲料的種類...等。根據這個情況，如果以程式來實現，通常是一杯飲料使用一個物件實例來使用，假設今天有1000杯飲料，就需要1000個實例來使用，大大的提升了對記憶體與效能的耗費，但是如果使用**享元模式**來體現，可以藉由飲料的物件池，保存物件實例，大大減少效能的消耗，以下就使用這個情況來舉例。

首先建立一個抽象享元角色`Cup`類別，這個類別分別定義了內部與外部的狀態，交由具體的享元角色`BigCup`與`SmallCup`去實現，內部的狀態不會因為外部的環境所改變，而外部的狀態則交由使用者的環境來決定，最後建立一個享元工廠`DrinkFactory`類別，來產生物件池與提供享元角色的取得，由物件池保存享元物件，大大減少重複物件的耗費，提升效率。以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/FlyweightPattern/Picture/FlyweightPatternPicture.png)

#### Cup
```java
/* Flyweight */
public abstract class Cup {
    // 內部狀態 -> 容量
    private int capacity;

    // 外部狀態 -> 飲料名稱
    protected final String name;

    public Cup(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    // 杯子的行為
    public abstract void drink();
}
```
#### BigCup
```java
/* ConcreteFlyweight */
public class BigCup extends Cup {
    public BigCup(String name) {
        super(name);
        // 大杯飲料是 700 CC
        this.setCapacity(700);
    }

    @Override
    public void drink() {
        System.out.println("正在喝大杯飲料：" + this.name);
    }
}

```
#### SmallCup
```java
/* ConcreteFlyweight */
public class SmallCup extends Cup {
    public SmallCup(String name) {
        super(name);
        // 小杯飲料是 500 CC
        this.setCapacity(500);
    }

    @Override
    public void drink() {
        System.out.println("正在喝小杯飲料：" + this.name);
    }
}
```
#### DrinkFactory
```java
public class DrinkFactory {
    public final static char BIG_SIZE = 'L';
    public final static char SMALL_SIZE = 'S';

    // 建立池
    private static HashMap<String, Cup> bigPool = new HashMap<>();
    private static HashMap<String, Cup> smallPool = new HashMap<>();

    public Cup getDrink(String name, char size) {
        Cup drink;
        // 判斷是哪一種杯子
        if (size == BIG_SIZE) {
            if (this.bigPool.containsKey(name)) {
                // 池裡有就從池中取出
                drink = this.bigPool.get(name);
            } else {
                // 池沒有就建立一個並放入
                drink = new BigCup(name);
                System.out.println("大杯飲料：" + name + " 製作完成");
                this.bigPool.put(name, drink);
            }
        } else {
            if (this.smallPool.containsKey(name)) {
                // 池裡有就從池中取出
                drink = this.smallPool.get(name);
            } else {
                // 池沒有就建立一個並放入
                drink = new SmallCup(name);
                System.out.println("小杯飲料：" + name + " 製作完成");
                this.smallPool.put(name, drink);
            }
        }
        return drink;
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 建立飲料工廠
        DrinkFactory drinkFactory = new DrinkFactory();

        // 客人要喝大杯飲料1號
        System.out.println("點單：大杯1號");
        drinkFactory.getDrink("飲料1號", DrinkFactory.BIG_SIZE).drink();

        // 客人要喝小杯飲料15號
        System.out.println("點單：大杯15號");
        drinkFactory.getDrink("飲料15號", DrinkFactory.SMALL_SIZE).drink();

        // 客人要喝大杯飲料1號
        System.out.println("點單：大杯1號");
        drinkFactory.getDrink("飲料1號", DrinkFactory.BIG_SIZE).drink();
    }
}
```
#### 執行結果
>     點單：大杯1號
>     大杯飲料：飲料1號 製作完成
>     正在喝大杯飲料：飲料1號
>     點單：大杯15號
>     小杯飲料：飲料15號 製作完成
>     正在喝小杯飲料：飲料15號
>     點單：大杯1號
>     正在喝大杯飲料：飲料1號

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Flyweight_pattern