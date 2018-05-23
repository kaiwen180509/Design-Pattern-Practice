# 抽象工廠模式 - Abstract Factory Pattern
## 定義
> - “Provide an interface for creating families of related or dependent objects without specifying their concrete classes.”
> - 提供介面，建立一系列相關或獨立的物件，而不指定這些物件的具體類別。

## 優點
> 1. 良好的封裝性
> 2. 具體實作被分離
> 
> 具體的實作在工廠類別被建立，使用者不需要關心如何產生，透過介面的參考來呼叫就好。

## 缺點
> 1. 產品族的擴展困難
> 
> 當發生變更時，需要修改上層介面，一旦修改約束的介面，導致下層實作的類別都要做出修改，嚴重違反[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")，並且增加了大量額外工作。

## 使用時機
> 1. 一組無關物件擁有相同的約束條件時

## 實踐
> 抽象工廠模式是[工廠模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/FactoryPattern/Notes.md "工廠模式")的升級版本，透過抽象的類別或介面來建立各個工廠，有N個產品族就建立N個工廠，以此實作不同的產品族生產。

## 範例
以一個甜點工廠來當作例子，這個甜點工廠總共有兩個產品族，分別是果凍以及糖果，首先為兩個產品族建立介面，分別是糖果`Candy`以及果凍`Jelly`，定義口味`flavor()`以及價格`price()`，接著實作不同的糖果產品`FruitCandy`、`MatchaCandy`與果凍產品`FruitJelly`、`MatchaJelly`的類別，當完成產品的佈署之後，需要開始建造抽象工廠，在甜點工廠`DessertFactory`的介面，定義製作糖果`makeCandy()`與製作果凍makeJelly()的方法，具體實作交給實作介面的類別`FruitDessertFactory`以及`MatchaDessertFactory`，使用者現在只需要知道甜點工廠的介面所提供的方法`DessertFactory`以及工廠類別`FruitDessertFactory`以及`MatchaDessertFactory`，就可以讓工廠開始製作甜點，不需要瞭解具體的實現方法就可以完成。

以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/AbstractFactoryPattern/Picture/AbstractFactoryPicture.png)

#### Candy
```java
public interface Candy {
    void flavor();
    void price();
}
```
#### FruitCandy
```java
public class FruitCandy implements Candy {
    @Override
    public void flavor() {
        System.out.println("水果口味");
    }

    @Override
    public void price() {
        System.out.println("價格：5 元");
    }
}
```
#### MatchaCandy
```java
public class MatchaCandy implements Candy {
    @Override
    public void flavor() {
        System.out.println("抹茶口味");
    }

    @Override
    public void price() {
        System.out.println("價格：6 元");
    }
}
```
#### Jelly
```java
public interface Jelly {
    void flavor();
    void price();
}
```
#### FruitJelly
```java
public class FruitJelly implements Jelly {
    @Override
    public void flavor() {
        System.out.println("水果口味");
    }

    @Override
    public void price() {
        System.out.println("價格：4 元");
    }
}
```
#### MatchaJelly
```java
public class MatchaJelly implements Jelly {
    @Override
    public void flavor() {
        System.out.println("抹茶口味");
    }

    @Override
    public void price() {
        System.out.println("價格：5 元");
    }
}
```
#### DessertFactory
```java
public interface DessertFactory {
    Candy makeCandy();
    Jelly makeJelly();
}
```
#### FruitDessertFactory
```java
public class FruitDessertFactory implements DessertFactory {
    @Override
    public Candy makeCandy() {
        return new FruitCandy();
    }

    @Override
    public Jelly makeJelly() {
        return new FruitJelly();
    }
}
```
#### MatchaDessertFactory
```java
public class MatchaDessertFactory implements DessertFactory {
    @Override
    public Candy makeCandy() {
        return new MatchaCandy();
    }

    @Override
    public Jelly makeJelly() {
        return new MatchaJelly();
    }
}

```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 建立水果甜點工廠與抹茶甜點工廠
        DessertFactory fruitDessertFactory = new FruitDessertFactory();
        DessertFactory matchaDessertFactory = new MatchaDessertFactory();

        // 生產各式果凍
        System.out.println("----------果凍產品----------");
        Jelly fruitJelly = fruitDessertFactory.makeJelly();
        fruitJelly.flavor();
        fruitJelly.price();
        Jelly matchaJelly = matchaDessertFactory.makeJelly();
        matchaJelly.flavor();
        matchaJelly.price();

        // 生產各式糖果
        System.out.println("----------糖果產品----------");
        Candy fruitCandy = fruitDessertFactory.makeCandy();
        fruitCandy.flavor();
        fruitCandy.price();
        Candy matchaCandy = matchaDessertFactory.makeCandy();
        matchaCandy.flavor();
        matchaCandy.price();
    }
}
```
#### 執行結果
>     ----------果凍產品----------
>     水果口味
>     價格：4 元
>     抹茶口味
>     價格：5 元
>     ----------糖果產品----------
>     水果口味
>     價格：5 元
>     抹茶口味
>     價格：6 元

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Abstract_factory_pattern
