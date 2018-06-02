package normal.visitor;

import normal.element.DrinkProduct;
import normal.element.FoodProduct;

public class Customer implements IVisitor {
    @Override
    public void visit(DrinkProduct drinkProduct) {
        if (drinkProduct.getPrice() <= 20) {
            buy(drinkProduct.getName());
        }
    }

    @Override
    public void visit(FoodProduct foodProduct) {
        if (foodProduct.getPrice() <= 50) {
            buy(foodProduct.getName());
        }
    }

    // 顧客買東西
    private void buy(String name) {
        System.out.println("購買：" + name);
    }
}
