package common;

public class MilkCookies implements ICookies {
    @Override
    public void flavor() {
        System.out.println("牛奶口味");
    }

    @Override
    public void price() {
        System.out.println("價格：12 元");
    }
}
