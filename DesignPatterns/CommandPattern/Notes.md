# 命令模式 - Command Pattern
## 定義
> - “Encapsulate a request as an object, thereby letting you parametrize clients with different requests, queue or log requests, and support undoable operations.”
> - 將一個要求封裝為物件，從而讓客戶的不同要求參數化，把要求放進佇列或紀錄，並且提供撤銷的操作。

## 優點
> 1. 降低模組間的耦合
> 2. 可擴展性佳
> 3. 可結合其他[設計模式](https://github.com/kaiwen180509/Design-Pattern-Practice "設計模式")
>
> 命令模式中，接收者與呼叫者之間是沒有依賴關係的，呼叫者只需要呼叫命令，不必關心如何完成命令，接收者等待命令，降低了模組間的耦合，高層模組間沒有耦合，也因此命令角色是很容易被擴展的；可以結合[模板方法模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/TemplateMethodPattern/Notes.md "模板方法模式")，減少命令膨脹的問題，也可以結合[責任鏈模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/ChainOfResponsibilityPattern/Notes.md "責任鏈模式")，實作命令族的解析。

## 缺點
> 1. 命令膨脹
>
> 有 N 個命令就會有 N 個命令類別，多個命令將導致命令類別遽增。

## 使用時機
> 只要是下命令的情況下，都可以使用命令模式。

## 參與者
> + 接收者角色 - Receiver
>     * 負責執行命令的角色
> + 命令角色 - Command
>     * 所有需要執行的命令，等待 Invoker 的呼叫
> + 呼叫者角色 - Invoker
>     * 接收命令並執行 Command 的角色

## 實踐
> 一般的命令模式中，Receiver 角色還是會給高層模組或使用者（Client）產生依賴，但是也可以藉由在 Command 角色的命令中定義 Receiver 角色來避免依賴。

## 範例
以一個主人在命令機器人為例子，主人`Host`的類別就是呼叫者角色，機器人`Reboot`的類別則是接收者角色，命令角色則是所有實作`Command`介面的類別，`Command`介面中定義了兩個方法，分別是執行`execute()`與撤銷`undo()`方法，主人只需要透過命令來讓機器人行動，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/CommandPattern/Picture/CommandPatternPicture.png)

#### Host
```java
public class Host {
    private ArrayList<Command> commandList = new ArrayList<>();

    // 主人下的命令
    public void action(Command command) {
        this.commandList.add(command);
        command.execute();
    }

    // 主人撤銷上一個命令
    public void cancel() {
        int commandCount = this.commandList.size() - 1;
        // 檢查是否有命令
        if (commandCount > 0) {
            Command command = this.commandList.get(commandCount);
            command.undo();
        } else {
            System.out.println("請先下命令");
        }
    }
}
```
#### Reboot
```java
public class Reboot {
    public void dance() {
        System.out.println("機器人跳舞");
    }

    public void unDance() {
        System.out.println("機器人停止跳舞");
    }

    public void sing() {
        System.out.println("機器人唱歌");
    }

    public void unSing() {
        System.out.println("機器人停止唱歌");
    }

    public void sweep() {
        System.out.println("機器人打掃");
    }

    public void unSweep() {
        System.out.println("機器人停止打掃");
    }
}
```
#### Command
```java
public interface Command {
    void execute();
    void undo();
}
```
#### DanceCommand
```java
public class DanceCommand implements Command {
    private Reboot reboot;

    public DanceCommand(Reboot reboot) {
        this.reboot = reboot;
    }

    @Override
    public void execute() {
        System.out.print("執行：");
        reboot.dance();
    }

    @Override
    public void undo() {
        System.out.print("撤銷：");
        reboot.unDance();
    }
}
```
#### SingCommand
```java
public class SingCommand implements Command {
    private Reboot reboot;

    public SingCommand(Reboot reboot) {
        this.reboot = reboot;
    }

    @Override
    public void execute() {
        System.out.print("執行：");
        reboot.sing();
    }

    @Override
    public void undo() {
        System.out.print("撤銷：");
        reboot.unSing();
    }
}
```
#### SweepCommand
```java
public class SweepCommand implements Command {
    private Reboot reboot;

    public SweepCommand(Reboot reboot) {
        this.reboot = reboot;
    }

    @Override
    public void execute() {
        System.out.print("執行：");
        reboot.sweep();
    }

    @Override
    public void undo() {
        System.out.print("撤銷：");
        reboot.unSweep();
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 有一個主人跟機器人
        Host host = new Host();
        Reboot reboot = new Reboot();

        // 主人要機器人唱歌跳舞
        host.action(new SingCommand(reboot));
        host.action(new DanceCommand(reboot));

        // 主人要機器人掃地
        host.action(new SweepCommand(reboot));
        // 主人取消命令
        host.cancel();
    }
}
```
#### 執行結果
>     執行：機器人唱歌
>     執行：機器人跳舞
>     執行：機器人打掃
>     撤銷：機器人停止打掃

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Command_pattern
