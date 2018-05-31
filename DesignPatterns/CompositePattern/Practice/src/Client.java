import component.Component;
import composite.SalesArea;
import leaf.Product;

public class Client {
    public static void main(String[] args) {
        // 為商店建立各個貨架
        SalesArea shop = new SalesArea("Shop");
        SalesArea drinkSalesArea = new SalesArea("Drink");
        SalesArea foodSalesArea = new SalesArea("Food");
        SalesArea cookiesSalesArea = new SalesArea("Cookies");

        shop.add(drinkSalesArea);
        shop.add(foodSalesArea);
        shop.add(cookiesSalesArea);

        // 進貨各個產品
        Product drinkProductA = new Product("綠茶", 20);
        Product drinkProductB = new Product("紅茶", 20);
        Product drinkProductC = new Product("奶茶", 30);

        Product foodProductA = new Product("三角飯糰", 25);
        Product foodProductB = new Product("便當", 65);
        Product foodProductC = new Product("涼麵", 50);
        Product foodProductD = new Product("義大利麵", 60);

        Product cookiesProductA = new Product("夾心餅乾", 35);
        Product cookiesProductB = new Product("洋芋片", 25);
        Product cookiesProductC = new Product("玉米棒", 10);

        // 組合 -> 產品上架
        drinkSalesArea.add(drinkProductA);
        drinkSalesArea.add(drinkProductB);
        drinkSalesArea.add(drinkProductC);

        foodSalesArea.add(foodProductA);
        foodSalesArea.add(foodProductB);
        foodSalesArea.add(foodProductC);
        foodSalesArea.add(foodProductD);

        cookiesSalesArea.add(cookiesProductA);
        cookiesSalesArea.add(cookiesProductB);
        cookiesSalesArea.add(cookiesProductC);

        // 下架某個產品
        foodSalesArea.remove(foodProductC);

        // 遍歷商店
        show(shop);

        // 找到三角飯糰 foodProductA 的販售區域
        System.out.println("-----三角飯糰的販售區-----");
        Component salesArea = foodProductA.getParent();
        System.out.println(salesArea.getInfo());
    }

    // 遍歷方法
    private static void show(SalesArea salesArea) {
        for (Component component : salesArea.getComponents()) {
            System.out.println(component.getInfo());
            // 判斷是否為販售區域，是就查看販售區域下的資訊
            if (component instanceof SalesArea) {
                show((SalesArea) component);
            }
        }
    }
}