package lsp;

public class Truck extends Car {
    @Override
    public void start() {
        System.out.println("卡車上路...");
    }

    @Override
    public void stop() {
        System.out.println("卡車停車...");
    }
}
