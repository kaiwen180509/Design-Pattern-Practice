package common;

public class ChocolateCookies implements ICookies {
    @Override
    public void flavor() {
        System.out.println("巧克力口味");
    }

    @Override
    public void price() {
        System.out.println("價格：15 元");
    }
}
