package product;

public class MatchaJelly implements Jelly {
    @Override
    public void flavor() {
        System.out.println("抹茶口味");
    }

    @Override
    public void price() {
        System.out.println("價格：5 元");
    }
}
