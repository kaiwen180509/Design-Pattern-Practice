# 组合模式 - Composite Pattern
## 定義
> - “Compose objects into tree structures to represent whole-part hierarchies.”
> - “Composite lets clients treat individual objects and compositions of objects uniformly.”
> - 將物件組合成樹狀結構以表示“部分 - 整體”的層次架構。
> - 組合讓使用者對於個別物件與組合物件具有一致性。

## 參與者
> + 抽象構件 - Component
>     * 定義參加組合的物件所共有的方法與屬性，以及預設的行為或屬性
> + 葉子構件 - Leaf
>     * 最小的單位，下面不會再有其他分支
> + 樹枝構件 - Composite
>     * 作用為組合葉子構件 Leaf 與樹枝構件 Composite，以形成一個數狀結構

## 優點
> 1. 高層模組容易使用
> 2. 擴展容易
> 3. 符合[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")
>
> 在**组合模式**中，所有的節點都繼承自 Component，高層模組不必關心處理的是個別物件還是組合物件，簡化使用；並且各個構件的節點擴展容易，只要找到父節點就可以新增，符合[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")。

## 缺點
> 1. 與[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")衝突
>
> 在進行節點的使用時，是直接定義樹葉構件與樹枝構件的實作類別，而非依賴抽象，與[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")衝突。

## 使用時機
> 1. 維護或展示“部分 - 整體”關係的時候
> 2. 從一個整體獨立出部分功能或模組的情況

## 實踐
> **组合模式**分為兩種，一種是安全模式，另一種是透明模式，兩者之間的差異在於安全模式是把組合的方法放到樹枝構件，優點與缺點如上所述；而透明模式則是放在抽象構件裡面，好處是使用者使用時可以透過抽象構件來依賴，遵守[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")，避免了安全模式的缺點，但缺點則是樹葉構件與樹枝構件的架構相同，處理節點時如果沒有處理好，會相當不安全；這裡以安全模式來舉例。

## 範例
一間超商通常都會有很多個販售區塊，不同的區塊放置不同的商品種類，有的地方是放飲料類的商品，有的則是食物類的商品，也有餅乾零食類的區域，在這種情況之下，如果使用一般的設計會很複雜，但是如果使用組合模式會非常簡單，以下就以這個情形來舉例說明。

首先需要先定義好構建`Component`的抽象類別，裡面定義所有節點共有的屬性以及需要實作的各個方法，接著實作樹葉構件`Product`的類別，這個類別是最小的單位，也就是商品，最後是樹枝的構件`SalesArea`類別，裡面具體實作了處理構件的組合，以及父節點的設定等，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/CompositePattern/Picture/CompositePatternPicture.png)

#### Component
```java
public abstract class Component {
    protected String name;

    private Component parent = null;

    public Component(String name) {
        this.name = name;
    }

    // 設置父節點
    public void setParent(Component parent) {
        this.parent = parent;
    }

    // 取得父節點
    public Component getParent() {
        return this.parent;
    }

    // 取得構件的資訊
    public abstract String getInfo();
}
```
#### Product
```java
public class Product extends Component {
    private int price;

    public Product(String name, int price) {
        super(name);
        this.price = price;
    }

    @Override
    public String getInfo() {
        return "產品名稱：" + this.name + "  價格：" + this.price + " 元";
    }
}
```
#### SalesArea
```java
public class SalesArea extends Component {
    private ArrayList<Component> list = new ArrayList<>();

    public SalesArea(String name) {
        super(name);
    }

    @Override
    public String getInfo() {
        return this.name + " 販售區";
    }

    public void add(Component product) {
        product.setParent(this);
        this.list.add(product);
    }

    public void remove(Component product) {
        this.list.remove(product);
    }

    public ArrayList<Component> getComponents() {
        return this.list;
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 為商店建立各個貨架
        SalesArea shop = new SalesArea("Shop");
        SalesArea drinkSalesArea = new SalesArea("Drink");
        SalesArea foodSalesArea = new SalesArea("Food");
        SalesArea cookiesSalesArea = new SalesArea("Cookies");

        shop.add(drinkSalesArea);
        shop.add(foodSalesArea);
        shop.add(cookiesSalesArea);

        // 進貨各個產品
        Product drinkProductA = new Product("綠茶", 20);
        Product drinkProductB = new Product("紅茶", 20);
        Product drinkProductC = new Product("奶茶", 30);

        Product foodProductA = new Product("三角飯糰", 25);
        Product foodProductB = new Product("便當", 65);
        Product foodProductC = new Product("涼麵", 50);
        Product foodProductD = new Product("義大利麵", 60);

        Product cookiesProductA = new Product("夾心餅乾", 35);
        Product cookiesProductB = new Product("洋芋片", 25);
        Product cookiesProductC = new Product("玉米棒", 10);

        // 組合 -> 產品上架
        drinkSalesArea.add(drinkProductA);
        drinkSalesArea.add(drinkProductB);
        drinkSalesArea.add(drinkProductC);

        foodSalesArea.add(foodProductA);
        foodSalesArea.add(foodProductB);
        foodSalesArea.add(foodProductC);
        foodSalesArea.add(foodProductD);

        cookiesSalesArea.add(cookiesProductA);
        cookiesSalesArea.add(cookiesProductB);
        cookiesSalesArea.add(cookiesProductC);

        // 下架某個產品
        foodSalesArea.remove(foodProductC);

        // 遍歷商店
        show(shop);

        // 找到三角飯糰 foodProductA 的販售區域
        System.out.println("-----三角飯糰的販售區-----");
        Component salesArea = foodProductA.getParent();
        System.out.println(salesArea.getInfo());
    }

    // 遍歷方法
    private static void show(SalesArea salesArea) {
        for (Component component : salesArea.getComponents()) {
            System.out.println(component.getInfo());
            // 判斷是否為販售區域，是就查看販售區域下的資訊
            if (component instanceof SalesArea) {
                show((SalesArea) component);
            }
        }
    }
}
```
#### 執行結果
>     Drink 販售區
>     產品名稱：綠茶  價格：20 元
>     產品名稱：紅茶  價格：20 元
>     產品名稱：奶茶  價格：30 元
>     Food 販售區
>     產品名稱：三角飯糰  價格：25 元
>     產品名稱：便當  價格：65 元
>     產品名稱：義大利麵  價格：60 元
>     Cookies 販售區
>     產品名稱：夾心餅乾  價格：35 元
>     產品名稱：洋芋片  價格：25 元
>     產品名稱：玉米棒  價格：10 元
>     -----三角飯糰的販售區-----
>     Food 販售區

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Composite_pattern