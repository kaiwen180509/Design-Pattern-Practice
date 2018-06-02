package normal.element;

import normal.visitor.IVisitor;

public class FoodProduct extends Product {
    public FoodProduct(String name, int price) {
        super(name, price);
    }

    @Override
    public void accept(IVisitor visitor) {
        // 接受存取
        visitor.visit(this);
    }
}
