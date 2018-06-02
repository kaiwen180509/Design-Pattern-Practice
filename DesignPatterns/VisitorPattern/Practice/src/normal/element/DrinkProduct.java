package normal.element;

import normal.visitor.IVisitor;

public class DrinkProduct extends Product {
    public DrinkProduct(String name, int price) {
        super(name, price);
    }

    @Override
    public void accept(IVisitor visitor) {
        // 接受存取
        visitor.visit(this);
    }
}
