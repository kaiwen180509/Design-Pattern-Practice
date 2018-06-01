# 備忘錄模式 - Memento Pattern
## 定義
> - “Without violating encapsulation, capture and externalize an object's internal state so that the object can be returned to this state later.”
> - 在不破壞封裝的情況下，獲取一個物件的內部狀態，並且在外部保存，讓該物件可以在之後恢復到先前保存的狀態。

## 參與者
> + 發起人角色 - Originator
>     * 定義需要備份的狀態，並紀錄目前的狀態，創建與恢復 Memento 的資料
> + 備忘錄角色 - Memento
>     * 負責保存 Originator 的內部狀態，以及提供 Originator 所需要的內部狀態
> + 備忘錄管理員角色 - Caretaker
>     * 對 Memento 進行管理、保存與提供

## 使用時機
> 1. 需要保存與恢復資料的情況
> 2. 需要監視控制的情況
> 3. 與資料庫連接的事物管理

## 注意事項
> 1. 備忘錄的生命週期
> 2. 備忘錄的效能問題
> 
> 當備忘錄被創建時，需要妥善管理其生命週期，當不使用時就立刻刪除引用，避免消耗資源，並且頻繁的建造備忘錄將會出現巨大的資源耗費，降低系統效能。

## 實踐
> 1. 一般備忘錄模式
> 2. Clone模式
> 3. 多備份模式
> 4. 安全封裝模式

## 範例
有一種遊戲的分類是[角色扮演遊戲（RPG）](https://en.wikipedia.org/wiki/Role-playing_game "角色扮演遊戲（RPG）")，玩家藉由扮演遊戲角色進行冒險，提升角色的等級或能力，並且獲得獎勵如：遊戲道具、遊戲金錢等物品。通常這類型的遊戲會有一個功能，叫做遊戲存檔，可以儲存某一個時間的遊戲狀態，並且在需要的時候恢復之前所保存遊戲的狀態，這個情形恰好符合**備忘錄模式**的功能。

以一個簡單的 RPG 遊戲來舉例，玩家負責操控遊戲角色，並且提升角色的等級，並且遊戲提供了一個簡單的存檔功能，以下詳細說明與實現程式。

### 一般備忘錄模式：
一開始先建立好發起人角色的類別`GameCharacter`，裡面只有一個狀態就是角色等級`state`，接著建造備忘錄角色的類別`Record`，這個類別負責記錄發起人角色的狀態，也就是等級`state`，最後實作備忘錄管理者角色的類別`RecordManager`，負責管理`Record`。

以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/MementoPattern/Picture/MementoPatternPicture.png)

#### GameCharacter
```java
public class GameCharacter {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    // 建立備份
    public Record createRecord() {
        return new Record(this.state);
    }

    // 恢復備份
    public void restoreRecord(Record record) {
        this.setState(record.getState());
    }
}
```
#### Record
```java
public class Record {
    private String state;

    public Record(String state) {
        this.state = state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
```
#### RecordManager
```java
public class RecordManager {
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
```
### Clone模式：
藉由與[原型模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/PrototypePattern/Notes.md "原型模式")相似的方法，使用複製的方法來複製發起人物件，保存發起人物件的狀態，藉此達成備忘錄的功能，並且藉由此方法，會保存發起人角色完整、全部的狀態。

依舊是 RPG 遊戲的例子，不過現在遊戲角色多了一個狀態，金錢的狀態`stateMoney`，透過讓遊戲角色的類別`CloneGameCharacter`實作 [JDK](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html "JDK") 提供的`Cloneable`介面，以此讓發起人能夠被複製，接著讓發起人自己保存自己的備份，連備忘錄角色與備忘錄管理者角色都不需要，只要讓遊戲角色自己使用備份還原狀態就可以了，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/MementoPattern/Picture/MementoPatternClonePicture.png)

