# 中介者模式 - Mediator Pattern
## 定義
> - “Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.”
> - 定義一個物件封裝一系列物件的相互作用，中介者藉由使各物件不需要直接互動而鬆開耦合，並且可以獨立的改變各物件的相互作用。

## 優點
> 1. 降低模組間的耦合
> 2. 需求變更容易
>
> 透過中介者來進行模組間的溝通，降低了模組彼此間的依賴，並且由於邏輯都在中介者中處理，需要改變時只需修改中介者，降低變更風險。

## 缺點
> 1. 中介者過於肥大
>
> 由於各個類別間的溝通交由中介者協調，導致中介者邏輯複雜，程式碼過多。

## 使用時機
> 1. 在類別關係為複雜的網狀結構下，整理成簡單的星狀結構
> 2. 產品開發

## 參與者
> + 抽象中介者角色 - Mediator
>     * 定義一個統一的介面，用於各個 Colleague 間的溝通
> + 具體中介者角色 - Concrete Mediator
>     * 依賴於各個 Colleague，負責協調各個 Colleague 實作的行為
> + 同事角色 - Colleague
>     * 每個 Colleague 都知道 Concrete Mediator，並且透過 Concrete Mediator 來跟其他的 Colleague 溝通，具有自發行為與依賴方法
>     * 自發行為（Self－Method）：Colleague 本身的行為，如改變本身的狀態、處理自己的行為等
>     * 依賴方法（Dep－Method）：需要透過 Concrete Mediator 才能完成的行為

## 實踐
> 使用中介者模式的目的是為了減少模組間的依賴，降低耦合，將原本一對多的關係變為一對一，以符合[迪米特法則（LoD）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LeastKnowledgePrinciple/Notes.md "迪米特法則（LoD）")，如果同事角色 Colleague 有共同的抽象方法，使得介面可以被定義，那也可以做到[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")。

## 範例
有一個餐廳，裡面有一個主廚、學徒、服務生，一般點餐到上菜的流程是這樣子的，服務生接到訂單，讓廚師先預備，也讓學徒去準備食材，學徒準備好食材，讓主廚開始煮，廚師煮完讓服務生上菜，並且讓學徒來洗碗，這個流程剛好可以使用中介者模式來協調。

首先，建立一個中介者`ConcreteMediator`，繼承自`Mediator`，在`Mediator`類別中定義好了同事角色，分別是繼承自`Colleague`的主廚`Chef`、學徒`Apprentice`、服務生`Waiter`，而`Colleague`類別主要傳遞中介者`Mediator`讓各個同事角色知道中介者，以便溝通，這個中介者主要負責協調主廚`Chef`、學徒`Apprentice`、服務生`Waiter`之間的溝通協調，讓工作順利進行，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/MediatorPattern/Picture/MediatorPatternPicture.png)

#### Mediator
```java
public abstract class Mediator {
    // 定義同事類別
    protected Apprentice apprentice;
    protected Chef chef;
    protected Waiter waiter;

    public Apprentice getApprentice() {
        return apprentice;
    }

    public void setApprentice(Apprentice apprentice) {
        this.apprentice = apprentice;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    // 中介者的事件方法
    public abstract void execute(String key, Object... objects);
}
```
#### ConcreteMediator
```java
public class ConcreteMediator extends Mediator {
    public static final String HOT_POT = "HOT_POT";
    public static final String COOK = "COOK";
    public static final String PREPARE = "PREPARE";
    public static final String SERVE = "SERVE";
    public static final String WASHING = "WASHING";

    @Override
    public void execute(String key, Object... objects) {
        if (key.equals(HOT_POT)) {
            doHotPot();
        }
        if (key.equals(COOK)) {
            doCook();
        }
        if (key.equals(PREPARE)) {
            doPrepare();
        }
        if (key.equals(SERVE)) {
            doServe();
        }
        if (key.equals(WASHING)) {
            doWashing();
        }
    }

    private void doHotPot() {
        getChef().hotPot();
    }

    private void doCook() {
        getChef().cook();
    }

    private void doPrepare() {
        getApprentice().prepare();
    }

    private void doServe() {
        getWaiter().serve();
    }

    private void doWashing() {
        getApprentice().washing();
    }
}
```
#### Colleague
```java
public abstract class Colleague {
    protected Mediator mediator;

    // 通過建構子傳遞中介者
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }
}
```
#### Chef
```java
public class Chef extends Colleague {
    public Chef(Mediator mediator) {
        super(mediator);
    }

    // 廚師預先熱鍋
    public void hotPot() {
        System.out.println("廚師開始熱鍋...");
    }

    // 廚師下廚
    public void cook() {
        System.out.println("廚師開始下廚...煮好食物了");
        mediator.execute(ConcreteMediator.SERVE);
        mediator.execute(ConcreteMediator.WASHING);
    }
}
```
#### Apprentice
```java
public class Apprentice extends Colleague {
    public Apprentice(Mediator mediator) {
        super(mediator);
    }

    // 學徒備料
    public void prepare() {
        System.out.println("學徒準備食材中...完成");
        mediator.execute(ConcreteMediator.COOK);
    }

    // 學徒洗盤子
    public void washing() {
        System.out.println("學徒開始洗盤子");
    }
}
```
#### Waiter
```java
public class Waiter extends Colleague {
    public Waiter(Mediator mediator) {
        super(mediator);
    }

    // 顧客點單完，讓廚師準備以及學徒備料
    public void order() {
        System.out.println("顧客點餐完畢，廚師預先熱鍋，學徒去準備食材");
        mediator.execute(ConcreteMediator.HOT_POT);
        mediator.execute(ConcreteMediator.PREPARE);
    }

    // 服務生上菜
    public void serve() {
        System.out.println("服務生開始上菜");
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 餐廳有一個中介者
        Mediator mediator = new ConcreteMediator();
        // 廚師、學徒與服務生都知道中介者，且願意聽從指揮
        Chef chef = new Chef(mediator);
        Apprentice apprentice = new Apprentice(mediator);
        Waiter waiter = new Waiter(mediator);
        // 讓中介者知道廚師、學徒與服務生是誰
        mediator.setChef(chef);
        mediator.setApprentice(apprentice);
        mediator.setWaiter(waiter);

        // 服務生接到客人的訂單
        waiter.order();
    }
}
```
#### 執行結果
>     顧客點餐完畢，廚師預先熱鍋，學徒去準備食材
>     廚師開始熱鍋...
>     學徒準備食材中...完成
>     廚師開始下廚...煮好食物了
>     服務生開始上菜
>     學徒開始洗盤子

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Mediator_pattern
