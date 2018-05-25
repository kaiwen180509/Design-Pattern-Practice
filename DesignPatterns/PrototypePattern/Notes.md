# 原型模式 - Prototype Pattern
## 定義
> - “Specify the kinds of objects to create using a prototypical instance, and create new object by copying this protype.”
> - 使用原型實例來創建具體的的物件種類，並且藉由拷貝這個原型來創建新的物件。
> - 不透過 new 來產生新的物件，而是透過複製來實作。

## 優點
> 1. 效能優良
> 2. 避免建構子的約束
>
> 由於物件是透過複製來建立的，因此複製的物件不會使用到建構子的方法，避開了約束，也因為是複製記憶體中的物件來建立，比透過 new 來建立物件節省了許多資源，有效的提升效能。

## 使用時機
> 1. 建立物件耗費資源多或是複雜時
> 2. 效能與安全要求高的情況時
> 3. 一個物件卻有多個修改者的情況時

## 實踐
> 1. 一般原型模式
> 2. 淺拷貝與深拷貝
> 
> 需要特別注意，當類別中出現`final`關鍵字時，就不能使用`clone()`方法，兩者之間會發生衝突，因為`final`的變數值是不能改變的。

## 範例
### 一般原型模式：
在JAVA中，要提供複製的物件本身需要實作 [JDK](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html "JDK") 提供的介面`Cloneable`，只有實作`Cloneable`介面的物件才有可能被複製，因為還需要實作從所有物件的父類別`Object`繼承的方法`clone()`，才可以被複製。

舉一個蛋糕製作的例子，一般的蛋糕被製作時，要先準備原料製作成麵糰，等待發酵之後再去烘烤，才能做出蛋糕原型，做出蛋糕原型之後需要裝飾，加上奶油、果醬、點綴物或是其他諸如：水果、巧克力、布丁、糖果...等，各種不同的配料，經過這些程序之後才會變成一般我們在蛋糕店所看到在販售的蛋糕，但是一個糕點師傅在等待蛋糕原型製作需要很久的時間以及很多的步驟，如果能夠讓糕點師傅一直專心裝飾蛋糕，可以有無限的蛋糕原型，就能提升很多的效率，這時候就可以使用原型模式了。

首先建立一個蛋糕的原型`Cake`類別，實作`Cloneable`介面與`clone()`方法，另外定義兩個方法`setFlavor()`、`getFlavor()`、來設定跟取得風味，接著建立一個糕點師傅`Decorateur`類別，糕點師傅`Decorateur`的`makeCake()`方法負責加工蛋糕原型，製作出可以販售的蛋糕，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/PrototypePattern/Picture/PrototypePatternPicture.png)

#### Cake
```java
public class Cake implements Cloneable {
    private String flavor;

    public Cake() {
        System.out.println("製作蛋糕原型要90分鐘");
    }

    @Override
    protected Cake clone() {
        Cake cake = null;
        try {
            cake = (Cake) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cake;
    }

    // 設定蛋糕的味道
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    // 取得蛋糕的味道
    public String getFlavor() {
        return this.flavor + "口味";
    }
}
```
#### Decorateur
```java
public class Decorateur {
    private Cake cake;

    public Decorateur(Cake cake) {
        this.cake = cake;
    }

    // 幫蛋糕進行後製
    public Cake makeCake(String flavor) {
        Cake cake = this.cake.clone();
        cake.setFlavor(flavor);
        return cake;
    }
}
```
### 淺拷貝與深拷貝：
在原型模式中，有一種情況需要特別注意，那就是淺拷貝與深拷貝，在淺拷貝中，只複製物件本身，對於內部的陣列、列表、引用的物件，都是指向原本的位置，造成共用變數的結果；深拷貝則是完整地進行複製，所以複製的物件與被複製的物件之間，完全沒有任何關係，物件彼此間的變數也是獨立分開的。

這裡以兩個類別來作例子，分別是淺拷貝物件`ShallowThing`類別與深拷貝物件`DeepThing`類別，兩個類別之間的差異只有在實作`clone()`方法時的不同以下是實際程式碼：
#### ShallowThing
```java
public class ShallowThing implements Cloneable {
    private ArrayList<String> contentList = new ArrayList<>();

    @Override
    public ShallowThing clone() {
        ShallowThing thing = null;
        try {
            thing = (ShallowThing) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thing;
    }

    // 寫入內容
    public void addContent(String content) {
        this.contentList.add(content);
    }

    // 顯示內容
    public void showContent() {
        for (int i = 0; i < this.contentList.size(); i++) {
            System.out.println(this.contentList.get(i));
        }
    }
}
```
#### DeepThing
```java
public class DeepThing implements Cloneable {
    private ArrayList<String> contentList = new ArrayList<>();

    @Override
    public DeepThing clone() {
        DeepThing thing = null;
        try {
            thing = (DeepThing) super.clone();
            thing.contentList = (ArrayList<String>) this.contentList.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thing;
    }

    // 寫入內容
    public void addContent(String content) {
        this.contentList.add(content);
    }

    // 顯示內容
    public void showContent() {
        for (int i = 0; i < this.contentList.size(); i++) {
            System.out.println(this.contentList.get(i));
        }
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        System.out.println("----------一般原型模式----------");
        doNormalPrototype();
        System.out.println("----------淺拷貝與深拷貝----------");
        doExtendPrototype();
    }

    private static void doNormalPrototype() {
        // 建立蛋糕原型
        Cake cake = new Cake();

        // 給糕點師蛋糕原型，讓他製作100個巧克力蛋糕，並且好好保存
        Decorateur decorateur = new Decorateur(cake);
        ArrayList<Cake> cakes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Cake cakeClone = decorateur.makeCake("巧克力");
            cakes.add(cakeClone);
        }

        // 老闆隨機抽取5個蛋糕來檢查有沒有問題
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int number = random.nextInt(100);
            System.out.println(number + " 號蛋糕是" + cakes.get(number).getFlavor());
        }
    }

    private static void doExtendPrototype() {
        System.out.println("-----淺拷貝-----");
        // 產生一個淺拷貝原型的物件
        ShallowThing shallowThing = new ShallowThing();
        shallowThing.addContent("Hello, Shallow");
        // 複製一個淺拷貝的物件
        ShallowThing shallowThingClone = shallowThing.clone();
        shallowThingClone.addContent("Shallow world!");
        // 淺拷貝會共用私有的變數
        shallowThing.showContent();

        System.out.println("-----深拷貝-----");
        // 產生一個深拷貝原型的物件
        DeepThing deepThing = new DeepThing();
        deepThing.addContent("Hello, Deep");
        // 複製一個深拷貝的物件
        DeepThing deepThingClone = deepThing.clone();
        deepThingClone.addContent("Deep world!");
        // 深拷貝對私有變數也進行拷貝
        System.out.println("deepThing：");
        deepThing.showContent();
        System.out.println("deepThingClone：");
        deepThingClone.showContent();
    }
}
```
#### 執行結果
>     ----------一般原型模式----------
>     製作蛋糕原型要90分鐘
>     67 號蛋糕是巧克力口味
>     99 號蛋糕是巧克力口味
>     41 號蛋糕是巧克力口味
>     2 號蛋糕是巧克力口味
>     59 號蛋糕是巧克力口味
>     ----------淺拷貝與深拷貝----------
>     -----淺拷貝-----
>     Hello, Shallow
>     Shallow world!
>     -----深拷貝-----
>     deepThing：
>     Hello, Deep
>     deepThingClone：
>     Hello, Deep
>     Deep world!

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Prototype_pattern
