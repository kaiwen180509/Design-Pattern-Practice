# 建造者模式 - Builder Pattern
## 定義
> - “Separate the construction of a complex object from its representation  so that the same construction process can create different representations.”
> - 將一個複雜物件的建造過程與它的表示分離（抽象方法），使這個建造過程（抽象方法）可以構造出不同的表示（屬性）。

## 優點
> 1. 封裝性良好
> 2. 擴展性佳
> 3. 細節控制容易
>
> 由於各個建造者是彼此獨立的，並且改變在建造者內部的細節不會影響到其他模組，也容易擴展其他建造者；使用者不必具體知道建造過程，封裝良好。

## 使用時機
> 1. 使用相同的方法，但執行順序不同時
> 2. 多個部件或零件可以裝配到一個物件中，但產生的結果不同時
> 3. 複雜的物件生成時
> 4. 補償設計缺陷，封裝物件的創建過程
> 5. 物件類別中的方法執行順序不同導致效能差異時

## 實踐
> 1. 結合[模板方法模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/TemplateMethodPattern/Notes.md "模板方法模式")
> 
> 與[工廠模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/FactoryPattern/Notes.md "工廠模式")同為創建型模式，但建造者模式的關注點在於基本方法的呼叫順序，而工廠模式的關注點則是物件的建造，兩者之間有明顯的差異，因此創造者模式與模板方法模式結合會帶來更靈活的應用。

## 參與者
> + 產品 - Product
>     * 被建造的複雜物件，一般由模板方法模式來實作
> + 抽象建造者 - Builder
>     * Product 創建的指定規範，一般為抽象類別
> + 具體建造者 - ConcreteBuilder
>     * 實作 Builder 定義的所有方法，並且回傳建造好的 Product
> + 指揮者 - Director
>     * 負責安排既有模組的基本方法順序並讓 ConcreteBuilder 建造

## 範例
以玩具積木工廠生產積木的過程來說明，產品族有四種，分別是正方形積木`SquareToyBrick`、長方形積木`RectangleToyBrick`、三角形積木`TriangleToyBrick`以及圓柱形積木`CylinderToyBrick`，結合[模板方法模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/TemplateMethodPattern/Notes.md "模板方法模式")來運用，每一個積木都是實作積木的抽象類別`ToyBrick`，定義了取得原料`getMaterials()`、雕塑形狀`sculptureShape()`以及打磨`grinding()`的方法，還有執行順序`sequence`的列表與執行關鍵字的靜態唯一值。

接著是建造者的建構，產生一個抽象的建造者類別`ToyBrickBuilder`，定義製造的基本順序方法`setSequence()`以及回傳完成製造的積木`getToyBrick()`方法，具體實作交給各個積木建造者，分別是`SquareBuilder`、`RectangleBuilder`、`TriangleBuilder`以及`CylinderBuilder`類別。

最後是工廠指揮者`ToyBrickFactoryDirector`的類別建立，提供使用者呼叫的方法，以便取得各個積木，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/BuilderPattern/Picture/BuilderPatternPicture.png)

