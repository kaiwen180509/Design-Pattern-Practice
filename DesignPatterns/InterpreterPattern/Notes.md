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

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Interpreter_pattern
 - https://en.wikipedia.org/wiki/Third-party_software_component