package isp;

public class Passenger implements IPassenger {
    @Override
    public void pressingRing(IBusRing busRing) {
        busRing.ring();
    }

    @Override
    public void board(ICar car) {
        System.out.println("乘客上車...");
    }

    @Override
    public void leave(ICar car) {
        System.out.println("乘客下車...");
    }
}
