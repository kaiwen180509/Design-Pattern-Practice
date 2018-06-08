package implementor;

/* ConcreteImplementor */
public class Cup implements Container {
    @Override
    public void pour() {
        System.out.println("杯子被倒出");
    }

    @Override
    public void fill() {
        System.out.println("杯子被裝入");
    }
}
