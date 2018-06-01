import facade.Kitchen;

public class Client {
    public static void main(String[] args) {
        // 門面
        Kitchen kitchen = new Kitchen();

        // 門面提供的服務
        System.out.println("-----顧客點餐完畢-----");
        kitchen.orderMainMeal("炒飯");
        kitchen.orderDessert("豆沙包");
    }
}