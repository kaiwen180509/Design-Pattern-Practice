package factory;

import flyweight.BigCup;
import flyweight.Cup;
import flyweight.SmallCup;

import java.util.HashMap;

public class DrinkFactory {
    public final static char BIG_SIZE = 'L';
    public final static char SMALL_SIZE = 'S';

    // 建立池
    private static HashMap<String, Cup> bigPool = new HashMap<>();
    private static HashMap<String, Cup> smallPool = new HashMap<>();

    public Cup getDrink(String name, char size) {
        Cup drink;
        // 判斷是哪一種杯子
        if (size == BIG_SIZE) {
            if (this.bigPool.containsKey(name)) {
                // 池裡有就從池中取出
                drink = this.bigPool.get(name);
            } else {
                // 池沒有就建立一個並放入
                drink = new BigCup(name);
                System.out.println("大杯飲料：" + name + " 製作完成");
                this.bigPool.put(name, drink);
            }
        } else {
            if (this.smallPool.containsKey(name)) {
                // 池裡有就從池中取出
                drink = this.smallPool.get(name);
            } else {
                // 池沒有就建立一個並放入
                drink = new SmallCup(name);
                System.out.println("小杯飲料：" + name + " 製作完成");
                this.smallPool.put(name, drink);
            }
        }
        return drink;
    }
}
