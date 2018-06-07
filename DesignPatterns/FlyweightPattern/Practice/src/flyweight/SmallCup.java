package flyweight;

/* ConcreteFlyweight */
public class SmallCup extends Cup {
    public SmallCup(String name) {
        super(name);
        // 小杯飲料是 500 CC
        this.setCapacity(500);
    }

    @Override
    public void drink() {
        System.out.println("正在喝小杯飲料：" + this.name);
    }
}
