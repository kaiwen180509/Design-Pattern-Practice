package abstractfactory;

import product.*;

public class FruitDessertFactory implements DessertFactory {
    @Override
    public Candy makeCandy() {
        return new FruitCandy();
    }

    @Override
    public Jelly makeJelly() {
        return new FruitJelly();
    }
}
