package product;

public class FruitCandy implements Candy {
    @Override
    public void flavor() {
        System.out.println("水果口味");
    }

    @Override
    public void price() {
        System.out.println("價格：5 元");
    }
}
