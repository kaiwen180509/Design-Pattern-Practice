package product;

public class RectangleToyBrick extends ToyBrick {
    @Override
    protected void getMaterials() {
        System.out.println("取得原料");
    }

    @Override
    protected void sculptureShape() {
        System.out.println("切割原料為長方形");
    }

    @Override
    protected void grinding() {
        System.out.println("打磨光滑");
    }
}
