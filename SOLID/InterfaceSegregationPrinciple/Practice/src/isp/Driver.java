package isp;

public class Driver implements IDriver {

    @Override
    public void drive(ICar car) {
        car.start();
    }

    @Override
    public void park(ICar car) {
        car.stop();
    }
}
