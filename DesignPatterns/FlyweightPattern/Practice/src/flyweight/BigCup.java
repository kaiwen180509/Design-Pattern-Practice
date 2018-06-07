package flyweight;

/* ConcreteFlyweight */
public class BigCup extends Cup {
    public BigCup(String name) {
        super(name);
        // 大杯飲料是 700 CC
        this.setCapacity(700);
    }

    @Override
    public void drink() {
        System.out.println("正在喝大杯飲料：" + this.name);
    }
}