#### CloneGameCharacter
```java
public class CloneGameCharacter implements Cloneable {
    private CloneGameCharacter backup;
    private String stateLevel;
    private String stateMoney;

    // 設定等級狀態
    public void setStateLevel(String stateLevel) {
        this.stateLevel = stateLevel;
    }

    // 設定金錢狀態
    public void setStateMoney(String stateMoney) {
        this.stateMoney = stateMoney;
    }

    // 取得等級狀態
    public String getStateLevel() {
        return stateLevel;
    }

    // 取得金錢狀態
    public String getStateMoney() {
        return stateMoney;
    }

    // 取得全部的狀態
    public String getAllState() {
        return "等級：" + stateLevel + " 金錢：" + stateMoney;
    }

    // 建立備份
    public void createRecord() {
        this.backup = this.clone();
    }

    // 恢復備份
    public void restoreRecord() {
        this.setStateLevel(this.backup.getStateLevel());
        this.setStateMoney(this.backup.getStateMoney());
    }

    // 複製自己
    @Override
    protected CloneGameCharacter clone() {
        try {
            return (CloneGameCharacter) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```
### 多備份模式：
一般的**備忘錄模式**只有一個備份，無法滿足多個備份的需求，因此多備份模式是擴展一般的模式來設計多個備份的模式，藉由檢查點（Check Point）來辨識哪一個備份。

使用一般模式的例子來擴展，擴展備忘錄管理者角色的類別為`ManyBackupRecordManager`，裡面把紀錄的保存容器擴展為`HashMap`，以此保存多個紀錄，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/MementoPattern/Picture/MementoPatternManyBackupPicture.png)

#### ManyBackupRecordManager
```java
public class ManyBackupRecordManager {
    private HashMap<String, Record> recordHashMap = new HashMap<>();

    // 取得紀錄
    public Record getRecord(String index) {
        return this.recordHashMap.get(index);
    }

    // 保存紀錄
    public void setRecord(String index, Record record) {
        this.recordHashMap.put(index, record);
    }
}
```
### 安全封裝模式：
備份應該是一個安全、完整且不能被修改的資料，必須保證資料的純潔，為了達到這個目的，備忘錄角色應該只有發起人角色能夠讀，為了達成這個目的，將備忘錄角色改為發起人角色的[內部類別](https://en.wikipedia.org/wiki/Inner_class "內部類別")，並且實作一個空介面，在需要產生關係時使用空介面來建立關聯。

建立一個備忘錄角色的空介面`IRecord`，提供關聯的存取，接著在發起人角色`SafeGameCharacter`類別內建立內部類別的備忘錄角色`SafeRecord`類別，並且修改備忘錄管理員角色`SafeRecordManager`的關聯為`IRecord`介面，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/MementoPattern/Picture/MementoPatternSafePicture.png)

