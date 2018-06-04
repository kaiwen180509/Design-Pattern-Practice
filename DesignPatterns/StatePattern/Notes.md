# 狀態模式 - State Pattern
## 定義
> - “Allow an object to alter its behavior when its internal state changes.”
> - “The object will appear to change its class.”
> - 當一個物件的內部狀態改變時允許它改變自身的行為。
> - 這個物件看起來像是改變了其類別。

## 參與者
> + 抽象狀態角色 - State
>     * 抽象類別或介面，定義物件的狀態
>     * 封裝環境角色 Context 用以實作切換狀態的功能
> + 具體狀態角色 - ConcreteState
>     * 包含兩個職責，本狀態的行為管理以及如何切換到下一個狀態的處理
> + 環境角色 - Context
>     * 定義使用者介面，負責具體的狀態切換
>     * 宣告狀態物件為靜態常數`static final`
>     * 具有抽象狀態角色 State 的所有行為，使用[代理模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/ProxyPattern/Notes.md "代理模式")執行

## 優點
> 1. 架構清晰
> 2. 擴展性佳
> 3. 封裝性佳
> 4. 符合[單一職責原則（SRP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "單一職責原則（SRP）")
>
> 在**狀態模式**之中，避免使用過多的判斷語句，如：`if...else`或`switch...case`等來判斷狀態，減少程式的複雜度，提升了維護性；並且每一個狀態都是一個子類別，需要擴展就增加一個子類別，容易擴展，由於類別的職責單一，修改也容易；使用者在使用時，也不需要知道類別內部的狀態變化，封裝完好。

## 缺點
> 1. 類別數量多
>
> 由於一個狀態就是一個子類別，在多個狀態的情況下，類別數量膨脹，導致管理問題。

## 使用時機
> 1. 隨著狀態改變行為的情況
> 2. 條件、判斷語句的替代

## 實踐
> 建議狀態不要超過五個，以免過大的變化導致程式複雜。

## 範例
[水](https://en.wikipedia.org/wiki/Water "水")是人的生存必需品，人如果沒有食物，可以存活三週，但是如果沒有水，最多只能支撐三天。水一般有三個型態，分別是氣態的水蒸氣；固態的冰；還有液態的水。在液態的情況下，水可以加熱成氣態，也可以冷卻成固態，而氣態的水則只有冷卻凝聚成液態水，固態的冰只能加熱成液態水，這個過程一般稱為水的三態循環，如下圖所示。

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/StatePattern/Picture/StateExample.png)

水的這個過程可以用**狀態模式**來說明，以下便以水來當例子，預設的狀態為液態水，進行加熱變成水蒸氣後，開始一連串的冷卻，最後再次加熱，查看狀態的各種變化。

首先建立一個狀態`State`的抽象類別，封裝環境角色，並且定義狀態以及水的兩種變化方法加熱`heating()`與冷卻`cooling()`，交由`IceState`、`WaterState`與`SteamState`類別實作，接著是環境角色`Context`類別的建造，裡面定義各種狀態以及目前的狀態，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/StatePattern/Picture/StatePatternPicture.png)

#### State
```java
public abstract class State {
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    // 水的加熱行為
    public abstract void heating();

    // 水的冷卻行為
    public abstract void cooling();

    // 取得目前的狀態
    public abstract void getState();
}
```
#### IceState
```java
public class IceState extends State {
    @Override
    public void heating() {
        // 設定下一個狀態並且查看狀態
        this.context.setCurrentState(Context.WATER_STATE);
        this.context.getState();
    }

    @Override
    public void cooling() {
        // 冰不會繼續冷卻了
        System.out.println("冰沒有反應");
    }

    @Override
    public void getState() {
        System.out.println("冰");
    }
}
```
#### WaterState
```java
public class WaterState extends State {
    @Override
    public void heating() {
        // 設定下一個狀態並且查看狀態
        this.context.setCurrentState(Context.STEAM_STATE);
        this.context.getState();
    }

    @Override
    public void cooling() {
        // 設定下一個狀態並且查看狀態
        this.context.setCurrentState(Context.ICE_STATE);
        this.context.getState();
    }

    @Override
    public void getState() {
        System.out.println("水");
    }
}
```
#### SteamState
```java
public class SteamState extends State {
    @Override
    public void heating() {
        // 水蒸氣不會繼續加熱了
        System.out.println("水蒸氣沒有反應");
    }

    @Override
    public void cooling() {
        // 設定下一個狀態並且查看狀態
        super.context.setCurrentState(Context.WATER_STATE);
        this.context.getState();
    }

    @Override
    public void getState() {
        System.out.println("水蒸氣");
    }
}
```
#### Context
```java
public class Context {
    // 定義各種狀態
    public final static WaterState WATER_STATE = new WaterState();
    public final static IceState ICE_STATE = new IceState();
    public final static SteamState STEAM_STATE = new SteamState();
    // 定義目前的狀態
    private State currentState;

    // 設定目前的狀態
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    // 加熱
    public void heating() {
        System.out.print("加熱...");
        this.currentState.heating();
    }

    // 冷卻
    public void cooling() {
        System.out.print("冷卻...");
        this.currentState.cooling();
    }

    // 取得狀態
    public void getState() {
        this.currentState.getState();
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 定義環境並且預設水是液態
        System.out.println("-----水-----");
        Context context = new Context();
        context.setCurrentState(new WaterState());

        // 進行各種動作
        context.heating();
        context.cooling();
        context.cooling();
        context.cooling();
        context.heating();
    }
}
```
#### 執行結果
>     -----水-----
>     加熱...水蒸氣
>     冷卻...水
>     冷卻...冰
>     冷卻...冰沒有反應
>     加熱...水

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/State_pattern
 - https://en.wikipedia.org/wiki/Water