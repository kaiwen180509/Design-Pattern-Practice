# 橋接模式 - Bridge Pattern
## 定義
> - “Decouple an abstraction from its implementation so that the two can vary independently.”
> - 將抽象與介面解耦合，使得兩者之間可以獨立地變化。

## 參與者
> + 抽象化角色 - Abstraction
>     * 一般為抽象類別，主要為定義角色的行為，並保存對 Implementor 的引用
> + 實作化角色 - Implementor
>     * 介面或抽象類別，定義角色必須具備的行為與屬性
> + 修正抽象化角色 - RefinedAbstraction
>     * 引用 Implementor 角色並且對 Abstraction 進行修正
> + 具體實作化角色 - ConcreteImplementor
>     * 負責實作 Implementor 所定義的方法與屬性

## 優點
> 1. 可擴展性佳
> 2. 抽象與實作分離
> 3. 細節對使用者透明
>
> 在**橋接模式**中，由於實作不再受到固定的抽象類別約束，讓抽象與實作分離，解決了繼承的缺點，並且擴展實作或抽象都很容易，而使用者也不再需要關注細節的實作，一切經由抽象層面透過聚合的封裝完成。

## 使用時機
> 1. 不適合或不希望使用繼承的情況
> 2. 介面或抽象類別不穩定時
> 3. 重利用性要求高的情況（避免繼承帶來的限制）

## 實踐
> 類別的繼承帶來許多好處，但是有好也會有壞，繼承的關係讓子類別受到父類別的強入侵，子類別必須強制接受父類別的所有方法與屬性，導致擴展性方面或需求變更的應變困難，尤其是多層的繼承更甚。藉由實作與抽象分離的**橋接模式**可以有效地改善繼承所帶來的缺點。

## 範例
一般的飲料工廠生產飲料之後，需要進行封裝的動作，把飲料裝入容器裡面，通常會有很多種不同的容器，例如：瓶子、杯子或鋁箔包等各種容器。如果今天把這個狀況使用程式來表現，會發現如果現在工廠要把容器從鋁箔包換成瓶子，這樣需要修改很多部分，但是藉由**橋接模式**來設計後，可以避免這種不穩定的情況。

首先需要把實作與抽象類別分離，決定實作化角色的部分是容器，而抽象化角色的部分是工廠，以下的例子就從這個方向開始設計。

建立實作化角色`Container`介面，定義容器的行為，並且交由`Cup`與`Bottle`類別來具體實現，接著建立抽象化角色`Factory`，負責引用實作化角色，並且定義工廠的行為，最後交由`DrinkFactory`的類別來實作，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/BridgePattern/Picture/BridgePatternPicture.png)

#### Container
```java
/* Implementor */
public interface Container {
    // 容器能倒水
    void pour();
    // 容器能裝水
    void fill();
}
```
#### Cup
```java
/* ConcreteImplementor */
public class Cup implements Container {
    @Override
    public void pour() {
        System.out.println("杯子被倒出");
    }

    @Override
    public void fill() {
        System.out.println("杯子被裝入");
    }
}
```
#### Bottle
```java
/* ConcreteImplementor */
public class Bottle implements Container {
    @Override
    public void pour() {
        System.out.println("瓶子被倒出");
    }

    @Override
    public void fill() {
        System.out.println("瓶子被裝入");
    }
}
```
#### Factory
```java
/* Abstraction */
public abstract class Factory {
    protected Container container;

    public Factory(Container container) {
        this.container = container;
    }

    // 工廠會生產
    public abstract void produce();

    // 工廠會回收
    public abstract void recycle();
}
```
#### DrinkFactory
```java
/* RefinedAbstraction */
public class DrinkFactory extends Factory {
    public DrinkFactory(Container container) {
        super(container);
    }

    @Override
    public void produce() {
        this.container.fill();
    }

    @Override
    public void recycle() {
        this.container.pour();
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 讓飲料工廠生產瓶裝飲料
        DrinkFactory factory = new DrinkFactory(new Bottle());
        factory.produce();

        // 飲料工廠回收杯裝飲料
        factory = new DrinkFactory(new Cup());
        factory.recycle();
    }
}
```
#### 執行結果
>     瓶子被裝入
>     杯子被倒出

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Bridge_pattern