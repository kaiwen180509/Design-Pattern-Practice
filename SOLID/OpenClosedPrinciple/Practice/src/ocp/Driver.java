package ocp;

public class Driver {
    private Car car;

    public Driver(Car car) {
        this.car = car;
    }

    public void drive() {
        car.start();
    }

    public void park() {
        car.stop();
    }
}
