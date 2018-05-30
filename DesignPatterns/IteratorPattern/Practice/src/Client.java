import aggregate.Aggregate;
import aggregate.ConcreteAggregate;
import iterator.Iterator;

public class Client {
    public static void main(String[] args) {
        // 建立容器並且裝入元素
        Aggregate aggregate = new ConcreteAggregate();
        aggregate.add("Hello");
        aggregate.add("World");
        aggregate.add("!");
        // 刪除某個元素
        aggregate.remove("!");

        // 取出疊代器
        Iterator iterator = aggregate.iterator();
        // 印出第一個
        System.out.println(iterator.first());

        // 遍歷一次
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}