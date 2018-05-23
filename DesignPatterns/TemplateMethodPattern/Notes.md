# 模板方法模式 - Template Method Pattern
## 定義
> - “Defines the program skeleton of an algorithm in an operation, deferring some steps to subclasses. It lets one redefine certain steps of an algorithm without changing the algorithm's structure.”
> - 在一個操作中定義一個演算法的步驟，並遞延步驟到子類別實踐。
> - 使子類別在不改變演算法結構的情況下，重新定義演算法中的某些步驟。

## 優點
> 1. 封裝不變的部分，擴展可變的部分
> 2. 可維護性高
> 3. 子類別實作行為，由父類別控制
> 
> 由於方法框架已經定義好了，只要擴展可變的方法，例如擴展子類別，實作不同的行為，就可以完成需求變更，符合[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")，並且由於方法框架固定，容易修改，所以可維護性也提升。

## 缺點
> 1. 與一般設計習慣相反
> 
> 通常抽象類別負責定義事物屬性與方法，具體實作交給子類別去完成，子類別不會影響到父類別，而模板方法模式相反，子類別的實作會對父類別產生影響，為程式的可讀性帶來難度。

## 使用時機
> 1. 多個類別具有公共的方法，且邏輯基本相同時
> 2. 重要、複雜的演算法情況時
> 3. 重構時，透過掛鉤函式（模板方法模式擴展）來約束行為

## 實踐
> 1. 基本方法
> 2. 模板方法
> 3. 掛鉤函式（Hook Method）
>
> 基本方法又稱基本操作，是由子類別實作的的方法，並且在模板方法中被呼叫，盡量設計為`protected`類型，以符合[迪米特法則（LoD）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LeastKnowledgePrinciple/Notes.md "迪米特法則（LoD）")；而模板方法有一個或多個，是一個具體的方法框架，用來呼叫基本方法的固定邏輯，通常加上`final`關鍵字，不允許被覆寫。

## 範例
以攝影師拍照為例子，建立一個抽象的攝影師類別`Photographer`，定義各種基本方法，接著定義一個模板方法`snap()`，用來控制基本方法，接著實作攝影師的子類別，分別是新手`AmateurPhotographer`以及專業`AdeptPhotographer`，分別實作具體的基本方法，以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/TemplateMethodPattern/Picture/TemplateMethodPicture.png)

#### Photographer
```java
public abstract class Photographer {
    // 調整光圈
    protected abstract void adjustAperture();

    // 設定感光度
    protected abstract void setISO();

    // 設定快門速度
    protected abstract void setShutterSpeed();

    // 設定倒數計時
    protected abstract void setTimer();

    // 對焦
    protected abstract void focusLens();

    // 按快門
    protected abstract void pressShutter();

    // 拍照
    final public void snap() {
        this.adjustAperture();
        this.setISO();
        this.setShutterSpeed();
        this.setTimer();
        this.focusLens();
        this.pressShutter();
    }
}
```
#### AmateurPhotographer
```java
public class AmateurPhotographer extends Photographer {
    @Override
    protected void adjustAperture() {
        System.out.println("調整光圈為：f/2.8");
    }

    @Override
    protected void setISO() {
        System.out.println("設定感光度為：ISO-200");
    }

    @Override
    protected void setShutterSpeed() {
        System.out.println("設定快門時間為：1/8s");
    }

    @Override
    protected void setTimer() {
        System.out.println("設定倒數計時為：3 秒");
    }

    @Override
    protected void focusLens() {
        System.out.println("讓鏡頭對焦");
    }

    @Override
    protected void pressShutter() {
        System.out.println("按下快門");
    }
}
```
#### AdeptPhotographer
```java
public class AdeptPhotographer extends Photographer {
    @Override
    protected void adjustAperture() {
        System.out.println("調整光圈為：f/22");
    }

    @Override
    protected void setISO() {
        System.out.println("設定感光度為：ISO-3200");
    }

    @Override
    protected void setShutterSpeed() {
        System.out.println("設定快門時間為：1/125s");
    }

    @Override
    protected void setTimer() {
        System.out.println("設定倒數計時為：10 秒");
    }

    @Override
    protected void focusLens() {
        System.out.println("讓鏡頭對焦");
    }

    @Override
    protected void pressShutter() {
        System.out.println("按下快門");
    }
}
```
### 掛鉤函式（Hook Method）：
模板方法模式的擴展，由子類別的方法回傳決定模板方法內的執行，在抽象類別內新增一個掛鉤方法`isTimer()`，並且在模板方法中以此掛鉤方法來判斷是不是要執行基本方法`setTimer()`，並且在實作的子類別新增`useTimer()`方法，讓使用者決定要不要使用此基本方法，以下是類別圖與實際程式：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/TemplateMethodPattern/Picture/TemplateMethodExtendPicture.png)

