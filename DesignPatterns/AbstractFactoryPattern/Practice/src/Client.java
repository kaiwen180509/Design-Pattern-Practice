import abstractfactory.DessertFactory;
import abstractfactory.FruitDessertFactory;
import abstractfactory.MatchaDessertFactory;
import product.Candy;
import product.Jelly;

public class Client {
    public static void main(String[] args) {
        // 建立水果甜點工廠與抹茶甜點工廠
        DessertFactory fruitDessertFactory = new FruitDessertFactory();
        DessertFactory matchaDessertFactory = new MatchaDessertFactory();

        // 生產各式果凍
        System.out.println("----------果凍產品----------");
        Jelly fruitJelly = fruitDessertFactory.makeJelly();
        fruitJelly.flavor();
        fruitJelly.price();
        Jelly matchaJelly = matchaDessertFactory.makeJelly();
        matchaJelly.flavor();
        matchaJelly.price();

        // 生產各式糖果
        System.out.println("----------糖果產品----------");
        Candy fruitCandy = fruitDessertFactory.makeCandy();
        fruitCandy.flavor();
        fruitCandy.price();
        Candy matchaCandy = matchaDessertFactory.makeCandy();
        matchaCandy.flavor();
        matchaCandy.price();
    }
}