package normal.objectstructure;

import normal.element.DrinkProduct;
import normal.element.FoodProduct;
import normal.element.Product;

import java.util.ArrayList;

public class Shop {
    // 貨品的貨架
    private ArrayList<Product> productList = new ArrayList<>();

    public Shop() {
        importProduct();
    }

    // 取得商店的貨架
    public ArrayList<Product> getProductList() {
        return this.productList;
    }

    // 商店進貨
    private void importProduct() {
        // 進貨飲料
        Product drinkA = new DrinkProduct("奶茶", 25);
        Product drinkB = new DrinkProduct("綠茶", 20);

        // 進貨食物
        Product foodA = new FoodProduct("便當", 60);
        Product foodB = new FoodProduct("飯糰", 25);

        // 商品上架
        productList.add(drinkA);
        productList.add(drinkB);
        productList.add(foodA);
        productList.add(foodB);
    }
}
