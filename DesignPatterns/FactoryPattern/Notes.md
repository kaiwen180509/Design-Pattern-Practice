# 工廠模式 - Factory Pattern
## 定義
> - “Define an interface for creating an object, but let subclasses decide which class to instantiate. The Factory method lets a class defer instantiation it uses to subclasses.”
> - 定義一個為了創建物件的介面，讓子類別決定哪一個類別被實例化。
> - 工廠方法讓一個類別的實例化延緩到子類別。

## 優點
> 1. 良好的封裝性
> 2. 架構清晰
> 3. 降低模組間的耦合
> 4. 良好的擴展性
> 5. 符合[迪米特法則（LoD）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LeastKnowledgePrinciple/Notes.md "迪米特法則（LoD）")
> 6. 符合[依賴反轉原則（DIP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "依賴反轉原則（DIP）")
> 7. 符合[里氏替換原則（LSP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "里氏替換原則（LSP）")
> 
> 使用工廠來產生物件的實例，讓模組間不需要知道如何去建立，降低了耦合，並且具有良好的擴展性，只要修改具體的工廠類別或是使用[開閉原則（OCP）](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "開閉原則（OCP）")來擴展工廠，都可以完成需求變更，是一個典型的解耦合框架。

## 缺點
> 1. 提升程式的複雜度
> 
> 由於需要建立工廠來產生物件的實例，因此導致程式的複雜度上升。

## 使用時機
> 1. 代替 new 來建立實例
> 2. 需要可擴展的靈活框架時

## 實踐
> 1. 工廠方法模式
> 2. 簡單工廠模式（擴展）
> 3. 多個工廠類別（擴展）
> 4. 替代[單例模式](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/SingletonPattern/Notes.md "單例模式")（擴展）
> 5. 延遲初始化（擴展）
> 
> 工廠模式是一個很常見的模式，其中分為一般的工廠方法模式，以及擴展的四個模式，分別是簡單工廠模式、多個工廠類別、替代單例模式還有延遲初始化等諸多基本的工廠方法模式擴展。

### 參考資料
 - [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
 - https://en.wikipedia.org/wiki/Factory_method_pattern