#### PhotographerExtend
```java
public abstract class PhotographerExtend {
    // 調整光圈
    protected abstract void adjustAperture();

    // 設定感光度
    protected abstract void setISO();

    // 設定快門速度
    protected abstract void setShutterSpeed();

    // 設定倒數計時
    protected abstract void setTimer();

    // 擴展的掛鉤方法，預設：True
    protected boolean isTimer() {
        return true;
    }

    // 對焦
    protected abstract void focusLens();

    // 按快門
    protected abstract void pressShutter();

    // 拍照
    final public void snap() {
        this.adjustAperture();
        this.setISO();
        this.setShutterSpeed();
        if (isTimer()) {
            this.setTimer();
        }
        this.focusLens();
        this.pressShutter();
    }
}
```
#### AmateurPhotographerExtend
```java
public class AmateurPhotographerExtend extends PhotographerExtend {
    private boolean isTimer = true;

    @Override
    protected void adjustAperture() {
        System.out.println("調整光圈為：f/2.8");
    }

    @Override
    protected void setISO() {
        System.out.println("設定感光度為：ISO-200");
    }

    @Override
    protected void setShutterSpeed() {
        System.out.println("設定快門時間為：1/8s");
    }

    @Override
    protected void setTimer() {
        System.out.println("設定倒數計時為：3 秒");
    }

    @Override
    protected void focusLens() {
        System.out.println("讓鏡頭對焦");
    }

    @Override
    protected void pressShutter() {
        System.out.println("按下快門");
    }

    @Override
    protected boolean isTimer() {
        return this.isTimer;
    }

    public void useTimer(boolean isTimer) {
        this.isTimer = isTimer;
    }
}
```
#### AdeptPhotographerExtend
```java
public class AdeptPhotographerExtend extends PhotographerExtend {
    private boolean isTimer = true;

    @Override
    protected void adjustAperture() {
        System.out.println("調整光圈為：f/22");
    }

    @Override
    protected void setISO() {
        System.out.println("設定感光度為：ISO-3200");
    }

    @Override
    protected void setShutterSpeed() {
        System.out.println("設定快門時間為：1/125s");
    }

    @Override
    protected void setTimer() {
        System.out.println("設定倒數計時為：10 秒");
    }

    @Override
    protected void focusLens() {
        System.out.println("讓鏡頭對焦");
    }

    @Override
    protected void pressShutter() {
        System.out.println("按下快門");
    }

    @Override
    protected boolean isTimer() {
        return this.isTimer;
    }

    public void useTimer(boolean isTimer) {
        this.isTimer = isTimer;
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        System.out.println("----------模板方法模式----------");
        doTemplateMethod();
        System.out.println("----------模板方法模式擴展----------");
        doExtended();
    }

    private static void doTemplateMethod() {
        System.out.println("-----專業攝影師-----");
        Photographer adeptPhotographer = new AdeptPhotographer();
        adeptPhotographer.snap();

        System.out.println("-----新手攝影師-----");
        Photographer amateurPhotographer = new AmateurPhotographer();
        amateurPhotographer.snap();
    }

    private static void doExtended() {
        System.out.println("-----專業攝影師-----");
        AdeptPhotographerExtend adeptPhotographer = new AdeptPhotographerExtend();
        // 專業攝影師不需要倒數
        adeptPhotographer.useTimer(false);
        adeptPhotographer.snap();

        System.out.println("-----新手攝影師-----");
        AmateurPhotographerExtend amateurPhotographer = new AmateurPhotographerExtend();
        // 新手攝影師需要倒數
        amateurPhotographer.useTimer(true);
        amateurPhotographer.snap();
    }
}
```
#### 執行結果
>     ----------模板方法模式----------
>     -----專業攝影師-----
>     調整光圈為：f/22
>     設定感光度為：ISO-3200
>     設定快門時間為：1/125s
>     設定倒數計時為：10 秒
>     讓鏡頭對焦
>     按下快門
>     -----新手攝影師-----
>     調整光圈為：f/2.8
>     設定感光度為：ISO-200
>     設定快門時間為：1/8s
>     設定倒數計時為：3 秒
>     讓鏡頭對焦
>     按下快門
>     ----------模板方法模式擴展----------
>     -----專業攝影師-----
>     調整光圈為：f/22
>     設定感光度為：ISO-3200
>     設定快門時間為：1/125s
>     讓鏡頭對焦
>     按下快門
>     -----新手攝影師-----
>     調整光圈為：f/2.8
>     設定感光度為：ISO-200
>     設定快門時間為：1/8s
>     設定倒數計時為：3 秒
>     讓鏡頭對焦
>     按下快門

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Template_method_pattern
