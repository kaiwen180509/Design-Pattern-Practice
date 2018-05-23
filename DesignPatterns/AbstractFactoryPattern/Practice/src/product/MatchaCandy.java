package product;

public class MatchaCandy implements Candy {
    @Override
    public void flavor() {
        System.out.println("抹茶口味");
    }

    @Override
    public void price() {
        System.out.println("價格：6 元");
    }
}
