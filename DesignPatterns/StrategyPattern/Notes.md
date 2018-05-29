# 策略模式 - Strategy Pattern
## 定義
> - “Define a family of algorithms, encapsulate each one, and make them interchangeable. ”
> - 定義一組演算法，把每個演算法都封裝起來，並且使它們之間可以互相交換。

## 參與者
> + 封裝角色 - Context
>     * 又稱為上下文角色，屏蔽高層模組對策略角色的存取
> + 抽象策略角色 - Strategy
>     * 通常為介面，定義每個策略必需具備的方法與屬性
> + 具體策略角色 - ConcreteStrategy
>     * 負則實作 Strategy介面，含有具體的執行策略

## 優點
> 1. 自由切換演算法
> 2. 避免多重判斷
> 3. 擴展性佳
>
> 在**策略模式**之中，只要實作了抽象策略角色 Strategy，就是一個策略家族成員，彼此可以互換，簡化了要執行哪個演算法的判斷，並且可以透過實作抽象策略角色 Strategy 來擴展，完全符合[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")。

## 缺點
> 1. 類別數量多
> 2. 違反[迪米特法則（LoD）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LeastKnowledgePrinciple/Notes.md "迪米特法則（LoD）")
>
> 每一個策略就是一個類別，重利用率低，並且高層模組需要使用策略時，需要知道是哪個策略，違反[迪米特法則（LoD）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LeastKnowledgePrinciple/Notes.md "迪米特法則（LoD）")，解決辦法是與其他**設計模式**結合，例如：[工廠模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/FactoryPattern/Notes.md "工廠模式")、[代理模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/ProxyPattern/Notes.md "代理模式")與[享元模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/FlyweightPattern/Notes.md "享元模式")。

## 使用時機
> 1. 多個類別只有演算法或行為有些不同時
> 2. 需要自由切換演算法時
> 3. 需要屏蔽演算法規則的情況時

## 實踐
> 如果策略過多導致類別過多時，需要考慮與其他**設計模式**結合，避免發生類別膨脹、策略對外開放等問題，以免日後維護困難。

## 範例
[貝爾．吉羅斯](https://en.wikipedia.org/wiki/Bear_Grylls "貝爾．吉羅斯")是一個知名的野外求生節目主持人，曾經主持一個[《荒野求生秘技》](https://en.wikipedia.org/wiki/Man_vs._Wild "《荒野求生秘技》")的節目，在節目之中跟觀眾介紹如何在惡劣的自然環境中，利用各種求生策略來生存與找到回歸文明的路。

以貝爾使用各種求生策略來說明**策略模式**，先定義一個抽象策略角色`Strategy`的介面，定義策略必須要有的執行`execute()`方法，接著實作具體策略角色，分別是`ShelterStrategy`、`HuntingStrategy`與`DodgeStrategy`類別，最後定義一個使用策略的封裝角色`Bear`來使用各個策略，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/StrategyPattern/Picture/StrategyPatternPicture.png)

#### Strategy
```java
public interface Strategy {
    void execute();
}
```
#### ShelterStrategy
```java
public class ShelterStrategy implements Strategy {
    @Override
    public void execute() {
        System.out.println("建造一個庇護所...");
    }
}
```
#### HuntingStrategy
```java
public class HuntingStrategy implements Strategy {
    @Override
    public void execute() {
        System.out.println("開始狩獵行動...");
    }
}
```
#### DodgeStrategy
```java
public class DodgeStrategy implements Strategy{
    @Override
    public void execute() {
        System.out.println("進行躲避行動...");
    }
}
```
#### Bear
```java
public class Bear {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void action() {
        this.strategy.execute();
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 貝爾出現
        Bear bear = new Bear();

        // 在荒野中建立一個庇護所以此維生
        System.out.println("-----在連綿不絕的雨林之中-----");
        bear.setStrategy(new ShelterStrategy());
        bear.action();

        // 在荒野中碰上毒蛇需要躲避
        System.out.println("-----前面有一條劇毒的毒蛇-----");
        bear.setStrategy(new DodgeStrategy());
        bear.action();

        // 在荒野中肚子飢餓，需要狩獵食物來進食
        System.out.println("-----連續兩天沒有食物可吃-----");
        bear.setStrategy(new HuntingStrategy());
        bear.action();
    }
}
```
#### 執行結果
>     -----在連綿不絕的雨林之中-----
>     建造一個庇護所...
>     -----前面有一條劇毒的毒蛇-----
>     進行躲避行動...
>     -----連續兩天沒有食物可吃-----
>     開始狩獵行動...

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Strategy_pattern
 - https://en.wikipedia.org/wiki/Bear_Grylls
 - https://en.wikipedia.org/wiki/Man_vs._Wild