import dip.*;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------建構子傳遞依賴----------");
        driving();
        System.out.println("----------介面傳遞依賴----------");
        drivingByInterface();
        System.out.println("----------Setter方法傳遞依賴----------");
        drivingBySetter();
    }

    private static void driving() {
        // 讓司機開卡車
        ICar car = new Truck();
        IDriver driver = new Driver(car);
        driver.drive();
        driver.park();
    }

    private static void drivingByInterface() {
        // 讓司機開公車
        IDriverByInterface driver = new DriverByInterface();
        ICar car = new Bus();
        driver.drive(car);
        driver.park(car);
    }

    private static void drivingBySetter() {
        // 讓司機開卡車
        IDriverBySetter driver = new DriverBySetter();
        ICar car = new Truck();
        driver.setCar(car);
        driver.drive();
        driver.park();
    }
}
