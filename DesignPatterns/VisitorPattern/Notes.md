# 存取者模式 - Visitor Pattern
## 定義
> - “Represent an operation to be performed on the elements of an object structure.”
> - “Visitor lets you define a new operation without changing the classes of the elements on which it operates.”
> - 代表一個物件結構的元素上的操作。
> - 存取者定義在不改變物件結構的情況下對元素的操作。

## 參與者
> + 抽象存取者 - Visitor
>     * 抽象類別或介面，定義那些物件是可以被存取的
> + 具體存取者 - ConcreteVisitor
>     * 定義存取者取得物件之後要怎麼做，做什麼事情
> + 抽象元素 - Element
>     * 抽象類別或介面，宣告接受哪一類存取者存取，通常是透過 `accept()` 方法定義
> + 具體元素 - ConcreteElement
>     *  實作 `accept()` 方法，基本上是一種固定的形式：`visitor.visit(this)`
> + 結構物件 - ObjectStructure
>     * 元素產生者，專案中很少抽象出這個角色，通常為 `List`、`Set`、`Map`

## 優點
> 1. 任務集中化
> 2. 擴展性佳
> 3. 靈活性高
> 4. 符合[單一職責原則（SRP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "單一職責原則（SRP）")
> 
> 在**存取者模式**之中，容易存取到容器中的具體元素，並且集中在存取者處理，且元素與存取者的職責明確，符合[單一職責原則（SRP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "單一職責原則（SRP）")，由於職責區分清楚，擴展容易，存取者更可以靈活使用存取到的元素資料。

## 缺點
> 1. 變更困難
> 2. 違反[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")
>
> 在具體元素的變更上修改，則存取者也得跟著修改，增加額外工作；且存取者依賴的是具體元素而不是抽象元素，違反[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")，將導致元素擴展困難。

## 使用時機
> 1. 一個物件結構包含多個類別的元素物件時
> 2. 需要對一個物件結構進行許多不同且無關的操作時
> 3. 必須遍歷多個不同的物件時

## 實踐
> 1. 一般存取者模式
> 2. 統計功能模式
> 
> **存取者模式**是一個集中規整模式，非常適用於大型的專案，容易達成功能集中化的目的。

## 範例
有一間商店裡面有賣食物、飲料，現在有一個客人上門，這個客人既想喝飲料，又想吃東西，但是又不想買太貴的東西，於是顧客決定只買低於20元的飲料以及低於50元的食物，下面使用這個簡單的例子來說明**存取者模式**。

### 一般存取者模式：
建立一個簡單的抽象元素`Product`抽象類別，負責定義每個商品所需具備的方法與屬性，並且要求每個商品需要實作`accept()`方法，接著建立實作的類別`FoodProduct`與`DrinkProduct`類別，具體實現功能；接下來定義一個存取者的介面`IVisitor`，定義好存取者可以存取那些物件，具體實作交給`Customer`類別，並且根據存取到的資料，進行下一步的動作；最後是物件結構的`Shop`類別建造，負責產生商品，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/VisitorPattern/Picture/VisitorPatternPicture.png)

#### Product
```java
public abstract class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    // 接受顧客的存取
    public abstract void accept(IVisitor visitor);
}
```
#### FoodProduct
```java
public class FoodProduct extends Product {
    public FoodProduct(String name, int price) {
        super(name, price);
    }

    @Override
    public void accept(IVisitor visitor) {
        // 接受存取
        visitor.visit(this);
    }
}
```
#### DrinkProduct
```java
public class DrinkProduct extends Product {
    public DrinkProduct(String name, int price) {
        super(name, price);
    }

    @Override
    public void accept(IVisitor visitor) {
        // 接受存取
        visitor.visit(this);
    }
}
```
#### IVisitor
```java
public interface IVisitor {
    void visit(DrinkProduct drinkProduct);

    void visit(FoodProduct foodProduct);
}
```
#### Customer
```java
public class Customer implements IVisitor {
    @Override
    public void visit(DrinkProduct drinkProduct) {
        if (drinkProduct.getPrice() <= 20) {
            buy(drinkProduct.getName());
        }
    }

    @Override
    public void visit(FoodProduct foodProduct) {
        if (foodProduct.getPrice() <= 50) {
            buy(foodProduct.getName());
        }
    }

    // 顧客買東西
    private void buy(String name) {
        System.out.println("購買：" + name);
    }
}
```
#### Shop
```java
public class Shop {
    // 貨品的貨架
    private ArrayList<Product> productList = new ArrayList<>();

    public Shop() {
        importProduct();
    }

    // 取得商店的貨架
    public ArrayList<Product> getProductList() {
        return this.productList;
    }

    // 商店進貨
    private void importProduct() {
        // 進貨飲料
        Product drinkA = new DrinkProduct("奶茶", 25);
        Product drinkB = new DrinkProduct("綠茶", 20);

        // 進貨食物
        Product foodA = new FoodProduct("便當", 60);
        Product foodB = new FoodProduct("飯糰", 25);

        // 商品上架
        productList.add(drinkA);
        productList.add(drinkB);
        productList.add(foodA);
        productList.add(foodB);
    }
}
```
### 統計功能模式：
藉由元素會集中在存取者進行處理的特性，可以完成許多需要統計類型的工作，例如以下的例子：

在上面的例子中的那間商店，現在來了一個客人，這個客人想要把整間店的商品都包下來，因此開始存取每個商品的價格，並且進行彙總，算出需要多少錢才能包店。

擴展存取者的介面為`IStatisticsVisitor`，繼承自`IVisitor`，且新增一個方法為`getTotalPrice()`，交給`StatisticsCustomer`去實作，將訪問到的商品價格集中處理後，最後得出結果，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/VisitorPattern/Picture/VisitorPatternStatisticsPicture.png)

#### IStatisticsVisitor
```java
public interface IStatisticsVisitor extends IVisitor {
    int getTotalPrice();
}
```
#### StatisticsCustomer
```java
public class StatisticsCustomer implements IStatisticsVisitor {
    private int totalPrice = 0;

    @Override
    public void visit(DrinkProduct drinkProduct) {
        this.totalPrice += drinkProduct.getPrice();
    }

    @Override
    public void visit(FoodProduct foodProduct) {
        this.totalPrice += foodProduct.getPrice();
    }

    @Override
    public int getTotalPrice() {
        return this.totalPrice;
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        System.out.println("----------一般存取者模式----------");
        doNormalVisitor();
        System.out.println("-----------統計功能模式----------");
        doStatisticsVisitor();
    }

    private static void doNormalVisitor() {
        // 一間商店有一個顧客要買東西
        Shop shop = new Shop();
        IVisitor customer = new Customer();

        // 顧客開始查看每個商品
        for (Product product : shop.getProductList()) {
            product.accept(customer);
        }
    }

    private static void doStatisticsVisitor() {
        // 商店裡面有一個想要包店的顧客在算總共要多少錢
        Shop shop = new Shop();
        StatisticsCustomer customer = new StatisticsCustomer();

        // 顧客開始查看每個商品
        for (Product product : shop.getProductList()) {
            product.accept(customer);
        }

        // 一共需要多少錢
        System.out.println("總共需要：" + customer.getTotalPrice() + " 元");
    }
}
```
#### 執行結果
>     ----------一般存取者模式----------
>     購買：綠茶
>     購買：飯糰
>     -----------統計功能模式----------
>     總共需要：130 元

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Visitor_pattern