import lsp.Bus;
import lsp.Car;
import lsp.Driver;
import lsp.Truck;

public class Client {
    public static void main(String[] args) {
        // 產生一台公車
        Car bus = new Bus();
        // 讓司機開公車
        Driver driver = new Driver(bus);
        driver.drive();

        // 公車的下車鈴響起
        ((Bus) bus).ring();

        // 司機停車
        driver.park();

        // 產生一台卡車讓司機2號開
        Car truck = new Truck();
        Driver driver2 = new Driver(truck);
        driver2.drive();
        driver2.park();
    }
}
