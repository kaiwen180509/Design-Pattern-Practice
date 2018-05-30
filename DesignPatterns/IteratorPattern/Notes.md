# 疊代器模式 - Iterator Pattern
## 定義
> - “Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.”
> - 提供一個方法去存取一個容器物件中的各個元素，且沒有暴露內部細節。

## 參與者
> + 抽象疊代器 - Iterator
>     * 定義存取與遍歷元素的介面，一般有 first() 、next() 與 hasNext() 方法
>     * first()：取得第一個元素
>     * next()：獲得下一個元素
>     * hasNext()：判斷是否還有元素
> + 具體疊代器 - ConcreteIterator
>     * 實作抽象疊代器 Iterator 的類別，負責完成容器元素的遍歷
> + 抽象容器 - Aggregate
>     * 負責提供創建具體疊代器 ConcreteIterator 的介面，一般為 iterator() 方法
> + 具體容器 - ConcreteAggregate
>     * 實作抽象容器 Aggregate 的類別，創建出容納疊代器 Iterator 的物件

## 使用時機
> 在大部分的程式語言中，都有 Iterator 這個介面或實作，基本上不太會需要自己手動建造。

## 實踐
> 在 **JAVA** 中，使用 [JDK](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html "JDK") 提供的 Iterator 基本已經夠用，可以節省自己的精力。

## 範例
以一般的常見疊代器來說明，建立一個疊代器`Iterator`介面，定義疊代器所需要的方法，讓`ConcreteIterator`來實作，並且建立一個容器`Aggregate`的介面來定義容器，交給`ConcreteAggregate`進行具體的實現，透過`iterator()`方法來取得疊代器，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/IteratorPattern/Picture/IteratorPatternPicture.png)

#### Iterator
```java
public interface Iterator {
    Object first();
    Object next();
    boolean hasNext();
}
```
#### ConcreteIterator
```java
public class ConcreteIterator implements Iterator {
    private Vector vector;
    // 目前的指標的位置
    public int curse = 0;

    public ConcreteIterator(Vector vector) {
        this.vector = vector;
    }

    @Override
    public Object first() {
        Object object = null;
        if (!vector.isEmpty()) {
            object = vector.get(0);
        }
        return object;
    }

    @Override
    public Object next() {
        Object object = null;
        // 判斷還有沒有元素，有就取出元素，並且指向下一個
        if (this.hasNext()) {
            object = vector.get(curse);
            curse++;
        }
        return object;
    }

    @Override
    public boolean hasNext() {
        // 判斷還有沒有元素，沒有了就把指標歸零
        if (this.curse == this.vector.size()) {
            this.curse = 0;
            return false;
        } else {
            return true;
        }
    }
}
```
#### Aggregate
```java
public interface Aggregate {
    void add(Object object);

    void remove(Object object);

    Iterator iterator();
}
```
#### ConcreteAggregate
```java
public class ConcreteAggregate implements Aggregate {
    // 保存元素的容器
    private Vector vector = new Vector();

    @Override
    public void add(Object object) {
        this.vector.add(object);
    }

    @Override
    public void remove(Object object) {
        this.vector.remove(object);
    }

    @Override
    public Iterator iterator() {
        // 回傳疊代器
        return new ConcreteIterator(this.vector);
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 建立容器並且裝入元素
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("Hello");
        aggregate.add("World");
        aggregate.add("!");
        // 刪除某個元素
        aggregate.remove("!");

        // 取出疊代器
        Iterator iterator = aggregate.iterator();
        // 印出第一個
        System.out.println(iterator.first());

        // 遍歷一次
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```
#### 執行結果
>     Hello
>     Hello
>     World

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Iterator_pattern
 - https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html