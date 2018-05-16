package dip;

public class Driver implements IDriver {
    private ICar car;

    public Driver(ICar car) {
        this.car = car;
    }

    @Override
    public void drive() {
        car.start();
    }

    @Override
    public void park() {
        car.stop();
    }
}