#### ToyBrick
```java
public abstract class ToyBrick {
    // 順序列表
    private ArrayList<String> sequence = new ArrayList<>();

    // 取得原料執行關鍵字
    public final static String GET_MATERIALS = "GET_MATERIALS";
    // 雕塑形狀執行關鍵字
    public final static String SCULPTURE_SHAPE = "SCULPTURE_SHAPE";
    // 打磨光滑執行關鍵字
    public final static String GRINDING = "GRINDING";

    // 取得原料
    protected abstract void getMaterials();

    // 雕塑形狀
    protected abstract void sculptureShape();

    // 打磨光滑
    protected abstract void grinding();

    // 設定順序
    final public void setSequence(ArrayList<String> sequence) {
        this.sequence = sequence;
    }

    // 製作積木
    final public void make() {
        for (int i = 0; i < sequence.size(); i++) {
            String key = sequence.get(i);
            if (key.equals(this.GET_MATERIALS)) {
                this.getMaterials();
            } else if (key.equals(this.SCULPTURE_SHAPE)) {
                this.sculptureShape();
            } else if (key.equals(this.GRINDING)) {
                this.grinding();
            }
        }
    }
}
```
#### SquareToyBrick
```java
public class SquareToyBrick extends ToyBrick {
    @Override
    protected void getMaterials() {
        System.out.println("取得原料");
    }

    @Override
    protected void sculptureShape() {
        System.out.println("切割原料為正方形");
    }

    @Override
    protected void grinding() {
        System.out.println("打磨光滑");
    }
}
```
#### RectangleToyBrick
```java
public class RectangleToyBrick extends ToyBrick {
    @Override
    protected void getMaterials() {
        System.out.println("取得原料");
    }

    @Override
    protected void sculptureShape() {
        System.out.println("切割原料為長方形");
    }

    @Override
    protected void grinding() {
        System.out.println("打磨光滑");
    }
}
```
#### TriangleToyBrick
```java
public class TriangleToyBrick extends ToyBrick {
    @Override
    protected void getMaterials() {
        System.out.println("取得原料");
    }

    @Override
    protected void sculptureShape() {
        System.out.println("切割原料為三角形");
    }

    @Override
    protected void grinding() {
        System.out.println("打磨光滑");
    }
}
```
#### CylinderToyBrick
```java
public class CylinderToyBrick extends ToyBrick {
    @Override
    protected void getMaterials() {
        System.out.println("取得原料");
    }

    @Override
    protected void sculptureShape() {
        System.out.println("切割原料為圓柱形");
    }

    @Override
    protected void grinding() {
        System.out.println("打磨光滑");
    }
}
```
#### ToyBrickBuilder
```java
public abstract class ToyBrickBuilder {
    public abstract void setSequence(ArrayList<String> sequence);

    public abstract ToyBrick getToyBrick();
}
```
#### SquareBuilder
```java
public class SquareBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new SquareToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}
```
#### RectangleBuilder
```java
public class RectangleBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new RectangleToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}
```
#### TriangleBuilder
```java
public class TriangleBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new TriangleToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}
```
#### CylinderBuilder
```java
public class CylinderBuilder extends ToyBrickBuilder {
    private ToyBrick toyBrick = new CylinderToyBrick();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.toyBrick.setSequence(sequence);
    }

    @Override
    public ToyBrick getToyBrick() {
        return this.toyBrick;
    }
}
```
#### ToyBrickFactoryDirector
```java
public class ToyBrickFactoryDirector {
    private ArrayList<String> sequence = new ArrayList<>();
    private SquareBuilder squareBuilder = new SquareBuilder();
    private RectangleBuilder rectangleBuilder = new RectangleBuilder();
    private TriangleBuilder triangleBuilder = new TriangleBuilder();
    private CylinderBuilder cylinderBuilder = new CylinderBuilder();

    // 取得正方形積木
    public ToyBrick getSquareToyBrick() {
        this.sequence.clear();
        // 取得原料後切割打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.SCULPTURE_SHAPE);
        this.sequence.add(ToyBrick.GRINDING);
        this.squareBuilder.setSequence(this.sequence);
        return this.squareBuilder.getToyBrick();
    }

    // 取得長方形積木
    public ToyBrick getRectangleToyBrick() {
        this.sequence.clear();
        // 取得的原料就是長方形，所以不用切割，直接打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.GRINDING);
        this.rectangleBuilder.setSequence(this.sequence);
        return this.rectangleBuilder.getToyBrick();
    }

    // 取得三角形積木
    public ToyBrick getTriangleToyBrick() {
        this.sequence.clear();
        // 取得原料後切割打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.SCULPTURE_SHAPE);
        this.sequence.add(ToyBrick.GRINDING);
        this.triangleBuilder.setSequence(this.sequence);
        return this.triangleBuilder.getToyBrick();
    }

    // 取得圓柱形積木
    public ToyBrick getCylinderToyBrick() {
        this.sequence.clear();
        // 取得原料後切割打磨
        this.sequence.add(ToyBrick.GET_MATERIALS);
        this.sequence.add(ToyBrick.SCULPTURE_SHAPE);
        this.sequence.add(ToyBrick.GRINDING);
        this.cylinderBuilder.setSequence(this.sequence);
        return this.cylinderBuilder.getToyBrick();
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 建立工廠指揮者
        ToyBrickFactoryDirector factoryDirector = new ToyBrickFactoryDirector();

        // 開始製造積木
        System.out.println("----------正方形積木製作----------");
        factoryDirector.getSquareToyBrick().make();
        System.out.println("----------長方形積木製作----------");
        factoryDirector.getRectangleToyBrick().make();
        System.out.println("----------三角形積木製作----------");
        factoryDirector.getTriangleToyBrick().make();
        System.out.println("----------圓柱形積木製作----------");
        factoryDirector.getCylinderToyBrick().make();
    }
}
```
#### 執行結果
>     ----------正方形積木製作----------
>     取得原料
>     切割原料為正方形
>     打磨光滑
>     ----------長方形積木製作----------
>     取得原料
>     打磨光滑
>     ----------三角形積木製作----------
>     取得原料
>     切割原料為三角形
>     打磨光滑
>     ----------圓柱形積木製作----------
>     取得原料
>     切割原料為圓柱形
>     打磨光滑

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Builder_pattern
