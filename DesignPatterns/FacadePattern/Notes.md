# 門面模式 - Facade Pattern
## 定義
> - “Provide a unified interface to a set of interfaces in a subsystem.”
> - “Facade defines a higher-level interface that makes the subsystem easier to use.”
> - 為子系統的一組介面提供一個統一的介面。
> - 門面提供一個高層介面使子系統更容易使用。

## 參與者
> + 門面角色 - Facade
>     * 提供使用者呼叫的角色，並且知曉所有子系統角色 SubSystem 的所有功能只職責，沒有任何業務邏輯，單純為一個委託類別
> + 子系統角色 - SubSystem
>     * 同時間有一個或多個子系統，一個子系統為一個或多個類別集合而成，子系統並不依賴於門面角色 Facade

## 優點
> 1. 減少相互依賴關係
> 2. 提升靈活性
> 3. 提升安全性
> 4. 擴展容易
> 
> 在**門面模式**之中，使用者的所有依賴都是對 Facade 的依賴，改善子系統與使用者之間的強耦合，由於依賴減少，靈活性自然提升，另外擴展也不會影響到高層模組的依賴，並且 Facade 中沒有提供的存取，也不會被高層模組知道，提升安全性。

## 缺點
> 1. 不符合[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")
> 2. 變更風險大
> 
> 當遇上修改或變更時，無法透過繼承或覆寫來解決問題，勢必會修改到門面 Facade，因此提升了修改與變更的風險。

## 使用時機
> 1. 一個複雜的子系統或模組需要提供外部存取時
> 2. 子系統需要相對獨立時
> 3. 降低開發風險，預防低水準開發人員帶來的風險擴散時

## 注意事項
> 1. 門面龐大問題
> 2. 不同存取權限問題
> 
> 當門面 Facade 類別已經過於肥大（超過200行程式碼），可以依據功能拆分為多個門面來解決問題；而遇上不同的存取權限問題時，可以藉由建立新的門面，透過引用舊的門面，並且限制提供的方法來解決問題。

## 實踐
> 避免門面參與到子系統之間的邏輯，當需要處理某些邏輯時，可以藉由封裝物件來解決，將封裝好的物件交給門面角色來使用，從而使門面角色保持穩定與一貫性。

## 範例
當我們上一間中餐館去吃飯時，會進行點餐的動作，負責完成餐點的人叫做廚師，一般的廚師會分成兩種，一種是[「紅案廚師」](https://baike.baidu.com/item/%E7%BA%A2%E7%99%BD%E6%A1%88 "「紅案廚師」")，一種是[「白案廚師」](https://baike.baidu.com/item/%E7%BA%A2%E7%99%BD%E6%A1%88 "「白案廚師」")，兩者之間的差異在於，紅案負責葷菜與烹飪；而白案則是做麵食、點心等。分工明確，但是又同屬一個系統，恰好是一個子系統的模型，剛好可以用來當一個**門面模式**的例子。

定義一個子系統，包含一個抽象的廚師介面`Chef`，負責廚師所需要具備的方法，讓實作介面的廚師類別`RedCase`與`WhiteCase`進行具體的實作，子系統完成之後，接著建立門面類別`Kitchen`，負責提供使用者對於子系統的存取，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/FacadePattern/Picture/FacadePatternPicture.png)

#### Chef
```java
public interface Chef {
    // 廚師備料
    void prepareMaterial();

    // 廚師製作菜品
    void make(String name);
}
```
#### RedCase
```java
public class RedCase implements Chef {
    @Override
    public void prepareMaterial() {
        System.out.println("紅案廚師準備材料...");

    }

    @Override
    public void make(String name) {
        System.out.println("紅案廚師開始製作" + name);
    }
}
```
#### WhiteCase
```java
public class WhiteCase implements Chef {
    @Override
    public void prepareMaterial() {
        System.out.println("白案廚師準備材料...");
    }

    @Override
    public void make(String name) {
        System.out.println("白案廚師開始製作" + name);
    }
}
```
#### Kitchen
```java
public class Kitchen {
    // 被委託的廚師物件
    private Chef redCase = new RedCase();
    private Chef whiteCase = new WhiteCase();

    // 顧客點餐 -> 主食
    public void orderMainMeal(String name) {
        redCase.prepareMaterial();
        redCase.make(name);
    }

    // 顧客點餐 -> 點心
    public void orderDessert(String name) {
        whiteCase.prepareMaterial();
        whiteCase.make(name);
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 門面
        Kitchen kitchen = new Kitchen();

        // 門面提供的服務
        System.out.println("-----顧客點餐完畢-----");
        kitchen.orderMainMeal("炒飯");
        kitchen.orderDessert("豆沙包");
    }
}
```
#### 執行結果
>     -----顧客點餐完畢-----
>     紅案廚師準備材料...
>     紅案廚師開始製作炒飯
>     白案廚師準備材料...
>     白案廚師開始製作豆沙包

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Facade_pattern
 - https://baike.baidu.com/item/%E7%BA%A2%E7%99%BD%E6%A1%88