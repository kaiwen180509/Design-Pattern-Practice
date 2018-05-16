import isp.*;

public class Client {
    public static void main(String[] args) {
        // 一個司機與乘客
        IDriver driver = new Driver();
        IPassenger passenger = new Passenger();

        System.out.println("----------場景一----------");
        // 乘客上車，司機開公車
        ICar bus = new Bus();
        passenger.board(bus);
        driver.drive(bus);

        // 乘客在下一站想要下車，按了下車鈴
        passenger.pressingRing((IBusRing) bus);

        // 司機停車，讓乘客下車
        driver.park(bus);
        passenger.leave(bus);

        System.out.println("----------場景二----------");
        // 司機開著定點接駁車，從起點駛向終點
        ICar shuttleBus = new ShuttleBus();
        // 乘客上車
        passenger.board(shuttleBus);
        // 司機開車
        driver.drive(shuttleBus);
        // 終點到了，司機停車
        driver.park(shuttleBus);
        // 乘客下車
        passenger.leave(shuttleBus);
    }
}