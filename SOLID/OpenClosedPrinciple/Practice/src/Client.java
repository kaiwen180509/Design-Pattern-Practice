import ocp.*;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------開公車----------");
        // 產生一台公車
        Car bus = new Bus();
        // 讓司機開公車
        Driver driver = new Driver(bus);
        driver.drive();

        // 公車的下車鈴響起
        ((Bus) bus).ring();

        // 司機停車
        driver.park();

        System.out.println("----------開卡車----------");
        // 產生一台卡車讓司機2號開
        Car truck = new Truck();
        Driver driver2 = new Driver(truck);
        driver2.drive();
        driver2.park();

        System.out.println("----------開新式公車----------");
        // 產生一台新式公車讓司機3號開
        Car newBus = new NewBus();
        Driver driver3 = new Driver(newBus);
        driver3.drive();

        // 新式公車的下車鈴響起，司機3號停車
        ((NewBus) newBus).ring();
        driver3.park();

        // 司機3號繼續開新式公車
        driver3.drive();

        // 警鈴聲響起，司機3號停車
        ((NewBus) newBus).alarm();
        driver3.park();
    }
}
