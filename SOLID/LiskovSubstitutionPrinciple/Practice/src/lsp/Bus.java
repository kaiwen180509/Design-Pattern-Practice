package lsp;

public class Bus extends Car {

    @Override
    public void start() {
        System.out.println("公車上路...");
    }

    @Override
    public void stop() {
        System.out.println("公車到站，停車...");
    }

    public void ring() {
        System.out.println("下車鈴響起，下一站停車...");
    }
}
