package dip;

public class DriverByInterface implements IDriverByInterface {
    @Override
    public void drive(ICar car) {
        car.start();
    }

    @Override
    public void park(ICar car) {
        car.stop();
    }
}
