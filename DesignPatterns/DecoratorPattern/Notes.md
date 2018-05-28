# 修飾模式 - Decorator Pattern
## 定義
> - “Attach additional responsibilities to an object dynamically keeping the same interface.”
> - “Decorators provide a flexible alternative to subclassing for extending functionality.”
> - 在保持相同的介面下動態地給一個物件額外的職責。
> - 修飾模式提供了一個彈性的繼承替代方案。

## 參與者
> + 抽象構件 - Component
>     * 一個抽象類別或介面，定義最核心的原始物件
> + 具體構件 - ConcreteComponent
>     * 實作抽象構件 Component 的的類別，負責被 ConcreteDecorator 修飾
> + 裝飾角色 - Decorator
>     * 一般為抽象類別，必定有一個變數指向抽象構件 Component
> + 具體裝飾角色 - ConcreteDecorator
>     * 負責對 ConcreteComponent 的類別進行修飾，繼承自裝飾角色 Decorator

## 優點
> 1. 可擴展性佳
> 2. 替代繼承關係
> 3. 動態擴展實作物件
>
> 在**修飾模式**中，Component 與 Decorator 類別可以獨立發展，不會產生耦合，對於需求變更與擴展來說非常方便；另外不論 Decorator 修飾了 Component 多少次，依舊是回傳 Component 物件，可以當作替代繼承關係的一個方案。

## 缺點
> 1.多層裝飾導致複雜
>
> 在多層修飾的情況下，由於有多個裝飾類別，因此導致類別數量增多，維護與除錯都相當複雜，避免的辦法就是減少修飾的數量。

## 使用時機
> 1. 需要給類別新增或附加功能時
> 2. 需要動態的給物件增加功能時，並且可以取消功能
> 3. 需修改或新增功能到兄弟類別時

## 實踐
> **修飾模式**與[代理模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/ProxyPattern/Notes.md "代理模式")其實非常相似，裝飾類別是一個特殊的代理類別，真正的執行者依舊是被代理的具體構件 ConcreteComponent。

## 範例
[自動鉛筆](https://zh.wikipedia.org/wiki/%E8%87%AA%E5%8B%95%E9%89%9B%E7%AD%86 "自動鉛筆")是一個很好用的發明，可以自由地更換筆芯，現在某些品牌的自動鉛筆，筆的後面還會有橡皮擦，非常方便。

今天以幫自動鉛筆換橡皮擦、筆芯為例子來說明**修飾模式**，首先是建立一個抽象的筆`Pen`類別，主要定義筆的功能，接著實作筆的具體類別自動鉛筆`MechanicalPencil`，主要實現寫`write()`的方法，接著是裝飾的抽象角色`Decorator`類別，主要藉由建構子傳遞筆，並且為筆提供修飾，類似[代理模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/ProxyPattern/Notes.md "代理模式")的代理類別，具體的實作交給`ReplaceEraserDecorator`與`RefillLeadDecorator`類別去做，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/DecoratorPattern/Picture/DecoratorPatternPicture.png)

#### Pen
```java
public abstract class Pen {
    public abstract void write();
}
```
#### MechanicalPencil
```java
public class MechanicalPencil extends Pen {
    @Override
    public void write() {
        System.out.println("自動鉛筆寫字...");
    }
}
```
#### Decorator
```java
public abstract class Decorator extends Pen {
    private Pen pen;

    // 建構子負責傳遞筆
    public Decorator(Pen pen) {
        this.pen = pen;
    }

    @Override
    public void write() {
        this.pen.write();
    }
}
```
#### ReplaceEraserDecorator
```java
public class ReplaceEraserDecorator extends Decorator {
    public ReplaceEraserDecorator(Pen pen) {
        super(pen);
    }

    // 換上上橡皮擦
    private void replaceEraser() {
        System.out.println("幫筆尾換上新橡皮擦...");
    }

    // 先換上橡皮擦才開始寫字
    @Override
    public void write() {
        replaceEraser();
        super.write();
    }
}
```
#### RefillLeadDecorator
```java
public class RefillLeadDecorator extends Decorator {
    public RefillLeadDecorator(Pen pen) {
        super(pen);
    }

    // 裝上筆芯
    private void refillLead() {
        System.out.println("裝上新的筆芯...");
    }

    // 先裝上筆芯才能寫字
    @Override
    public void write() {
        refillLead();
        super.write();
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 有一隻自動鉛筆
        Pen pen = new MechanicalPencil();
        System.out.println("-----修飾前-----");
        // 用自動鉛筆寫字
        pen.write();

        System.out.println("-----修飾後-----");
        // 裝上新的筆芯與橡皮擦再繼續寫
        pen = new ReplaceEraserDecorator(pen);
        pen = new RefillLeadDecorator(pen);
        pen.write();
    }
}
```
#### 執行結果
>     -----修飾前-----
>     自動鉛筆寫字...寫的字是黑的
>     -----修飾後-----
>     裝上新的筆芯...
>     幫筆尾換上新橡皮擦...
>     自動鉛筆寫字...寫的字是黑的

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Decorator_pattern
 - https://zh.wikipedia.org/wiki/%E8%87%AA%E5%8B%95%E9%89%9B%E7%AD%86
