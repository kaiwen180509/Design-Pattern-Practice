import factory.DrinkFactory;

public class Client {
    public static void main(String[] args) {
        // 建立飲料工廠
        DrinkFactory drinkFactory = new DrinkFactory();

        // 客人要喝大杯飲料1號
        System.out.println("點單：大杯1號");
        drinkFactory.getDrink("飲料1號", DrinkFactory.BIG_SIZE).drink();

        // 客人要喝小杯飲料15號
        System.out.println("點單：大杯15號");
        drinkFactory.getDrink("飲料15號", DrinkFactory.SMALL_SIZE).drink();

        // 客人要喝大杯飲料1號
        System.out.println("點單：大杯1號");
        drinkFactory.getDrink("飲料1號", DrinkFactory.BIG_SIZE).drink();
    }
}