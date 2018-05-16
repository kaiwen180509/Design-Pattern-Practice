package isp;

public class ShuttleBus implements ICar {
    @Override
    public void start() {
        System.out.println("接駁車上路...");
    }

    @Override
    public void stop() {
        System.out.println("接駁車到站，停車...");
    }
}
