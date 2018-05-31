# 觀察者模式 - Observer Pattern
## 定義
> - “Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.”
> - 定義一個一對多的依賴關係，使得當一個物件的狀態改變時，所有依賴於它的物件都會被通知並自動更新。

## 參與者
> + 抽象被觀察者 - Subject
>     * 一般為抽象類別或實作類別，定義被觀察者必須實作的職責：管理觀察者並且通知觀察者，必須能動態增加、取消觀察者
> + 具體被觀察者 - ConcreteSubject
>     * 定義被觀察者自身的邏輯，並且定義那些事件需要通知觀察者 Observer
> + 抽象觀察者 - Observer
>     * 接收觀察到的訊息，並且進行 update 的操作，對接收的訊息進行處理
> + 具體觀察者 - ConcreteObserver
>     * 處理各自具體觀察者所需的邏輯，每個具體觀察者 ConcreteObserver 都不同

## 優點
> 1. 擴展容易
> 2. 觸發機制
> 
> 在**觀察者模式**中，由於觀察者與被觀察者之間是抽象耦合，因此擴展非常容易，並且符合[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")，並且也可以藉由**觀察者模式**的通知機制來建立一條觸發鏈，讓每一個[符合單一職責原則（SRP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "符合單一職責原則（SRP）")的類別可以形成複雜的邏輯關係。

## 缺點
> 1. 效率差
> 2. 開發與除錯困難
> 
> 多個觀察者將導致類別過多，增加開發與除錯的複雜度，並且由於**JAVA**中訊息的通知是順序執行，一個觀察者卡住，則會影響到後面的觀察者，讓整體效率低下。

## 使用時機
> 1. 關聯行為場景時（並非組合關係）
> 2. 多級觸發機制的事件
> 3. 跨系統的訊息交換情況

## 注意事項
> 1. 廣播鏈問題
> 2. 異步處理問題
> 
> 在做觸發鏈時，如果觀察者本身也是被觀察者，以此類推，邏輯會較複雜，並且提升維護的困難，避免的方法是盡量使訊息傳遞不超過兩次；而異步處理需要特別考慮到執行緒的安全與訊息佇列的問題。

## 實踐
> 在**JAVA**的 [JDK](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html "JDK") 中，[API](https://en.wikipedia.org/wiki/Application_programming_interface "API") 已經設計了一個**觀察者模式**的開發框架，提供了被觀察者可以繼承的父類別`Observable`，以及提供觀察者實作的介面`Observer`，可以直接使用這個框架來開發。

## 範例
有一種攝影師很辛苦，需要在野外偽裝，並且隱藏自己，躲在暗處觀察動物的行為，調整好相機，等待最佳的時機到來，在關鍵時刻按下快門，這個職業就是[野生動物攝影師](https://www.natgeomedia.com/news/ngnews/62417 "野生動物攝影師")，也是由於這個職業，我們才能在家就能看到各種珍貴的野生動物們生活的樣貌，同時，這個行為也是一個很好的**觀察者模式**的範例。

舉一個例子，在草原上有兩個攝影師，其中一個負責拍照，一個負責錄影，並且現在有一隻老虎在發呆，攝影師們觀察等待中，等到老虎做出動作時，就是關鍵的時機點，這個時候就到了紀錄的時刻。

首先建立動物與攝影師的介面`IAnimal`與`IPhotographer`，分別定義動物與攝影師所需要實作的方法，接著建立實作動物介面的老虎類別`Tiger`，並且繼承自 [JDK](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html "JDK") 提供的`Observable`類別，成為一個被觀察者，最後是實作攝影師介面的攝影師類別`PhotographerA`與`PhotographerB`，還需要再實作 [JDK](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html "JDK") 提供的介面`Observer`，以便成為觀察者，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/ObserverPattern/Picture/ObserverPatternPicture.png)

#### IPhotographer
```java
public interface IPhotographer {
    // 攝影師的動作
    void take(String action);
}
```
#### IAnimal
```java
public interface IAnimal {
    // 動物會喝水
    void drink();
    // 動物會狩獵
    void hunting();
    // 動物會跑
    void run();
}
```
#### Tiger
```java
public class Tiger extends Observable implements IAnimal {
    @Override
    public void drink() {
        System.out.println("老虎喝水...");
        // 通知觀察者
        super.setChanged();
        super.notifyObservers("老虎喝水");
    }

    @Override
    public void hunting() {
        System.out.println("老虎狩獵...");
        // 通知觀察者
        super.setChanged();
        super.notifyObservers("老虎狩獵");
    }

    @Override
    public void run() {
        System.out.println("老虎奔跑...");
        // 通知觀察者
        super.setChanged();
        super.notifyObservers("老虎奔跑");
    }
}
```
#### PhotographerA
```java
public class PhotographerA implements Observer, IPhotographer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("攝影師A觀察到變化");
        take(arg.toString());
    }

    @Override
    public void take(String action) {
        System.out.println("拍下：" + action + "的畫面");
    }
}
```
#### PhotographerB
```java
public class PhotographerB implements Observer, IPhotographer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("攝影師B觀察到變化");
        take(arg.toString());
    }

    @Override
    public void take(String action) {
        System.out.println("錄下：" + action + "的畫面");
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 有一隻老虎
        Observable tiger = new Tiger();
        // 觀察者出現
        Observer photographerA = new PhotographerA();
        Observer photographerB = new PhotographerB();
        // 讓觀察者開始觀察被觀察者
        tiger.addObserver(photographerA);
        tiger.addObserver(photographerB);

        // 老虎開始各種行動
        IAnimal animal = (IAnimal) tiger;
        animal.drink();
        animal.hunting();
        animal.run();
    }
}
```
#### 執行結果
>     老虎喝水...
>     攝影師B觀察到變化
>     錄下：老虎喝水的畫面
>     攝影師A觀察到變化
>     拍下：老虎喝水的畫面
>     老虎狩獵...
>     攝影師B觀察到變化
>     錄下：老虎狩獵的畫面
>     攝影師A觀察到變化
>     拍下：老虎狩獵的畫面
>     老虎奔跑...
>     攝影師B觀察到變化
>     錄下：老虎奔跑的畫面
>     攝影師A觀察到變化
>     拍下：老虎奔跑的畫面

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Observer_pattern
 - https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html
 - https://en.wikipedia.org/wiki/Application_programming_interface
 - https://www.natgeomedia.com/news/ngnews/62417