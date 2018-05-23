package abstractfactory;

import product.Candy;
import product.Jelly;

public interface DessertFactory {
    Candy makeCandy();

    Jelly makeJelly();
}
