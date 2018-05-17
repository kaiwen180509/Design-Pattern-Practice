package ocp;

public class NewBus extends Bus {
    public void alarm() {
        System.out.println("警報聲響起...");
    }

    @Override
    public void stop() {
        System.out.println("公車停車...");
    }
}
