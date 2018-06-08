package implementor;

/* ConcreteImplementor */
public class Bottle implements Container {
    @Override
    public void pour() {
        System.out.println("瓶子被倒出");
    }

    @Override
    public void fill() {
        System.out.println("瓶子被裝入");
    }
}