#### IRecord
```java
public interface IRecord {
}
```
#### SafeGameCharacter
```java
public class SafeGameCharacter {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    // 建立備份
    public SafeRecord createRecord() {
        return new SafeRecord(this.state);
    }

    // 恢復備份
    public void restoreRecord(IRecord record) {
        this.setState(((SafeRecord) record).getState());
    }

    // 建立紀錄的內部類別
    private class SafeRecord implements IRecord {
        private String state;

        public SafeRecord(String state) {
            this.state = state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}
```
#### SafeRecordManager
```java
public class SafeRecordManager {
    private IRecord record;

    public IRecord getRecord() {
        return record;
    }

    public void setRecord(IRecord record) {
        this.record = record;
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        System.out.println("----------一般備忘錄模式----------");
        doNormalMemento();
        System.out.println("----------Clone模式----------");
        doCloneMemento();
        System.out.println("----------多備份模式----------");
        doManyBackupMemento();
        System.out.println("----------安全封裝模式----------");
        doSafeMemento();
    }

    private static void doNormalMemento() {
        // 定義一個記錄管理員
        RecordManager recordManager = new RecordManager();
        // 有一個遊戲角色，現在10等
        System.out.println("-----最初的狀態-----");
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setState("Lv.10");
        System.out.println("遊戲角色等級：" + gameCharacter.getState());
        // 保存遊戲角色紀錄
        recordManager.setRecord(gameCharacter.createRecord());

        // 遊戲角色等級下降
        System.out.println("-----等級下降-----");
        gameCharacter.setState("Lv.9");
        System.out.println("遊戲角色等級：" + gameCharacter.getState());

        // 決定恢復紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord(recordManager.getRecord());
        System.out.println("遊戲角色等級：" + gameCharacter.getState());
    }

    private static void doCloneMemento() {
        // 有一個遊戲角色，現在87等，且身上有81000元
        System.out.println("-----最初的狀態-----");
        CloneGameCharacter gameCharacter = new CloneGameCharacter();
        gameCharacter.setStateLevel("Lv.87");
        gameCharacter.setStateMoney("81000");
        System.out.println("狀態 -> " + gameCharacter.getAllState());

        // 保存遊戲角色紀錄
        gameCharacter.createRecord();

        // 遊戲角色遇到平偉怪物，慘賠錢
        System.out.println("-----金錢下降-----");
        gameCharacter.setStateMoney("0");
        System.out.println("狀態 -> " + gameCharacter.getAllState());

        // 決定恢復紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord();
        System.out.println("狀態 -> " + gameCharacter.getAllState());
    }

    private static void doManyBackupMemento() {
        // 定義一個記錄管理員
        ManyBackupRecordManager recordManager = new ManyBackupRecordManager();
        // 有一個遊戲角色，現在75等
        System.out.println("-----最初的狀態-----");
        GameCharacter gameCharacter = new GameCharacter();
        gameCharacter.setState("Lv.75");
        System.out.println("狀態 -> " + gameCharacter.getState());
        // 保存目前遊戲角色狀態為1號紀錄
        recordManager.setRecord("1", gameCharacter.createRecord());

        // 遊戲角色等級升級
        System.out.println("-----等級上升-----");
        gameCharacter.setState("Lv.76");
        System.out.println("狀態 -> " + gameCharacter.getState());
        // 保存目前遊戲角色狀態為2號紀錄
        recordManager.setRecord("2", gameCharacter.createRecord());

        // 遊戲角色等級下降
        System.out.println("-----等級下降-----");
        gameCharacter.setState("Lv.75");
        System.out.println("狀態 -> " + gameCharacter.getState());

        // 決定恢復紀錄 -> 選擇2號紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord(recordManager.getRecord("2"));
        System.out.println("狀態 -> " + gameCharacter.getState());
    }

    private static void doSafeMemento() {
        // 定義一個記錄管理員
        SafeRecordManager recordManager = new SafeRecordManager();
        // 有一個遊戲角色，現在33等
        System.out.println("-----最初的狀態-----");
        SafeGameCharacter gameCharacter = new SafeGameCharacter();
        gameCharacter.setState("Lv.33");
        System.out.println("角色狀態 -> " + gameCharacter.getState());
        // 保存遊戲角色紀錄
        recordManager.setRecord(gameCharacter.createRecord());

        // 遊戲角色等級下降
        System.out.println("-----等級下降-----");
        gameCharacter.setState("Lv.32");
        System.out.println("角色狀態 -> " + gameCharacter.getState());

        // 決定恢復紀錄
        System.out.println("-----恢復紀錄-----");
        gameCharacter.restoreRecord(recordManager.getRecord());
        System.out.println("角色狀態 -> " + gameCharacter.getState());
    }
}
```
#### 執行結果
>     ----------一般備忘錄模式----------
>     -----最初的狀態-----
>     遊戲角色等級：Lv.10
>     -----等級下降-----
>     遊戲角色等級：Lv.9
>     -----恢復紀錄-----
>     遊戲角色等級：Lv.10
>     ----------Clone模式----------
>     -----最初的狀態-----
>     狀態 -> 等級：Lv.87 金錢：81000
>     -----金錢下降-----
>     狀態 -> 等級：Lv.87 金錢：0
>     -----恢復紀錄-----
>     狀態 -> 等級：Lv.87 金錢：81000
>     ----------多備份模式----------
>     -----最初的狀態-----
>     狀態 -> Lv.75
>     -----等級上升-----
>     狀態 -> Lv.76
>     -----等級下降-----
>     狀態 -> Lv.75
>     -----恢復紀錄-----
>     狀態 -> Lv.76
>     ----------安全封裝模式----------
>     -----最初的狀態-----
>     角色狀態 -> Lv.33
>     -----等級下降-----
>     角色狀態 -> Lv.32
>     -----恢復紀錄-----
>     角色狀態 -> Lv.33

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Memento_pattern
 - https://en.wikipedia.org/wiki/Role-playing_game
 - https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/InvocationHandler.html
 - https://en.wikipedia.org/wiki/Inner_class