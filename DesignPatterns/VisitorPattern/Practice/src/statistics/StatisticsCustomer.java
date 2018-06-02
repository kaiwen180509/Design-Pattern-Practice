package statistics;

import normal.element.DrinkProduct;
import normal.element.FoodProduct;

public class StatisticsCustomer implements IStatisticsVisitor {
    private int totalPrice = 0;

    @Override
    public void visit(DrinkProduct drinkProduct) {
        this.totalPrice += drinkProduct.getPrice();
    }

    @Override
    public void visit(FoodProduct foodProduct) {
        this.totalPrice += foodProduct.getPrice();
    }

    @Override
    public int getTotalPrice() {
        return this.totalPrice;
    }
}
