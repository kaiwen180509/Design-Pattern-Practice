# 迪米特法則 - Law of Demeter, LoD
#### 又名 : 最少知識原則 - Least Knowledge Principle, LKP
## 定義
> - “Each unit should have only limited knowledge about other units: only units "closely" related to the current unit.”
> - “Each unit should only talk to its friends; don't talk to strangers.”
> - “Only talk to your immediate friends.”
> - 每個單元對於其他的單元只能擁有有限的知識：只是與當前單元緊密聯繫的單元。
> - 每個單元只能和它的朋友交談：不能和陌生單元交談。
> - 只和自己直接的朋友交談。
> - 避免不必要的依賴。

## 優點
> 1. 可維護性好
> 2. 適應性好
> 3. 降低類別間耦合的強度
> 4. 提高類別的重利用率
> 
> 由於類別之間的低偶合關係，導致變更發生時，修改的部分會比較少，因此對於維護的工作或是需求變更的情況都有很好的幫助。

## 缺點
> 1. 類別複雜性提高
> 2. 過度解耦合導致維護困難
> 
> 由於需要避免不必要的依賴關係，導致類別的設計變得複雜，類別複雜後，也會使得維護困難。

## 實踐
> 1. 盡量減少依賴
> 2. 有限度的解耦合（弱耦合）
> 
> 對於類別的設計上，如果新增一個方法在一個類別上，不會導致新的依賴關係發生，也不會對此類別造成其他的影響，那就在此類別新增此方法；而在耦合的情況，類別存取其他類別的跳轉在兩次內都可以接受，超過則需要考慮重構。

## 範例
舉一個例子，有一間公司的老闆要發500元的獎金給每個員工，於是老闆要會計師去計算總共需要多少錢，在這個例子中，先建立老闆`Boss`、會計師`Accountant`以及員工`Employee`的類別，接著老闆的類別`Boss`只要有一個命令的方法`command()`就好，老闆只負責命令會計師去計算，而會計師的類別`Accountant`則提供計算`count()`的`public`方法給老闆命令，以及取得金額`getAmount()`的`private`方法給自己去計算獎金一共要多少錢，員工`Employee`的類別則不做任何事。

根據此例子類別之間的依賴關係，分別是老闆`Boss`依賴會計師`Accountant`，而會計師`Accountant`依賴員工`Employee`，老闆不需要依賴到員工`Employee`的類別，即使計算獎金總金額這件事情需要計算全部的員工人數，但是只要會計師`Accountant`去計算就好，避免老闆`Boss`依賴到`Employee`這個類別，這就是最少知識原則（LKP）的應用。

類別圖與實際範例如下：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/SOLID/Picture/LKPClassPicture.png)

#### Boss
```java
public class Boss {
    public void command(Accountant accountant) {
        accountant.count();
    }
}
```
#### Accountant
```java
public class Accountant {
    private List<Employee> employees;

    public Accountant(List<Employee> employees) {
        this.employees = employees;
    }

    public void count() {
        System.out.println("總共要：" + getAmount() + " 元");
    }

    private int getAmount() {
        return this.employees.size() * 500;
    }
}
```
#### Employee
```java
public class Employee {
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // 有一個老闆，有一間25個員工的公司，以及一個會計師
        Boss boss = new Boss();
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            employees.add(new Employee());
        }
        Accountant accountant = new Accountant(employees);

        // 老闆指示會計師去算總共要多少錢
        boss.command(accountant);
    }
}
```
#### 執行結果
>     總共要：12500 元

## 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Law_of_Demeter
