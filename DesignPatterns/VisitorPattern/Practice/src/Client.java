import normal.element.Product;
import normal.objectstructure.Shop;
import normal.visitor.Customer;
import normal.visitor.IVisitor;
import statistics.StatisticsCustomer;

public class Client {
    public static void main(String[] args) {
        System.out.println("----------一般存取者模式----------");
        doNormalVisitor();
        System.out.println("-----------統計功能模式----------");
        doStatisticsVisitor();
    }

    private static void doNormalVisitor() {
        // 一間商店有一個顧客要買東西
        Shop shop = new Shop();
        IVisitor customer = new Customer();

        // 顧客開始查看每個商品
        for (Product product : shop.getProductList()) {
            product.accept(customer);
        }
    }

    private static void doStatisticsVisitor() {
        // 商店裡面有一個想要包店的顧客在算總共要多少錢
        Shop shop = new Shop();
        StatisticsCustomer customer = new StatisticsCustomer();

        // 顧客開始查看每個商品
        for (Product product : shop.getProductList()) {
            product.accept(customer);
        }

        // 一共需要多少錢
        System.out.println("總共需要：" + customer.getTotalPrice() + " 元");
    }
}