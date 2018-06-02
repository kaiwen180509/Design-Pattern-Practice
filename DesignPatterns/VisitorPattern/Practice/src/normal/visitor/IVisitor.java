package normal.visitor;

import normal.element.DrinkProduct;
import normal.element.FoodProduct;

public interface IVisitor {
    void visit(DrinkProduct drinkProduct);

    void visit(FoodProduct foodProduct);
}
