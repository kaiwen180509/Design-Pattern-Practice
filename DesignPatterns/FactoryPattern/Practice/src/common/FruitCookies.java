package common;

public class FruitCookies implements ICookies {
    @Override
    public void flavor() {
        System.out.println("水果口味");
    }

    @Override
    public void price() {
        System.out.println("價格：10 元");
    }
}
