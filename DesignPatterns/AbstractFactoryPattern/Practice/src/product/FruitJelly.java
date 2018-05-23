package product;

public class FruitJelly implements Jelly {
    @Override
    public void flavor() {
        System.out.println("水果口味");
    }

    @Override
    public void price() {
        System.out.println("價格：4 元");
    }
}
