package prototype;

public class Decorateur {
    private Cake cake;

    public Decorateur(Cake cake) {
        this.cake = cake;
    }

    // 幫蛋糕進行後製
    public Cake makeCake(String flavor) {
        Cake cake = this.cake.clone();
        cake.setFlavor(flavor);
        return cake;
    }
}
