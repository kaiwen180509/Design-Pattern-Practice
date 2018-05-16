package dip;

public class Truck implements ICar {

    @Override
    public void start() {
        System.out.println("卡車上路...");
    }

    @Override
    public void stop() {
        System.out.println("卡車停車...");
    }
}
