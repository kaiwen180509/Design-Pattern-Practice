# 單例模式 - Singleton Pattern
## 定義
> - “Ensure a class has only one instance,and provide a global point of access to it.”
> - 確保某一個類別只有一個實例，並且向整個系統提供自己的實例。

## 優點
> 1. 減少記憶體開支
> 2. 減少效能開銷
> 3. 避免佔用資源
> 4. 優化共享資源的訪問
> 
> 由於單例模式在應用程式中只產生唯一的一個實例，減少對於系統資源的占用，也避免繁複產生實例所造成的記憶體與效能開銷，並且優化了共享資源的存取。

## 缺點
> 1. 沒有介面擴展困難
> 2. 測試不易
> 3. 與[單一職責原則（SRP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "單一職責原則（SRP）")衝突
> 
> 使用單例模式後，在並行開發中進行測試時，須先完成單例模式的類別才能測試，導致測試不易，其次單例模式沒有介面，如果需要擴展則需要修改類別的程式，變更風險提升，而一個類別應該只做一個職責，但是單例模式明顯違反[單一職責原則（SRP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "單一職責原則（SRP）")。

## 使用時機
> 1. 需要產生唯一序號的環境
> 2. 建立一個實例所需要消耗的資源過多時
> 3. 在專案中需要一個共享資源的存取時
> 4. 定義大量的靜態方法與變數的環境

## 實踐
> 1. 懶漢方式（實例在第一次被使用時構建）
> 2. 餓漢方式（實例在[類別裝載](https://en.wikipedia.org/wiki/Java_Classloader "類別裝載")時構建）
> 
> 餓漢方式是一般的單例模式用法；而懶漢方式也被稱為惰性初始化，是在多執行序緒的情況下使用，為了減少並發系統中競爭和同步的開銷，使用雙重檢查鎖來優化，使用雙重檢查鎖定模式需要對單例聲明為 [volatile](https://en.wikipedia.org/wiki/Volatile_(computer_programming) "volatile")。

## 範例
舉一個[《 韓非子．難勢》](https://zh.wikisource.org/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E5%8B%A2 "《《 韓非子．難勢》》")提到的人物，在春秋戰國時期，有一個晉人叫王良，很會駕馭馬車，據說能夠一日千里而名傳天下。

假設有一天齊宣公姜積想要送一封加急的信給楚惠王熊章，於是便決定讓王良來送信。

由於天下只有一個王良，因此這個王良就是一個很好的單例模式的範本，首先建一個單例的類別王良`WangLiang`，有一個送信`send()`的方法，並且建立一個信件的類別`Letter`與介面`ILetter`，給信件實作寄件人`setSender()`、收件人`setRecipient()`與目的地`setRecipientAddress()`，接著讓王良去送這封信。

實際的類別圖與範例如下：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/SingletonPattern/Picture/Singleton.png)

#### ILetter
```java
public interface ILetter {
    void setSender(String senderName);
    void setRecipient(String recipientName);
    void setRecipientAddress(String address);
    String getSender();
    String getRecipient();
    String getRecipientAddress();
}
```
#### Letter
```java
public class Letter implements ILetter {
    private String recipientName;
    private String senderName;
    private String address;

    @Override
    public void setSender(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public void setRecipient(String recipientName) {
        this.recipientName = recipientName;
    }

    @Override
    public void setRecipientAddress(String address) {
        this.address = address;
    }

    @Override
    public String getSender() {
        return this.senderName;
    }

    @Override
    public String getRecipient() {
        return this.recipientName;
    }

    @Override
    public String getRecipientAddress() {
        return this.address;
    }
```
#### WangLiangHungry - 餓漢方式
```java
public class WangLiangHungry {
    private final static WangLiangHungry INSTANCE = new WangLiangHungry();

    private WangLiangHungry() {
    }

    public static WangLiangHungry getInstance() {
        return INSTANCE;
    }

    public static void send(ILetter letter) {
        System.out.println("自 " + letter.getSender() + " 送到 " + letter.getRecipientAddress() + " 的 " + letter.getRecipient() + " 手上");
    }
}
```
#### WangLiang - 懶漢方式
```java
public class WangLiang {
    private static volatile WangLiang INSTANCE = null;

    private WangLiang() {
    }

    public static WangLiang getInstance() {
        if (INSTANCE == null) {
            // 雙重檢查鎖
            synchronized (WangLiang.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WangLiang();
                }
            }
        }
        return INSTANCE;
    }

    public static void send(ILetter letter) {
        System.out.println("自 " + letter.getSender() + " 送到 " + letter.getRecipientAddress() + " 的 " + letter.getRecipient() + " 手上");
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 建立一封信
        ILetter letter = new Letter();
        letter.setSender("齊宣公姜積");
        letter.setRecipient("楚惠王熊章");
        letter.setRecipientAddress("楚國王宮");

        System.out.println("----------送信-懶漢方式----------");
        // 找到王良，把信給他去送
        WangLiang wangLiang = WangLiang.getInstance();
        wangLiang.send(letter);

        System.out.println("----------送信-餓漢方式----------");
        // 找到王良，把信給他去送
        WangLiangHungry wangLiang2 = WangLiangHungry.getInstance();
        wangLiang2.send(letter);
    }
}
```
#### 執行結果
>     ----------送信-懶漢方式----------
>     自 齊宣公姜積 送到 楚國王宮 的 楚惠王熊章 手上
>     ----------送信-餓漢方式----------
>     自 齊宣公姜積 送到 楚國王宮 的 楚惠王熊章 手上

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Singleton_pattern
 - https://en.wikipedia.org/wiki/Volatile_(computer_programming)
 - https://zh.wikisource.org/wiki/%E9%9F%93%E9%9D%9E%E5%AD%90/%E9%9B%A3%E5%8B%A2
 - https://en.wikipedia.org/wiki/Java_Classloader
