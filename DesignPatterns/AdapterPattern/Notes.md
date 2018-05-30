# 介面卡模式 - Adapter Pattern
## 定義
> - “Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.”
> - “Convert the interface of a class into another interface clients expect.”
> - 介面卡讓原本因介面不同導致無法一起工作的類別可以一起工作。
> - 轉換一個類別的介面成另一個使用者所期望的介面。

## 參與者
> + 目標角色 - Target
>     * 定義把其他類別轉換成何種介面，就是指使用者所期望的介面
> + 源角色 - Adaptee
>     * 被轉換成目標角色 Target 的那個物件
> + 介面卡角色 - Adapter
>     * 透過繼承或類別關聯，把源角色 Adaptee 轉換成目標角色 Target 的角色

## 優點
> 1. 讓無關的類別可以一起執行
> 2. 增加類別的透明度
> 3. 提升類別的重用率
> 4. 靈活性佳
> 5. 降低變更風險
>
> 在**介面卡模式**中，可以讓兩個不相關的類別透過介面卡來一起工作，並且存取的是 Target 角色，但具體的實作則交給 Adaptee 角色，提高了 Adaptee 角色的使用率，並且高層模組間也無須關心如何實作，提升了透明度，在棄用介面卡之後，也只需要刪除介面卡就好，對於整體系統沒有其他影響，降低了變更的風險且提高了靈活性。

## 使用時機
> 1. 需要修改已上線的介面時
> 2. 需要擴展系統時
> 3. 介面不相容時
> 4. 源角色存在缺陷需要補救時

## 實踐
> 在使用**介面卡模式**之前，專案必需要先遵守[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")與[里氏替換原則（LSP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "里氏替換原則（LSP）")，否則使用**介面卡模式**之後，會對原有的系統帶來巨大的變更。

## 範例
**介面卡模式**分為類別介面卡與物件介面卡兩種，類別介面卡是指類別使用繼承來轉換源角色；物件介面卡則是透過類別之間的關聯來轉換源角色成目標角色，兩者間具有明顯的差異。

現在主流的螢幕傳輸線有很多種，例如：[DisplayPort](https://en.wikipedia.org/wiki/DisplayPort "DisplayPort")、[HDMI](https://en.wikipedia.org/wiki/HDMI "HDMI")以及[D-Sub](https://en.wikipedia.org/wiki/D-subminiature "D-Sub")...等眾多的傳輸格式，每個格式之間都有差異，D-Sub只能傳輸視訊，如果需要聲音，還要外接音源線；HDMI除了視訊還能傳輸音訊；DisplayPort甚至能夠傳輸USB格式。

但是如果在某些情況下，像是螢幕與顯示卡的格式不匹配，這個時候就需要用到轉接器了，透過轉接器來轉接傳輸的格式，可以在不更換螢幕或顯示卡的情況下，繼續使用，以下便以這個情形來舉**介面卡模式**的使用例子。

首先需要確定我們的目標角色 Target，這個角色就是我們希望轉換成的角色，這裡以HDMI格式來當 Target，接著確定源角色 Adaptee，這個角色將被使用來轉換成 Target，當目標與來源角色都確定完成，開始建立實際的專案。

在專案中，先為目標角色定義一個介面`IHDMI`，並且建立實作介面的類別`HDMI`，接著定義源角色的`IDSub`、`ISoundSourceLine`與`IDisplayPort`的介面，以及實作介面的類別`DSub`、`SoundSourceLine`與`DisplayPort`，最後是介面卡的建造，介面卡有兩種，一種是物件介面卡`Adapter`，裡面透過`DSub`、`SoundSourceLine`的關聯來建立；另一種是類別介面卡，使用繼承`DisplayPort`類別來建造介面卡，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/AdapterPattern/Picture/AdapterPatternPicture.png)

#### IHDMI
```java
public interface IHDMI {
    void videoSignal();
    void voiceSignal();
}
```
#### HDMI
```java
public class HDMI implements IHDMI {
    @Override
    public void videoSignal() {
        System.out.println("傳輸視訊畫面...");
    }

    @Override
    public void voiceSignal() {
        System.out.println("傳輸音訊效果...");
    }
}
```
#### IDSub
```java
public interface IDSub {
    // DSub 只能傳輸視訊
    void videoSignal();
}
```
#### DSub
```java
public class DSub implements IDSub {
    @Override
    public void videoSignal() {
        System.out.println("傳輸視訊畫面...");
    }
}
```
#### ISoundSourceLine
```java
public interface ISoundSourceLine {
    void voiceSignal();
}
```
#### SoundSourceLine
```java
public class SoundSourceLine implements ISoundSourceLine{
    @Override
    public void voiceSignal() {
        System.out.println("傳輸音訊效果...");
    }
}
```
#### IDisplayPort
```java
public interface IDisplayPort {
    void transportVideoSignal();
    void transportVoiceSignal();
    void transportUSB();
}
```
#### DisplayPort
```java
public class DisplayPort implements IDisplayPort {
    @Override
    public void transportVideoSignal() {
        System.out.println("傳輸視訊畫面...");
    }

    @Override
    public void transportVoiceSignal() {
        System.out.println("傳輸音訊效果...");
    }

    @Override
    public void transportUSB() {
        System.out.println("傳輸USB資料...");
    }
}
```
#### Adapter
```java
public class Adapter implements IHDMI {
    private IDSub dSub;
    private ISoundSourceLine soundSourceLine;

    public Adapter(IDSub dSub, ISoundSourceLine soundSourceLine) {
        this.dSub = dSub;
        this.soundSourceLine = soundSourceLine;
    }

    @Override
    public void videoSignal() {
        System.out.print("介面卡進行轉換...");
        dSub.videoSignal();
    }

    @Override
    public void voiceSignal() {
        System.out.print("介面卡進行轉換...");
        soundSourceLine.voiceSignal();
    }
}
```
#### DisplayPortAdapter
```java
public class DisplayPortAdapter extends DisplayPort implements IHDMI {
    @Override
    public void videoSignal() {
        System.out.print("DisplayPort 介面卡進行轉換...");
        super.transportVideoSignal();
    }

    @Override
    public void voiceSignal() {
        System.out.print("DisplayPort 介面卡進行轉換...");
        super.transportVoiceSignal();
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // HDMI 可以傳輸畫面與聲音
        IHDMI hdmi = new HDMI();
        hdmi.videoSignal();
        hdmi.voiceSignal();

        // 使用一個介面卡轉換 DSub 與音源線為 HDMI
        IHDMI newHdmi = new Adapter(new DSub(), new SoundSourceLine());
        newHdmi.videoSignal();
        newHdmi.voiceSignal();

        // 使用 DisplayPort 介面卡傳換為 HDMI
        IHDMI newHdmi2 = new DisplayPortAdapter();
        newHdmi2.videoSignal();
        newHdmi2.voiceSignal();
    }
}
```
#### 執行結果
>     傳輸視訊畫面...
>     傳輸音訊效果...
>     介面卡進行轉換...傳輸視訊畫面...
>     介面卡進行轉換...傳輸音訊效果...
>     DisplayPort 介面卡進行轉換...傳輸視訊畫面...
>     DisplayPort 介面卡進行轉換...傳輸音訊效果...

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Adapter_pattern
 - https://en.wikipedia.org/wiki/DisplayPort
 - https://en.wikipedia.org/wiki/HDMI
 - https://en.wikipedia.org/wiki/D-subminiature