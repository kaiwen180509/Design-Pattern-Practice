import abstraction.DrinkFactory;
import implementor.Bottle;
import implementor.Cup;

public class Client {
    public static void main(String[] args) {
        // 讓飲料工廠生產瓶裝飲料
        DrinkFactory factory = new DrinkFactory(new Bottle());
        factory.produce();

        // 飲料工廠回收杯裝飲料
        factory = new DrinkFactory(new Cup());
        factory.recycle();
    }
}