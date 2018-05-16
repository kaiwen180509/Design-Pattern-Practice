package dip;

public class DriverBySetter implements IDriverBySetter {
    private ICar car;

    @Override
    public void drive() {
        car.start();
    }

    @Override
    public void park() {
        car.stop();
    }

    @Override
    public void setCar(ICar car) {
        this.car = car;
    }
}
