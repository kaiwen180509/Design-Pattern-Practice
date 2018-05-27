# 責任鏈模式 - Chain of Responsibility Pattern
## 定義
> - “Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request.”
> - “Chain the receiving objects and pass the request along the chain until an object handles it.”
> - 讓多個物件都有機會處理請求，避免請求者與接收者之間的耦合關係。
> - 將要求的接收者串成一條責任鏈，並沿著責任鏈傳遞請求，直到請求被接收者處理。

## 優點
> 1. 靈活度高
> 2. 減少耦合關係
> 3. 擴展容易
>
> 在責任鏈模式中，由於請求者與接收者的耦合被分開，減少了高層模組之間的耦合，也因此提升了靈活度；而需要擴展時，只要增加一個接收者進入責任鏈就可以完成。

## 缺點
> 1. 效能問題
> 2. 偵錯困難
>
> 在處理請求上，由於是從責任鏈的頭走到尾，在較長的責任鏈中會導致效能較差，也因為責任鏈長，接收者的環節較多，邏輯會比較複雜，偵錯也因此變難。

## 參與者
> + 抽象處理者 - Handler
>     * 負責定義請求的處理方法、責任鏈的編排方法、具體處理者必須實作的方法
> + 具體處理者 - ConcreteHandler
>     * 定義處理者 Handler 的處理等級以及實作處理請求的方法
> + 請求者 - Request
>     * 負責定義自身的請求等級以及發出請求

## 實踐
> 1. 結合[模板方法模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/TemplateMethodPattern/Notes.md "模板方法模式")

## 範例
在大學裡面，學生的課表一般是自己安排的，這個事情叫做選課，通常會在開學前兩周完成，選課的時候，通常會分成兩種情況，一種是電腦選課，就是學生在電腦上使用選課系統進行課程的安排，選擇要修的課程，系統會自動安排課表；另外一種則是手動選課，一般是系統無法進行安排的時候使用人工來處理這問題。

今天有一個學生，要選一門課程，需要手動選課，但是不知道誰能幫他處理這個問題，於是只好從最高的級別開始一層一層的問了，首先問教務處，教務處的人員告知要去找課程指導組（一般簡稱為課指組），到了課指組，這裡的人員又告知學生要去找系辦公室處理，最後學生到了系辦公室終於完成了課程的安排，恰好這個例子完全符合責任鏈模式的處理。

首先要有一個要求者的學生`Student`類別，負責提出要求；以及一串處理者，先建立出抽象的處理者`Handler`，裡面定義了責任鏈的編排方法`setNextHandler()`、每個處理必須實作的方法`getEventLevel()`、`Response()`以及對於請求處理`handle()`，接著建立實作`Handler`的類別教務處`AcademicAffairsOfficeHandler`、課指組`CourseGuideGroupHandler`以及系辦公室`DepartmentOfficeHandler`，然後定義一個`EventLevel`類別，負責定義要求的各個層級，最後把所有的處理者封裝成`EventHandler`，方便使用者呼叫。

以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/ChainOfResponsibilityPattern/Picture/ChainOfResponsibilityPicture.png)

#### Student
```java
public class Student {
    private String name;
    private int eventLevel = 0;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEvent(int eventLevel) {
        this.eventLevel = eventLevel;
    }

    public int getEventLevel() {
        return this.eventLevel;
    }
}
```
#### Handler
```java
public abstract class Handler {
    private Handler nextHandler = null;

    // 每個處理者的層級
    protected abstract int getEventLevel();

    // 每個處理者的回應
    protected abstract void Response(Student student);

    // 設定下一個處理者
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 實際的處理
    public final void handle(Student student) {
        // 判斷是否是自己能處理的事情
        if (student.getEventLevel() == this.getEventLevel()) {
            this.Response(student);
        } else {
            // 還有下一個處理者時，交給下一個處理者去處理
            if (this.nextHandler != null) {
                this.nextHandler.handle(student);
            } else {
                // 沒有處理者了
                System.out.println(student.getName() + " 的這個問題無法處理");
            }
        }
    }
}
```
#### AcademicAffairsOfficeHandler
```java
public class AcademicAffairsOfficeHandler extends Handler {
    @Override
    protected int getEventLevel() {
        return EventLevel.LEAVE_SCHOOL;
    }

    @Override
    protected void Response(Student student) {
        System.out.println("-----教務處的回覆-----");
        System.out.println(student.getName() + " 的申請通過");
    }
}
```
#### CourseGuideGroupHandler
```java
public class CourseGuideGroupHandler extends Handler {
    @Override
    protected int getEventLevel() {
        return EventLevel.FINANCIAL_AFFAIRS;
    }

    @Override
    protected void Response(Student student) {
        System.out.println("-----課指組的回覆-----");
        System.out.println(student.getName() + " 的申請通過");
    }
}
```
#### DepartmentOfficeHandler
```java
public class DepartmentOfficeHandler extends Handler {
    @Override
    protected int getEventLevel() {
        return EventLevel.COURSE_SELECTION;
    }

    @Override
    protected void Response(Student student) {
        System.out.println("-----系辦公室的回覆-----");
        System.out.println(student.getName() + " 的安排完成");
    }
}
```
#### EventHandler
```java
public class EventHandler {
    // 教務處處理者
    private Handler academicAffairsOfficeHandler = new AcademicAffairsOfficeHandler();
    // 課指組處理者
    private Handler courseGuideGroupHandler = new CourseGuideGroupHandler();
    // 系辦公室處理者
    private Handler departmentOfficeHandler = new DepartmentOfficeHandler();

    // 對責任鏈進行封裝
    public EventHandler() {
        createChain();
    }

    // 建立責任鏈
    private void createChain() {
        // 責任鏈的順序安排
        this.academicAffairsOfficeHandler.setNextHandler(this.courseGuideGroupHandler);
        this.courseGuideGroupHandler.setNextHandler(this.departmentOfficeHandler);
    }

    // 處理要求
    public void handle(Student student) {
        this.academicAffairsOfficeHandler.handle(student);
    }
}
```
#### EventLevel
```java
public class EventLevel {
    // 休學的申請 -> 教務處
    public static final int LEAVE_SCHOOL = 1;
    // 經費的申請 -> 課指組
    public static final int FINANCIAL_AFFAIRS = 2;
    // 課程的安排 -> 系辦公室
    public static final int COURSE_SELECTION = 3;
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 定義各處室的責任鏈
        EventHandler handler = new EventHandler();

        // 有一個叫 Jack 的學生要休學
        Student jack = new Student("Jack");
        jack.setEvent(EventLevel.LEAVE_SCHOOL);
        handler.handle(jack);

        // 有一個叫 Kata 的學生要申請獎學金
        Student kata = new Student("Kata");
        kata.setEvent(EventLevel.FINANCIAL_AFFAIRS);
        handler.handle(kata);

        // 有一個叫 Burton 的學生要選課
        Student burton = new Student("Burton");
        burton.setEvent(EventLevel.COURSE_SELECTION);
        handler.handle(burton);
    }
}
```
#### 執行結果
>     -----教務處的回覆-----
>     Jack 的申請通過
>     -----課指組的回覆-----
>     Kata 的申請通過
>     -----系辦公室的回覆-----
>     Burton 的安排完成

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Chain-of-responsibility_pattern
