# Design-Pattern-Practice
> - 本專案為學習設計模式之中，所做的練習與筆記
> - 使用 Java 實踐

### 開發環境 - Development Environment
> - Windows 8.1
> - Java Version 1.8
> - Intelij IDEA Community Edition

## 物件導向設計基本原則 - SOLID Principles
>“ 物件導向設計基本原則是一種基礎的開發指南，它使得程式設計師更容易進行軟體維護和擴展。”

| 中文名稱  | 英文名稱  | 筆記  | 實作  |
| ------------- | ------------- | ------------- |------------- |
| 單一職責原則  | Single Responsibility Principle, SRP  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/SingleResponsibilityPrinciple/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/SOLID/SingleResponsibilityPrinciple/Practice "Link") |
| 開閉原則  | Open/Closed Principle, OCP  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/OpenClosedPrinciple/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/SOLID/OpenClosedPrinciple/Practice "Link") |
| 里氏替換原則  | Liskov Substitution Principle, LSP  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LiskovSubstitutionPrinciple/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/SOLID/LiskovSubstitutionPrinciple/Practice "Link") |
| 迪米特法則<br>( 最少知識原則 ) | Law of Demeter, LoD<br>( Least Knowledge Principle, LKP )  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/LeastKnowledgePrinciple/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/SOLID/LeastKnowledgePrinciple/Practice "Link") |
| 介面隔離原則  | Interface Segregation Principle, ISP  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/InterfaceSegregationPrinciple/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/SOLID/InterfaceSegregationPrinciple/Practice "Link") |
| 依賴反轉原則  | Dependency Inversion Principle, DIP  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/SOLID/DependencyInversionPrinciple/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/SOLID/DependencyInversionPrinciple/Practice "Link") |

詳細內容請參考：[維基百科(Wiki)](https://en.wikipedia.org/wiki/SOLID)

## 設計模式 - Design Patterns
>“設計模式是一種思維的方式，也是對於一些常見軟體設計問題與標準解決方案的總結，目的是增強軟體設計適應變化的能力”，分為建立型模式、結構型模式、行為型模式。

#### 創建型模式 - Creational Patterns
>“處理物件建立的設計模式，根據實際情況使用合適的方式建立物件。”

| 中文名稱  | 英文名稱  | 筆記  | 實作  |
| ------------- | ------------- | ------------- |------------- |
| 單例模式  | Singleton Pattern  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/SingletonPattern/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/DesignPatterns/SingletonPattern/Practice "Link") |
| 工廠模式  | Factory Pattern  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/FactoryPattern/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/DesignPatterns/FactoryPattern/Practice "Link") |
| 抽象工廠模式  | Abstract Factory Pattern  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/AbstractFactoryPattern/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/DesignPatterns/AbstractFactoryPattern/Practice "Link") |
| 建造者模式  | Builder Pattern  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/BuilderPattern/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/DesignPatterns/BuilderPattern/Practice "Link") |
| 原型模式  | Prototype Pattern  | Link  | Link  | 

#### 結構型模式 - Structural Patterns
>“藉由一以貫之的方式來了解元件間的關係，以簡化設計。”

| 中文名稱  | 英文名稱  | 筆記  | 實作  |
| ------------- | ------------- | ------------- |------------- |
| 介面卡模式  | Adapter Pattern  | Link  | Link  | 
| 橋接模式  | Bridge Pattern  | Link  | Link  | 
| 组合模式  | Composite Pattern  | Link  | Link  | 
| 修飾模式  | Decorator Pattern  | Link  | Link  | 
| 外觀模式  | Facade Pattern  | Link  | Link  | 
| 享元模式  | Flyweight Pattern  | Link  | Link  | 
| 代理模式  | Proxy Pattern  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/ProxyPattern/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/DesignPatterns/ProxyPattern/Practice "Link") |

#### 行為型模式 - Behavioral Patterns
>“用來識別對象之間的常用交流模式並加以實現，可在進行這些交流活動時增強彈性。”

| 中文名稱  | 英文名稱  | 筆記  | 實作  |
| ------------- | ------------- | ------------- |------------- |
| 責任鏈模式  | Chain of Responsibility Pattern  | Link  | Link  | 
| 命令模式  | Command Pattern  | Link  | Link  | 
| 直譯器模式  | Interpreter Pattern  | Link  | Link  | 
| 疊代器模式  | Iterator Pattern  | Link  | Link  | 
| 中介者模式  | Mediator Pattern  | Link  | Link  | 
| 備忘錄模式  | Memento Pattern  | Link  | Link  | 
| 觀察者模式  | Observer Pattern  | Link  | Link  | 
| 狀態模式  | State Pattern  | Link  | Link  | 
| 策略模式  | Strategy Pattern  | Link  | Link  | 
| 模板方法模式  | Template Method Pattern  | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/blob/master/DesignPatterns/TemplateMethodPattern/Notes.md "Link") | [Link](https://github.com/kaiwen180509/Design-Pattern-Practice/tree/master/DesignPatterns/TemplateMethodPattern/Practice "Link") |
| 存取者模式  | Visitor Pattern  | Link  | Link  | 

詳細內容請參考：[維基百科(Wiki)](https://en.wikipedia.org/wiki/Software_design_pattern)

### 參考資料 - Reference Materials
- [《設計模式之禪》](http://www.books.com.tw/products/CN11096287 "《設計模式之禪》")
- https://en.wikipedia.org/wiki/SOLID
- https://en.wikipedia.org/wiki/Software_design_pattern
- https://en.wikipedia.org/wiki/Creational_pattern
- https://en.wikipedia.org/wiki/Structural_pattern
- https://en.wikipedia.org/wiki/Behavioral_pattern