# 單一職責原則 - Single Responsibility Principle, SRP
## 定義
> - “There should never be more than one reason for a class to change.”
> - 用職責或變化原因（因專案與環境而異）來衡量方法、介面或類別設計的標準。
> - 要求一個方法、介面或類別只有一個原因引起變化。

## 優點
>     類別複雜性降低
>     可讀性提高
>     可維護性提高
>     變更引起的風險降低
> 職責分割清楚之後，降低了類別的複雜度，這有助於提升程式的可讀性，可讀性變好之後，維護也變得較容易，且變更引起的風險會被控制在該職責內，自然比較安全。

## 缺點
>     過度細分將導致類別數量遽增
>     專案實際情況可能導致體現困難
> 實際專案的開發上，可能過度複雜，導致較難實作此原則，並且過度細分職責也將導致類別與介面的數量遽增。

## 實踐
>     方法與介面必須遵守
>     類別盡力而為
> 在方法與介面層級上遵守此原則，並且清晰明確的設計，而類別則依照專案的開發情況與環境而定，盡力而為。

## 範例
以人為例，會吃東西、走路、跳還有睡覺，並且會有身高、體重、年紀還有名子。
接著可以依照人的行為與人的資訊來拆分職責。
根據上述，類別圖如下：
![image](https://github.com/kaiwen180509/Design-Pattern-Practice/SOLID/Picture/SRPClassPicture.png)

#### IPersonInfo
```java
public interface IPersonInfo {
    void setHeight(float height);
    float getHeight();
    void setWeight(float weight);
    float getWeight();
    void setYear(int year);
    int getYear();
    void setName(String name);
    String getName();
}
```
#### IPersonBehavior
```java
public interface IPersonBehavior {
    void walk();
    void jump();
    void eat();
    void sleep();
}
```
#### Person
```java
public class Person implements IPersonBehavior, IPersonInfo {
    private String name;
    private int year = 0;
    private float height = 0f;
    private float weight = 0f;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void walk() {
        System.out.println(name + "走路");
    }

    @Override
    public void jump() {
        System.out.println(name + "跳");
    }

    @Override
    public void eat() {
        System.out.println(name + "吃東西");
    }

    @Override
    public void sleep() {
        System.out.println(name + "睡覺");
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
```
#### 實際應用
```java
public class Client {
    public static void main(String[] args) {
        Person jhon = new Person("Jhon");
        // Jhon 走路
        jhon.walk();
        // Jhon 改名為 Jhon2
        jhon.setName("Jhon2");
        // Jhon2 開始動作
        jhon.jump();
        jhon.eat();
        jhon.sleep();
    }
}
```
#### 執行結果
> Jhon走路
> Jhon2跳
> Jhon2吃東西
> Jhon2睡覺

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Single_responsibility_principle
