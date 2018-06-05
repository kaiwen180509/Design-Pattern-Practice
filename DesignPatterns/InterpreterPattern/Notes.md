# 直譯器模式 - Interpreter Pattern
## 定義
> - “Given a language, define a representation for its grammar along with an interpreter that uses the representation to interpret sentences in the language.”
> - 給定一個語言，定義它的文法表式法，以及一個使用該文法表式法來解釋語言的直譯器。

## 參與者
> + 抽象表達式 - AbstractExpression
>     * 定義交給各個實作類別的解釋任務
> + 終結表達式 - TerminalExpression
>     * 一般只有一個終結表達式 TerminalExpression，對應到多個實例
> + 非終結表達式 - NonterminalExpression
>     * 每個文法規則都對應一個非終結表達式 NonterminalExpression，根據邏輯複雜程度增加
> + 環境角色 - Context
>     * 保存目前輸入與輸出的狀態

## 優點
> 1. 擴展性佳
>
> 在**直譯器模式**之中，如果需要修改文法規則，只需要增加非終結表達式 NonterminalExpression 就好，擴展容易。

## 缺點
> 1. 類別膨脹
> 2. 效能問題
> 3. 維護性差
> 
> 由於每個文法規則都會產生一個非終結表達式，因此在複雜的情況下，將產生大量的類別；且由於使用大量遞迴與迴圈，導致效率降低；也由於複雜的遞迴與迴圈，造成偵錯與維護困難。

## 使用時機
> 1. 重複發生的問題時
> 2. 解析一個簡單的語法時

## 實踐
> 在實際的專案開發之中很少見，由於**直譯器模式**會引起效能與維護方面的問題，一般可以先考慮使用[第三方套件](https://en.wikipedia.org/wiki/Third-party_software_component "第三方套件")來解決問題。

## 範例
有一種簡單的加密方法是[替換式密碼](https://zh.wikipedia.org/wiki/%E6%9B%BF%E6%8D%A2%E5%BC%8F%E5%AF%86%E7%A0%81 "替換式密碼")，這種加密法是以特定的排序法來改變字母表的順序，獲得一個新的排序，通常是先寫一個關鍵字在最前面，接著刪去重複的字母，剩下的字母依序填上，最後得到一個字母表，這個字母表被稱為「混合表」，例如以下便是使用 *Crazy* 當作關鍵字來做的混合表：

**明文：ABCDEFGHIJKLMNOPQRSTUVWXYZ**
**密文：CRAZYBDEFGHIJKLMNOPQSTUVWX**

若是使用上面的混合表來加密一段文字，例如：*I LOVE YOU*，這個簡單的英文句子，就會得到 *F ILTY WLS* 這段密文，藉由這種方法來隱藏原文的方法就是簡易替換加密。而這個密文的解析動作可以藉由**直譯器模式**來實現。

首先建立一個抽象表達式`Expression`抽象類別，定義各個表達式所必須具備的方法，接著是實作非終結表達式`AlphabetExpression`類別、終結表達式 `TerminalExpression`類別，兩個類別分別處理字母與非字母的狀況，最後是環境角色`Context`類別，負責管理需要處理的密文，以下是類別圖與實際程式碼：

![image](https://raw.githubusercontent.com/kaiwen180509/Design-Pattern-Practice/master/DesignPatterns/InterpreterPattern/Picture/InterpreterPatternPicture.png)

#### Expression
```java
public abstract class Expression {
    public abstract String interpreter(String input);
}
```
#### AlphabetExpression
```java
/* NonterminalExpression */
public class AlphabetExpression extends Expression {
    // 明文
    private final String plaintext = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // 密文
    private final String Ciphertext = "CRAZYBDEFGHIJKLMNOPQSTUVWX";

    @Override
    public String interpreter(String input) {
        return decryption(input);
    }

    // 解密
    private String decryption(String input) {
        String result;
        int index;
        boolean isLower = false;

        // 判斷原輸入是否為小寫
        if (Character.isLowerCase(input.charAt(0))) {
            isLower = true;
        }

        // 找出對應的位置並取得明文
        index = Ciphertext.indexOf(input.toUpperCase());
        result = plaintext.charAt(index) + "";

        // 原輸入是小寫，因此轉換輸出為小寫
        if (isLower) {
            return result.toLowerCase();
        }
        return result;
    }
}
```
#### TerminalExpression
```java
public class TerminalExpression extends Expression {
    @Override
    public String interpreter(String input) {
        return input;
    }
}
```
#### Context
```java
public class Context {
    // 需要處理的語句
    private String input;

    public Context(String input) {
        this.input = input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
```
## 實際應用
```java
public class Client {
    public static void main(String[] args) {
        // I love you!! 的密文
        Context context = new Context("F ilty wls!!");

        // 交給直譯器處理
        Expression expression;
        for (char input : context.getInput().toCharArray()) {
            // 判斷需要哪個規則來解析
            if (Character.isLetter(input)) {
                expression = new AlphabetExpression();
            } else {
                expression = new TerminalExpression();
            }
            // 開始解析
            System.out.print(expression.interpreter("" + input));
        }
        System.out.println();
        System.out.println("轉換完成");
    }
}
```
#### 執行結果
>     I love you!!
>     轉換完成

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Interpreter_pattern
 - https://en.wikipedia.org/wiki/Third-party_software_component
 - https://en.wikipedia.org/wiki/Substitution_cipher